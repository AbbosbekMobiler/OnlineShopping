package mobiler.abbosbek.onlineshopping.screen.FavoriteScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite.*
import mobiler.abbosbek.onlineshopping.R
import mobiler.abbosbek.onlineshopping.screen.MainViewModel
import mobiler.abbosbek.onlineshopping.utils.PrefUtils
import mobiler.abbosbek.onlineshopping.view.ProductAdapter

class FavoriteFragment : Fragment() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.productData.observe(this,Observer{
            recyclerProducts.adapter = ProductAdapter(it)
        })
        viewModel.progress.observe(this,Observer{
            swipe.isRefreshing = it
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerProducts.layoutManager = LinearLayoutManager(requireActivity())

        swipe.setOnRefreshListener {
            loadData()
        }
        loadData()
    }

    fun loadData(){
        viewModel.getProductsByIds(PrefUtils.getFavoriteList())
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}