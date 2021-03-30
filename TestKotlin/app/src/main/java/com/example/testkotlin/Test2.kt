package com.example.testkotlin

import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// region generic


//class Person<out T>(val value: T) {
//    fun get(): T {
//        return value
//    }
//}

//class Person<in T>() {
//    fun say(value : T){
//        println("${value.hashCode()}")
//    }
//}



// endregion


class Person<in T>() {
    fun say(value : T){
        println("${value.hashCode()}")
    }
}

open class Father()

class Son() : Father()

fun main() {
    var fatherObject : Person<Father> = Person()
    var sonObject : Person<Son>
    sonObject = fatherObject
}

// out
//var sonObject = Person(Son())
//var fatherObject : Person<Father>
//fatherObject = sonObject

