package com.example.dabashop.data

class User (
    val firstName : String,
    var lastName : String,
    var email : String,
    var imagePath : String = "",
){
    constructor(): this ("","","", "")
}