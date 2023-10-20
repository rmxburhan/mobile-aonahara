package com.example.studentclass.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.studentclass.databinding.ActivityPostDetailBinding
import com.example.studentclass.models.CommentEntity
import com.example.studentclass.repository.Repository
import com.example.studentclass.adapter.CommentsAdapter
import com.example.studentclass.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPostDetailBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var commentsAdapter: CommentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = PostViewModelFactory(Repository())
        viewModel = ViewModelProvider(this,factory).get(PostViewModel::class.java)

        commentsAdapter = CommentsAdapter(this, listOf())
        binding.listComments.adapter = commentsAdapter

        binding.toolBar.setNavigationOnClickListener {
            finish()
        }

        val postId = intent.getStringExtra("postId")  ?: ""
        if (!postId.isNullOrEmpty())
        {
          viewModel.getPost(postId)
        }

        binding.btnSubmitComment.setOnClickListener {
               val result =  viewModel.postComment(binding.edtComment.text.toString(), postId)
                result.enqueue(object : Callback<CommentEntity> {
                    override fun onResponse(call: Call<CommentEntity>, response: Response<CommentEntity>) {
                        if (response.isSuccessful) {
                            viewModel.getPost(postId)
                            Toast.makeText(this@PostDetailActivity, "success", Toast.LENGTH_SHORT).show()
                            binding.edtComment.setText("")
                        }
                    }

                    override fun onFailure(call: Call<CommentEntity>, t: Throwable) {
                        try {
                            throw t
                        }catch (ex: Exception) {
                            Log.d("err-submitCommernt", ex.toString())
                        }
                    }

                })
        }


        viewModel.post.observe(this, Observer {
            try {
                if (it.banner.isNullOrEmpty())
                    binding.imgBanner.visibility = GONE
                else {

                    Glide.with(this)
                        .load("${Constant.BASE_URL}/${it.banner}")
                        .into(binding.imgBanner)
                }
                commentsAdapter.list = it.comments
                commentsAdapter.notifyDataSetChanged()
                binding.txtTitle.setText(it.title)
                binding.txtSpoiler.setText(it.content)
                binding.txtInfo.setText(it.createdAt)
                binding.toolBar.title = it.title
            } catch (ex : Exception) {

            }

        })
    }
}