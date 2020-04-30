package appUnqflix.appModel

import domain.Category

class CategoryAppModel (val category: Category) {
    var id = ""
    var name = ""

    init {
        this.id = category.id
        this.name = category.name
    }
}