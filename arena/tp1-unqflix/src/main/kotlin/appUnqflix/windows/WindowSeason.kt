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

    fun createEmptySeasonAppModel(): SeasonAppModel {
        return SeasonAppModel( unqflixAppModel = modelObject.unqflixAppModel,serieAppModel = modelObject)
    }

    override fun createFormPanel(p0: Panel) {
        title = "temporadas"
        Panel(p0) with {
            Label(it) with {
                bindTo("title")
                alignLeft()
            }
            Label(it) with {
                text = "Seasons:"
            }

            table<SeasonAppModel>(it) with {
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


            Panel(it) with {
                asHorizontal()
                Button(it) with {
                    text = "Add new season"
                    fontSize = 10

                    onClick(Action {
//                        WindowCargaDeSeason(owner, thisWindow.modelObject.selected!!).open()
                        WindowCargaDeSeason(owner, thisWindow.createEmptySeasonAppModel()).open()
                    })

                }

                Button(it) with {
                    text = "Modified Season"
                    fontSize = 10
                    bindEnabledTo("hasSelected")
                    onClick(Action {
                        WindowModificarSeason(owner, thisWindow.createEmptySeasonAppModel()).open()
                    })

                }

                Button(it) with {
                    text = "Show chapters"
                    fontSize = 10
                    bindEnabledTo("hasSelected")
                    onClick(Action {
                        thisWindow.close()
                        WindowChapters(thisWindow, thisWindow.modelObject.selected!!).open()
                    })

                }

                Button(it) with {
                    text = "Back to Unqflix"
                    fontSize = 10

                    onClick {
                        thisWindow.close()
                        WindowPpal(thisWindow, thisWindow.modelObject.unqflixAppModel).open()
                    }
                }
            }
        }
    }
}