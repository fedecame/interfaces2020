package appUnqflix.windows

import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color
import java.awt.SystemColor.text



class WindowModifSerie (owner: WindowOwner, model: UnqflixAppModel) : SimpleWindow<UnqflixAppModel>(owner, model) {

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
                 //   bindToModel(thisWindow.modelObject.selectedSerie!!, "title")
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
              //      bindToModel(thisWindow.modelObject.selectedSerie!!, "poster")
                }
            }
        }
        Label(p0) with {
            text = "Description:"
            alignLeft()
        }
        Panel(p0) with {
            asHorizontal()
            Panel(it) with {

                KeyWordTextArea(it) with {
                    height = 100
                    width = 150

                  //  bindToModel(thisWindow.modelObject.selectedSerie!!, "descripcion")
                }
            }

            Panel(it) with {
                asHorizontal()
                Label(it) with {
                    text = "State"
                    alignLeft()
                }
                CheckBox(it) with {
                    //


                }
            }

//////////Panel Categorias//////

            Panel(p0) with {
                Label(it) with {
                    text = "Categories:"
                    alignLeft()
                }
            }
            Panel(p0) with {
                asHorizontal()
                KeyWordTextArea(it) with {
                    height = 200
                    width = 70
                    bgColor = Color.orange
//            bindTo("falta var")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
                }
                Panel(it) with {
                    asVertical()
                    Button(it) with {
                        text = "<"
                        fontSize = 10
                    }
                    Button(it) with {
                        text = ">"
                        fontSize = 10
                    }
                }
                KeyWordTextArea(it) with {
                    width = 70
                    height = 200
//            bgColor = Color.orange
//            bindTo("falta var")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
                }


            }

///////////////Related Content
            Panel(p0) with {
                asHorizontal()
                Label(it) with {
                    text = "Related content:"
                    alignLeft()
                }
            }
            Panel(p0) with {
                asHorizontal()
                KeyWordTextArea(it) with {
                    height = 100
                    width = 180
                    height = 200
                    bgColor = Color.blue
//            bindTo("falta var")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
                }
                Panel(it) with {
                    asVertical()
                    Button(it) with {
                        text = "<"
                        fontSize = 10
                    }
                    Button(it) with {
                        text = ">"
                        fontSize = 10
                    }
                }
                KeyWordTextArea(it) with {
                    width = 180
                    height = 200
                    bgColor = Color.yellow
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
                }

                Button(it) with {
                    text = "Cancel"
                    fontSize = 10
                }
            }


        }

    }}