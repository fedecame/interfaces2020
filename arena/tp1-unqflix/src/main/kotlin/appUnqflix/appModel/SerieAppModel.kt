package appUnqflix.appModel

import data.idGenerator
import domain.*

import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import scala.util.control.TailCalls

@Observable
class SerieAppModel(val serie: Serie? = null, val unqflixAppModel: UnqflixAppModel) {
    var id: String
    var title: String
    var descripcion: String
    var poster: String
    var state: ContentState // esto tmb se podria representar con un AppModel..preguntar si se justifica.
    var categories = mutableListOf<CategoryAppModel>()
    var myseasons = mutableListOf<SeasonAppModel>()
    var relatedContent = mutableListOf<ContentAppModel>()
//    var relatedContent = mutableListOf<Content>() // preguntar si se justifica crear un ContentAppModel (similar a CategoryAppModel)

    var selected : SeasonAppModel? = null
  
    var otherCategories = mutableListOf<CategoryAppModel>()
    var ownCategorySelected: CategoryAppModel? = null
    var otherCategorySelected: CategoryAppModel? = null

    var otherContents = mutableListOf<ContentAppModel>()
    var ownContentSelected: ContentAppModel? = null
    var otherContentSelected: ContentAppModel? = null

    init {
//        this.id = serie.id
//        this.title = serie.title
//        this.descripcion = serie.description
//        this.poster = serie.poster
//        this.state = serie.state
      
        this.id = serie?.id ?: ""
        this.title = serie?.title ?: ""
        this.descripcion = serie?.description ?: ""
        this.poster = serie?.poster ?: ""
        this.state = serie?.state ?: Unavailable()

//        this.relatedContent = serie.relatedContent
//        this.relatedContent = serie?.relatedContent ?: mutableListOf<Content>()
        this.initContents()
        this.initOtherContents()
        this.initCategories()
        this.initOtherCategories()
        this.initSeasons()



    }

    fun initContents() {
        this.relatedContent = serie?.relatedContent?.map { ContentAppModel(it) }?.toMutableList() ?: mutableListOf<ContentAppModel>()
    }

    fun initSeasons() {
        this.myseasons = serie?.seasons?.map {SeasonAppModel(it, unqflixAppModel, this) }?.toMutableList() ?: mutableListOf<SeasonAppModel>()
//        this.myseasons = serie.seasons.map { SeasonAppModel(it, unqflixAppModel, this) }.toMutableList()
    }

    fun initCategories() {
        this.categories = serie?.categories?.map { CategoryAppModel(it) }?.toMutableList() ?: mutableListOf<CategoryAppModel>()
//        this.categories = serie!!.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    fun agregarSeason(title : String, description : String, poster : String): SeasonAppModel {
        val seasonAppModel = unqflixAppModel.createSeason(this, title, description, poster)
        myseasons.add(seasonAppModel)
        return seasonAppModel
    }

    fun adaptar():String{
        if(serie!!.state is Available){
            return "OK"
        }else{
            return "X"
        }
    }

    fun setNewCategory() {
        categories.add(this.otherCategorySelected!!)
        otherCategories.remove(this.otherCategorySelected!!)
    }

    fun removeCategory() {
        otherCategories.add(this.ownCategorySelected!!)
        categories.remove(this.ownCategorySelected!!)
    }

    fun setNewContent() {
        relatedContent.add(this.otherContentSelected!!)
        otherContents.remove(this.otherContentSelected!!)
    }

    fun removeContent() {
        otherContents.add(this.ownContentSelected!!)
        relatedContent.remove(this.ownContentSelected!!)
    }

    fun idEstaContenidoEnCategorias(id: String): Boolean {
        var ids = this.categories.map { it.id }
        return ids.contains(id)
    }

    fun initOtherCategories() {
        otherCategories = unqflixAppModel.getAllCategories().filter{!this.idEstaContenidoEnCategorias(it.id)}.toMutableList()
    }

    fun initOtherContents() {
        otherContents = unqflixAppModel.getAllContents().map {ContentAppModel(it)}.filter { !this.idEstaContenidoEnContent(it.id) }.toMutableList()
    }

    fun idEstaContenidoEnContent(id: String): Boolean {
        var ids = this.relatedContent.map { it.id }
        return ids.contains(id)
    }
    fun isEmptyCategory():Boolean = this.otherCategorySelected != null
}