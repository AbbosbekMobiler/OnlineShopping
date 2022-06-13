package mobiler.abbosbek.onlineshopping

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import mobiler.abbosbek.onlineshopping.model.ProductModel
import mobiler.abbosbek.onlineshopping.utils.Constants
import mobiler.abbosbek.onlineshopping.utils.LocaleManager
import mobiler.abbosbek.onlineshopping.utils.PrefUtils

class ProductDetailActivity : AppCompatActivity() {

    lateinit var item : ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        cardViewBack.setOnClickListener{
            finish()
        }

        item = intent.getSerializableExtra(Constants.EXTRA_DATA) as ProductModel

        tvTitle.text = item.name
        tvProductName.text = item.name
        tvProductPrice.text = item.price

        if (PrefUtils.getCartCount(item)>0){
            btnAdd2Cart.visibility = View.GONE
        }

        if (PrefUtils.checkFavorite(item)){
            imgFavorite.setImageResource(R.drawable.ic_favorite)
        }else{
            imgFavorite.setImageResource(R.drawable.ic_love)
        }

        Glide.with(this).load(Constants.HOST_IMAGE + item.image).into(imgProduct)

        btnAdd2Cart.setOnClickListener {
                item.cartCount = 1
            PrefUtils.setCart(item)
            Toast.makeText(this,"Product added to cart!",Toast.LENGTH_SHORT).show()
            finish()
            }

        cardViewFavorite.setOnClickListener{
            PrefUtils.setFavorite(item)

            if (PrefUtils.checkFavorite(item)){
                imgFavorite.setImageResource(R.drawable.ic_favorite)
            }else{
                imgFavorite.setImageResource(R.drawable.ic_love)
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}