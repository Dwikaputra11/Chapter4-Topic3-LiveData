package com.example.chapter4_topic3_livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter4_topic3_livedata.R
import com.example.chapter4_topic3_livedata.data.Product
import com.example.chapter4_topic3_livedata.databinding.HighlightItemBinding

class HighlightAdapter(private var highlightList: ArrayList<Product>):
    RecyclerView.Adapter<HighlightAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var listener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(id: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    inner class ViewHolder(binding: HighlightItemBinding): RecyclerView.ViewHolder(binding.root) {
        var ivHighlight = binding.ivHighlight
        var tvNameHighlight = binding.tvNameHighlight
        var tvPriceHighlight = binding.tvPriceHighlight

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(highlightList[adapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HighlightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams =
            ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(highlightList[position].img)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(holder.ivHighlight)
        holder.tvNameHighlight.text = highlightList[position].name
        holder.tvPriceHighlight.text = highlightList[position].price.toString()
    }

    override fun getItemCount(): Int {
        return highlightList.size
    }

    fun setHighlightList(list: ArrayList<Product>){
        highlightList = list
    }
}