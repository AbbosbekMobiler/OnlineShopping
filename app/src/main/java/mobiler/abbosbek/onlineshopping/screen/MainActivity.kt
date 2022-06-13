package mobiler.abbosbek.onlineshopping.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.activity_main.*
import mobiler.abbosbek.onlineshopping.R
import mobiler.abbosbek.onlineshopping.db.AppDataBase
import mobiler.abbosbek.onlineshopping.screen.CartScreen.CartFragment
import mobiler.abbosbek.onlineshopping.screen.FavoriteScreen.FavoriteFragment
import mobiler.abbosbek.onlineshopping.screen.HomeScreen.HomeFragment
import mobiler.abbosbek.onlineshopping.screen.ProfileScreen.ProfileFragment
import mobiler.abbosbek.onlineshopping.screen.changeLanguage.ChangLangFragment
import mobiler.abbosbek.onlineshopping.utils.LocaleManager

class MainActivity : AppCompatActivity() {

    val homeFragment = HomeFragment.newInstance()
    val favoriteFragment = FavoriteFragment.newInstance()
    val cartFragment = CartFragment.newInstance()
    val profileFragment = ProfileFragment.newInstance()

    var activFragment : Fragment = homeFragment

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.productData.observe(this, Observer {
            viewModel.insertAllProducts2DB(it)
            homeFragment.loadData()
        })

        viewModel.error.observe(this,Observer{
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })



        supportFragmentManager.beginTransaction().add(R.id.flContainer,homeFragment,homeFragment.tag).hide(homeFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer,favoriteFragment,favoriteFragment.tag).hide(favoriteFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer,cartFragment,cartFragment.tag).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer,profileFragment,profileFragment.tag).hide(profileFragment).commit()

        supportFragmentManager.beginTransaction().show(activFragment).commit()

        bottom_nav.setOnItemSelectedListener {
            if(it.itemId==R.id.ActionHome){
                supportFragmentManager.beginTransaction().hide(activFragment).show(homeFragment).commit()
                activFragment = homeFragment
            }else if (it.itemId == R.id.ActionFavorite){
                supportFragmentManager.beginTransaction().hide(activFragment).show(favoriteFragment).commit()
                activFragment = favoriteFragment
            }else if (it.itemId == R.id.ActionCart){
                supportFragmentManager.beginTransaction().hide(activFragment).show(cartFragment).commit()
                activFragment = cartFragment
            }else if (it.itemId == R.id.ActionProfile){
                supportFragmentManager.beginTransaction().hide(activFragment).show(profileFragment).commit()
                activFragment = profileFragment
            }

            true
        }

        // bottom sheet
        btnMenu.setOnClickListener{
            val fragment = ChangLangFragment.newInstance()

            fragment.show(supportFragmentManager,fragment.tag)

        }

        loadData()

    }
    fun loadData(){
        viewModel.getTopProducts()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}