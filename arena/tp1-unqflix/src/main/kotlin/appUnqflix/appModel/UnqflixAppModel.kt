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
        val serieAppModel = SerieAppModel(serie, this)
        system.addSerie(serie)
        myseries.add(serieAppModel)
        return serieAppModel
    }

    fun createSeason(serieAppModel: SerieAppModel, title: String, description: String, poster: String): SeasonAppModel {
        val season = Season(
            idGenerator.nextSeasonId(),
            title,
            description,
            poster
        )
        system.addSeason(serieAppModel.id, season)
        return SeasonAppModel(season, this, serieAppModel)
    }

    fun createChapter(seasonAppModel: SeasonAppModel, serieId: String, title: String,
                      description: String, duration: Int, video: String, thumbnail: String): ChaptersAppModel {
        val chapter = Chapter(
            idGenerator.nextChapterId(),
            title,
            description,
            duration,
            video,
            thumbnail
        )
        system.addChapter(serieId, seasonAppModel.id, chapter)
        return ChaptersAppModel(chapter, seasonAppModel)
    }

    fun buscarSeries(){
        var seriesEncontradas = listOf<Serie>()

        seriesEncontradas= system.searchSeries(serieSearch)
        myseries = seriesEncontradas.map { SerieAppModel(it, this) }.toMutableList()

    }

    fun borrarSerie(serie: SerieAppModel){
        system.deleteSerie(serie.id)
        myseries.remove(serie)
    }
}



