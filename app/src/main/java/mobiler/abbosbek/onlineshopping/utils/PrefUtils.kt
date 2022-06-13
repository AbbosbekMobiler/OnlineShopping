package mobiler.abbosbek.onlineshopping.utils

import com.orhanobut.hawk.Hawk
import mobiler.abbosbek.onlineshopping.model.CartModel
import mobiler.abbosbek.onlineshopping.model.ProductModel

object PrefUtils {
    const val PREF_FAVORITE = "pref_favorite"
    const val PREF_CART = "pref_cart"

    fun setFavorite(item : ProductModel){
        val items = Hawk.get(PREF_FAVORITE, arrayListOf<Int>())

        if (items.filter { it == item.id}.firstOrNull() != null){
            items.remove(item.id)
        }else{
            items.add(item.id)
        }

        Hawk.put(PREF_FAVORITE,items)
    }

    fun getFavoriteList() : ArrayList<Int>{
        return Hawk.get(PREF_FAVORITE, arrayListOf<Int>())
    }

    fun checkFavorite(item : ProductModel) :Boolean{
        val items = Hawk.get(PREF_FAVORITE, arrayListOf<Int>())

        return items.filter { it == item.id }.firstOrNull() != null
    }

    fun setCart(item: ProductModel){
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf<CartModel>())
        var cart = items.filter { it.productId == item.id }.firstOrNull()

        if (cart != null){
            if (cart.count > 0){
                cart.count = item.cartCount
            }else{
                items.remove(cart)
            }
        }else{
            val newwCart = CartModel(item.id,item.cartCount)
            items.add(newwCart)
        }

        Hawk.put(PREF_CART,items)
    }

    fun getCartList() : ArrayList<CartModel>{
        return Hawk.get(PREF_CART, arrayListOf<CartModel>())
    }

    fun getCartCount(item: ProductModel) : Int{
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf<CartModel>())
        return items.filter { it.productId == item.id }.firstOrNull()?.count ?: 0
    }

}