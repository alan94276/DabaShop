package com.example.dabashop.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dabashop.R
import com.example.dabashop.databinding.FragmentHomeBinding
import com.example.dabashop.fragments.categories.AccesoryFragment
import com.example.dabashop.fragments.categories.JaketsFragment
import com.example.dabashop.fragments.categories.MainCategoryFragment
import com.example.dabashop.fragments.categories.PantsFragment
import com.example.dabashop.fragments.categories.ShirtFragment
import com.example.dabashop.adapters.HomeViewpagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragment = arrayListOf<Fragment>(
            MainCategoryFragment(),
            PantsFragment(),
            ShirtFragment(),
            JaketsFragment(),
            AccesoryFragment()

        )

        binding.viewpagerHome.isUserInputEnabled = false

        val viewPager2Adapter = HomeViewpagerAdapter(categoriesFragment,childFragmentManager,lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout,binding.viewpagerHome){ tab, position->
            when(position){
                0 -> tab.text = "Inicio"
                1 -> tab.text = "Pantalones"
                2 -> tab.text = "Camisas"
                3 -> tab.text = "Chamarras"
                4 -> tab.text = "Accesorios"
            }
        }.attach()
    }

}