package org.unq.ar.Controllers

import domain.*
import io.javalin.http.*
import org.unq.ar.mapper.*



class UnqFlixControllers ( val unqflix:UNQFlix)  {

     val usersController = UsersController(unqflix)

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
        var lastSeen: MutableList<Content> = mutableListOf()
        var favoritos:MutableList<Content> = mutableListOf()
        val user=User("xxxx","name","xxxxx","image1","usuario@usuario.com","1234",favoritos,lastSeen)
        val fav =
            UserFavorites(user.name, user.image,
                    user.favorites.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) },
                    user.lastSeen.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) })

        ctx.status(200).json(fav)
        }
    fun getContentAvailableOrderByTitle(ctx:Context){
        var seriesAvailables:List<Content> = unqflix.series.filter { adapterAvailable(it.state) }
        var moviesAvailables:List<Content> = unqflix.movies.filter { adapterAvailable(it.state) }
             seriesAvailables += moviesAvailables
         val contenido:List<Contenido> = seriesAvailables.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }
        ctx.status(200).json(contenido.sortedBy { it.title })
    }
    fun getBanners(ctx: Context){
        val banners: List<Contenido> = unqflix.banners.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }
        ctx.status(200).json(banners.shuffled())
    }

    fun adapterAvailable(cont:ContentState):Boolean= cont is Available
    }


