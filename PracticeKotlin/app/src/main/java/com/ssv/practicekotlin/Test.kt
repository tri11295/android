package com.ssv.practicekotlin

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// region enum

//enum class laBan(){
//    EAST,
//    WEST,
//    SOUTH,
//    NORTH
//}
//
//enum class Color(var red:Int,var green:Int){
//    RED(123,33),
//    GREEN(44,55),
//    BLACK(33,55)
//}

// endregion

//region delegation

interface Bird{
    fun flyAndFindFood()
}

class Eagle : Bird {
    override fun flyAndFindFood() {
        println("I am Eagle, i go to find food")
    }
}
class Cuckkoo(b : Bird) : Bird by b {
//    override fun flyAndFindFood() {
//    println("I am Cuckkoo, i go to find food")
//    }
}

//endregion

//region delegation pro

//class User1{
//    var name : String by DelegatedUser()
//}
//
//class DelegatedUser{
//    operator fun getValue(thisRef : Any?,property : KProperty<*>) : String {
//        return " ${thisRef}, thank for delegating ${property.name}"
//    }
//
//    operator fun setValue(thisRef: Any?,property: KProperty<*>,value : String){
//        println("assign to ${property.name} in ${thisRef}. Detail :${value}")
//    }
//}

//endregion

//region lamda

//var sum = {x:Int,y:Int -> x+y}
//var mimus : (Int,Int) -> Int ={x,y -> x-y}
//
////Anomymous Fun
//var div = fun(x:Double,y:Double) : Double{
//    return x/y
//}

//endregion

//region Observable and map

//Observable
//class User2(name : String){
//    var currentAddress : String by Delegates.observable("No Address"){
//        property, oldValue, newValue ->  run {
//            println("${property.name} change")
//            println("From ${oldValue} To ${newValue}")
//        }
//    }
//}

// map
var user2Obj = mutableMapOf<String,Any?>("name" to "Tri","age" to 26)

class Customer(val mapObj : Map<String,Any?>){
    val name : String by mapObj
    val age : Int by mapObj
    val className : String by mapObj
}

//endregion

fun main(){
// region enum

//    print("Huong ${laBan.EAST}")
//    for (color in Color.values()){
//        println("mau ${color} - vi tri ${color.ordinal}")
//    }

// endregion
//region delegation
//val eagle = Eagle();
//val cuckkoo = Cuckkoo(eagle)
//cuckkoo.flyAndFindFood()
//endregion
//region delegation pro

//var user1 : User1 = User1()
//user1.name = "tri"
//println(user1.name)

//endregion
//region lamda

//    println("${sum(3,5)}")
//    println("${mimus(93,5)}")
//    println("${div(10.0,2.5)}")

//    var arr = doubleArrayOf(10.0,3.0,5.0,7.8)
//    arr.forEach { num -> println("${num + 1}") }

    //endregion
    // region Observable and map

    //Observable
    //    val user2 : User2 = User2("Tri")
    //    user2.currentAddress = "Hue"
    //    user2.currentAddress = "DN"

    //map
//    println("detail : ${user2Obj.getValue("name")} age : ${user2Obj.getValue("age")}")
//    user2Obj.set("age",27)
//    println("detail : ${user2Obj.getValue("name")} age : ${user2Obj.getValue("age")}")

    val customer : Customer = Customer(mapOf("name" to "tri","age" to 15,"className" to "A2"))
    println("Detail ${customer.name} age ${customer.age} class ${customer.className}")

    //endregion


    //region lamda

    //endregion
}