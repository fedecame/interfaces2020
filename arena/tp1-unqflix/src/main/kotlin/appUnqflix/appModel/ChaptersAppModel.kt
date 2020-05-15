package appUnqflix.appModel

import domain.Chapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class ChaptersAppModel (val chapter : Chapter? = null, val seasonAppModel: SeasonAppModel) {
    var id: String
    var title: String = ""
        set(value) {
            field = value
            hasValidInputs = duration !== 0 && value.isNotEmpty()
        }
    var duration: Int = 0
        set(value) {
            field = value
            hasValidInputs = title.isNotEmpty() && value !== 0
        }
    var description: String
    var thumbnail: String
    var video: String

    var hasValidInputs : Boolean = false

    // fix de bug de arena, que al deshabilitar un boton se lleva puesto y hace invisible uno que esta en el panel padre
    val visible = true

    init {
        this.id = chapter?.id ?: ""
        this.title = chapter?.title ?: ""
        this.duration = chapter?.duration ?: 0
        this.description = chapter?.description ?: ""
        this.thumbnail = chapter?.thumbnail ?: ""
        this.video = chapter?.video ?: ""
    }
}