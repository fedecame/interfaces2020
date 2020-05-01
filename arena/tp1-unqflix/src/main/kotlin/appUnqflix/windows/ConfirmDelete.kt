package appUnqflix.windows


import appUnqflix.appModel.UnqflixAppModel
import org.uqbar.arena.kotlin.extensions.asHorizontal
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class ConfirmDelete(owner: WindowOwner , model: UnqflixAppModel): Dialog<UnqflixAppModel>(owner,model) {

    override fun createFormPanel(mainPanel: Panel) {
        title = "Confirm Delete"

        Label(mainPanel) with {
            // var nombre:String = modelObject.selectedSerie!!.title
            text = "Confirm Delete of"//+ nombre
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                text = "Accept"
                onClick { accept() }
            }
            Button(it) with {
                text = "Cancel"
                onClick { cancel() }
            }

        }
    }
}