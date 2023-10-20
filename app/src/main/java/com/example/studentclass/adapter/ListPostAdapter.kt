package com.example.studentclass.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.studentclass.R
import com.example.studentclass.models.PostEntity
import com.example.studentclass.ui.post.PostDetailActivity
import com.example.studentclass.utils.Constant
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.TimeZone

class ListPostAdapter(val context : Activity, var list : List<PostEntity>, val fragment : Fragment? = null) : RecyclerView.Adapter<ListPostAdapter.ListPostHolder>() {
    class ListPostHolder(itemView :View) : ViewHolder(itemView) {
        var imgBanner : ImageView = itemView.findViewById(R.id.imgBanner)
        var txtTitle : TextView = itemView.findViewById(R.id.txtTitle)
        var txtInfo : TextView = itemView.findViewById(R.id.txtInfo)
        var txtSpoiler : TextView = itemView.findViewById(R.id.txtSpoiler)
        var container : MaterialCardView = itemView.findViewById(R.id.container)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ListPostHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListPostHolder, position: Int) {
        val data = list.get(position)

        if (data != null) {
            with(holder) {
                txtTitle.setText(data.title)
                val spoiler = if (data.content.length < 100) {
                    data.content + "..."
                } else {
                    data.content.substring(0, 100) + "..."
                }
                txtSpoiler.setText(spoiler)
                try {
                    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    formatter.timeZone = TimeZone.getTimeZone("UTC")
                    val value = formatter.parse(data.createdAt)
                    val dateFormatter = SimpleDateFormat("E, MMMM dd, HH:mm:ss") //this format changeable
                    dateFormatter.timeZone = TimeZone.getDefault()
                    data.createdAt = dateFormatter.format(value)
                } catch (ex : Exception) {
                    Log.d("failed",ex.toString())
                }
                if(!data.banner.isNullOrEmpty())
                    try {
                        imgBanner.visibility = VISIBLE
                        if (fragment == null) {
                            Glide.with(context)
                                .load("${Constant.BASE_URL}/${data.banner}")
                                .into(imgBanner)
                        } else {
                            Glide.with(fragment)
                                .load("${Constant.BASE_URL}/${data.banner}")
                                .into(imgBanner)
                        }
                    } catch (ex : Exception) {
                        Log.d("err-getImage", ex.toString())
                    }

                else {
                    imgBanner.visibility = GONE
                }
                txtInfo.setText(data.createdAt.toString())
                container.setOnClickListener {
                    context.startActivity(Intent(context, PostDetailActivity::class.java).apply {
                        putExtra("postId", data.id)
                    })
                }
            }
        }

//        with(holder) {
//            txtTitle.setText(data.title)
//
//
//

//
//
//        }
//        }

    }
}