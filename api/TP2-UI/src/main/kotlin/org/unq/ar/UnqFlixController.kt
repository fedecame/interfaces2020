package org.unq.ar

import domain.UNQFlix
import domain.User
import io.javalin.http.*


data class UserJason( val name: String, val creditCard: String, val image: String, val email: String, val password: String)
class UnqFlixControllers ( val unqflix:UNQFlix)  {

    /*
    *  Crear Un usuario
     */
    fun userRegister(ctx:Context){
        val user =ctx.body<User>()
        unqflix.users.add(user)
        ctx.status(201).json(user)
    }


}