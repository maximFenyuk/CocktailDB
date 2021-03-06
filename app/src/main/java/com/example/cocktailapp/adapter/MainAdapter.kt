package com.example.cocktailapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.cocktailapp.DrinkDetailsForDbActivity
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.ItemDrinkBinding
import com.example.cocktailapp.model.DBDrink

class MainAdapter (): RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var drinks = ArrayList<DBDrink>()
    private val TAG = "MainAdapter"
    private var idDrink: ArrayList<String> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding: ItemDrinkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_drink, parent, false)
        return MainViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(drinks[position])
    }

    fun updateData(drink: List<DBDrink>) {
        this.drinks.clear()
        this.drinks.addAll(drink)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemDrinkBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(drink: DBDrink) {
            binding.drinkName.text = drink.strDrink
            binding.drinkImage.load(drink.strDrinkThumb){
                transformations(RoundedCornersTransformation(25f))
            }
            if (!idDrink.contains(drink.idDrink)) {
                idDrink.add(drink.idDrink)
            }
            itemView.setOnClickListener{
                val intent = Intent(context, DrinkDetailsForDbActivity::class.java)
                intent.putExtra("idDrink", idDrink[position])
                Log.e(TAG, idDrink.toString())
                context.startActivity(intent)}

        }

    }




}