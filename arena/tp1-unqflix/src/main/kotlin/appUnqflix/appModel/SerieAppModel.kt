package appUnqflix.appModel

import data.idGenerator
import domain.*

import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import scala.util.control.TailCalls

@Observable
class SerieAppModel(val serie: Serie? = null, val unqflixAppModel: UnqflixAppModel) {
    var id: String
    var title: String = ""
        set(value) {
            field = value
            hasValidInputs = value.isNotEmpty()
        }
    var descripcion: String
    var poster: String
    var state: ContentState
    var categories = mutableListOf<CategoryAppModel>()
    var myseasons = mutableListOf<SeasonAppModel>()
    var relatedContent = mutableListOf<ContentAppModel>()

    var hasValidInputs: Boolean = false

    // fix de bug de arena, que al deshabilitar un boton se lleva puesto y hace invisible uno que esta en el panel padre
    val visible = true

    var selected : SeasonAppModel? = null
        set(value) {
            field = value
            this.hasSelected = value !== null
        }

    var hasSelected : Boolean = false
    // Categories
    var otherCategories = mutableListOf<CategoryAppModel>()
    var ownCategorySelected: CategoryAppModel? = null
        set(value) {
            field = value
            this.hasOwnCategorySelection = value !== null
        }
    var hasOwnCategorySelection: Boolean = false
    var otherCategorySelected: CategoryAppModel? = null
        set(value) {
            field = value
            this.hasOtherCategorySelection = value !== null
        }
    var hasOtherCategorySelection: Boolean = false

    // Contents
    var otherContents = mutableListOf<ContentAppModel>()
    var ownContentSelected: ContentAppModel? = null
        set(value) {
            field = value
            this.hasOwnContentSelection = value !== null
        }
    var hasOwnContentSelection: Boolean = false
    var otherContentSelected: ContentAppModel? = null
        set(value) {
            field = value
            this.hasOtherContentSelection = value !== null
        }
    var hasOtherContentSelection: Boolean = false

    init {
        this.id = serie?.id ?: ""
        this.title = serie?.title ?: ""
        this.descripcion = serie?.description ?: ""
        this.poster = serie?.poster ?: ""
        this.state = serie?.state ?: Unavailable()

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
    }

    fun initCategories() {
        this.categories = serie?.categories?.map { CategoryAppModel(it) }?.toMutableList() ?: mutableListOf<CategoryAppModel>()
    }

    fun agregarSeason(title : String, description : String, poster : String): SeasonAppModel {
        val seasonAppModel = unqflixAppModel.createSeason(this, title, description, poster)
        myseasons.add(seasonAppModel)
        return seasonAppModel
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
}