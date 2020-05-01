package appUnqflix.windows

import appUnqflix.appModel.UnqflixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class WindowCargaSerie (owner: WindowOwner, model: UnqflixAppModel) : SimpleWindow<UnqflixAppModel>(owner, model){

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
                    height = 100
                    width = 150
//            bgColor = Color.orange
//            bindTo("falta var")
//            bindColorTo("blue")
//            bindEnabledTo("enabled")
                }
            }

            Panel(it) with {
                asHorizontal()
                Label(it) with {
                    text = "State"
                    alignLeft()
                }

                CheckBox(it) with {
                    //            bindTo("selected")
//            bindEnabledTo("disabled")

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
            }

            Button(it) with {
                text = "Cancel"
                fontSize = 10
            }
        }


    }

}