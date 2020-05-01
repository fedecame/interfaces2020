package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color


class WindowModificarSeason(owner: WindowOwner, model: SeasonAppModel): Dialog<SeasonAppModel>(owner, model){
    override fun createFormPanel(p0: Panel?) {
        title = "modificar Season"
        val tempSeason = SeasonAppModel(modelObject.season, modelObject.unqflixAppModel, modelObject.serieId)

        Label(p0) with  {
            alignLeft()
            text = "Title:"
        }

        TextBox(p0) with {

            fontSize = 10
            width = 200
            alignLeft()  // right, left, center

            bgColor = Color.GRAY
            bindTo("tituloSeason")

        }
        Label(p0) with {
            alignLeft()
            text = "Description:"
        }

        KeyWordTextArea(p0) with {

            height = 200
            bgColor = Color.GRAY
          bindTo("description")
//            bindColorTo("blue")

        }

        Label(p0) with {
            alignLeft()
            text = "Poster:"
        }

        TextBox(p0) with {

            fontSize = 10
            width = 200
            alignLeft()  // right, left, center

            bgColor = Color.GRAY
           bindTo("poster")
        }

        Button(p0) with {
            text = "Accept"
            color = Color.BLUE
            fontSize = 10
            onClick(Action {

                close() })
        }

        Button(p0) with {
            text = "Cancel"
            color = Color.BLUE
            fontSize = 10
            onClick(Action {
                modelObject.tituloSeason = tempSeason.tituloSeason
                modelObject.description = tempSeason.description
                modelObject.poster = tempSeason.poster
                close()
            })
        }
    }

    override fun addActions(p0: Panel?) {

    }

}


