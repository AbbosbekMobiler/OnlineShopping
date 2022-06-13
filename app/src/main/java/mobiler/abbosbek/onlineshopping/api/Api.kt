package mobiler.abbosbek.onlineshopping.api

import io.reactivex.Observable
import mobiler.abbosbek.onlineshopping.model.BaseResponse
import mobiler.abbosbek.onlineshopping.model.CategoryModel
import mobiler.abbosbek.onlineshopping.model.OfferModel
import mobiler.abbosbek.onlineshopping.model.ProductModel
import mobiler.abbosbek.onlineshopping.model.request.GetProductsByIdRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("get_offers")
    fun getOffers() : Observable<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories() : Observable<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts() : Observable<BaseResponse<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id" ) categoryId : Int) : Observable<BaseResponse<List<ProductModel>>>


    @POST("get_products_by_ids")
    fun getTopProductsByIds(@Body request: GetProductsByIdRequest) : Observable<BaseResponse<List<ProductModel>>>
}