package com.example.dabashop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dabashop.data.CartProduct
import com.example.dabashop.firebase.FirebaseCommon
import com.example.dabashop.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon : FirebaseCommon
) : ViewModel(){
    private val _addCart = MutableStateFlow<Resource<CartProduct>>(Resource.Unspecified())
    val addToCart = _addCart.asStateFlow()

    fun addUpdateProductInCart(cartProduct: CartProduct){
        viewModelScope.launch { _addCart.emit(Resource.Loading()) }
        firestore.collection("user")
            .document(auth.uid!!)
            .collection("cart")
            .whereEqualTo("product.id",cartProduct.product.id)
            .get()
            .addOnSuccessListener {
                it.documents.let {
                    if (it.isEmpty()){// Add new product
                        addNewProduct(cartProduct)
                    }else{
                        val product = it.first().toObject(CartProduct::class.java)
                        if (product == cartProduct){// Increase the quantity
                            val documentId = it.first().id
                            increaseQuantity(documentId,cartProduct)
                        }else{//add new product
                            addNewProduct(cartProduct)
                        }
                    }
                }
            }.addOnFailureListener {
                viewModelScope.launch { _addCart.emit(Resource.Error(it.message.toString())) }
            }
    }

    private fun addNewProduct(cartProduct: CartProduct){
        firebaseCommon.addProductToCart(cartProduct){ addedProduct,e ->
            viewModelScope.launch {
                if (e == null)
                    _addCart.emit(Resource.Success(addedProduct!!))
                else
                    _addCart.emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private fun increaseQuantity(documentId: String, cartProduct: CartProduct){
        firebaseCommon.increaseQuantity(documentId){_,e ->
            viewModelScope.launch {
                if (e == null)
                    _addCart.emit(Resource.Success(cartProduct))
                else
                    _addCart.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}