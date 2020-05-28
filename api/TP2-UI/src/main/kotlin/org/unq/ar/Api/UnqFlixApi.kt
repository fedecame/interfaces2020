package org.unq.ar.Api

import data.getUNQFlix
import domain.NotFoundException
import io.javalin.Javalin
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.NotFoundResponse
import org.unq.ar.Controllers.UnqFlixControllers

fun main(args: Array<String>) {
    UsersApi(7000).init()
}

class UsersApi(private val port: Int) {
    fun init(): Javalin {
        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
            //  it.get("/") { ctx -> ctx.result("Hello World") }
        }
        app.start(port)

        val unqflixController = UnqFlixControllers(getUNQFlix())

        app.routes {
            path("/register") {
                post(unqflixController::userRegister)

            }
            path("/login") {
                // post(unqflixController::login)
            }
            path("/user") {
                get(unqflixController::GetUnUsuario)
                //get(unqflixController::getFavoritesAndLastSeen)
                path("fav"){
                    path(":contentId"){
                        post(unqflixController::addOrDeleteContentFromFav)
                    }
                }
            }
            path("/content") {
                // get(unqflixController::getContentAvailableOrderByTitle)
                path(":contentId"){
                    get(unqflixController::searchContentById)
                }
            }
            path("/banners") {
                // get(unqflixController::getBanners)
            }
            path("/search") {
                get(unqflixController::searchByText)
            }
            path("/content/{:contentId}") {
                //get(unqflixController)
            }
            path("/user/lastSeen") {

            }


        }
        app.exception(NotFoundException::class.java){ e,_ ->
            throw NotFoundResponse(e.toString())
        }
        return app
    }

}

