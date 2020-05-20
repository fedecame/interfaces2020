package org.unq.ar

import domain.UNQFlix
import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)
    app.get("/") { ctx -> ctx.result("Hello World") }

    //CRUD
    val unqflix = UNQFlix()
    //GET /users
        app.get("/users") { ctx ->
            val users = unqflix.users
            ctx.json(users)
        }
}

