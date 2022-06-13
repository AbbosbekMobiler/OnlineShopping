package mobiler.abbosbek.onlineshopping

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.orhanobut.hawk.Hawk
import mobiler.abbosbek.onlineshopping.db.AppDataBase

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        Hawk.init(this).build()

        AppDataBase.initDataBase(this)
    }


}