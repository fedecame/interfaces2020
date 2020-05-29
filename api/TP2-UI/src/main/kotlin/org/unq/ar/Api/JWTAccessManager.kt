package org.unq.ar.Api

import domain.UNQFlix
import domain.User
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.*
import org.unq.ar.exceptions.NotFoundTokenException
import org.unq.ar.exceptions.NotFoundUserException
import org.unq.ar.roles.Roles

class JWTAccessManager(val jwt: TokenJWT, val unqFlix: UNQFlix): AccessManager {

    private fun getUser(token: String): User {
        try {
            var userToken = jwt.validate(token)

            var user: User? = null
            if(unqFlix.users.isNotEmpty()) {
                user =  unqFlix.users.first { it.id == userToken.id }
            }
            return user ?: throw NotFoundUserException()
        } catch (e: NotFoundUserException) {
            throw NotFoundResponse(e.message!!)
        } catch (e: NotFoundTokenException) {
            throw NotFoundResponse(e.message!!)
        }
    }

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when {
            roles.contains(Roles.ANYONE) -> handler.handle(ctx)
            token == null -> throw NotFoundResponse("Missing or invalid token")
            roles.contains(Roles.USER) -> {
                getUser(token)
                handler.handle(ctx)
            }
            else -> throw BadRequestResponse("Oops something went wrong")
        }
    }
}