package com.ssv.practicekotlin

class User(email : String, age : Int, passWord : String) {
    private var Email : String = ""
    private var Age : Int = 0
    private var PassWord : String = ""

    init {
        Email = email
        Age = age
        PassWord = passWord
    }

//    constructor(email : String, age : Int, passWord : String){
//        Email = email
//        Age = age
//        PassWord = passWord
//    }

    fun getPassWord() : String{
        return PassWord
    }

    fun  setPassWord(newPassWord : String){
        PassWord = newPassWord
    }
}