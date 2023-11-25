package com.example.dabashop.data

sealed class Category(val category: String){
    object Camisas: Category("Camisas")
    object Pantalones: Category("Pantalones")
    object Chamarras: Category("Chamarras")
    object Accesory: Category("Accesorios")
}
