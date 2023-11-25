package com.example.dabashop.fragments.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat.NestedScrollType
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dabashop.R
import com.example.dabashop.adapters.MejoresOfertasAdapter
import com.example.dabashop.adapters.MejoresProductosAdapter
import com.example.dabashop.adapters.SpecialProductsAdapter
import com.example.dabashop.databinding.FragmentMainCategoryBinding
import com.example.dabashop.util.Resource
import com.example.dabashop.viewmodel.MainCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private val TAG = "MainCategoryFragment"

@AndroidEntryPoint
class MainCategoryFragment: Fragment(R.layout.fragment_main_category) {
    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var specialProducstAdapter: SpecialProductsAdapter
    private lateinit var mejoresOfertasAdapter: MejoresOfertasAdapter
    private lateinit var mejoresProductosAdapter: MejoresProductosAdapter
    private val viewModel by viewModels<MainCategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpecialProducstRv()
        setupMejoresOfertasRv()
        setupMejoresProductosRv()

        //recycler view productos especiales.
        lifecycleScope.launchWhenStarted {
            viewModel.specialProducst.collectLatest {
                when (it){
                    is Resource.Loading ->{
                        showLoading()
                    }
                    is Resource.Success ->{
                        specialProducstAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error ->{
                        hideLoading()
                        Log.e(TAG,it.message.toString())
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }


        //recycler view mejores ofertas
        lifecycleScope.launchWhenStarted {
            viewModel.mejoresOfertas.collectLatest {
                when (it){
                    is Resource.Loading ->{
                        showLoading()
                    }
                    is Resource.Success ->{
                        mejoresOfertasAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error ->{
                        hideLoading()
                        Log.e(TAG,it.message.toString())
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        //recycler view mejores productos
        lifecycleScope.launchWhenStarted {
            viewModel.mejoresProductos.collectLatest {
                when (it){
                    is Resource.Loading ->{
                        binding.bestProductsProgessBar.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        mejoresProductosAdapter.differ.submitList(it.data)
                        binding.bestProductsProgessBar.visibility = View.GONE

                    }
                    is Resource.Error ->{
                        hideLoading()
                        Log.e(TAG,it.message.toString())
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                        binding.bestProductsProgessBar.visibility = View.GONE

                    }
                    else -> Unit
                }
            }
        }

        binding.nestedCoreScrollBaseCategory.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (v.getChildAt(0).bottom <= v.height + scrollY){
                viewModel.fetchMejoresProductos()
            }
        })

    }

    private fun hideLoading() {
        binding.mainCategoryProgessBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.mainCategoryProgessBar.visibility = View.VISIBLE
    }

    private fun setupSpecialProducstRv() {
        specialProducstAdapter = SpecialProductsAdapter()
        binding.rvSpecialProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            adapter = specialProducstAdapter
        }
    }

    private fun setupMejoresProductosRv() {
        mejoresProductosAdapter = MejoresProductosAdapter()
        binding.rvBestProducts.apply {
            layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
            adapter = mejoresProductosAdapter
        }
    }

    private fun setupMejoresOfertasRv() {
        mejoresOfertasAdapter = MejoresOfertasAdapter()
        binding.rvBestDealsProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            adapter = mejoresOfertasAdapter
        }
    }
}