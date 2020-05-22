package org.unq.ar

import domain.*
import io.javalin.http.*


data class UserJason( val name: String,  val image: String, val email: String)
data class UserLogin(val email: String, val password: String )
data class Contenido(val id:String, val description:String, val title:String, val state:Boolean)
data class UserFavorites(val name:String, val image:String, val favorites:List<Contenido>, val lastSeen:List<Contenido>)
class UnqFlixControllers ( val unqflix:UNQFlix)  {

    /*
    *  Registra Un usuario
     */
    fun userRegister(ctx:Context){
        val user =unqflix.users.firstOrNull { it.email == ctx.pathParam("email") }
                ?: throw NotFoundResponse ("The mail is registered")
        unqflix.users.add(user)
        ctx.status(201).json(UserJason(user.name,user.image,user.email ))
    }

    fun login(ctx: Context){
          val  log = unqflix.users.firstOrNull { it.email == ctx.pathParam("email") && it.password == ctx.pathParam("password") }
                    ?: throw NotFoundResponse("Invalid Username or Password")

    }

    fun getFavoritesAndLastSeen(ctx: Context){
        val fav = unqflix.users.map {  UserFavorites(it.name,it.image,
                   it.favorites.map{ Contenido(it.id, it.description, it.title,adapterAvailable(it.state) )},
                   it.lastSeen.map {  Contenido(it.id,it.description,it.title,adapterAvailable(it.state)) })}
        ctx.json(fav.shuffled())
        }
    fun getContentAvailableOrderByTitle(ctx:Context){
        var seriesAvailables:List<Content> = unqflix.series.filter { adapterAvailable(it.state) }
        var moviesAvailables:List<Content> = unqflix.movies.filter { adapterAvailable(it.state) }
             seriesAvailables += moviesAvailables
         val contenido:List<Contenido> = seriesAvailables.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }
        ctx.json(contenido.sortedBy { it.title })
    }
    fun getBanners(ctx: Context){
        val banners: List<Contenido> = unqflix.banners.map { Contenido(it.id,it.description,it.title,adapterAvailable(it.state)) }
        ctx.json(banners.shuffled())
    }

    fun adapterAvailable(cont:ContentState):Boolean= cont is Available
    }


