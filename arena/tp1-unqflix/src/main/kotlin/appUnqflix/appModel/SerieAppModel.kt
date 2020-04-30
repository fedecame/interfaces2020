package appUnqflix.appModel

import data.idGenerator
import domain.ContentState
import domain.Season

import domain.Serie
import domain.Unavailable
import org.uqbar.commons.model.annotations.Observable

@Observable
class SerieAppModel(val serie: Serie, val unqflixAppModel: UnqflixAppModel) {
    var id: String = ""
    var title: String = ""
    var myseasons = mutableListOf<SeasonAppModel>()
    var selected : SeasonAppModel? = null
    var tituloSeason = ""
    var descripcion = ""
    var poster = ""
    var categories = mutableListOf<CategoryAppModel>()

    var cantSeasons = 0
    var state : ContentState = Unavailable()


    init {
        this.id = serie.id
        this.title = serie.title
        this.descripcion = serie.description
        this.poster = serie.poster
        this.state = serie.state

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
//        this.initSeasons()

        return seasonAppModel
    }

    //
//        fun elegirSeason(seasonSeleccionada : SeasonAppModel){
////        selectSeason?.numeroPrueba =
//  }


//    fun modificarSeason(){
//
//    }


}