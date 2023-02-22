package com.example.giphyapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapp.R
import com.example.giphyapp.models.Data
import kotlinx.android.synthetic.main.item_giphy.view.*

class GiphyAdapter : RecyclerView.Adapter<GiphyAdapter.GiphyViewHolder>() {

    inner class GiphyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        return  GiphyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_giphy, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val giphy = differ.currentList[position]
        holder.itemView.apply {
            tvTitle.text = giphy.title
            Glide.with(this).load(giphy.images.original.url).into(ivGiphy)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}