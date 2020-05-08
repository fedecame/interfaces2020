package appUnqflix.appModel.transformers

import domain.Available
import domain.ContentState
import domain.Unavailable
import org.apache.commons.collections15.Transformer
import org.uqbar.arena.bindings.ValueTransformer

class StateToStringTransformer : ValueTransformer<ContentState, String>, Transformer<Any, String> {

    override fun getModelType() = ContentState::class.java
    override fun getViewType() = String::class.java

    override fun modelToView(valueFromModel: ContentState): String {
        if (valueFromModel is Available) {
            return "OK"
        } else {
            return "X"
        }
    }

    override fun viewToModel(valueFromView: String): ContentState {
        if (valueFromView == "OK") {
            return Available()
        } else {
            return Unavailable()
        }
    }

    override fun transform(p0: Any?): String {
        if (p0 is Available) {
            return "OK"
        } else {
            return "X"
        }
    }
}