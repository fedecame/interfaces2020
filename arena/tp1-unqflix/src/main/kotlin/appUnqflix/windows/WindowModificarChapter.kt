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

    private fun mirrorSelectedChapterValues() {
        modelObject.title = modelObject.seasonAppModel.selectedChapter!!.title
        modelObject.description = modelObject.seasonAppModel.selectedChapter!!.description
        modelObject.duration = modelObject.seasonAppModel.selectedChapter!!.duration
        modelObject.thumbnail = modelObject.seasonAppModel.selectedChapter!!.thumbnail
        modelObject.video = modelObject.seasonAppModel.selectedChapter!!.video
    }

    private fun updateSelectedChapterValues() {
        modelObject.seasonAppModel.selectedChapter!!.title = modelObject.title
        modelObject.seasonAppModel.selectedChapter!!.description = modelObject.description
        modelObject.seasonAppModel.selectedChapter!!.duration = modelObject.duration
        modelObject.seasonAppModel.selectedChapter!!.thumbnail = modelObject.thumbnail
        modelObject.seasonAppModel.selectedChapter!!.video = modelObject.video
    }

    override fun createFormPanel(mainPanel: Panel?) {
        this.mirrorSelectedChapterValues()

        this.title = "Modify chapter: ${modelObject.seasonAppModel.selectedChapter!!.title}"

        setMinWidth(100)
        Panel(mainPanel) with {
            Label(it) with {
                text = "Title"
                alignLeft()
            }
            TextBox(it) with {
                bindTo("title")
                width = 200
            }

        }

        Panel(mainPanel) with {
            Label(it) with {
                alignLeft()
                text = "Description"
            }
            KeyWordTextArea(it) with {
                bindTo("description")
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
                bindTo("duration")
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
                bindTo("thumbnail")
            }
        }

        Panel(mainPanel) with {
            Label(it) with {
                text = "Video"
                alignLeft()
            }
            TextBox(it) with {
                bindTo("video")
                width = 200
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick {
                    thisWindow.updateSelectedChapterValues()
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