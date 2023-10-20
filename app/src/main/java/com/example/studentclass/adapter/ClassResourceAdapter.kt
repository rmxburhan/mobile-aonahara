package com.example.studentclass.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.example.studentclass.models.ClassResourceEntity
import com.example.studentclass.utils.Constant
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ClassResourceAdapter(val context : Activity, var list : List<ClassResourceEntity>, val fragment: Fragment? = null) : RecyclerView.Adapter<ClassResourceAdapter.ClassResourceHolder>() {
    class ClassResourceHolder(itemView : View) : ViewHolder(itemView) {
        var txtTitle : TextView = itemView.findViewById(R.id.txtTitle)
        var txtMessagae : TextView = itemView.findViewById(R.id.txtMessage)
        var imgTeacher : ImageView = itemView.findViewById(R.id.imgTeacher)
        val container : MaterialCardView = itemView.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassResourceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_classresource,parent, false)
        return  ClassResourceHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ClassResourceHolder, position: Int) {
        val data = list.get(position)

        if (data != null) {

        with(holder) {
            txtTitle.setText(data.title)
            txtMessagae.setText(data.message)
            try {
                if (fragment == null) {
                    Glide.with(context)
                        .load("${Constant.BASE_URL}/${data.`class`.teacher.profilePicture}")
                        .into(imgTeacher)
                } else {
                    Glide.with(fragment)
                        .load("${Constant.BASE_URL}/${data.`class`.teacher.profilePicture}")
                        .into(imgTeacher)
                }

            } catch (ex : Exception) {
                Log.d("err-getImage",ex.toString())
            }


            container.setOnClickListener {
                MaterialAlertDialogBuilder(context)
                    .setTitle("Konfirmasi")
                    .setMessage("Anda akan diarahkan ke ${Constant.BASE_URL}/${data.fileLocation}")
                    .setPositiveButton("Yes") {dialog, _ ->
                        val url = "${Constant.BASE_URL}/${data.fileLocation}"
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse(url))
                        context.startActivity(intent)
                    }
                    .setNegativeButton("Cancel") {dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
        }
        }

    }
}