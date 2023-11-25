package com.example.dabashop.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dabashop.R
import com.example.dabashop.adapters.MejoresOfertasAdapter
import com.example.dabashop.adapters.MejoresProductosAdapter
import com.example.dabashop.databinding.FragmentBaseCategoryBinding

open class BaseCategoryFragment: Fragment(R.layout.fragment_base_category) {
    private lateinit var binding : FragmentBaseCategoryBinding
    protected val mejoresOfertasAdapter : MejoresProductosAdapter by lazy { MejoresProductosAdapter() }
    protected val mejoresProductosAdapter : MejoresProductosAdapter by lazy { MejoresProductosAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMejoresOfertasRv()
        setupMejoresProductosRv()

        binding.rvOfferProducts.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1) && dx !=0)
                    onOfferPagingRequest()
            }
        })
        binding.nestedCoreScrollBaseCategory.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (v.getChildAt(0).bottom <= v.height + scrollY){
                onBestProductsPagingRequest()
            }
        })
    }

    fun showBestProductsLoading(){
        binding.bestProductsProgessBar.visibility = View.VISIBLE
    }

    fun showOfferLoading(){
        binding.offerProductsProgessBar.visibility = View.VISIBLE
    }

    fun hideOfferLoading(){
        binding.offerProductsProgessBar.visibility = View.GONE
    }

    fun hideBestProductsLoading(){
        binding.bestProductsProgessBar.visibility = View.GONE
    }



    open fun onOfferPagingRequest(){

    }

    open fun onBestProductsPagingRequest(){

    }

    private fun setupMejoresProductosRv() {
        binding.rvBestProducts.apply {
            layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
            adapter = mejoresProductosAdapter
        }
    }

    private fun setupMejoresOfertasRv() {
        binding.rvOfferProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
            adapter = mejoresOfertasAdapter
        }
    }
}