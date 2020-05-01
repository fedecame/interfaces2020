package appUnqflix.windows

import appUnqflix.appModel.UnqflixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class WindowModifSerie (owner: WindowOwner, model: UnqflixAppModel) : SimpleWindow<UnqflixAppModel>(owner, model){

    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(p0: Panel?) {

        Panel(p0) with {
            asHorizontal()
            Panel(it) with {
                asVertical()
                Label(it) with {
                    text = "Title:"
                    alignLeft()
                }
                TextBox(it) with {
                    fontSize = 10
                    width = 200
//            alignLeft()  // right, left, center
//
//            bgColor = Color.orange
//            bindTo("falta var")
                    bindToModel(thisWindow.modelObject.selectedSerie!!, "title")
                }

            }
            Panel(it) with {
                Label(it) with {
                    text = "Poster:"
                    alignLeft()
                }

                TextBox(it) with {
                    fontSize = 10
                    width = 200
//            alignLeft()  // right, left, center
//
//            bgColor = Color.orange
//            bindTo("falta var")
                    bindToModel(thisWindow.modelObject.selectedSerie!!, "poster")
                }
            }
        }

        Panel(p0)with {
            asHorizontal()
            Panel(it) with {
                Label(it) with {
                    text = "Desciption:"
                    alignLeft()
                }

                KeyWordTextArea(it) with {
                    this.isMultiLine = true
                    height = 100
                    width = 208
//                    this.selectFinalLine()
//            bgColor = Color.orange
//            bindTo("falta var")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
                    bindToModel(thisWindow.modelObject.selectedSerie!!, "descripcion")
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = ""
                    setHeight(13)
                }

                Panel(it) with {
                    asHorizontal()
                    Label(it) with {
                        text = "Enabled"
                        setWidth(55)
//                    alignLeft()
                    }

                    CheckBox(it) with {
                        //            bindTo("selected")
//            bindEnabledTo("disabled")

                        //OJO aca hay q ver que tipo de dato guardamos en "state" del SerieAppModel y probablemente necesitemos un Transformer (de arena)
//                    bindToModel(thisWindow.modelObject.selectedSerie!!, "state")
                    }

                }
            }
        }



        Panel(p0) with {
            Label(it) with {
                text = "Categories:"
                alignLeft()
            }

            KeyWordTextArea(it) with {
                height = 100
                width = 50
//            bgColor = Color.orange
//            bindTo("falta var")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
            }
        }


        Panel(p0) with {
            Label(it) with {
                text = "Related content:"
                alignLeft()
            }

            KeyWordTextArea(it) with {
                height = 100
                width = 50
//            bgColor = Color.orange
//            bindTo("falta var")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
            }
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                text = "Accept"
                fontSize = 10
                onClick {
                    thisWindow.close()
                }
            }

            Button(it) with {
                text = "Cancel"
                fontSize = 10
                onClick {
                    //setear los valores que tenia antes de editarse
                    // para eso hay q hacer un SerieAppModel temporal
                    thisWindow.close()
                }
            }
        }


    }

}