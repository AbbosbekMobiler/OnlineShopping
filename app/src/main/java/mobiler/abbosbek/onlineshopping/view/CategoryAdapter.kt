package mobiler.abbosbek.onlineshopping.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_item_layout.view.*
import mobiler.abbosbek.onlineshopping.R
import mobiler.abbosbek.onlineshopping.model.CategoryModel

interface CategoryAdapterCallback{
    fun onClickItem(item : CategoryModel)
}

class CategoryAdapter(val items : List<CategoryModel>,val callBack: CategoryAdapterCallback) : RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {
    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout,parent,false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var item = items[position]

        holder.itemView.setOnClickListener{
            items.forEach {
                it.checked = false
            }

            item.checked = true

            callBack.onClickItem(item)

            notifyDataSetChanged()
        }

        holder.itemView.tvTitle.text = item.title

        if (item.checked){
            holder.itemView.tvCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.colorPrimary))
            holder.itemView.tvTitle.setTextColor(Color.WHITE)
        }else{
            holder.itemView.tvCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.gray))
            holder.itemView.tvTitle.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}
