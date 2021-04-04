package com.example.demomvp.services

import com.example.demomvp.utils.Constants
import com.ssv.cooktailproject.models.Cocktail
import org.json.JSONArray
import org.json.JSONObject

class ParseJson {
    fun parseJsonFood(jsonObject: JSONObject): Cocktail {
        jsonObject.run {
            return Cocktail(
                getString("idDrink"),
                getString("strDrink"),
                getString("strCategory"),
                getString("strAlcoholic"),
                getString("strGlass"),
                getString("strInstructions"),
                getString("strInstructionsDE"),
                getString("strInstructionsIT"),
                getString("strDrinkThumb"),
                getString("strIngredient1"),
                getString("strIngredient2"),
                getString("strIngredient3")
            )
        }
    }

    fun parseJsonArray(jsonObject: JSONObject): MutableList<Cocktail> {
        val result: MutableList<Cocktail> = mutableListOf()

        jsonObject.getJSONArray("drinks").let { array ->
            for (it in 0 until array.length()) {
                result.add(parseJsonFood(array.getJSONObject(it)))
            }
        }

        return result
    }
}