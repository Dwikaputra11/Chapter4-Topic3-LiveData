package com.example.chapter4_topic3_livedata.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.chapter4_topic3_livedata.R
import com.example.chapter4_topic3_livedata.adapter.CategoryAdapter
import com.example.chapter4_topic3_livedata.adapter.HighlightAdapter
import com.example.chapter4_topic3_livedata.adapter.ProductItemAdapter
import com.example.chapter4_topic3_livedata.data.Product
import com.example.chapter4_topic3_livedata.databinding.FragmentHomeBinding
import com.example.chapter4_topic3_livedata.viewmodel.ProductViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var productVM: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        productVM = ViewModelProvider(this)[ProductViewModel::class.java]
        setViews()
    }

    fun setViews(){
        val categoryList = arrayListOf<String>(
            "Male",
            "Women",
            "Summer",
            "Winter",
        )
        val highlightAdapter = HighlightAdapter(ArrayList())
        binding.vpHighlight.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpHighlight.offscreenPageLimit = 3
        binding.vpHighlight.adapter = highlightAdapter
        binding.vpHighlight.setPageTransformer(MarginPageTransformer(50))
        binding.vpHighlight.clipToPadding = false
        binding.vpHighlight.setPadding(10,10,10,0)
        highlightAdapter.setOnItemClickListener(object : HighlightAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                moveToDetail(id)
            }
        })

        val categoryAdapter = CategoryAdapter(categoryList)
        binding.rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener{
            override fun onItemClick(category: String) {
                Log.d("Category Adapter", "onItemClick: $id")
            }
        })

        val productAdapter = ProductItemAdapter(ArrayList())
        binding.rvProducts.layoutManager = object : GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        productVM.setProductLiveData()
        productVM.productListLiveData.observe(viewLifecycleOwner, Observer {
            highlightAdapter.setHighlightList(it as ArrayList<Product>)
            productAdapter.setProductList(it as ArrayList<Product>)
        })
        binding.rvProducts.adapter = productAdapter
        productAdapter.setOnItemClickListener(object: ProductItemAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                moveToDetail(id)
            }
        })
    }

    fun moveToDetail(id: Int){
        val bundle = Bundle()
        val product = productVM.productListLiveData.value?.first {
            it.id == id
        } as  Product
        bundle.putParcelable("product_details", product)
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}
