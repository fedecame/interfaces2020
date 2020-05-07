package appUnqflix.windows

import appUnqflix.appModel.ChaptersAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowCargaChapter(owner: WindowOwner, chaptersAppModel: ChaptersAppModel) :
    SimpleWindow<ChaptersAppModel>(owner, chaptersAppModel) {
    override fun addActions(actionsPanel: Panel?) {

    }

    override fun createFormPanel(mainPanel: Panel?) {

        this.title = "Create new chapter"

        setMinWidth(100)
        Panel(mainPanel) with {

            Panel(it) with {
                Label(it) with {
                    text = "Title"
                    alignLeft()
                }

                TextBox(it) with {
                    bindToModel(modelObject, "title")
                    width= 200
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = "Description"
                    alignLeft()
                }
                KeyWordTextArea(it) with {
                    bindToModel(modelObject, "description")
                    width = 200
                }

            }

            Panel(it) with {
                Label(it) with {
                    text = "Duration"
                    alignLeft()
                }
                NumericField(it) with {
                    bindToModel(modelObject, "duration")
                    width = 200
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = "Thumbnail"
                    alignLeft()
                }
                TextBox(it) with {
                    bindToModel(modelObject, "thumbnail")
                    width = 200
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = "Video"
                    alignLeft()
                }
                TextBox(it) with {
                    bindToModel(modelObject, "video")
                    width = 200
                }
            }

            Panel(it) with {
                asHorizontal()
                Button(it) with {
                    caption = "Accept"
                    onClick {
                        thisWindow.modelObject.seasonAppModel.agregarChapter(
                            thisWindow.modelObject.title,
                            thisWindow.modelObject.description,
                            thisWindow.modelObject.duration,
                            thisWindow.modelObject.thumbnail,
                            thisWindow.modelObject.video
                        )
                        thisWindow.close()
                    }
                }

                Button(it) with {
                    caption = "Cancel"
                    onClick {
                        thisWindow.close()
                    }
                }

            }
        }
    }

}