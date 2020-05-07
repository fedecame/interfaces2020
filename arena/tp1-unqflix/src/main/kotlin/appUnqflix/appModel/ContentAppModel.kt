package appUnqflix.appModel

import domain.Content
import domain.ContentState
import org.uqbar.commons.model.annotations.Observable

@Observable
class ContentAppModel (val content: Content) {
    var id: String
    var description: String
    var poster: String
    var title: String
    var state: ContentState
    var relatedContent = mutableListOf<Content>()

    init {
        this.id = content.id
        this.description = content.description
        this.poster = content.poster
        this.title = content.title
        this.state = content.state

        this.relatedContent = content.relatedContent
//        this.initRelatedContent()
    }

    fun initRelatedContent() {
//        relatedContent = content.relatedContent.map { ContentAppModel(it) }.toMutableList()
    }

}