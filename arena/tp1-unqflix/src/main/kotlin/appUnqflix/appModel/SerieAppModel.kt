package appUnqflix.appModel

import data.idGenerator
import domain.*

import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class SerieAppModel(val serie: Serie? = null, val unqflixAppModel: UnqflixAppModel) {
    var id: String
    var title: String
    var descripcion: String
    var poster: String
    var state: ContentState // esto tmb se podria representar con un AppModel..preguntar si se justifica.
    var categories = mutableListOf<CategoryAppModel>()
    var myseasons = mutableListOf<SeasonAppModel>()
    var relatedContent = mutableListOf<Content>() // preguntar si se justifica crear un ContentAppModel (similar a CategoryAppModel)

    var selected : SeasonAppModel? = null

    init {
//        this.id = serie.id
//        this.title = serie.title
//        this.descripcion = serie.description
//        this.poster = serie.poster
//        this.state = serie.state

        this.id = serie?.id ?: ""
        this.title = serie?.title ?: ""
        this.descripcion = serie?.description ?: ""
        this.poster = serie?.poster ?: ""
        this.state = serie?.state ?: Unavailable()

//        this.relatedContent = serie.relatedContent
        this.relatedContent = serie?.relatedContent ?: mutableListOf<Content>()
        this.initCategories()
        this.initSeasons()



    }

    fun initSeasons() {
        this.myseasons = serie?.seasons?.map {SeasonAppModel(it, unqflixAppModel, this) }?.toMutableList() ?: mutableListOf<SeasonAppModel>()
//        this.myseasons = serie.seasons.map { SeasonAppModel(it, unqflixAppModel, this) }.toMutableList()
    }

    fun initCategories() {
        this.categories = serie?.categories?.map { CategoryAppModel(it) }?.toMutableList() ?: mutableListOf<CategoryAppModel>()
//        this.categories = serie.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    fun agregarSeason(title : String, description : String, poster : String): SeasonAppModel {
        val seasonAppModel = unqflixAppModel.createSeason(this, title, description, poster)
        myseasons.add(seasonAppModel)
        return seasonAppModel
    }
}