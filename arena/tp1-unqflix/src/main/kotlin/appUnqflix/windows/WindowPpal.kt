package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color

class WindowPpal (owner:WindowOwner, model:UnqflixAppModel) : SimpleWindow<UnqflixAppModel>(owner,model) {
    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "UnqFlix"

        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Search:"
            }

            TextBox(it) with {

                fontSize = 10
                width = 100
                alignLeft()  // right, left, center


           bindTo("serieSearch")

            }

            Button(it) with {
                text = "Buscar"
                fontSize = 10
                width =70
                onClick(Action {
                    buscarSeries()
                })
            }
        }

        Label(p0) with {
            text = "Series:"

        }



        table<SerieAppModel>(p0) with {
            bindItemsTo("myseries")
            bindSelectionTo("selectedSerie")

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
//                bindContentsTo("cantSeasons")
                bindContentsTo("myseasons.size")
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
                onClick {
//                    thisWindow.close()
                    WindowSeason(thisWindow, modelObject.selectedSerie!!).open()
                }
            }
        }
    }

    fun buscarSeries(){
        modelObject.buscarSeries()
    }
}