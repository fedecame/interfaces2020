package org.unq.ar

import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)
    app.get("/") { ctx -> ctx.result("Hello World") }

    //CRUD

    //GET /users
    app.get("/") {

    }
}

