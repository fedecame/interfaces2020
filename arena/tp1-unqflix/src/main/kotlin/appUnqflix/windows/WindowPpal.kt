package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import domain.ContentState
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
                width = 300
                alignLeft()  // right, left, center


           bindTo("serieSearch")

            }

            Button(it) with {
                text = "Buscar"
                fontSize = 10
                width =70
                setAsDefault()
                onClick(Action {
                    buscarSeries()
                })
            }
        }

        Label(p0) with {
            text = "Series:"
            alignLeft()
        }



        table<SerieAppModel>(p0) with {
            bindItemsTo("myseries")
            bindSelectionTo("selectedSerie")

            column {
                title = "#"
                fixedSize = 70
                bindContentsTo("id")
            }
            column {
                title = "Title"
                fixedSize = 200
                bindContentsTo("title")
            }
            column {
                title = "Seasons"
                fixedSize = 80
//                bindContentsTo("cantSeasons")
                bindContentsTo("myseasons.size")
            }
            column {
                title = "State"
                fixedSize = 50
                bindContentsTo("adaptar")

            }
            Panel(p0) with {
                asHorizontal()
            Button(it) with {
                text = "Add new Serie"
                fontSize = 10
                width = 100
                onClick(Action {
                    val serieAppModel = SerieAppModel(unqflixAppModel = thisWindow.modelObject)
                    WindowCargaSerie(owner, serieAppModel).open()
                })
            }
            Button(it) with {
                text = "Modify Serie"
                fontSize = 10
                width = 100
                bindEnabledToModel(modelObject,"hasSelection")
                onClick(Action {
                    WindowModifSerie(owner, thisWindow.modelObject.selectedSerie!!).open()
                })
            }
            Button(it) with {
                text = "Delete Serie"
                fontSize = 10
                width = 100
                bindEnabledToModel(modelObject,"hasSelection")
                onClick {
                    val acepta = ConfirmDelete(thisWindow,thisWindow.modelObject!!)
                         acepta.onAccept {
                             thisWindow.modelObject.borrarSerie(thisWindow.modelObject.selectedSerie!!)
                         }
                       acepta.open()

                     }
            }
            Button(it) with {
                text = "Show Serie"
                fontSize = 10
                width = 100
                bindEnabledToModel(modelObject,"hasSelection")
                onClick {
                    thisWindow.close()
                    WindowSeason(thisWindow, thisWindow.modelObject.selectedSerie!!).open()
                }
            }}
        }
    }

    fun buscarSeries(){
        modelObject.buscarSeries()
    }
}