package com.ssv.practicekotlin

import kotlin.properties.Delegates
import kotlin.reflect.KProperty


fun getMaxValue (listNumber : List<Int>, less : (Int,Int) -> Boolean) : Int{
    var maxValue : Int = 0
    for (value in listNumber){
        if ( less(maxValue,value) ){
            maxValue = value
        }
    }
    return maxValue
}
val less = fun ( x : Int, y : Int) : Boolean {
    return x < y
}
fun ArrayList<Int>.swapElement(index1 : Int, index2 : Int){
    var temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}



var doing = fun (){
    println("I am working")
}

fun doSomething( myFun: () -> Unit ) {
    myFun()
}


fun calculateSum() : ((Int,Int) -> Int){
    return ::sum
}

fun sum (x: Int,y: Int) : Int {
    return x + y
}

//fun calculation( x:Int, y:Int, funCalculation : ( Int, Int) -> Int ) : Int {
//    var result : Int = funCalculation( x, y )
//    return result
//}
//
//var sum = fun (x: Int,y: Int) : Int {
//    return x + y
//}

fun main(){
    var sumResult = calculateSum()
    println(sumResult(1,2))
    //    println(calculation(10,12, sum ))   // 22
}





//var listNumber = arrayListOf<Int>(1,2,3,4,5,6)
//listNumber.swapElement(1,3)
//println(listNumber) // [1, 4, 3, 2, 5, 6]

//    var listNumber = listOf<Int>(1,5,13,9,5)
//    println("Max Value in listNumber is : ${getMaxValue(listNumber, less)}")
//    // Max Value in listNumber is : 13

