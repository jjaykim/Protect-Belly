package com.example.protectbelly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.protectbelly.databinding.GroupListAdapterBinding
import com.example.protectbelly.models.Group

class GroupListAdapter: RecyclerView.Adapter<GroupListAdapter.Companion.ItemViewHolder> {

    private val context: Context;
    var joinedGroup: ArrayList<Group>;

    constructor(context: Context, joinedGroup: ArrayList<Group>) {
        this.context = context
        this.joinedGroup = joinedGroup
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            GroupListAdapterBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currItem: Group = joinedGroup[position];
        holder.bind(context, currItem)
    }

    override fun getItemCount(): Int {
        return joinedGroup.size;
    }

    companion object{
        class ItemViewHolder(binding: GroupListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
            var itemBinding: GroupListAdapterBinding = binding

            fun bind(context: Context, currItem: Group) {
                itemBinding.groupProfile.setImageResource(currItem.logo);

                itemBinding.groupName.text = currItem.title
                itemBinding.groupDetails.text = currItem.createdAt + " | " + currItem.type + " | by " + currItem.organizerName

            }
        }


    }


}