package mobiler.abbosbek.onlineshopping

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_make_order.*
import mobiler.abbosbek.onlineshopping.model.AddressModel
import mobiler.abbosbek.onlineshopping.model.ProductModel
import mobiler.abbosbek.onlineshopping.utils.Constants
import mobiler.abbosbek.onlineshopping.utils.LocaleManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MakeOrderActivity : AppCompatActivity() {

    var address : AddressModel ?= null
    lateinit var items : List<ProductModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)

        edtAddress.setOnClickListener{
            startActivity(Intent(this,MapsActivity::class.java))
        }

        items = intent.getSerializableExtra(Constants.EXTRA_DATA) as List<ProductModel>

        tvTotalAmount.setText(items.sumByDouble { it.cartCount.toDouble() * (it.price.replace(" ","").toDoubleOrNull() ?: 0.0) }.toString())

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }

    @SuppressLint("SetTextI18n")
    @Subscribe
    fun onEvent(address : AddressModel){
        this.address = address
        edtAddress.setText("${address.latitude}, ${address.longitude}")
    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}