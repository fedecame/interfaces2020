package appUnqflix.appModel

import data.idGenerator
import domain.Season
import domain.Serie
import org.uqbar.commons.model.annotations.Observable
import support.getById

@Observable
class SerieAppModel(val serie: Serie) {
    var id: String = ""
    var title: String = ""
    var myseasons = initSeasons()
    var selected : SeasonAppModel? = null
    var tituloSeason = ""
    var descripcion = ""
    var poster = ""

    init {

        this.id = serie.id
        this.title = serie.title
    }

    fun initSeasons() = serie.seasons.map { SeasonAppModel(it) }.toMutableList()
    //
//        fun elegirSeason(seasonSeleccionada : SeasonAppModel){
////        selectSeason?.numeroPrueba =
//  }
    fun agregarSeason(tituloSeason : String, descripcion : String, poster : String) {
        var nuevaSeason = crearSeason(tituloSeason,descripcion,poster)
        serie.addSeason(nuevaSeason)
        myseasons.add(SeasonAppModel(nuevaSeason))
    }

    fun crearSeason(tituloSeason: String,descripcion: String, poster: String): Season{
//        var numberId = myseasons.size+1
//        var id = "sea_$numberId"

        return Season(idGenerator.nextSeasonId(),tituloSeason,descripcion,poster)
    }

    fun modificarSeason(){

    }


}