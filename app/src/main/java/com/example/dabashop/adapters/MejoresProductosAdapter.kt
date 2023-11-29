package com.example.dabashop.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dabashop.data.Product
import com.example.dabashop.databinding.BestDealsRvItemBinding
import com.example.dabashop.databinding.ProductRvItemBinding
import com.example.dabashop.helper.getProductPrice

class MejoresProductosAdapter: RecyclerView.Adapter<MejoresProductosAdapter.MejoresProductosViewHolder>(){

    inner class MejoresProductosViewHolder(private val binding: ProductRvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgProduct)
                    val priceAffterOffer = product.offerPercentage.getProductPrice(product.price)
                    tvNewPrice.text ="$ ${String.format("%.2f",priceAffterOffer)}"
                    tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                if (product.offerPercentage == null)
                    tvNewPrice.visibility = View.VISIBLE
                tvPrice.text = "$ ${String.format("%.2f",product.price)}"
                tvName.text = product.name
            }
        }
    }

    private  val diffCallBack =  object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MejoresProductosViewHolder {
        return MejoresProductosViewHolder(
            ProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MejoresProductosViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }
    }

    var onClick : ((Product) -> Unit)? = null

}