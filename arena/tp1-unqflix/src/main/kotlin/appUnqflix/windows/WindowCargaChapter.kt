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
        title = "Create new chapter"
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
                thisWindow.close()
            }
        }
    }
}