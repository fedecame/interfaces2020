package appUnqflix.appModel

import domain.Chapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class ChaptersAppModel (val chapter : Chapter? = null, val seasonAppModel: SeasonAppModel) {
    var id: String
    var title: String
    var duration: Int
    var description: String
    var thumbnail: String
    var video: String

    init {
        this.id = chapter?.id ?: ""
        this.title = chapter?.title ?: ""
        this.duration = chapter?.duration ?: 0
        this.description = chapter?.description ?: ""
        this.thumbnail = chapter?.thumbnail ?: ""
        this.video = chapter?.video ?: ""
    }

}