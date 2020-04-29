package appUnqflix.appModel

import domain.ContentState
import domain.Season
import org.apache.commons.collections.functors.FalsePredicate
import org.uqbar.commons.model.annotations.Observable

@Observable
class SeasonAppModel (var season: Season){
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters = initChapters()
    var cantchapter : Int = cantidadChapter()
   var selectSeason : ChaptersAppModel? = null


    init {
        this.id = season.id
        this.title = season.title
    }

    fun initChapters(){}

    fun cantidadChapter(): Int= season.chapters.size

//    fun elegirSeason(seasonSeleccionada : SeasonAppModel): String{
////        selectSeason?.numeroPrueba =
//        return "algo"
//    }
}