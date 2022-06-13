package mobiler.abbosbek.onlineshopping.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_item_layout.view.*
import mobiler.abbosbek.onlineshopping.ProductDetailActivity
import mobiler.abbosbek.onlineshopping.R
import mobiler.abbosbek.onlineshopping.model.ProductModel
import mobiler.abbosbek.onlineshopping.utils.Constants

class ProductAdapter(val items : List<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ItemHolder>() {
    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, ProductDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA,item)
            it.context.startActivity(intent)
        }
        Glide.with(holder.itemView.context).load(Constants.HOST_IMAGE+item.image).into(holder.itemView.imgProduct)
        holder.itemView.tvName.text = item.name
        holder.itemView.tvPrice.text = item.price
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}