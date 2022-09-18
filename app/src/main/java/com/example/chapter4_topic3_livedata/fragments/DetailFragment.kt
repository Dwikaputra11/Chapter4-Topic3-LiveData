package com.example.chapter4_topic3_livedata.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chapter4_topic3_livedata.R
import com.example.chapter4_topic3_livedata.data.Product
import com.example.chapter4_topic3_livedata.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val product = arguments?.getParcelable<Product>("product_details") as Product
        Glide.with(binding.root)
            .load(product.img)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(binding.ivImageItem)
        binding.tvDescItem.text = product.desc
        binding.tvNameItem.text = product.name
        binding.tvPriceItem.text = product.price.toString()
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

}