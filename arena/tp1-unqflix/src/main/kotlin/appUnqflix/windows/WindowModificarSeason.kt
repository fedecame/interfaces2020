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
        title = "modify Season"
        val tempSeason = SeasonAppModel(modelObject.season, modelObject.unqflixAppModel, modelObject.serieAppModel)

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
                width = 100
                bindTo("description")
            }

            Label(it) with {
                alignLeft()
                text = "Poster:"
            }

            TextBox(it) with {
                fontSize = 10
                width = 100
                alignLeft()
                bindTo("poster")
            }

            Panel(it) with {
                asHorizontal()
                Button(it) with {
                    text = "Accept"
                    fontSize = 10
                    onClick(Action {

                        close()
                    })
                }

                Button(it) with {
                    text = "Cancel"
                    fontSize = 10
                    onClick(Action {

                        thisWindow.modelObject.tituloSeason = tempSeason.tituloSeason
                        thisWindow.modelObject.description = tempSeason.description
                        thisWindow.modelObject.poster = tempSeason.poster
                        close()
                    })
                }
            }
        }
    }

    override fun addActions(p0: Panel?) {

    }

}


