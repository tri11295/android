package com.ssv.practicekotlin


class VietNamemese(val name : String, val age : Int){
    companion object{
        fun nation() : String = "I am VietNamese"
    }
    fun info() : String{
        return "name : ${name}, age : ${age}"
    }
}

fun main(){

    var personA = VietNamemese("tri", 25)
    println(personA.info())         // name : tri, age : 25
    println(VietNamemese.nation())  // I am VietNamese

}