package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color

class WindowPpal (owner:WindowOwner, model:UnqflixAppModel) : SimpleWindow<UnqflixAppModel>(owner,model) {
    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "UnqFlix"
        Label(p0) with {
            text = "Search:"
        }
        Label(p0) with {
            text = "Series:"
        }

        table<SerieAppModel>(p0) with {
            bindItemsTo("myseries")
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
                title = "Seasons"
                fixedSize = 100
                bindContentsTo("cantSeasons")
            }
            column {
                title = "State"
                fixedSize = 100
                bindContentsTo("state")
            }
            Button(p0) with {
                text = "Add new Serie"
                fontSize = 10
                onClick(Action { WindowCargaSerie(owner,modelObject).open() })
            }
            Button(p0) with {
                text = "Modified Serie"
                fontSize = 10
                onClick(Action { WindowModifSerie(owner,modelObject).open() })
            }
            Button(p0) with {
                text = "Delete Serie"
                fontSize = 10
            }
            Button(p0) with {
                text = "Show Serie"
                fontSize = 10

            }
        }
    }
}