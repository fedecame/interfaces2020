package appUnqflix.windows

import appUnqflix.appModel.CategoryAppModel
import appUnqflix.appModel.ContentAppModel
import appUnqflix.appModel.SerieAppModel
import appUnqflix.appModel.UnqflixAppModel
import appUnqflix.appModel.transformers.StateTransformer
import domain.Content
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class WindowModifSerie (owner: WindowOwner, model: SerieAppModel) : SimpleWindow<SerieAppModel>(owner, model){

    override fun addActions(p0: Panel?) {

    }

    private fun mirrorSelectedSerieValues() {
        modelObject.title = modelObject.unqflixAppModel.selectedSerie!!.title
        modelObject.descripcion = modelObject.unqflixAppModel.selectedSerie!!.descripcion
        modelObject.poster = modelObject.unqflixAppModel.selectedSerie!!.poster
        modelObject.state = modelObject.unqflixAppModel.selectedSerie!!.state

        modelObject.categories.addAll(modelObject.unqflixAppModel.selectedSerie!!.categories)
        modelObject.relatedContent.addAll(modelObject.unqflixAppModel.selectedSerie!!.relatedContent)
        modelObject.otherCategories.removeAll { modelObject.idEstaContenidoEnCategorias(it.id) }
        modelObject.otherContents.removeAll { modelObject.idEstaContenidoEnContent(it.id) }

        modelObject.ownCategorySelected = null
        modelObject.otherCategorySelected = null
        modelObject.ownContentSelected = null
        modelObject.otherContentSelected = null
    }

    private fun updateSelectedSerieValues() {
        modelObject.unqflixAppModel.selectedSerie!!.title = modelObject.title
        modelObject.unqflixAppModel.selectedSerie!!.descripcion = modelObject.descripcion
        modelObject.unqflixAppModel.selectedSerie!!.poster = modelObject.poster
        modelObject.unqflixAppModel.selectedSerie!!.state = modelObject.state

        modelObject.unqflixAppModel.selectedSerie!!.categories = modelObject.categories
        modelObject.unqflixAppModel.selectedSerie!!.otherCategories = modelObject.otherCategories
        modelObject.unqflixAppModel.selectedSerie!!.relatedContent = modelObject.relatedContent
        modelObject.unqflixAppModel.selectedSerie!!.otherContents = modelObject.otherContents
    }

    override fun createFormPanel(p0: Panel?) {
        this.mirrorSelectedSerieValues()

        this.title = "Modify serie: ${modelObject.unqflixAppModel.selectedSerie!!.title}"

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
                    text = "Description:"
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
                        bindTo("state").setTransformer(StateTransformer())
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
                            onClick {
                                thisWindow.modelObject.setNewCategory()
                            }
                        }

                        //botonAbajo
                        Button(it) with {
                            caption = ">"
                            fontSize = 10
                            bindEnabledTo("hasOwnCategorySelection")
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
                Panel(it) with {
                    Label(it) with {
                        text = "Content selected"
                        fontSize = 8
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
                            bindEnabledTo("hasOtherContentSelection")
                            onClick {
                                thisWindow.modelObject.setNewContent()
                            }
                        }

                        //botonAbajo
                        Button(it) with {
                            caption = ">"
                            fontSize = 10
                            bindEnabledTo("hasOwnContentSelection")
                            onClick {
                                thisWindow.modelObject.removeContent()
                            }
                        }
                    }
                }

                Panel(it) with {
                    Label(it) with {
                        text = "Content to choose"
                        fontSize = 8
                    }

                    //listaDer
                    List<ContentAppModel>(it) with {
                        bindItemsTo("otherContents").adaptWithProp<ContentAppModel>("title")
                        bindSelectedTo("otherContentSelected")
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
                    thisWindow.updateSelectedSerieValues()
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