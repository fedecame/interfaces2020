package appUnqflix.appModel

import data.getUNQFlix
import data.idGenerator
import domain.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class UnqflixAppModel {
    var system : UNQFlix = getUNQFlix()
    var myseries = initSeries()


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

    fun createSeason(serieId: String, title: String, description: String, poster: String): SeasonAppModel {
        var season = Season(
            idGenerator.nextSeasonId(),
            title,
            description,
            poster
        )
        system.addSeason(serieId, season)
        return SeasonAppModel(season)
    }




    fun createChapter(serieId: String, seasonId: String, title: String,
                      description: String, duration: Int, video: String, thumbnail: String): ChaptersAppModel {
        var chapter = Chapter(
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
}