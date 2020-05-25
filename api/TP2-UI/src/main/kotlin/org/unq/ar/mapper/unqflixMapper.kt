package org.unq.ar.mapper

//data class unqflixmapper()
data class Result(val result:String)
data class message(val result:String, val message:String)
data class UserJason( val name: String,  val image: String, val email: String)
data class UserLogin(val email: String, val password: String )
data class Contenido(val id:String, val description:String, val title:String, val state:Boolean)
data class UserFavorites(val name:String, val image:String, val favorites:List<Contenido>, val lastSeen:List<Contenido>)