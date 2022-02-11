package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.MovieItemBinding
import com.example.movies.model.Result

 class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
     inner class MovieViewHolder(val binding: MovieItemBinding) :
         RecyclerView.ViewHolder(binding.root)

     private val diffCallBack = object : DiffUtil.ItemCallback<Result>() {
         override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
             return oldItem.id == newItem.id
         }

         override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
             return oldItem == newItem
         }
     }

     val differ = AsyncListDiffer(this, diffCallBack)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
         val binding =
             MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

         return MovieViewHolder(binding)
     }

     override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
         val result = differ.currentList[position]
         val context = holder.itemView.context
         holder.binding.apply {
             Glide.with(context).load(result.poster_path).into(ivMovieImage)
             tvOriginalTitle.text = result.original_title
             tvOverView.text = result.overview
             tvReleaseDate.text = result.release_date


         }
         holder.itemView.setOnClickListener {
             onItemClickListener?.let {
                 it(result)
             }
         }
     }

     override fun getItemCount(): Int = differ.currentList.size

     private var onItemClickListener: ((Result) -> Unit)? = null

     fun setOnItemClickListener(listener: (Result) -> Unit) {
         this.onItemClickListener = listener
     }
 }





