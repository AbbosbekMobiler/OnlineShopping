package mobiler.abbosbek.onlineshopping.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mobiler.abbosbek.onlineshopping.model.ProductModel


@Dao
interface ProductDo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items : List<ProductModel>)

    @Query("select * from products")
    fun getAllProducts() : List<ProductModel>
}