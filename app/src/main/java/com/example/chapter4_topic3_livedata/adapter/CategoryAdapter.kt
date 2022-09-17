package com.example.chapter4_topic3_livedata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4_topic3_livedata.databinding.CategoryFilterBinding

class CategoryAdapter(private var categoryList: ArrayList<String>): RecyclerView.Adapter<CategoryAdapter.ViewModel>() {
    private lateinit var listener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(category: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    inner class ViewModel(binding: CategoryFilterBinding):RecyclerView.ViewHolder(binding.root) {
        var tvCategory = binding.tvCategory

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(categoryList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val binding = CategoryFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewModel(binding)
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.tvCategory.text = categoryList[position]
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}