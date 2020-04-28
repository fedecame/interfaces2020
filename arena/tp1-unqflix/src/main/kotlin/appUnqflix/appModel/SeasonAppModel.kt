package appUnqflix.appModel

import domain.Season
import org.uqbar.commons.model.annotations.Observable

@Observable
class SeasonAppModel (var season: Season){
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var chapters = initChapters()
    var cantchapter : Int = cantidadChapter()

    init {
        this.id = season.id
        this.title = season.title
    }

    fun initChapters(){}

    fun cantidadChapter(): Int= season.chapters.size
}