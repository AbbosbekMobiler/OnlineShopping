package mobiler.abbosbek.onlineshopping.screen.HomeScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_home.*
import mobiler.abbosbek.onlineshopping.R
import mobiler.abbosbek.onlineshopping.model.CategoryModel
import mobiler.abbosbek.onlineshopping.model.OfferModel
import mobiler.abbosbek.onlineshopping.screen.MainViewModel
import mobiler.abbosbek.onlineshopping.view.CategoryAdapter
import mobiler.abbosbek.onlineshopping.view.CategoryAdapterCallback
import mobiler.abbosbek.onlineshopping.view.ProductAdapter

class HomeFragment : Fragment() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerCategory.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        recyclerProduct.layoutManager = LinearLayoutManager(requireActivity())

        viewModel.error.observe(requireActivity(),Observer{
            Toast.makeText(requireActivity(),it,Toast.LENGTH_SHORT).show()
        })
        viewModel.offersData.observe(requireActivity(), Observer {
            Glide.with(imageCarousel).load("http://osonsavdo.devapp.uz/images/${it[1].image}").into(imageCarousel)
        })


        viewModel.error.observe(requireActivity(),Observer{
            Toast.makeText(requireActivity(),it,Toast.LENGTH_SHORT).show()
        })

        viewModel.categoryData.observe(requireActivity(),Observer{
            recyclerCategory.adapter = CategoryAdapter(it,object : CategoryAdapterCallback{
                override fun onClickItem(item: CategoryModel) {
                    viewModel.getProductsByCategory(item.id)
                }
            })
        })

        viewModel.productData.observe(requireActivity(),Observer{
            recyclerProduct.adapter = ProductAdapter(it)
        })

        loadData()
    }

    fun loadData(){
        viewModel.getOffers()
        viewModel.getCategories()
        viewModel.getAllDBProducts()
//        viewModel.getTopProducts()
    }
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}