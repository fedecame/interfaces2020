package org.unq.ar.Api

import domain.UNQFlix
import domain.User
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.NotFoundResponse
import io.javalin.http.UnauthorizedResponse
import org.unq.ar.exceptions.NotFoundTokenException
import org.unq.ar.exceptions.NotFoundUserException
import org.unq.ar.roles.Roles

class JWTAccessManager(val jwt: TokenJWT, val unqFlix: UNQFlix): AccessManager {

    private fun getUser(token: String): User {
        try {
            var userToken = jwt.validate(token)
            return unqFlix.users.first { it.id == userToken.id } ?: throw NotFoundResponse()
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
        }
    }
}