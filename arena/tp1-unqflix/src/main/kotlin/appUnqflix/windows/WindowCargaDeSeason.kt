package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import appUnqflix.appModel.SerieAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color

class WindowCargaDeSeason (owner: WindowOwner, model : SeasonAppModel) : Dialog<SeasonAppModel>(owner,model) {
    override fun addActions(p0: Panel?) {

    }


    override fun createFormPanel(p0: Panel) {
        title = "Add Season"
        Panel(p0) with {

            Label(it) with {
                alignLeft()
                text = "Title:"
            }

            TextBox(it) with {
                fontSize = 10
                width = 200
                alignLeft()
                bindTo("tituloSeason")
            }
            Label(it) with {
                alignLeft()
                text = "Description:"
            }

            KeyWordTextArea(it) with {
                height = 100
                bindTo("description")
            }

            Label(it) with {
                alignLeft()
                text = "Poster:"
            }

            TextBox(it) with {
                fontSize = 10
                width = 200
                alignLeft()
                bindTo("poster")
            }

            Panel(it) with {
                asHorizontal()
                Button(it) with {
                    text = "Accept"
                    fontSize = 10
                    onClick(Action {
                        thisWindow.agregarSeason()
                        close()
//                        limpiarValoresNuevos()
                    })
                }

                Button(it) with {
                    text = "Cancel"
                    fontSize = 10
                    onClick(Action {
//                        limpiarValoresNuevos()
                        close()
                    })
                }
            }
        }
    }


    private fun agregarSeason() {
        modelObject.serieAppModel.agregarSeason(modelObject.tituloSeason, modelObject.description, modelObject.poster)
    }

//    private fun limpiarValoresNuevos() {
//        modelObject.titleSeason = ""
//        modelObject.descripcionSeason = ""
//        modelObject.posterSeason = ""
//    }
}