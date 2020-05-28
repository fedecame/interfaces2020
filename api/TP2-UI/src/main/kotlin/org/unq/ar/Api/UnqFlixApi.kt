package org.unq.ar.Api

import data.getUNQFlix
import data.idGenerator
import domain.User
import io.javalin.Javalin
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import org.unq.ar.Controllers.UnqFlixController
import javalinjwt.JavalinJWT
import org.unq.ar.Controllers.UserController
import org.unq.ar.roles.Roles

fun main(args: Array<String>) { UsersApi(7000).init()}

class UsersApi(private val port: Int) {
    fun init(): Javalin {
        val unqFlix = getUNQFlix()
        unqFlix.addUser(User(idGenerator.nextUserId(), "Diego", "1111 2222 5555 6666", "un_gato.jpg", "asd@gmail.com", "1234"))
        unqFlix.addUser(User(idGenerator.nextUserId(), "Marito", "4646 2525 7878 9191", "pato.jpg", "comboloco@gmail.com", "777"))

        val tokenJWT = TokenJWT(unqFlix)
        val unqflixController = UnqFlixController(unqFlix, tokenJWT)
        val userController = UserController(unqFlix, tokenJWT)
        val jwtAccessManager = JWTAccessManager(tokenJWT, unqFlix)

        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
            it.accessManager(jwtAccessManager)
        }

        app.start(port)

        app.routes{
            path("/register"){
                post(userController::register, mutableSetOf<Role>(Roles.ANYONE))
            }
            path("/login"){
                post(userController::login, mutableSetOf<Role>(Roles.ANYONE))
            }
            path("/user"){
                //get(unqflixController::getFavoritesAndLastSeen)
                path("/lastSeen"){
                    post(unqflixController::addLastSeen, mutableSetOf<Role>(Roles.USER))
                }
            }
            path("/content") {
                // get(unqflixController::getContentAvailableOrderByTitle)
            }
            path("/banners"){
                // get(unqflixController::getBanners)
            }
            path("/search"){
                // get(unqflixController::searchByText)
            }
            path("/content"){
                //get(unqflixController)
            }

        }

        return app
    }

}

