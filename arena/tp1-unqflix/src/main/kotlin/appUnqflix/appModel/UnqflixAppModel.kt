package appUnqflix.appModel

import data.getUNQFlix
import data.idGenerator
import domain.*
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

    fun createSeason(serie: SerieAppModel, title: String, description: String, poster: String): SeasonAppModel {
        var season = Season(
            idGenerator.nextSeasonId(),
            title,
            description,
            poster
        )
        system.addSeason(serie.id, season)
        return SeasonAppModel(season)
    }

    fun createChapter(serie: SerieAppModel, season: SeasonAppModel, title: String,
                      description: String, duration: Int, video: String, thumbnail: String): ChaptersAppModel {
        var chapter = Chapter(
            idGenerator.nextChapterId(),
            title,
            description,
            duration,
            video,
            thumbnail
        )
        system.addChapter(serie.id, season.id, chapter)
        return ChaptersAppModel(chapter)
    }
}