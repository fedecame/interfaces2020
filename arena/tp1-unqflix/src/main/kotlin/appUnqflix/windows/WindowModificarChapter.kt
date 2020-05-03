package appUnqflix.windows


import appUnqflix.appModel.ChaptersAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowModificarChapter(owner: WindowOwner, chaptersAppModel: ChaptersAppModel) :
    SimpleWindow<ChaptersAppModel>(owner, chaptersAppModel) {
    override fun addActions(actionsPanel: Panel?) {
    }

    override fun createFormPanel(mainPanel: Panel?) {
        this.title = "Modify chapter: ${modelObject.title}"
        // Preguntar si tiene sentido delegar lo siguiente al appModel/viewModel
        val tempChapter = ChaptersAppModel(modelObject.chapter, modelObject.seasonAppModel)
        Panel(mainPanel) with {
            Label(it) with {
                text = "Title"
                alignLeft()
            }
            TextBox(it) with {
                bindToModel(modelObject, "title")
            }

        }

        Panel(mainPanel) with {
            Label(it) with {
                alignLeft()
                text = "Description"
            }
            KeyWordTextArea(it) with {
                
                bindToModel(modelObject, "description")
            }
        }

        Panel(mainPanel) with {
            Label(it) with {
                alignLeft()
                text = "Duration"
            }
            NumericField(it) with {
                bindToModel(modelObject, "duration")
            }
        }

        Panel(mainPanel) with {
            Label(it) with {
                text = "Thumbnail"
                alignLeft()
            }
            TextBox(it) with {
                width=100
                bindToModel(modelObject, "thumbnail")
            }
        }

        Panel(mainPanel) with {
            Label(it) with {
                text = "Video"
                alignLeft()
            }
            TextBox(it) with {
                bindToModel(modelObject, "video")
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick {
                    thisWindow.close()
                }
            }

            Button(it) with {
                caption = "Cancel"
                onClick {
                    // Preguntar si tiene sentido delegar lo siguiente al appModel/viewModel
                    thisWindow.modelObject.title = tempChapter.title
                    thisWindow.modelObject.description = tempChapter.description
                    thisWindow.modelObject.duration = tempChapter.duration
                    thisWindow.modelObject.thumbnail = tempChapter.thumbnail
                    thisWindow.modelObject.video = tempChapter.video
                    thisWindow.close()
                }
            }
        }
    }
}