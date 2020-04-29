package appUnqflix.appModel

import data.getUNQFlix
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable

@Observable
class UnqflixAppModel {

        var system : UNQFlix = getUNQFlix()
        var myseries = initSeries()

    var unaSerie = initSeries().first()


        fun initSeries() = system.series.map { SerieAppModel(it) }.toMutableList()

}