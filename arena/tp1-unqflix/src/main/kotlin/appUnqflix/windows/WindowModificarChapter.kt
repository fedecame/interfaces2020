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
        val tempChapter = ChaptersAppModel(modelObject.chapter, modelObject.seasonAppModel)
        setMinWidth(100)
        Panel(mainPanel) with {
            Label(it) with {
                text = "Title"
                alignLeft()
            }
            TextBox(it) with {
                bindToModel(modelObject, "title")
                width = 200
            }

        }

        Panel(mainPanel) with {
            Label(it) with {
                alignLeft()
                text = "Description"
            }
            KeyWordTextArea(it) with {
                
                bindToModel(modelObject, "description")
                width = 200
                height = 50
            }
        }

        Panel(mainPanel) with {
            Label(it) with {
                alignLeft()
                text = "Duration"
            }
            NumericField(it) with {
                bindToModel(modelObject, "duration")
                width = 200
            }
        }

        Panel(mainPanel) with {
            Label(it) with {
                text = "Thumbnail"
                alignLeft()
            }
            TextBox(it) with {
                width=200
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
                width = 200
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