package com.example.workout.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.R
import com.example.workout.model.CardPopular

class CardsPopularAdapter(private val context: Context, private val cardsList: List<CardPopular>) :
    RecyclerView.Adapter<CardsPopularAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_popular, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardsList[position]
        with(holder) {
            title.text = card.title
            image.setImageResource(card.imageResourceId)
            itemView.setOnClickListener {
                val uri = Uri.parse(card.link)
                if (uri.scheme == "http" || uri.scheme == "https") {
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}


