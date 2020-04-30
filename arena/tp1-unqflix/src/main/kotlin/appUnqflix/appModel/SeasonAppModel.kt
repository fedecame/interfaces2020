package appUnqflix.appModel


import domain.Chapter

import domain.Season

import org.uqbar.commons.model.annotations.Observable

@Observable
class SeasonAppModel (var season: Season, val unqflixAppModel: UnqflixAppModel, val serieId: String){
    var id: String = ""
    var tituloSeason: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters = mutableListOf<ChaptersAppModel>()
    var selectedChapter : ChaptersAppModel? = null
    var cantchapter : Int = cantidadChapter()

    var titleNC = ""
    var descriptionNC = ""
    var durationNC = 0
    var thumbnailNC = ""
    var videoNC = ""

    init {
        this.id = season.id
        this.tituloSeason = season.title
        this.description = season.description
        this.poster = season.poster

        initChapters(season.chapters)
    }

    fun initChapters(chapters: MutableList<Chapter>){
        this.chapters = chapters.map { ChaptersAppModel(it) }.toMutableList()
    }

    fun cantidadChapter(): Int= season.chapters.size

//    fun elegirSeason(seasonSeleccionada : SeasonAppModel): String{
////        selectSeason?.numeroPrueba =
//        return "algo"
//    }
    fun agregarChapter(title: String, description: String, duration: Int, video: String,
                       thumbnail: String): ChaptersAppModel {
        val chapterAppModel = unqflixAppModel.createChapter(serieId, this.id, title,
            description, duration, video, thumbnail)
        this.chapters.add(chapterAppModel)
//        this.initChapters(season.chapters)

        return chapterAppModel
    }
}