package com.example.studentclass.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.studentclass.R
import com.example.studentclass.models.AnnounceData
import com.example.studentclass.ui.announcement.detail.AnnouncementDetailActivity
import com.example.studentclass.utils.Constant
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.TimeZone

class AnnouncementAdapter(
    val context: Context,
    var list: List<AnnounceData>,
    val homeFragment: Fragment
) : RecyclerView.Adapter<AnnouncementAdapter.AnnouncementHolder>() {
    class AnnouncementHolder(itemView : View) : ViewHolder(itemView) {
        var txtTitle : TextView = itemView.findViewById(R.id.txtTitle)
        var txtMessage : TextView = itemView.findViewById(R.id.txtMessage)
        var txtDate : TextView = itemView.findViewById(R.id.txtDate)
        var imgTeacher : ImageView = itemView.findViewById(R.id.imgTeacher)
        var container : MaterialCardView = itemView.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_announcements, parent, false)
        return AnnouncementHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AnnouncementHolder, position: Int) {
        val data = list.get(position)
        if (data != null) {
            try {
                with(holder) {
                    txtMessage.setText(data.description)
                    txtTitle.setText(data.title)
                    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

                    formatter.timeZone = TimeZone.getTimeZone("UTC")
                    val value = formatter.parse(data.createdAt)
                    val dateFormatter = SimpleDateFormat("E, MMMM dd, HH:mm:ss") //this format changeable
                    dateFormatter.timeZone = TimeZone.getDefault()
                    data.createdAt = dateFormatter.format(value)
                    txtDate.setText("${data.createdAt} ")
                        try {
                            Glide.with(homeFragment)
                                .load("${Constant.BASE_URL}/${data.teacher.profilePicture}")
                                .into(imgTeacher)
                        } catch (ex : Exception) {
                            Log.d("err-getImage", ex.toString())
                        }
                    container.setOnClickListener {
                        context.startActivity(Intent(context, AnnouncementDetailActivity::class.java).apply {
                            putExtra("announcementId", data.id)
                        })
                    }
                }
            } catch (ex : Exception) {
                ex.printStackTrace()
            }

        }

    }
}
