package com.example.studentclass.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.databinding.ActivityPostBinding
import com.example.studentclass.repository.Repository
import com.example.studentclass.adapter.ListPostAdapter

class PostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPostBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var adapter : ListPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = PostViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)

        adapter = ListPostAdapter(this, listOf())

        binding.listPosts.adapter = adapter
        binding.toolBar.setNavigationOnClickListener {
            finish()
        }

        viewModel.getPosts()
        viewModel.posts.observe(this, Observer {
            try {
                adapter.list = it
                adapter.notifyDataSetChanged()
            } catch (ex : Exception) {
                ex.printStackTrace()
            }
        })
    }
}