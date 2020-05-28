package org.unq.ar.Controllers

import domain.*
import io.javalin.http.*
import javalinjwt.JavalinJWT
import org.unq.ar.Api.TokenJWT
import org.unq.ar.exceptions.NotFoundTokenException
import org.unq.ar.mappers.*



class UnqFlixController (val unqflix:UNQFlix, val jwt: TokenJWT)  {

    /*
    *  Registra Un usuario
     */
    fun userRegister(ctx:Context){
        val user = unqflix.users.firstOrNull { it.email == ctx.pathParam("email") }
                ?: throw NotFoundResponse ("The mail is registered")
        unqflix.users.add(user)
        ctx.status(201).json(Result("ok"))
    }

    fun login(ctx: Context){
          val  loginUser = ctx.bodyAsClass(UserLogin::class.java)/*
        try{
            val user = usersController.login(loginUser.email, loginUser.password)
            ctx.json(user)
        }catch (){
            throw NotFoundResponse("Wrong email or password")
        }
     */
    }

    fun getFavoritesAndLastSeen(ctx: Context){
        val fav = unqflix.users.map {
            UserFavorites(it.name, it.image,
                    it.favorites.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) },
                    it.lastSeen.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) })
        }
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
        val banners: List<Contenido> = unqflix.banners.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }
        ctx.json(banners.shuffled())
    }

    fun adapterAvailable(cont:ContentState):Boolean= cont is Available

    fun addLastSeen(ctx: Context) {
        lateinit var token : UserToken
        try {
            token = jwt.validate(ctx.header("Authorization")!!)
        } catch (e: NotFoundTokenException) {
            throw NotFoundResponse(e.message!!)
        } catch (e: Exception) {
            throw NotFoundResponse(e.message!!)
        }
        val idLastSeen = ctx.bodyValidator<IdLastSeen>()
            .check({it.id != null}, "Missing id inside body")
            .get().id

        try {
            unqflix.addLastSeen(token.id, idLastSeen!!)
        } catch (e: NotFoundException) {
            throw NotFoundResponse(e.message!!)
        }

        val lastSeen = unqflix.users.find { it.id == token.id }!!.lastSeen
        val lastSeenMapped = lastSeen.map { Contenido(it.id, it.description, it.title, it.state is Available) }.toMutableList()
        ctx.status(200)
        ctx.header("Authorization", jwt.generateToken(unqflix.users.find{it.id == token.id}!!))
        ctx.json(mapOf(
            "lastSeen" to lastSeenMapped
        ))
    }
}


