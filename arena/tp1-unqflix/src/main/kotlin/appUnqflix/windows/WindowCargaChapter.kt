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

    private fun addNewChapter() {
        modelObject.seasonAppModel.agregarChapter(
            modelObject.title,
            modelObject.description,
            modelObject.duration,
            modelObject.thumbnail,
            modelObject.video
        )
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
                    bindTo("title")
                    width= 200
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = "Description"
                    alignLeft()
                }
                KeyWordTextArea(it) with {
                    bindTo("description")
                    width = 200
                }

            }

            Panel(it) with {
                Label(it) with {
                    text = "Duration"
                    alignLeft()
                }
                NumericField(it) with {
                    bindTo("duration")
                    width = 200
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = "Thumbnail"
                    alignLeft()
                }
                TextBox(it) with {
                    bindTo("thumbnail")
                    width = 200
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = "Video"
                    alignLeft()
                }
                TextBox(it) with {
                    bindTo("video")
                    width = 200
                }
            }

            Panel(it) with {
                asHorizontal()
                Button(it) with {
                    caption = "Accept"
                    onClick {
                        thisWindow.addNewChapter()
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