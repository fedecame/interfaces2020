package org.unq.ar.Controllers

import domain.*
import io.javalin.http.*
import org.unq.ar.mapper.*
import support.getById


class UnqFlixControllers(val unqflix: UNQFlix) {
    init {
        unqflix.users.add(User("unid", "juan",  "master","adfadffda", "juan@unmail",  "1234"))
    }
    val usersController = UsersController(unqflix)

    fun GetUnUsuario (ctx: Context) {
        val unUser = unqflix.users.first()

        ctx.status(200).json(UserFavorites(unUser.name, unUser.image,this.transformarContentsToContenido(unUser.favorites.toMutableList()), this.transformarContentsToContenido(unUser.lastSeen.toMutableList())))

    }

    private fun transformarContentsToContenido(favorites: MutableList<Content>): MutableList<Contenido> {
        var res = mutableListOf<Contenido>()
        for(cont in favorites){
            var unContenido = Contenido(cont.id, cont.description, cont.title, adapterAvailable(cont.state ))
            res.add(unContenido)
        }
        return res
    }

    private fun transformarMovieToContenido(favorites: MutableList<Movie>): MutableList<Contenido> {
        var res = mutableListOf<Contenido>()
        for (cont in favorites) {
            var unContenido = Contenido(cont.id, cont.description, cont.title, adapterAvailable(cont.state))
            res.add(unContenido)
        }
        return res
    }
    /*
    *  Registra Un usuario
     */
    fun userRegister(ctx: Context) {
        val user = unqflix.users.firstOrNull { it.email == ctx.pathParam("email") }
            ?: throw NotFoundResponse("The mail is registered")
        unqflix.users.add(user)
        ctx.status(201).json(Result("ok"))
    }

    fun login(ctx: Context) {
        val loginUser = ctx.bodyAsClass(UserLogin::class.java)/*
        try{
            val user = usersController.login(loginUser.email, loginUser.password)
            ctx.json(user)
        }catch (){
            throw NotFoundResponse("Wrong email or password")
        }
     */
    }

    fun getFavoritesAndLastSeen(ctx: Context) {
        val fav = unqflix.users.map {
            UserFavorites(it.name, it.image,
                it.favorites.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }.toMutableList(),
                it.lastSeen.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }.toMutableList())
        }
        ctx.json(fav.shuffled())
    }

    fun getContentAvailableOrderByTitle(ctx: Context) {
        var seriesAvailables: List<Content> = unqflix.series.filter { adapterAvailable(it.state) }
        var moviesAvailables: List<Content> = unqflix.movies.filter { adapterAvailable(it.state) }
        seriesAvailables += moviesAvailables
        val contenido: List<Contenido> =
            seriesAvailables.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }
        ctx.json(contenido.sortedBy { it.title })
    }

    fun getBanners(ctx: Context) {
        val banners: List<Contenido> =
            unqflix.banners.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state)) }
        ctx.json(banners.shuffled())
    }

    fun adapterAvailable(cont: ContentState): Boolean = cont is Available

    fun addOrDeleteContentFromFav(ctx: Context){
        val idUser = "unid" //aca seria el jwt
        val contentId = ctx.pathParam("contentId")
        unqflix.addOrDeleteFav(idUser,contentId)
        ctx.status(200)
        ctx.json(mapOf("result:" to "ok"))
        //hacer el caso de la exception
    }

    fun searchByText(ctx: Context){
        val texToSearch = ctx.queryParam("text").toString()

        val searchMovie = unqflix.searchMovies(texToSearch!!)
//        val searchSerie = unqflix.searchSeries(texToSearch!!)
//        val searchRes = searchMovie.addAll(searc

        ctx.status(200)
        ctx.json(this.transformarMovieToContenido(searchMovie))
    }

    fun searchContentById(ctx: Context){
        val contentId = ctx.pathParam("contentId").toString()
        val unContent = getContentById(contentId)
        ctx.status(200).json(this.transformMovieToMovieMapper(unContent as Movie))

    }

    fun getContentById(id:String): Any {
        if (id.startsWith("mov"))return getById(unqflix.movies,id)
        if (id.startsWith("ser"))return getById(unqflix.series,id)
        throw NotFoundException("Content", "id", id)
    }

    fun transformMovieToMovieMapper(movie: Movie): MovieMapper{
        return MovieMapper(movie.id, movie.title, movie.description, movie.poster, movie.video, movie.duration, movie.actors, movie.directors,this.categoriesToString(movie.categories), this.transformarContentsToContenido(movie.relatedContent) )
    }

    private fun categoriesToString(categories: MutableList<Category>): MutableList<String> {
        var res = mutableListOf<String>()
        for (cat in categories){
            res.add(cat.name)
        }
        return res
    }
}


