package appUnqflix.windows

import appUnqflix.appModel.SerieAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color

class WindowCargaDeSeason (owner: WindowOwner, model : SerieAppModel) : Dialog<SerieAppModel>(owner,model) {
    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(p0: Panel) {
        title = "agregar Season"
        setMinHeight(400)
        setMinWidth(200)

        Label(p0) with {
            alignLeft()
            text = "Title:"
        }

        TextBox(p0) with {
            fontSize = 10
            width = 200
            alignLeft()  // right, left, center

            bgColor = Color.orange
           bindTo("tituloSeason")
        }
        Label(p0) with {
            alignLeft()
            text = "Description:"
        }

        KeyWordTextArea(p0) with {
            height = 200
            bgColor = Color.orange
            bindTo("descripcionSeason")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
        }

        Label(p0) with {
            alignLeft()
            text = "Poster:"
        }

        TextBox(p0) with {
            fontSize = 10
            width = 200
            alignLeft()  // right, left, center

            bgColor = Color.orange
            bindTo("posterSeason")
        }

        Button(p0) with {
            text = "Accept"
            color = Color.BLUE
            fontSize = 10
            onClick(Action {
                agregarSeason()

                limpiarValoresNuevos()
                close() })
        }

        Button(p0) with {
            text = "Cancel"
            color = Color.BLUE
            fontSize = 10
            onClick(Action {
                limpiarValoresNuevos()
                close()
            })
        }

    }


    fun agregarSeason() {
        modelObject.agregarSeason(modelObject.tituloSeason, modelObject.descripcionSeason, modelObject.posterSeason)
    }

    fun limpiarValoresNuevos() {
        modelObject.tituloSeason = ""
        modelObject.descripcionSeason = ""
        modelObject.posterSeason = ""
    }
}