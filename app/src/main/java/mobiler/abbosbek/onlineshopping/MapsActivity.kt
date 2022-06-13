package mobiler.abbosbek.onlineshopping

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import mobiler.abbosbek.onlineshopping.databinding.ActivityMapsBinding
import mobiler.abbosbek.onlineshopping.model.AddressModel
import mobiler.abbosbek.onlineshopping.utils.LocaleManager
import org.greenrobot.eventbus.EventBus

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        btnConfirm.setOnClickListener {
            val addressModel = AddressModel("",mMap.cameraPosition.target.latitude,mMap.cameraPosition.target.longitude)
            EventBus.getDefault().post(addressModel)
            finish()
        }

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}