package appUnqflix.windows


import appUnqflix.appModel.SeasonAppModel
import appUnqflix.appModel.ChaptersAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowModificarChapter(owner: WindowOwner, seasonAppModel: SeasonAppModel) : SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {
    override fun addActions(actionsPanel: Panel?) {
    }

    override fun createFormPanel(mainPanel: Panel?) {
        this.title = "Modify chapter: ${modelObject.selectedChapter!!.title}"
        val tempChapter = ChaptersAppModel(modelObject.selectedChapter!!.chapter)
        Label(mainPanel) withText "Title"

        TextBox(mainPanel) with {
            bindToModel(modelObject.selectedChapter!!, "title")
        }

        Label(mainPanel) withText "Description"

        KeyWordTextArea(mainPanel) with {
            bindToModel(modelObject.selectedChapter!!, "description")
        }

        Label(mainPanel) withText "Duration"

        NumericField(mainPanel) with {
            bindToModel(modelObject.selectedChapter!!, "duration")
        }

        Label(mainPanel) withText "Thumbnail"

        TextBox(mainPanel) with {
            bindToModel(modelObject.selectedChapter!!, "thumbnail")
        }

        Label(mainPanel) withText "Video"

        TextBox(mainPanel) with {
            bindToModel(modelObject.selectedChapter!!, "video")
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick {
                thisWindow.close()
            }
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick {
                modelObject.selectedChapter = tempChapter
                // Corregir, no funciona bien el cancel.
                thisWindow.close()
            }
        }
    }
}