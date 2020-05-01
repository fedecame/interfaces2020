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
        // Preguntar si tiene sentido delegar lo siguiente al appModel/viewModel
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
                // Preguntar si tiene sentido delegar lo siguiente al appModel/viewModel
                modelObject.selectedChapter!!.title = tempChapter.title
                modelObject.selectedChapter!!.description = tempChapter.description
                modelObject.selectedChapter!!.duration = tempChapter.duration
                modelObject.selectedChapter!!.thumbnail = tempChapter.thumbnail
                modelObject.selectedChapter!!.video = tempChapter.video
                thisWindow.close()
            }
        }
    }
}