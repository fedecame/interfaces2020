package appUnqflix.windows

import appUnqflix.appModel.ChaptersAppModel
import appUnqflix.appModel.SeasonAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder

class WindowChapters (owner: WindowOwner, seasonAppModel: SeasonAppModel) : SimpleWindow<SeasonAppModel>(owner, seasonAppModel) {
    override fun addActions(actionsPanel: Panel?) {
    }
    override fun createFormPanel(mainPanel: Panel) {
        this.title = "${modelObject.tituloSeason}'s Chapters"
        Label (mainPanel) with {
            text = title
        }
        table<ChaptersAppModel>(mainPanel) {
            bindItemsTo("chapters")
            bindSelectionTo("selectedChapter")
            column {
                title = "#"
                fixedSize = 30
                bindContentsTo("id")
            }
            column {
                title = "Title"
                weight = 50
                align("center")
                bindContentsTo("title")
            }
            column {
                title = "Duration"
                align("right")
                bindContentsTo("duration")
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Add new chapter"
                onClick {
                    //abrir ventana para crear chapter
//                    thisWindow.close()
                    WindowCargaChapter(thisWindow, thisWindow.modelObject).open()
                }
            }
            Button(it) with {
                caption = "Modify selected chapter"
//                bindEnabledTo("selectedChapter")
                onClick {
                    //abrir ventana para editar chapter
                    WindowModificarChapter(thisWindow, thisWindow.modelObject).open()
                }
            }
            Button(it) with {
                caption = "Back to Seasons"
                onClick {
                    thisWindow.close()
                }
            }
        }
    }

}