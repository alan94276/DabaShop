package com.example.dabashop.util

import android.view.View
import androidx.fragment.app.Fragment
import com.example.dabashop.R
import com.example.dabashop.activities.ShoppingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigationView(){
    val bottonNavigateView = (activity as ShoppingActivity).findViewById<BottomNavigationView>(
        R.id.bottom_navigation
    )
    bottonNavigateView.visibility = View.GONE
}

fun Fragment.showBottomNavigationView(){
    val bottonNavigateView = (activity as ShoppingActivity).findViewById<BottomNavigationView>(
        R.id.bottom_navigation
    )
    bottonNavigateView.visibility = View.VISIBLE
}