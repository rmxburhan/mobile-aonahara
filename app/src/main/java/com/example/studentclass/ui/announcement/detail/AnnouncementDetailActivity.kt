package com.example.studentclass.ui.announcement.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import retrofit2.Callback
import com.example.studentclass.adapter.AnnouncementCommentAdapter
import com.example.studentclass.databinding.ActivityAnnouncementDetailBinding
import com.example.studentclass.dto.PostAnnouncementCommentResponse
import com.example.studentclass.repository.Repository
import com.example.studentclass.utils.Constant
import retrofit2.Call
import retrofit2.Response

class AnnouncementDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnnouncementDetailBinding
    private lateinit var viewModel : ActivityAnnouncementDetailViewModel
    private lateinit var commentAdapter : AnnouncementCommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnnouncementDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        commentAdapter = AnnouncementCommentAdapter(this, listOf())
        binding.listComments.adapter = commentAdapter

        val announcementId = intent.getStringExtra("announcementId") ?: ""

        val factory = ActivityAnnouncementDetailViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(ActivityAnnouncementDetailViewModel::class.java)
        if (!announcementId.isNullOrEmpty())
        {
            viewModel.getAnnouncement(announcementId)
        }

        binding.btnSubmitComment.setOnClickListener {
            if (binding.edtComment.text.toString().isNullOrEmpty())
            {
                Toast.makeText(this, "Comment tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val response = viewModel.postComment(message = binding.edtComment.text.toString(), announcementId = announcementId)
            response.enqueue(object : Callback<PostAnnouncementCommentResponse> {
                override fun onResponse(
                    call: Call<PostAnnouncementCommentResponse>,
                    response: Response<PostAnnouncementCommentResponse>
                ) {
                    if (response.isSuccessful) {
                        viewModel.getAnnouncement(announcementId)
                        Toast.makeText(this@AnnouncementDetailActivity, "Add comment success", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<PostAnnouncementCommentResponse>, t: Throwable) {
                    try {
                        throw t
                    } catch (ex : Exception) {
                        Log.d("err-postCommentFailure", ex.toString())
                    }
                }

            })
        }

        viewModel.announcement.observe(this, Observer {announceData ->
            try {
                binding.txtTitle.setText(announceData.title)
                binding.txtInfo.setText(announceData.createdAt.toString())
                binding.txtContent.setText(announceData.description)
                if (!announceData.teacher.profilePicture.isNullOrEmpty()) {
                    Glide.with(this)
                        .load("${Constant.BASE_URL}/${announceData.teacher.profilePicture}")
                        .into(binding.imgTeacher)
                }
                announceData.announcementComments?.let {
                    commentAdapter.list = it
                    commentAdapter.notifyDataSetChanged()
                }
            } catch (ex : Exception) {

            }

        })
    }
}