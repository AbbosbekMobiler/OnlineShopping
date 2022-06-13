package mobiler.abbosbek.onlineshopping.model

import java.io.Serializable

data class CartModel(
    val productId : Int,
    var count : Int
): Serializable
