package appUnqflix.windows

import appUnqflix.appModel.ChaptersAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowCargaChapter(owner: WindowOwner, chaptersAppModel: ChaptersAppModel) : SimpleWindow<ChaptersAppModel>(owner, chaptersAppModel) {
    override fun addActions(actionsPanel: Panel?) {
    }

    override fun createFormPanel(mainPanel: Panel?) {
        this.title = "Create new chapter"
        setMinHeight(400)
        setMinWidth(200)
        Label(mainPanel) withText "Title"

        TextBox(mainPanel) with {
            bindToModel(modelObject, "title")
            setWidth(200)
        }

        Label(mainPanel) withText "Description"

        KeyWordTextArea(mainPanel) with {
            bindToModel(modelObject, "description")
            setWidth(200)
            setHeight(100)
            isMultiLine = true
//            selectFinalLine()
        }

        Label(mainPanel) withText "Duration"

        NumericField(mainPanel) with {
            bindToModel(modelObject, "duration")
            setWidth(200)
        }

        Label(mainPanel) withText "Thumbnail"

        TextBox(mainPanel) with {
            bindToModel(modelObject, "thumbnail")
            setWidth(200)
        }

        Label(mainPanel) withText "Video"

        TextBox(mainPanel) with {
            bindToModel(modelObject, "video")
            setWidth(200)
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick {
                modelObject.seasonAppModel.agregarChapter(
                    modelObject.title,
                    modelObject.description,
                    modelObject.duration,
                    modelObject.thumbnail,
                    modelObject.video
                )
                thisWindow.limpiarValoresNuevos()
                thisWindow.close()
            }
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick {
                thisWindow.limpiarValoresNuevos()
                thisWindow.close()
            }
        }
    }

    // Preguntar si tiene sentido delegar lo siguiente al appModel/viewModel
    fun limpiarValoresNuevos() {
        modelObject.title = ""
        modelObject.description = ""
        modelObject.duration = 0
        modelObject.video = ""
        modelObject.thumbnail = ""
    }
}