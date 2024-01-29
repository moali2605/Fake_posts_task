package com.example.fake_posts_task.present.view.users.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fake_posts_task.databinding.UserListItemBinding
import com.example.fake_posts_task.present.model.user.UserUiModel

class UserAdapter :
    ListAdapter<UserUiModel, UserAdapter.UserViewHolder>(RecyclerDiffUtiluser_list_item()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = UserListItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.onBind(currentItem)
    }

    inner class UserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(currentItem: UserUiModel) {
            binding.tvUserId.text = currentItem.userId.toString()
            binding.tvTitle.text = currentItem.title
            binding.tvBody.text = currentItem.body
            binding.cvAlbum.setOnClickListener {
                // onClick(currentItem)
            }
        }
    }
}

class RecyclerDiffUtiluser_list_item : DiffUtil.ItemCallback<UserUiModel>() {
    override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
        return oldItem == newItem
    }
}