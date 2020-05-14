package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color


class WindowModificarSeason(owner: WindowOwner, model: SeasonAppModel): Dialog<SeasonAppModel>(owner, model){

    fun mirrorSelectedSeasonValues(){
        modelObject.tituloSeason = modelObject.serieAppModel!!.selected!!.tituloSeason
        modelObject.description = modelObject.serieAppModel!!.selected!!.description
        modelObject.poster = modelObject.serieAppModel!!.selected!!.poster
    }

    fun updateSelectedSeasonValues(){
        modelObject.serieAppModel!!.selected!!.tituloSeason = modelObject.tituloSeason
        modelObject.serieAppModel!!.selected!!.description = modelObject.description
        modelObject.serieAppModel!!.selected!!.poster = modelObject.poster
    }

    override fun createFormPanel(p0: Panel?) {
        this.mirrorSelectedSeasonValues()
        title = "modify Season"


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
                        thisWindow.updateSelectedSeasonValues()
                        thisWindow.close()
                    })
                }

                Button(it) with {
                    text = "Cancel"
                    fontSize = 10
                    onClick(Action {

//                        thisWindow.modelObject.tituloSeason = tempSeason.tituloSeason
//                        thisWindow.modelObject.description = tempSeason.description
//                        thisWindow.modelObject.poster = tempSeason.poster
                        thisWindow.close()
                    })
                }
            }
        }
    }

    override fun addActions(p0: Panel?) {

    }

}


