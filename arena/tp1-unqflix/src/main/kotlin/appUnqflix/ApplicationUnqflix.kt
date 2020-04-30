package appUnqflix

import appUnqflix.appModel.UnqflixAppModel

import appUnqflix.windows.WindowPpal

import appUnqflix.windows.WindowCargaDeSeason
import appUnqflix.windows.WindowModificarSeason

import appUnqflix.windows.WindowSeason
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class ApplicationUnqflix : Application() {
    override fun createMainWindow(): Window<*> {

        return WindowPpal(this, UnqflixAppModel())

//        return WindowSeason(this, UnqflixAppModel().unaSerie)
//        return  WindowCargaDeSeason(this,UnqflixAppModel().unaSerie)
//        return WindowModificarSeason(this,UnqflixAppModel().unaSerie)

    }

}

fun main(){
    ApplicationUnqflix().start()
}

