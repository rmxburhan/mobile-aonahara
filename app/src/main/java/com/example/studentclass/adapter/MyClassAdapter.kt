package com.example.studentclass.adapter

import android.app.Activity
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
import com.example.studentclass.dto.GetMyClassResponse
import com.example.studentclass.models.ClassEntity
import com.example.studentclass.utils.Constant
import java.text.SimpleDateFormat
import java.util.TimeZone

class MyClassAdapter(val activity: Activity, val list : GetMyClassResponse, val fragment : Fragment, val listUrutanData : List<String>) : RecyclerView.Adapter<MyClassAdapter.MyClassHolder>() {
    class MyClassHolder(itemView : View) : ViewHolder(itemView) {
        var txtHari = itemView.findViewById<TextView>(R.id.txtHari)
        var listDetailClass = itemView.findViewById<RecyclerView>(R.id.listClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClassHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_myclass, parent, false)
        return MyClassHolder(view)
    }

    override fun getItemCount(): Int {
        return listUrutanData.size
    }

    override fun onBindViewHolder(holder: MyClassHolder, position: Int) {
        val data = when(listUrutanData.get(position)) {
            "Sunday" -> list.data.Sunday ;
            "Monday" -> list.data.Monday;
            "Tuesday" -> list.data.Tuesday;
            "Wednesday" -> list.data.Wednesday;
            "Thursday" -> list.data.Thursday;
            "Friday" -> list.data.Friday;
            "Saturday" -> list.data.Saturday;
            else -> null
        }

        with(holder) {
            txtHari.setText(listUrutanData.get(position))
            if (data != null && data.size > 0) {
                listDetailClass.adapter = DetailClassAdapter(activity, data, fragment)
            } else {
                listDetailClass.adapter = DetailClassAdapterZero()
            }
        }
    }
}

class DetailClassAdapterZero() : RecyclerView.Adapter<DetailClassAdapterZero.DetailClasssHolder>() {
    class DetailClasssHolder(itemView : View) : ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailClasssHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noclasss, parent , false)
        return DetailClasssHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: DetailClasssHolder, position: Int) {
    }
}


class DetailClassAdapter(val activity: Activity, val list : List<ClassEntity>, val fragment : Fragment) : RecyclerView.Adapter<DetailClassAdapter.DetailClasssHolder>() {
    class DetailClasssHolder(itemView : View) : ViewHolder(itemView) {
        var imgTeacher = itemView.findViewById<ImageView>(R.id.imgTeacher)
        var txtTeacherName = itemView.findViewById<TextView>(R.id.txtTeacherName)
        var txtTopic = itemView.findViewById<TextView>(R.id.txtTopic)
        var txtTime = itemView.findViewById<TextView>(R.id.txtTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailClasssHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_class, parent , false)
        return DetailClasssHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DetailClasssHolder, position: Int) {
        val data = list.get(position)

        with(holder) {
            if (data != null) {
                if (!data.teacher.profilePicture.isNullOrEmpty()) {
                    Glide.with(fragment)
                        .load("${Constant.BASE_URL}/${data.teacher.profilePicture}")
                        .into(imgTeacher)
                }
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                formatter.timeZone = TimeZone.getTimeZone("UTC")
                val value = formatter.parse(data.createdAt)
                val dateFormatter = SimpleDateFormat("HH:mm") //this format changeable
                dateFormatter.timeZone = TimeZone.getDefault()
                val newDate  = dateFormatter.format(value)
                txtTeacherName.setText("Teacher : " + data.teacher.name)
                txtTopic.setText("Topic : " + data.topic)
                txtTime.setText("Time : " + newDate + " WIB")
            }
        }
    }
}
