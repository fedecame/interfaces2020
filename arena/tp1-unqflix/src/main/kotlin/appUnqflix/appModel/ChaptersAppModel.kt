package appUnqflix.appModel

import domain.Chapter

class ChaptersAppModel (var chapter : Chapter) {
    var id: String = ""
    var description: String = ""
    var duration: Int = 0

    init {
        this.id = chapter.id
        this.description = chapter.description
        this.duration = chapter.duration
    }

}