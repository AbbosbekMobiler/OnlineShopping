package mobiler.abbosbek.onlineshopping.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mobiler.abbosbek.onlineshopping.db.AppDataBase
import mobiler.abbosbek.onlineshopping.model.CategoryModel
import mobiler.abbosbek.onlineshopping.model.OfferModel
import mobiler.abbosbek.onlineshopping.model.ProductModel
import mobiler.abbosbek.onlineshopping.repository.ShopRepository

class MainViewModel : ViewModel() {

    val repository = ShopRepository()
    val error = MutableLiveData<String>()
    val offersData = MutableLiveData<List<OfferModel>>()
    val categoryData = MutableLiveData<List<CategoryModel>>()
    val productData = MutableLiveData<List<ProductModel>>()
    val progress = MutableLiveData<Boolean>()

    fun getOffers(){
        repository.getOffers(error,offersData)
    }

    fun getCategories(){
        repository.getCategories(error,categoryData)
    }
    fun getTopProducts(){
        repository.getTopProducts(error,productData)
    }

    fun getProductsByCategory(id : Int){
        repository.getProductsByCategory(id,error, productData)
    }
    fun getProductsByIds(ids : List<Int>){
        repository.getProductsByIds(ids,error,progress, productData)
    }


    fun insertAllProducts2DB(items : List<ProductModel>){
        CoroutineScope(Dispatchers.IO).launch {
            AppDataBase.getDataBase().getProductDao().insertAll(items)
            CoroutineScope(Dispatchers.Main).launch {
                error.value = "Ma'lumotlar bazaga yuklandi!"
            }
        }
    }

    fun getAllDBProducts(){
        CoroutineScope(Dispatchers.Main).launch {
            productData.value = withContext(Dispatchers.IO){
                AppDataBase.getDataBase().getProductDao().getAllProducts()
            }
        }
    }

}