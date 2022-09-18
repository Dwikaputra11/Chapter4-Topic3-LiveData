package com.example.chapter4_topic3_livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter4_topic3_livedata.R
import com.example.chapter4_topic3_livedata.data.Product
import com.example.chapter4_topic3_livedata.databinding.ProductItemBinding

class ProductItemAdapter(private var productList: ArrayList<Product>):
    RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var listener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(id: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    inner class ViewHolder(binding: ProductItemBinding):RecyclerView.ViewHolder(binding.root) {
        var ivProduct = binding.ivProduct
        var tvName = binding.tvNameProduct
        var tvPrice = binding.tvProductPrice
        var btnFavorite = binding.btnFavorite
        init {
            binding.root.setOnClickListener {
                listener.onItemClick(productList[adapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(productList[position].img)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(holder.ivProduct)
        holder.tvName.text = productList[position].name
        holder.tvPrice.text = productList[position].price.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(list: ArrayList<Product>){
        productList = list
    }
}