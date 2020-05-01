package appUnqflix.appModel

import data.idGenerator
import domain.*

import org.uqbar.commons.model.annotations.Observable

@Observable
class SerieAppModel(val serie: Serie, val unqflixAppModel: UnqflixAppModel) {
    var id: String = ""
    var title: String = ""
    var descripcion = ""
    var poster = ""
    var state : ContentState = Unavailable() // esto tmb se podria representar con un AppModel..preguntar si se justifica.
    var categories = mutableListOf<CategoryAppModel>()
    var myseasons = mutableListOf<SeasonAppModel>()
    var relatedContent = mutableListOf<Content>() // preguntar si se justifica crear un ContentAppModel (similar a CategoryAppModel)

    var selected : SeasonAppModel? = null

    var tituloSeason = ""
    var descripcionSeason = ""
    var posterSeason = ""

    var cantSeasons = 0


    init {
        this.id = serie.id
        this.title = serie.title
        this.descripcion = serie.description
        this.poster = serie.poster
        this.state = serie.state

        this.relatedContent = serie.relatedContent
        this.initCategories()
        this.initSeasons()
    }

    fun initSeasons() {
        this.myseasons = serie.seasons.map { SeasonAppModel(it, unqflixAppModel, this.id) }.toMutableList()
    }

    fun initCategories() {
        this.categories = serie.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    fun agregarSeason(title : String, description : String, poster : String): SeasonAppModel {
        val seasonAppModel = unqflixAppModel.createSeason(this.id, title, description, poster)
        myseasons.add(seasonAppModel)

        return seasonAppModel
    }
}