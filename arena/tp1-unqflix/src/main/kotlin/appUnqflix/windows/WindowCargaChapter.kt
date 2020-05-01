package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowCargaChapter(owner: WindowOwner, seasonAppModel: SeasonAppModel) : SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {
    override fun addActions(actionsPanel: Panel?) {
    }

    override fun createFormPanel(mainPanel: Panel?) {
        this.title = "Create new chapter"
        setMinHeight(400)
        setMinWidth(200)
        Label(mainPanel) withText "Title"

        TextBox(mainPanel) with {
//            bindToModel(modelObject.selectedChapter!!, "title")
            setWidth(200)
            bindTo("titleNC")
        }

        Label(mainPanel) withText "Description"

        KeyWordTextArea(mainPanel) with {
//            bindToModel(modelObject.selectedChapter!!, "description")
            setWidth(200)
            isMultiLine = true
//            selectFinalLine()
            setHeight(100)
            bindTo("descriptionNC")
        }

        Label(mainPanel) withText "Duration"

        NumericField(mainPanel) with {
//            bindToModel(modelObject.selectedChapter!!, "duration")
            setWidth(200)
            bindTo("durationNC")
        }

        Label(mainPanel) withText "Thumbnail"

        TextBox(mainPanel) with {
//            bindToModel(modelObject.selectedChapter!!, "thumbnail")
            setWidth(200)
            bindTo("thumbnailNC")
        }

        Label(mainPanel) withText "Video"

        TextBox(mainPanel) with {
//            bindToModel(modelObject.selectedChapter!!, "video")
            setWidth(200)
            bindTo("videoNC")
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick {
                modelObject.agregarChapter(
                    modelObject.titleNC,
                    modelObject.descriptionNC,
                    modelObject.durationNC,
                    modelObject.videoNC,
                    modelObject.thumbnailNC
                )
                thisWindow.close()
            }
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick {
                thisWindow.close()
            }
        }
    }
}