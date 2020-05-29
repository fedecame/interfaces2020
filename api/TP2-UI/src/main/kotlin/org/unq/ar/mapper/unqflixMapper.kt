package org.unq.ar.mapper

import domain.Season

data class Contenido(val id:String, val description:String, val title:String, val state:Boolean)
data class UserFavorites(val name:String, val image:String, val favorites:MutableList<Contenido>, val lastSeen:MutableList<Contenido>)
data class MovieMapper(val id:String, val title:String, val description: String, val poster:String, val video:String, val duration:Int, val actors:MutableList<String>, val directors:MutableList<String>, val categories:MutableList<String>, val relatedContent:MutableList<Contenido>)
data class SerieMapper(val id:String, val title: String, val description: String, val poster: String, val categories: MutableList<String>, val season:MutableList<Season>, val relatedContent: MutableList<Contenido>)
data class IdLastSeen(var id: String? = null)
data class UserRegister(val name: String, val email: String, val password: String, val image: String, val creditCard: String)
data class UserLogin(val email: String, val password: String)
data class UserToken(val id: String, val name: String, val email: String, val role: String)

