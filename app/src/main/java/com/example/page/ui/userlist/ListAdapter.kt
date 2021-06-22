package com.example.page.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.page.data.User
import com.example.page.databinding.ItemUserBinding

class ListAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<User, ListAdapter.ListViewHolder>(USER_COMPARATOR) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null){
            holder.bind(currentItem)
        }
    }


   inner class ListViewHolder (private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){


       init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null){
                        listener.onItemClick(item)
                    }
                }

            }
        }


        fun bind(user: User){
            binding.apply {
                Glide.with(itemView)
                    .load(user.photo.large)
                    .centerCrop()
                    .transform(CircleCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(userPhotoVh)
                flName.text = user.name.firstName + " " + user.name.lastName
                phoneNumber.text = user.phone
            }
        }



    }

    interface OnItemClickListener{
        fun onItemClick(user: User)
    }


    companion object{
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem
        }
    }




}