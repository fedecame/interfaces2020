package org.unq.ar.mapper

import domain.Season

//data class unqflixmapper()
data class Result(val result:String)
data class message(val result:String, val message:String)
data class UserJason( val name: String,  val image: String, val email: String)
data class UserLogin(val email: String, val password: String )
data class Contenido(val id:String, val description:String, val title:String, val state:Boolean)
data class UserFavorites(val name:String, val image:String, val favorites:MutableList<Contenido>, val lastSeen:MutableList<Contenido>)
data class MovieMapper(val id:String, val title:String, val description: String, val poster:String, val video:String, val duration:Int, val actors:MutableList<String>, val directors:MutableList<String>, val categories:MutableList<String>, val relatedContent:MutableList<Contenido>)
data class SerieMapper(val id:String, val title: String, val description: String, val poster: String, val categories: MutableList<String>, val season:MutableList<Season>, val relatedContent: MutableList<Contenido>)