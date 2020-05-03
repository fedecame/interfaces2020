package appUnqflix.appModel

import data.getUNQFlix
import data.idGenerator
import domain.*
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class UnqflixAppModel {
    var system : UNQFlix = getUNQFlix()
    var serieSearch : String = ""
  
    var myseries = mutableListOf<SerieAppModel>()
    var selectedSerie : SerieAppModel? = null
    var bufferSerie : SerieAppModel?=null


    init {
        this.initSeries()
    }

    fun initSeries() {
        myseries = system.series.map { SerieAppModel(it, this) }.toMutableList()
    }

    fun createSerie(title: String, description: String, poster: String, state: ContentState,
                    categories: MutableList<CategoryAppModel>, relatedContent: MutableList<ContentAppModel>): SerieAppModel {

        val categories = categories.map { Category(it.id, it.name) }.toMutableList()
        val relatedContent = relatedContent.map { it.content }.toMutableList()

        val serie = Serie(
            idGenerator.nextSerieId(),
            title,
            description,
            poster,
            state,
            categories,
            relatedContent = relatedContent
        )

        try {
            val serieAppModel = SerieAppModel(serie, this)
            system.addSerie(serie)
            myseries.add(serieAppModel)
            return serieAppModel
        }catch (e: ExistsException){
            throw UserException(e.message)
        }
    }
    fun agregarSerie(){
        this.createSerie(this.bufferSerie!!.title , this.bufferSerie!!.descripcion!!, this.bufferSerie!!.poster)
        //myseries.add(this.bufferSerie)
        this.bufferSerie = null
    }

    fun createSeason(serieAppModel: SerieAppModel, title: String, description: String, poster: String): SeasonAppModel {
        val season = Season(
            idGenerator.nextSeasonId(),
            title,
            description,
            poster
        )
        try {
            system.addSeason(serieAppModel.id, season)
            return SeasonAppModel(season, this, serieAppModel)
        }catch (e: ExistsException){
            throw UserException(e.message)
        }

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
        try {
            system.addChapter(serieId, seasonAppModel.id, chapter)
            return ChaptersAppModel(chapter, seasonAppModel)
        }catch (e: ExistsException){
            throw UserException(e.message)
        }

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

    fun getAllCategories(): MutableList<CategoryAppModel> {
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    fun getAllContents(): MutableList<Content> {
        val allContents = mutableListOf<Content>()
        allContents.addAll(system.movies)
        allContents.addAll(system.series)
//        return myseries.last().relatedContent.toMutableList()
        return allContents
    }

}



