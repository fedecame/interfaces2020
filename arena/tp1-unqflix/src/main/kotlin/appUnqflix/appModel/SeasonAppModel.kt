package appUnqflix.appModel

import domain.Season
import org.uqbar.commons.model.annotations.Observable

@Observable
class SeasonAppModel (var season: Season){
    var id: String = ""
    var tituloSeason: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters = initChapters()
    var cantchapter : Int = cantidadChapter()
   var selectSeason : ChaptersAppModel? = null

    init {
        this.id = season.id
        this.tituloSeason = season.title
        this.description = season.description
    }

    fun initChapters(){}

    fun cantidadChapter(): Int= season.chapters.size

//    fun elegirSeason(seasonSeleccionada : SeasonAppModel): String{
////        selectSeason?.numeroPrueba =
//        return "algo"
//    }
}