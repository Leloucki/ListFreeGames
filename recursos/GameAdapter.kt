package com.example.listfreegames.recursos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listfreegames.data.Game
import com.example.listfreegames.databinding.GameItemBinding

class GameAdapter(private val onClickListener: OnClickListener
) :
    ListAdapter<Game, GameAdapter.GamesViewHolder>(GamesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val binding =
            GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
            holder.itemView.setOnClickListener {
                onClickListener.onClick(currentItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class GamesViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(Games: Game) {
            binding.apply {
                Glide.with(itemView)
                    .load(Games.thumbnail)
                    .into(imageViewLogo)

                textViewName.text = Games.title
                textViewType.text = Games.genre
                textViewAddress.text = Games.platform
            }
        }
    }

    class GamesComparator : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game) =
            oldItem.gameId == newItem.gameId

        override fun areContentsTheSame(oldItem: Game, newItem: Game) =
            oldItem == newItem
    }

    class OnClickListener(val clickListener: (game: Game) -> Unit) {
        fun onClick(game: Game) = clickListener(game)
    }
}

