package appUnqflix.appModel

import domain.Category
import org.uqbar.commons.model.annotations.Observable

@Observable
class CategoryAppModel (val category: Category) {
    var id = ""
    var name = ""

    init {
        this.id = category.id
        this.name = category.name
    }
}