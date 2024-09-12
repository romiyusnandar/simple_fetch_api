package com.ryu.apifetch.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryu.apifetch.R
import com.ryu.apifetch.api.model.AnimeOngoing

class AnimeAdapter (private val onClick: (AnimeOngoing) -> Unit) :
    ListAdapter<AnimeOngoing, AnimeAdapter.AnimeViewHolder>(AnimeCallBack) {

    class AnimeViewHolder(itemView: View, val onClick: (AnimeOngoing) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
            private val animeTitle: TextView = itemView.findViewById(R.id.textViewTitle)
            private var imagePoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
            private val animeEpisode: TextView = itemView.findViewById(R.id.textViewEpisode)
            private val animeReleaseDay: TextView = itemView.findViewById(R.id.textViewReleaseDay)


            fun bind(anime: AnimeOngoing) {
                animeTitle.text = anime.judul
                animeEpisode.text = "Episode: " + anime.episodeTerbaru
                animeReleaseDay.text = "Rilis: " + anime.hariRilis
                Glide.with(itemView).load(anime.poster).centerCrop().into(imagePoster)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return AnimeViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        holder.bind(anime)
    }
}

object AnimeCallBack : DiffUtil.ItemCallback<AnimeOngoing>() {
    override fun areItemsTheSame(oldItem: AnimeOngoing, newItem: AnimeOngoing): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AnimeOngoing, newItem: AnimeOngoing): Boolean {
        return oldItem.slug == newItem.slug
    }

}