package org.unq.ar.Controllers

import domain.*
import io.javalin.http.*
import org.unq.ar.Api.TokenJWT
import org.unq.ar.exceptions.NotFoundUserException
import org.unq.ar.mapper.*
import org.unq.ar.mapper.Id
import support.getById


class UnqFlixControllers (val unqflix:UNQFlix, val jwt: TokenJWT)  {

    private fun transformarContentsToContenido(favorites: MutableList<Content>): MutableList<Contenido> {
        var res = mutableListOf<Contenido>()
        for(cont in favorites){
            var unContenido = Contenido(cont.id, cont.description, cont.title, adapterAvailable(cont.state ), cont.poster)
            res.add(unContenido)
        }
        return res
    }

    private fun transformarMovieToContenido(favorites: MutableList<Movie>): MutableList<Contenido> {
        var res = mutableListOf<Contenido>()
        for (cont in favorites) {
            var unContenido = Contenido(cont.id, cont.description, cont.title, adapterAvailable(cont.state), cont.poster)
            res.add(unContenido)
        }
        return res
    }

    fun getFavoritesAndLastSeen(ctx: Context){
        lateinit var token : UserToken
        try {
            token = jwt.validate(ctx.header("Authorization")!!)
        } catch (e: Exception) {
            throw UnauthorizedResponse(e.message!!)
        }

        lateinit var user: User
        try {
            user = unqflix.users.find { it.id == token.id } ?: throw NotFoundUserException()
        } catch (e: NotFoundUserException) {
            ctx.status(401)
            ctx.json(mapOf(
                "result" to "error",
                "message" to e.message
            ))
        }
        val fav =
            UserFavorites(user.name, user.image,
                user.favorites.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state), it.poster) }.toMutableList(),
                user.lastSeen.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state), it.poster) }.toMutableList())

        ctx.status(200).json(fav)
    }



    fun getContentAvailableOrderByTitle(ctx:Context){
        var seriesAvailables:List<Content> = unqflix.series.filter { adapterAvailable(it.state) }
        var moviesAvailables:List<Content> = unqflix.movies.filter { adapterAvailable(it.state) }
        seriesAvailables += moviesAvailables
        val contenido:List<Contenido> = seriesAvailables.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state), it.poster) }
        ctx.status(200).json(contenido.sortedBy { it.title })
    }





    fun getBanners(ctx: Context){
        val banners: List<Contenido> = unqflix.banners.map { Contenido(it.id, it.description, it.title, adapterAvailable(it.state), it.poster) }
        ctx.status(200).json(banners.shuffled())
    }

    fun adapterAvailable(cont: ContentState): Boolean = cont is Available

    fun searchByText(ctx: Context){
        val texToSearch = ctx.queryParam("text").toString()

        val searchMovie = unqflix.searchMovies(texToSearch!!)
        val searchRes = this.transformarMovieToContenido(searchMovie)
        val searchSerie = unqflix.searchSeries(texToSearch!!)
        searchRes.addAll(this.transformarSerieToContenido(searchSerie))

        ctx.status(200)
        ctx.json(searchRes)
    }

    private fun transformarSerieToContenido(searchSerie: MutableList<Serie>): Collection<Contenido> {
        var res = mutableListOf<Contenido>()
        for (cont in searchSerie) {
            var unContenido = Contenido(cont.id, cont.description, cont.title, adapterAvailable(cont.state), cont.poster)
            res.add(unContenido)
        }
        return res
    }

    fun searchContentById(ctx: Context){
        val contentId = ctx.pathParam("contentId").toString()
        try {
            val unContent = getContentById(contentId)
            if (contentId.startsWith("mov")) {
                ctx.status(200).json(this.transformMovieToMovieMapper(unContent as Movie))
            } else {
                ctx.status(200).json(this.transformSerieToSerieMapper(unContent as Serie))
            }
        }catch (e : NotFoundException){
            throw BadRequestResponse(e.message.toString())
        }
    }

    fun getContentById(id:String): Any {
        if (id.startsWith("mov"))return getById(unqflix.movies,id)
        if (id.startsWith("ser"))return getById(unqflix.series,id)
        throw NotFoundException("Content", "id", id)
    }

    fun transformMovieToMovieMapper(movie: Movie): MovieMapper{
        return MovieMapper(movie.id, movie.title, movie.description, movie.poster, movie.video, movie.duration, movie.actors, movie.directors,this.categoriesToString(movie.categories), this.transformarContentsToContenido(movie.relatedContent) )
    }

    fun transformSerieToSerieMapper(serie: Serie): SerieMapper{
        return SerieMapper(serie.id, serie.title, serie.description, serie.poster, this.categoriesToString(serie.categories),serie.seasons, this.transformarContentsToContenido(serie.relatedContent))
    }

    private fun categoriesToString(categories: MutableList<Category>): MutableList<String> {
        var res = mutableListOf<String>()
        for (cat in categories){
            res.add(cat.name)
        }
        return res
    }

    fun addLastSeen(ctx: Context) {
        lateinit var token : UserToken
        try {
            token = jwt.validate(ctx.header("Authorization")!!)
        } catch (e: Exception) {
            throw UnauthorizedResponse(e.message!!)
        }

        val idLastSeen = ctx.bodyValidator<Id>()
            .check({it.id != null}, "Missing id inside body")
            .get().id

        try {
            unqflix.addLastSeen(token.id, idLastSeen!!)
        } catch (e: NotFoundException) {
            throw NotFoundResponse(e.message!!)
        }

        val lastSeen = unqflix.users.find { it.id == token.id }!!.lastSeen
        val lastSeenMapped = lastSeen.map { Contenido(it.id, it.description, it.title, it.state is Available, it.poster) }.toMutableList()
        ctx.status(200)
        ctx.header("Authorization", jwt.generateToken(unqflix.users.find{it.id == token.id}!!))
        ctx.json(mapOf(
            "lastSeen" to lastSeenMapped
        ))
    }

    fun addOrDeleteContentFromFav(ctx: Context){
        lateinit var token : UserToken
        try {
            token = jwt.validate(ctx.header("Authorization")!!)
        } catch (e: Exception) {
            throw UnauthorizedResponse(e.message!!)
        }

        val idUser = token.id

        val contentId = ctx.bodyValidator<Id>()
            .check({it.id != null}, "Missing id inside body")
            .get().id

        try {
            unqflix.addOrDeleteFav(idUser,contentId!!)
            ctx.status(200)
            ctx.json(mapOf("result:" to "ok"))
        }catch (e : NotFoundException){
            throw BadRequestResponse(e.message.toString())
        }
    }

}