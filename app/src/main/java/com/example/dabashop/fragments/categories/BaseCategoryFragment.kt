package com.example.dabashop.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dabashop.R
import com.example.dabashop.adapters.MejoresOfertasAdapter
import com.example.dabashop.adapters.MejoresProductosAdapter
import com.example.dabashop.databinding.FragmentBaseCategoryBinding

open class BaseCategoryFragment: Fragment(R.layout.fragment_base_category) {
    private lateinit var binding : FragmentBaseCategoryBinding
    private lateinit var mejoresOfertasAdapter : MejoresProductosAdapter
    private lateinit var mejoresProductosAdapter : MejoresProductosAdapter

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
    }

    private fun setupMejoresProductosRv() {
        mejoresProductosAdapter = MejoresProductosAdapter()
        binding.rvBestProducts.apply {
            layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
            adapter = mejoresProductosAdapter
        }
    }

    private fun setupMejoresOfertasRv() {
        mejoresOfertasAdapter = MejoresProductosAdapter()
        binding.rvBestProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
            adapter = mejoresOfertasAdapter
        }
    }
}