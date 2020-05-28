package org.unq.ar.Controllers

import data.idGenerator
import domain.ExistsException
import domain.UNQFlix
import domain.User
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ar.Api.TokenJWT
import org.unq.ar.exceptions.NotFoundUserException
import org.unq.ar.mappers.UserLogin
import org.unq.ar.mappers.UserRegister


class UserController (val unqflix: UNQFlix, val jwt: TokenJWT) {

    fun register(ctx: Context) {
        lateinit var newUser: User
        try {
            val registerUser = ctx.bodyAsClass(UserRegister::class.java)
            newUser = User(idGenerator.nextUserId(), registerUser.name, registerUser.creditCard, registerUser.image, registerUser.email, registerUser.password)
        } catch (e: Exception) {
            throw BadRequestResponse("Invalid body")
        }

        try {
            unqflix.addUser(newUser)
            ctx.status(201)
            ctx.header("Authorization", jwt.generateToken(newUser))
            ctx.json(mapOf(
                "result" to "ok"
            ))
        } catch (existsException: ExistsException) {
            ctx.status(400)
            ctx.json(mapOf(
                "result" to "error",
                "message" to "Email already taken"
            ))
        }
    }

    fun login(ctx: Context) {
        lateinit var loginUser: UserLogin
        try {
            loginUser = ctx.bodyAsClass(UserLogin::class.java)
        } catch (e: Exception) {
            throw BadRequestResponse("Invalid body")
        }

        try {
            val user = unqflix.users.find { it.email == loginUser.email && it.password == loginUser.password } ?: throw NotFoundUserException()
            ctx.status(200)
            ctx.header("Authorization", jwt.generateToken(user))
            ctx.json(mapOf(
                "result" to "ok"
            ))
        } catch (e: NotFoundUserException) {
            ctx.status(404)
            ctx.json(mapOf(
                "result" to "error",
                "message" to e.message
            ))
        }
    }
}