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
import com.example.studentclass.models.CommentEntity
import com.example.studentclass.utils.Constant
import java.text.SimpleDateFormat
import java.util.TimeZone

class CommentsAdapter(val context : Context, var list : List<CommentEntity>) : RecyclerView.Adapter<CommentsAdapter.CommentsHolder>() {
    class CommentsHolder(itemView : View) : ViewHolder(itemView) {
        var imgProfile : ImageView = itemView.findViewById(R.id.imgProfile)
        var txtUserName : TextView = itemView.findViewById(R.id.txtNamaUser)
        var txtDate : TextView = itemView.findViewById(R.id.txtTanggal)
        var txtComment : TextView = itemView.findViewById(R.id.txtComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentsHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentsHolder, position: Int) {
        val data = list.get(position)

        if (data != null) {

        with(holder) {
            txtComment.setText(data.message)
            txtUserName.setText(data.student.fullName)
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value = formatter.parse(data.createdAt)
            val dateFormatter = SimpleDateFormat("E, MMMM dd, HH:mm:ss") //this format changeable
            dateFormatter.timeZone = TimeZone.getDefault()
            data.createdAt = dateFormatter.format(value)
            txtDate.setText(data.createdAt)
            try {
                Glide.with(context)
                    .load("${Constant.BASE_URL}/${data.student.profilePicture}")
                    .into(imgProfile)
            } catch (ex :Exception) {
                Log.d("err-getImage", ex.toString())
            }

        }
        }

    }
}