package appUnqflix

import appUnqflix.appModel.UnqflixAppModel
import appUnqflix.windows.WindowSeason
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class ApplicationUnqflix : Application() {
    override fun createMainWindow(): Window<*> {
        return WindowSeason(this, UnqflixAppModel().unaSerie)
    }

}

fun main(){
    ApplicationUnqflix().start()
}

