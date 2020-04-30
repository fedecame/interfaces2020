package appUnqflix.windows

import appUnqflix.appModel.SeasonAppModel

import appUnqflix.appModel.SerieAppModel
import org.uqbar.arena.widgets.Panel

import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner




//class WindowsDePrueba(owner: WindowOwner, model: SerieAppModel) : SimpleWindow<SerieAppModel>(owner,model) {

class WindowsDePrueba(owner: WindowOwner, model: SeasonAppModel) : SimpleWindow<SeasonAppModel>(owner,model) {

    override fun createFormPanel(p0: Panel) {
        title = "estaEsUnaVentanaDePrueba"
    }

    override fun addActions(p0: Panel?) {

    }
}