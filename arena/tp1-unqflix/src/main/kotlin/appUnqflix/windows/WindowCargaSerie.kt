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

                    }

                    CheckBox(it) with {

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
                        fontSize = 7
                    }
                    //listaIzq
                    List<CategoryAppModel>(it) with {
                        bindItemsTo("categories").adaptWithProp<CategoryAppModel>("name")
                        bindSelectedTo("ownCategorySelected")

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

                            onClick {
                                thisWindow.modelObject.setNewCategory()
                            }
                        }

                        //botonAbajo
                        Button(it) with {
                            caption = ">"
                            fontSize = 10

                            onClick {
                                thisWindow.modelObject.removeCategory()
                            }
                        }
                    }
                }


                Panel(it) with {
                    Label(it) with {
                        text = "Categories to choose"
                        fontSize = 7
                    }
                    //listaDer
                    List<CategoryAppModel>(it) with {
                        bindItemsTo("otherCategories").adaptWithProp<CategoryAppModel>("name")
                        bindSelectedTo("otherCategorySelected")

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
                        fontSize = 7
                    }

                    //listaIzq
                    List<ContentAppModel>(it) with {
                        bindItemsTo("relatedContent").adaptWithProp<ContentAppModel>("title")
                        bindSelectedTo("ownContentSelected")

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

                            onClick {
                                thisWindow.modelObject.setNewContent()
                            }
                        }

                        //botonAbajo
                        Button(it) with {
                            caption = ">"
                            fontSize = 10

                            onClick {
                                thisWindow.modelObject.removeContent()
                            }
                        }

                    }
                }

                Panel(it) with {
                    Label(it) with {
                        text = "Related to choose"
                        fontSize = 7
                    }
                    //listaDer
                    List<ContentAppModel>(it) with {
                        bindItemsTo("otherContents").adaptWithProp<ContentAppModel>("title")
                        bindSelectedTo("otherContentSelected")
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