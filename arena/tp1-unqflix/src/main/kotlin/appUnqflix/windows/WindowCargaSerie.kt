package appUnqflix.windows

import appUnqflix.appModel.CategoryAppModel
import appUnqflix.appModel.ContentAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import domain.Unavailable
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class WindowCargaSerie (owner: WindowOwner, model: SerieAppModel) : SimpleWindow<SerieAppModel>(owner, model){

    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(p0: Panel?) {

        this.title = "Create new serie"

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
                    text = "Description:"
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
                Panel(it) with {
                    Label(it) with {
                        text = "Categories selected"
                        fontSize = 8
                    }

                    //listaIzq
                    List<CategoryAppModel>(it) with {
                        bindItemsTo("categories").adaptWithProp<CategoryAppModel>("name")
                        bindSelectedTo("ownCategorySelected")
//                    bindBackgroundTo("color")
                        setHeight(100)
                        setWidth(110)
                    }
                }

                Panel(it) with {
                    Label(it) with {
                        text = ""
                        setHeight(14)
                    }

                    Panel(it) with {
                        //botonArriba
                        Button(it) with {
                            caption = "<"
                            fontSize = 10
                            bindEnabledTo("hasOtherCategorySelection")
//                        width =70
                            onClick {
                                thisWindow.modelObject.setNewCategory()
                            }
                        }

                        //botonAbajo
                        Button(it) with {
                            caption = ">"
                            fontSize = 10
                            bindEnabledTo("hasOwnCategorySelection")
//                        width =70
                            onClick {
                                thisWindow.modelObject.removeCategory()
                            }
                        }
                    }
                }

                Panel(it) with {
                    Label(it) with {
                        text = "Categories to choose"
                        fontSize = 8
                    }

                    //listaDer
                    List<CategoryAppModel>(it) with {
                        bindItemsTo("otherCategories").adaptWithProp<CategoryAppModel>("name")
                        bindSelectedTo("otherCategorySelected")
                        bindVisibleTo("visible")
//                    bindBackgroundTo("color")
                        setHeight(100)
                        setWidth(110)
                    }
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
                Panel(it)with {
                    Label(it) with {
                        text = "Related selected"
                        fontSize = 8
                    }

                    //listaIzq
                    List<ContentAppModel>(it) with {
                        bindItemsTo("relatedContent").adaptWithProp<ContentAppModel>("title")
                        bindSelectedTo("ownContentSelected")
//                    bindBackgroundTo("color")
                        setHeight(100)
                        setWidth(110)
                    }
                }

                Panel(it) with {
                    Label(it) with {
                        text = ""
                        setHeight(14)
                    }

                    Panel(it) with {
                        //botonArriba
                        Button(it) with {
                            caption = "<"
                            fontSize = 10
                            bindEnabledTo("hasOtherContentSelection")
//                        width =70
                            onClick {
                                thisWindow.modelObject.setNewContent()
                            }
                        }

                        //botonAbajo
                        Button(it) with {
                            caption = ">"
                            fontSize = 10
                            bindEnabledTo("hasOwnContentSelection")
//                        width =70
                            onClick {
                                thisWindow.modelObject.removeContent()
                            }
                        }
                    }
                }

                Panel(it) with {
                    Label(it) with {
                        text = "Related to choose"
                        fontSize = 8
                    }

                    //listaDer
                    List<ContentAppModel>(it) with {
                        bindItemsTo("otherContents").adaptWithProp<ContentAppModel>("title")
                        bindSelectedTo("otherContentSelected")
                        bindVisibleTo("visible")
//                    bindBackgroundTo("color")
                        setHeight(100)
                        setWidth(250)
                    }
                }
            }
        }

        Panel(p0) with {
            asHorizontal()
            Button(it) with {
                text = "Accept"
                fontSize = 10
                onClick {
                    thisWindow.modelObject.unqflixAppModel.createSerie(
                        thisWindow.modelObject.title,
                        thisWindow.modelObject.descripcion,
                        thisWindow.modelObject.poster,
                        Unavailable(),
                        thisWindow.modelObject.categories,
                        thisWindow.modelObject.relatedContent
                    )
                    thisWindow.close()
                }
            }

            Button(it) with {
                text = "Cancel"
                fontSize = 10
                onClick {
                    thisWindow.close()
                }
            }
        }
    }

}