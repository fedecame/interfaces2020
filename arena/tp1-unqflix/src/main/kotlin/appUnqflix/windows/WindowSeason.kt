package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import domain.Serie
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class WindowSeason (owner: WindowOwner, model : SerieAppModel) : SimpleWindow<SerieAppModel>(owner,model) {

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "temporadas"
        Label(p0) with  {
            text = "FaltaNombre de la serie"
        }

        table<SeasonAppModel>(p0)with {
            bindItemsTo("myseasons")
//            bindSelectionTo("selected")

            column {
                title = "#"
                fixedSize = 100
                bindContentsTo("id")
            }
            column {
                title = "Title"
                fixedSize = 100
                bindContentsTo("title")
            }
            column {
                title = "Chapters"
                fixedSize = 100
                bindContentsTo("cantidadChapter")
            }
        }

    }
}