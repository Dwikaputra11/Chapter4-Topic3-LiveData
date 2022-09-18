package com.example.chapter4_topic3_livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.chapter4_topic3_livedata.data.Product
import com.example.chapter4_topic3_livedata.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setContentView(binding.root)
        val product = intent.getParcelableExtra<Product>("product_details") as Product
        binding.product = product
        binding.btnOrderNow.setOnClickListener {
            val sendIntent = Intent()
            val message = "Hello. This is Dwika, I want to ask product: ${product.name}, is still available?"
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT,message)
            sendIntent.type = "text/plain"
            sendIntent.setPackage("com.whatsapp")
            startActivity(sendIntent)
        }
    }
    companion object{
        @JvmStatic
        @BindingAdapter("productImage")
        fun loadImage(view: ImageView, productImage: String){
            Glide.with(view.context)
                .load(productImage)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(view)
        }
    }
}