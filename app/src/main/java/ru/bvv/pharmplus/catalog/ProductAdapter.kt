package ru.bvv.pharmplus.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bvv.pharmplus.R

class ProductAdapter(private val exampleList: List<ProductItem>,
                     private val listener: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val textViewProductName: TextView = itemView.findViewById(R.id.item_product_name)
        val textViewPrice: TextView = itemView.findViewById(R.id.item_price)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_product,
        parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.image)
        holder.textViewProductName.text = currentItem.textName
        holder.textViewPrice.text = currentItem.textPrice
    }

    override fun getItemCount() = exampleList.size
}