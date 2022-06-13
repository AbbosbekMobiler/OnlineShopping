package mobiler.abbosbek.onlineshopping.screen.CartScreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import mobiler.abbosbek.onlineshopping.MakeOrderActivity
import mobiler.abbosbek.onlineshopping.R
import mobiler.abbosbek.onlineshopping.model.ProductModel
import mobiler.abbosbek.onlineshopping.screen.MainViewModel
import mobiler.abbosbek.onlineshopping.utils.Constants
import mobiler.abbosbek.onlineshopping.utils.PrefUtils
import mobiler.abbosbek.onlineshopping.view.CartAdapter
import java.io.Serializable

class CartFragment : Fragment() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.progress.observe(this,Observer{
            swipeCart.isRefreshing = it
        })

        viewModel.error.observe(this,Observer{
            Toast.makeText(requireActivity(),it,Toast.LENGTH_SHORT).show()
        })

        viewModel.productData.observe(this,Observer{

            recycler.adapter = CartAdapter(it)

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.layoutManager = LinearLayoutManager(requireActivity())
        swipeCart.setOnRefreshListener {
            loadData()
        }

        btnMakeOrder.setOnClickListener {

            val  intent = Intent(requireActivity(),MakeOrderActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA,(viewModel.productData.value ?: emptyList<ProductModel>()) as Serializable)
            startActivity(intent)
        }
        loadData()
    }

    fun loadData(){
        viewModel.getProductsByIds(PrefUtils.getCartList().map { it.productId })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}