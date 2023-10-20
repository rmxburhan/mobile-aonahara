package com.example.studentclass.ui.learning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.databinding.FragmentLearningBinding
import com.example.studentclass.repository.Repository
import com.example.studentclass.ui.classresources.ClassResourcesActivity
import com.example.studentclass.adapter.ClassResourceAdapter
import com.example.studentclass.adapter.ListPostAdapter
import com.example.studentclass.models.ClassResourceEntity
import com.example.studentclass.ui.post.PostActivity

class LearningFragment : Fragment() {
    private lateinit var binding : FragmentLearningBinding
    private lateinit var viewModel : LearningViewModel
    private lateinit var adapter : ListPostAdapter
    private lateinit var classResourcesAdapter : ClassResourceAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLearningBinding.inflate(inflater, container, false)

        adapter = ListPostAdapter(requireActivity(), listOf(), this@LearningFragment)
        classResourcesAdapter = ClassResourceAdapter(requireActivity(), arrayListOf(), this@LearningFragment)

        binding.listPosts.adapter = adapter
        binding.listClassResource.adapter = classResourcesAdapter

        val factory = LearningViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(LearningViewModel::class.java)
        viewModel.getClassResources()
        viewModel.getPosts()

        binding.headlineTeacherPost.setOnClickListener {
            val intent = Intent(requireActivity(), PostActivity::class.java)
            startActivity(intent)
        }
        binding.actionShowMore.setOnClickListener {
            val intent = Intent(requireActivity(), ClassResourcesActivity::class.java).apply {
                val list : ArrayList<ClassResourceEntity> = arrayListOf()
                list.addAll(classResourcesAdapter.list)
                putParcelableArrayListExtra("classResources", list)
            }
            startActivity(intent)
        }
        viewModel.classResources.observe(viewLifecycleOwner, Observer {
            classResourcesAdapter.list = it.take(3)
            classResourcesAdapter.notifyDataSetChanged()
        })
        viewModel.post.observe(viewLifecycleOwner, Observer {
            adapter.list = it.data.take(1)
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }
}