package appUnqflix.appModel.transformers

import domain.Available
import domain.ContentState
import domain.Unavailable
import org.uqbar.arena.bindings.ValueTransformer

class StateTransformer : ValueTransformer<ContentState, Boolean> {

    override fun getModelType() = ContentState::class.java
    override fun getViewType() = Boolean::class.java

    override fun modelToView(valueFromModel: ContentState): Boolean {
        return valueFromModel is Available
    }

    override fun viewToModel(valueFromView: Boolean): ContentState {
        if (valueFromView) {
            return Available()
        } else {
            return Unavailable()
        }
    }
}