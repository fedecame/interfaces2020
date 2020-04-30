package appUnqflix.appModel

import data.getUNQFlix
import data.idGenerator
import domain.Serie
import domain.UNQFlix
import domain.Unavailable
import org.uqbar.commons.model.annotations.Observable

@Observable
class UnqflixAppModel {
    var system : UNQFlix = getUNQFlix()
    var myseries = initSeries()
    var unaSerie = myseries.last()

    fun initSeries() = system.series.map { SerieAppModel(it, this) }.toMutableList()

    fun createSerie(title: String, description: String, poster: String): SerieAppModel {
        var serie = Serie(
            idGenerator.nextSerieId(),
            title,
            description,
            poster,
            Unavailable()
        )
        system.addSerie(serie)
        return SerieAppModel(serie, this)
    }

}