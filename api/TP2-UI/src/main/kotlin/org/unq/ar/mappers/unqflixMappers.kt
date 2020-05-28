package org.unq.ar.mappers

//data class unqflixmapper()
data class Result(val result:String)
data class Message(val result:String, val message:String)
data class UserRegister(val name: String, val email: String, val password: String, val image: String, val creditCard: String)
data class UserLogin(val email: String, val password: String)
data class UserToken(val id: String, val name: String, val email: String, val role: String)
data class Contenido(val id:String, val description:String, val title:String, val state:Boolean)
data class UserFavorites(val name:String, val image:String, val favorites:List<Contenido>, val lastSeen:List<Contenido>)
data class IdLastSeen(var id: String? = null)
data class ContenidoMini(val id:String, val description:String, val title:String)