package org.unq.ar.Api;

import domain.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import domain.Available
import domain.Content
import domain.UNQFlix
import javalinjwt.JWTGenerator
import javalinjwt.JWTProvider
import org.unq.ar.exceptions.NotFoundTokenException
import org.unq.ar.exceptions.NotFoundUserException
import org.unq.ar.mapper.Contenido
import org.unq.ar.mapper.UserToken
import org.unq.ar.roles.Roles

private class UserGenerator : JWTGenerator<User> {

    override fun generate(user: User, algorithm: Algorithm): String {
        val token = JWT.create()
                    .withClaim("id", user.id)
                    .withClaim("name", user.name)
                    .withClaim("email", user.email)
                    .withClaim("role",  Roles.USER.toString())

        return token.sign(algorithm)
    }
}

class TokenJWT (val unqFlix: UNQFlix){

    val algorithm = Algorithm.HMAC256("seed_secreta_napolitana")
    private val generator = UserGenerator()
    val verifier = JWT.require(algorithm).build()
    val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(user: User): String {
        return provider.generateToken(user)
    }

    fun validate(token: String): UserToken {
        val validatedToken = provider.validateToken(token)
        if (!validatedToken.isPresent) throw NotFoundTokenException()
        return UserToken(
            validatedToken.get().getClaim("id").asString(),
            validatedToken.get().getClaim("name").asString(),
            validatedToken.get().getClaim("email").asString(),
            validatedToken.get().getClaim("role").asString()
        )
    }

}