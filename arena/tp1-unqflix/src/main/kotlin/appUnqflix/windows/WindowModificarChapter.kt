package appUnqflix.windows


import appUnqflix.appModel.ChaptersAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowModificarChapter(owner: WindowOwner, chaptersAppModel: ChaptersAppModel) : SimpleWindow<ChaptersAppModel>(owner, chaptersAppModel) {
    override fun addActions(actionsPanel: Panel?) {
    }

    override fun createFormPanel(mainPanel: Panel?) {
        this.title = "Modify chapter: ${modelObject.title}"
        // Preguntar si tiene sentido delegar lo siguiente al appModel/viewModel
        val tempChapter = ChaptersAppModel(modelObject.chapter, modelObject.seasonAppModel)

        Label(mainPanel) withText "Title"

        TextBox(mainPanel) with {
            bindToModel(modelObject, "title")
        }

        Label(mainPanel) withText "Description"

        KeyWordTextArea(mainPanel) with {
            bindToModel(modelObject, "description")
        }

        Label(mainPanel) withText "Duration"

        NumericField(mainPanel) with {
            bindToModel(modelObject, "duration")
        }

        Label(mainPanel) withText "Thumbnail"

        TextBox(mainPanel) with {
            bindToModel(modelObject, "thumbnail")
        }

        Label(mainPanel) withText "Video"

        TextBox(mainPanel) with {
            bindToModel(modelObject, "video")
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
                modelObject.title = tempChapter.title
                modelObject.description = tempChapter.description
                modelObject.duration = tempChapter.duration
                modelObject.thumbnail = tempChapter.thumbnail
                modelObject.video = tempChapter.video
                thisWindow.close()
            }
        }
    }
}