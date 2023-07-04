package com.emmanuel_rono.fav_dish.Domain


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.emmanuel_rono.fav_dish.Presentation.Activities.AddUpdateDish
import com.emmanuel_rono.fav_dish.databinding.DialoglistBinding

class dialoListAdapter(
    private val activity:Activity,
    private val listItems:List<String>,
    private val selection:String
): RecyclerView.Adapter<dialoListAdapter.dialogListViwHOlder>()
{
    inner class dialogListViwHOlder(View:DialoglistBinding) :ViewHolder(View.root)
    {
        val tvtext=View.titleTv

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dialogListViwHOlder {
val binding:DialoglistBinding=DialoglistBinding.inflate(LayoutInflater.from(activity),parent,false)
        return dialogListViwHOlder(binding)
    }

    override fun onBindViewHolder(holder: dialogListViwHOlder, position: Int) {
val item=listItems[position]
        holder.tvtext.text=item
        holder.itemView.setOnClickListener {
            if (activity is AddUpdateDish) {
                activity.seletedListItem(item, selection)
            }

        }
    }
    override fun getItemCount(): Int {
        return listItems.size
    }
}