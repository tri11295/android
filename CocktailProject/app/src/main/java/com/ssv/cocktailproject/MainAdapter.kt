package com.ssv.cocktailproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demomvp.base.BaseAdapter
import com.example.demomvp.base.BaseHolder
import com.example.demomvp.extensions.loadFromUrl
import com.example.demomvp.utils.Constants
import com.ssv.cooktailproject.models.Cocktail
import kotlinx.android.synthetic.main.item_cocktail.view.*

class MainAdapter(context: Context,
                  var listCocktai: MutableList<Cocktail>
) :
    BaseAdapter<BaseHolder<Cocktail?>>(context)
{
    var positionMax = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Cocktail?> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cocktail, parent, false)
        return CocktailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCocktai.size
    }

    override fun onBindViewHolder(holder: BaseHolder<Cocktail?>, position: Int) {
        holder.bindData(context,listCocktai,position)
    }

    inner class CocktailViewHolder(itemView : View) : BaseHolder<Cocktail?>(itemView) {
        override fun bindData(context: Context, listData: List<Cocktail?>, position: Int) {
            itemView.apply {
                imgCocktail.loadFromUrl(listData[position]!!.strDrinkThumb)
                tvCocktailName.setText(listData[position]!!.strDrink)
                tvCocktailCategory.setText(listData[position]!!.strCategory)
            }
        }
    }

}