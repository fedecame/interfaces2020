package appUnqflix.windows

import appUnqflix.appModel.CategoryAppModel
import appUnqflix.appModel.ContentAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import domain.Content
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class WindowModifSerie (owner: WindowOwner, model: SerieAppModel) : SimpleWindow<SerieAppModel>(owner, model){

    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(p0: Panel?) {
        this.title = "Modify serie: ${modelObject.title}"
        // Preguntar si tiene sentido delegar lo siguiente al appModel/viewModel
        val tempSerie = SerieAppModel(modelObject.serie, modelObject.unqflixAppModel)

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
                    bindToModel(thisWindow.modelObject, "title")
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
                    bindToModel(thisWindow.modelObject, "poster")
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
                    bindToModel(thisWindow.modelObject, "descripcion")
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

            Panel(it) with {
                asHorizontal()
                //listaIzq
                List<CategoryAppModel>(it) with {
                    bindItemsTo("categories").adaptWithProp<CategoryAppModel>("name")
                    bindSelectedTo("ownCategorySelected")
//                    bindBackgroundTo("color")
                    setHeight(300)
                    setWidth(110)
                }

                Panel(it) with {
                    //botonArriba
                    Button(it) with {
                        caption = "<"
                        fontSize = 10
//                        width =70
                        onClick {
                            thisWindow.modelObject.setNewCategory()
                        }
                    }

                    //botonAbajo
                    Button(it) with {
                        caption = ">"
                        fontSize = 10
//                        width =70
                        onClick {
                            thisWindow.modelObject.removeCategory()
                        }
                    }

                }

                //listaDer
                List<CategoryAppModel>(it) with {
                    bindItemsTo("otherCategories").adaptWithProp<CategoryAppModel>("name")
                    bindSelectedTo("otherCategorySelected")
//                    bindBackgroundTo("color")
                    setHeight(300)
                    setWidth(110)
                }
            }
        }


        Panel(p0) with {
            Label(it) with {
                text = "Related Content:"
                alignLeft()
            }

            Panel(it) with {
                asHorizontal()
                //listaIzq
                List<ContentAppModel>(it) with {
                    bindItemsTo("relatedContent").adaptWithProp<ContentAppModel>("title")
                    bindSelectedTo("ownContentSelected")
//                    bindBackgroundTo("color")
                    setHeight(300)
                    setWidth(110)
                }

                Panel(it) with {
                    //botonArriba
                    Button(it) with {
                        caption = "<"
                        fontSize = 10
//                        width =70
                        onClick {
                            thisWindow.modelObject.setNewContent()
                        }
                    }

                    //botonAbajo
                    Button(it) with {
                        caption = ">"
                        fontSize = 10
//                        width =70
                        onClick {
                            thisWindow.modelObject.removeContent()
                        }
                    }

                }

                //listaDer
                List<ContentAppModel>(it) with {
                    bindItemsTo("otherContents").adaptWithProp<ContentAppModel>("title")
                    bindSelectedTo("otherContentSelected")
//                    bindBackgroundTo("color")
                    setHeight(300)
                    setWidth(250)
                }
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
                    thisWindow.modelObject.title = tempSerie.title
                    thisWindow.modelObject.descripcion = tempSerie.descripcion
                    thisWindow.modelObject.poster = tempSerie.poster
                    thisWindow.modelObject.state = tempSerie.state
                    thisWindow.modelObject.categories = tempSerie.categories
                    thisWindow.modelObject.myseasons = tempSerie.myseasons
                    thisWindow.modelObject.relatedContent = tempSerie.relatedContent

                    thisWindow.modelObject.otherCategories = tempSerie.otherCategories
                    thisWindow.modelObject.ownCategorySelected = tempSerie.ownCategorySelected
                    thisWindow.modelObject.otherCategorySelected = tempSerie.otherCategorySelected
                    thisWindow.modelObject.otherContents = tempSerie.otherContents
                    thisWindow.modelObject.ownContentSelected = tempSerie.ownContentSelected
                    thisWindow.modelObject.otherContentSelected = tempSerie.otherContentSelected

                    thisWindow.close()
                }
            }
        }


    }

}