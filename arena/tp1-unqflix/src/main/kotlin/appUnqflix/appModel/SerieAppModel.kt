package appUnqflix.appModel

import data.idGenerator
import domain.ContentState
import domain.Season

import domain.Serie
import org.uqbar.commons.model.annotations.Observable

@Observable
class SerieAppModel(val serie: Serie, val unqflixAppModel: UnqflixAppModel) {
    var id: String = ""
    var title: String = ""
    var myseasons = initSeasons()
    var selected : SeasonAppModel? = null
    var tituloSeason = ""
    var descripcion = ""
    var poster = ""

    var appModelCreator = unqflixAppModel

    var cantSeasons = 0
    var state : ContentState? = null


    init {

        this.id = serie.id
        this.title = serie.title
    }

    fun initSeasons() = serie.seasons.map { SeasonAppModel(it) }.toMutableList()
    //
//        fun elegirSeason(seasonSeleccionada : SeasonAppModel){
////        selectSeason?.numeroPrueba =
//  }
    fun agregarSeason(title : String, description : String, poster : String): SeasonAppModel {
        val seasonAppModel = appModelCreator.createSeason(this.id, title, description, poster)
//        this.initSeasons()
        myseasons.add(seasonAppModel)
        return seasonAppModel
    }

//    fun modificarSeason(){
//
//    }


}