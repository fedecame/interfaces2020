package org.unq.ar.Controllers

import domain.UNQFlix
import domain.User
import io.javalin.http.NotFoundResponse



class UsersController (val unqflix: UNQFlix) {
    val user:List<User> = unqflix.users

    fun login( email:String, pass:String){/*
        try {
            val user = unqflix.users.firstOrNull { it.email == email && it.password == pass }
        }catch (e: )

*/
    }
}