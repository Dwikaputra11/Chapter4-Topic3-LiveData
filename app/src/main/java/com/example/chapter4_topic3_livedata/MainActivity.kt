package com.example.chapter4_topic3_livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.chapter4_topic3_livedata.adapter.CategoryAdapter
import com.example.chapter4_topic3_livedata.adapter.HighlightAdapter
import com.example.chapter4_topic3_livedata.adapter.ProductItemAdapter
import com.example.chapter4_topic3_livedata.data.Product
import com.example.chapter4_topic3_livedata.databinding.ActivityMainBinding
import com.example.chapter4_topic3_livedata.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productVM: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener{
            override fun onItemClick(category: String) {
                Log.d("Category Adapter", "onItemClick: $category")
            }
        })

        val productAdapter = ProductItemAdapter(ArrayList())
        binding.rvProducts.layoutManager = object : GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        productVM.setProductLiveData()
        productVM.productListLiveData.observe(this, Observer {
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
        } as Product
        bundle.putParcelable("product_details", product)
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}