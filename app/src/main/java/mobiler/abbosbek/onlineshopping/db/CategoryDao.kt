package mobiler.abbosbek.onlineshopping.db

import androidx.room.*
import mobiler.abbosbek.onlineshopping.model.CategoryModel

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items : List<CategoryModel>)

    @Query("select * from categories")
    fun getAllCategories() : List<CategoryModel>
}