package com.example.dabashop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dabashop.data.Product
import com.example.dabashop.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
)
: ViewModel() {
    private val _specialProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val specialProducst: StateFlow<Resource<List<Product>>> = _specialProducts

    private val _mejoresOfertas = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val mejoresOfertas: StateFlow<Resource<List<Product>>> = _mejoresOfertas

    private val _mejoresProductos = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val mejoresProductos: StateFlow<Resource<List<Product>>> = _mejoresProductos

    init {
        fetchSpecialProducts()
        fetchMejoresOfertas()
        fetchMejoresProductos()
    }

    fun fetchSpecialProducts(){
        viewModelScope.launch {
            _specialProducts.emit(Resource.Loading())
        }
        firestore.collection("Products")
            .whereEqualTo("category","Productos Especiales").get().addOnSuccessListener { result ->
                val specialProductsList = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Success(specialProductsList))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Error(it.message.toString()))
                }
            }

    }

    fun fetchMejoresOfertas(){
        viewModelScope.launch {
            _mejoresOfertas.emit(Resource.Loading())
        }
        firestore.collection("Products")
            .whereEqualTo("category", "Mejores Ofertas")
            .get()
            .addOnSuccessListener { result ->
                val mejoresOfertas = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _mejoresOfertas.emit(Resource.Success(mejoresOfertas))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _mejoresOfertas.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    fun fetchMejoresProductos(){
        viewModelScope.launch {
            _mejoresProductos.emit(Resource.Loading())
        }
        firestore.collection("Products")
            .get()
            .addOnSuccessListener { result ->
                val mejoresProductos = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _mejoresProductos.emit(Resource.Success(mejoresProductos))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _mejoresProductos.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}