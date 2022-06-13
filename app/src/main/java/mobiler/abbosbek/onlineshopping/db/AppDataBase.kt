package mobiler.abbosbek.onlineshopping.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mobiler.abbosbek.onlineshopping.model.CategoryModel
import mobiler.abbosbek.onlineshopping.model.ProductModel

@Database(entities = [CategoryModel::class,ProductModel::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getProductDao() : ProductDo
    abstract fun getCategoryDao() : CategoryDao

    companion object{
        var INSTANCE : AppDataBase ?= null

        fun  initDataBase(context: Context){
            if (INSTANCE == null){
                synchronized(AppDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"online_shop_db").build()
                }
            }
        }

        fun getDataBase() : AppDataBase{
            return INSTANCE!!
        }
    }
}