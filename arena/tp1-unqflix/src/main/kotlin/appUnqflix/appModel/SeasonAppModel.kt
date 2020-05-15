package appUnqflix.appModel


import domain.Chapter

import domain.Season

import org.uqbar.commons.model.annotations.Observable

@Observable
class SeasonAppModel (val season: Season? = null, val unqflixAppModel: UnqflixAppModel, val serieAppModel: SerieAppModel){
    var id: String
    var tituloSeason: String
    var description: String
    var poster: String
    var chapters = mutableListOf<ChaptersAppModel>()

    var selectedChapter : ChaptersAppModel? = null
        set(value) {
            field = value
            hasSelectedChapter = value !== null
        }

    var hasSelectedChapter : Boolean = false

    init {
        this.id = season?.id ?: ""
        this.tituloSeason = season?.title ?: ""
        this.description = season?.description ?: ""
        this.poster = season?.poster ?: ""

        this.initChapters()
    }

    fun initChapters(){
        this.chapters = season?.chapters?.map { ChaptersAppModel(it, this) }?.toMutableList() ?: mutableListOf()
    }

    fun cantidadChapter(): Int= season!!.chapters.size

    fun agregarChapter(title: String, description: String, duration: Int, video: String,
                       thumbnail: String): ChaptersAppModel {
        val chapterAppModel = unqflixAppModel.createChapter(this, serieAppModel!!.id, title,
            description, duration, video, thumbnail)
        this.chapters.add(chapterAppModel)

        return chapterAppModel
    }
}