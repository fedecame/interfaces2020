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

       Panel(mainPanel) with {
           Label(it) with {
               text = title
           }
           table<ChaptersAppModel>(it) {
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
           Panel(it) with {
               asHorizontal()
               Button(it) with {
                   caption = "Add new chapter"
                   onClick {
                       val chaptersAppModel = ChaptersAppModel(seasonAppModel = thisWindow.modelObject)
                       WindowCargaChapter(thisWindow, chaptersAppModel).open()
                   }
               }
               Button(it) with {
                   caption = "Modify selected chapter"
                   onClick {
                       WindowModificarChapter(thisWindow, thisWindow.modelObject.selectedChapter!!).open()
                   }
               }
               Button(it) with {
                   caption = "Back to ${thisWindow.modelObject.serieAppModel!!.title}'s Seasons"
                   onClick {
                       thisWindow.close()
                       WindowSeason(thisWindow, thisWindow.modelObject.serieAppModel!!).open()
                   }
               }
           }
       }
    }

}