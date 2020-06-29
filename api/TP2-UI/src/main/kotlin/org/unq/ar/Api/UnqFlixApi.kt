package org.unq.ar.Api

import data.getUNQFlix
import data.idGenerator
import domain.User
import domain.NotFoundException
import io.javalin.Javalin
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import org.unq.ar.Controllers.UserController
import org.unq.ar.roles.Roles
import io.javalin.http.NotFoundResponse
import org.unq.ar.Controllers.UnqFlixControllers

fun main(args: Array<String>) {
    UsersApi(7000).init()
}

class UsersApi(private val port: Int) {
    fun init(): Javalin {
        val unqFlix = getUNQFlix()
        val tokenJWT = TokenJWT(unqFlix)
        val unqflixController = UnqFlixControllers(unqFlix, tokenJWT)
        val userController = UserController(unqFlix, tokenJWT)
        val jwtAccessManager = JWTAccessManager(tokenJWT, unqFlix)

        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
            it.accessManager(jwtAccessManager)
        }

        app.before {
            it.header("Access-Control-Expose-Headers", "*")
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
                get(unqflixController::getFavoritesAndLastSeen, mutableSetOf<Role>(Roles.USER))
                path("/fav"){
                    path(":contentId"){
                        post(unqflixController::addOrDeleteContentFromFav, mutableSetOf<Role>(Roles.USER))
                    }
                }
                path("/lastSeen"){
                    post(unqflixController::addLastSeen, mutableSetOf<Role>(Roles.USER))
                }
            }
            path("/content") {
                path(":contentId"){
                    get(unqflixController::searchContentById, mutableSetOf<Role>(Roles.USER))
                }
                get(unqflixController::getContentAvailableOrderByTitle, mutableSetOf<Role>(Roles.USER))
            }
            path("/banners"){
                 get(unqflixController::getBanners, mutableSetOf<Role>(Roles.USER))
            }
            path("/search") {
                get(unqflixController::searchByText, mutableSetOf<Role>(Roles.USER))
            }

        }
        return app
    }

}

