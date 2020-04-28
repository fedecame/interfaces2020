package appUnqflix.appModel

import domain.Serie
import org.uqbar.commons.model.annotations.Observable

@Observable
class SerieAppModel(val serie: Serie) {
    var id : String = ""
    var title : String = ""
    var myseasons  = initSeasons()
//    var selected : SeasonAppModel? = null

    init {
        this.id = serie.id
        this.title = serie.title
    }

       fun initSeasons()=  serie.seasons.map{SeasonAppModel (it)}.toMutableList()
//
//        fun elegirSeason(seasonSeleccionada : SeasonAppModel){
////        selectSeason?.numeroPrueba =
//

}