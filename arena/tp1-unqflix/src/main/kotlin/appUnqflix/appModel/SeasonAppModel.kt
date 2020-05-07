package appUnqflix.appModel


import domain.Chapter

import domain.Season

import org.uqbar.commons.model.annotations.Observable

@Observable
class SeasonAppModel (var season: Season, val unqflixAppModel: UnqflixAppModel, val serieAppModel: SerieAppModel? = null){
    var id: String = ""
    var tituloSeason: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters = mutableListOf<ChaptersAppModel>()
    var selectedChapter : ChaptersAppModel? = null
    var cantchapter : Int = cantidadChapter()

    var titleSeason = ""
    var descripcionSeason = ""
    var posterSeason = ""
  
    init {
        this.id = season.id
        this.tituloSeason = season.title
        this.description = season.description
        this.poster = season.poster

        initChapters(season.chapters)
    }

    fun initChapters(chapters: MutableList<Chapter>){
        this.chapters = chapters.map { ChaptersAppModel(it, this) }.toMutableList()
    }

    fun cantidadChapter(): Int= season.chapters.size

    fun agregarChapter(title: String, description: String, duration: Int, video: String,
                       thumbnail: String): ChaptersAppModel {
        val chapterAppModel = unqflixAppModel.createChapter(this, serieAppModel!!.id, title,
            description, duration, video, thumbnail)
        this.chapters.add(chapterAppModel)

        return chapterAppModel
    }
}