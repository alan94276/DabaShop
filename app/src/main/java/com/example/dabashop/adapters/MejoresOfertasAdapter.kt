package com.example.dabashop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.dabashop.data.Product
import com.example.dabashop.databinding.BestDealsRvItemBinding

class MejoresOfertasAdapter: RecyclerView.Adapter<MejoresOfertasAdapter.MejoresOfertasViewHolder>() {

    inner class MejoresOfertasViewHolder(private val binding: BestDealsRvItemBinding): ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgBestDeal)
                product.offerPercentage?.let {
                    val remainingPricePercentage = 1f -it
                    val priceAffterOffer = remainingPricePercentage * product.price
                    tvNewPrice.text ="$ ${String.format("%.2f",priceAffterOffer)}"
                }
                tvOldPrice.text = "$ ${String.format("%.2f",product.price)}"
                tvDealProductName.text = product.name
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MejoresOfertasViewHolder {
        return MejoresOfertasViewHolder(
            BestDealsRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MejoresOfertasViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }


}