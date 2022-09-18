package com.example.chapter4_topic3_livedata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter4_topic3_livedata.data.Product

class ProductViewModel:ViewModel() {
    var productList = arrayListOf<Product>(
        Product(id = 1, name = "Shirt", category = "Women", price = 200, img = "https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80", "A Shirt"),
        Product(id = 2, name = "Short", category = "Women", price = 200, img = "https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80", "A Shirt"),
        Product(id = 3, name = "Pants", category = "Male", price = 200, img = "https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80", "A Shirt"),
        Product(id = 4, name = "Shoes", category = "Male", price = 200, img = "https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80", "A Shirt"),
        Product(id = 5, name = "Jacket", category = "Summer", price = 200, img = "https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80", "A Shirt"),
    )

    var productListLiveData: MutableLiveData<List<Product>> = MutableLiveData()

    fun setProductLiveData(){
        productListLiveData.value = productList
    }
}