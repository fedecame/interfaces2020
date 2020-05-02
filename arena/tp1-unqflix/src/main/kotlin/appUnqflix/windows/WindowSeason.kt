package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import domain.Serie
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color

class WindowSeason (owner: WindowOwner, model : SerieAppModel) : SimpleWindow<SerieAppModel>(owner,model) {

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "temporadas"
        Label(p0) with {
            bindTo("title")
        }
        Label(p0) with  {
            text = "Seasons:"
        }

        table<SeasonAppModel>(p0)with {
            bindItemsTo("myseasons")

            bindSelectionTo("selected")


            column {
                title = "#"
                fixedSize = 100
                bindContentsTo("id")
            }
            column {
                title = "Title"
                fixedSize = 100

                bindContentsTo("tituloSeason")

            }
            column {
                title = "Chapters"
                fixedSize = 100
                bindContentsTo("cantidadChapter")
            }
        }

        Button(p0) with {
            text = "Add new season"
            color = Color.green
            fontSize = 10

            onClick(Action {
                WindowCargaDeSeason(owner,modelObject.selected!!).open()

            })

        }

        Button(p0) with {
            text = "Modified Season"
            color = Color.PINK
            fontSize = 10

            onClick(Action { WindowModificarSeason(owner,modelObject.selected!!).open() })

        }

        Button(p0) with {
            text = "Show chapters"
            color = Color.BLUE
            fontSize = 10

            onClick(Action {
                thisWindow.close()
                WindowChapters(thisWindow, modelObject.selected!!).open()
            })

        }

        Button(p0) with {
            text = "Back to Unqflix"
            fontSize = 10

            onClick {
                thisWindow.close()
                WindowPpal(thisWindow, modelObject.unqflixAppModel).open()
            }
        }


    }
}