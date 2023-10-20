package com.example.studentclass.ui.classresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.databinding.ActivityClassResourcesBinding
import com.example.studentclass.repository.Repository
import com.example.studentclass.adapter.ClassResourceAdapter
import com.example.studentclass.models.ClassResourceEntity

class ClassResourcesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityClassResourcesBinding
    private lateinit var adapter : ClassResourceAdapter
    private lateinit var viewModel : ClassResourcesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassResourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ClassResourcesViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(ClassResourcesViewModel::class.java)

        adapter = ClassResourceAdapter(this, arrayListOf())
        binding.listClassResource.adapter = adapter

        binding.toolBar.setNavigationOnClickListener {
            finish()
        }

        val list = intent.getParcelableArrayListExtra<ClassResourceEntity>("classResources")
        if (list != null && list.size >= 0) {
            viewModel.classResource.value = list
        }
        viewModel.classResource.observe(this, Observer {
            try  {
                if (list != null) {
                    adapter.list = list
                }
                adapter.notifyDataSetChanged()
            }catch (ex : Exception) {
            }
        })
    }
}