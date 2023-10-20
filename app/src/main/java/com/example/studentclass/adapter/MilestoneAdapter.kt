package com.example.studentclass.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.studentclass.R
import com.example.studentclass.models.MilestoneEntity
import com.example.studentclass.utils.Constant

class MilestoneAdapter(val context: Context, var list : List<MilestoneEntity>) : RecyclerView.Adapter<MilestoneAdapter.MilestoneHolder>() {
    class MilestoneHolder(itemView: View) : ViewHolder(itemView) {
        var txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var txtContent = itemView.findViewById<TextView>(R.id.txtContent)
        var imgMilestone = itemView.findViewById<ImageView>(R.id.imgMilestone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilestoneHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_milestones, parent, false)
        return MilestoneHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MilestoneHolder, position: Int) {
        val data = list.get(position)

        if (data != null) {
            with(holder) {
                txtTitle.setText(data.title)
                txtContent.setText(data.description)
                if (!data.image.isNullOrEmpty()) {

                    try {
                        Glide.with(context)
                            .load("${Constant.BASE_URL}/${data.image}")
                            .into(imgMilestone)
                    } catch (ex : Exception) {
                        Log.d("err-getImage", ex.toString())
                    }

                }
            }
        }

    }

}