package appUnqflix.appModel

import data.getUNQFlix
import data.idGenerator
import domain.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class UnqflixAppModel {
    var system : UNQFlix = getUNQFlix()
    var serieSearch : String = ""
    var myseries = mutableListOf<SerieAppModel>()
    var selectedSerie : SerieAppModel? = null


    init {
        this.initSeries()
    }

    fun initSeries() {
        myseries = system.series.map { SerieAppModel(it, this) }.toMutableList()
    }

    fun createSerie(title: String, description: String, poster: String): SerieAppModel {
        val serie = Serie(
            idGenerator.nextSerieId(),
            title,
            description,
            poster,
            Unavailable()
        )
        system.addSerie(serie)
        return SerieAppModel(serie, this)
    }

    fun createSeason(serieId: String, title: String, description: String, poster: String): SeasonAppModel {
        val season = Season(
            idGenerator.nextSeasonId(),
            title,
            description,
            poster
        )
        system.addSeason(serieId, season)
        return SeasonAppModel(season, this, serieId)
    }

    fun createChapter(serieId: String, seasonId: String, title: String,
                      description: String, duration: Int, video: String, thumbnail: String): ChaptersAppModel {
        val chapter = Chapter(
            idGenerator.nextChapterId(),
            title,
            description,
            duration,
            video,
            thumbnail
        )
        system.addChapter(serieId, seasonId, chapter)
        return ChaptersAppModel(chapter)
    }


    fun buscarSeries(){
        var seriesEncontradas = listOf<Serie>()

        seriesEncontradas= system.searchSeries(serieSearch)
        myseries = seriesEncontradas.map { SerieAppModel(it, this) }.toMutableList()

    }
}



