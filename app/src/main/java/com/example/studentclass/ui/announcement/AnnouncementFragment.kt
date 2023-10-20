package com.example.studentclass.ui.announcement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentclass.databinding.FragmentAnnouncementBinding
import com.example.studentclass.repository.Repository
import com.example.studentclass.adapter.AnnouncementAdapter

class AnnouncementFragment : Fragment() {
    private lateinit var binding : FragmentAnnouncementBinding
    private lateinit var adapter : AnnouncementAdapter
    private lateinit var viewModel : AnnouncementViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentAnnouncementBinding.inflate(inflater, container, false)

        val factory =AnnouncementViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(AnnouncementViewModel::class.java)

        adapter = AnnouncementAdapter(requireActivity(), listOf(), this@AnnouncementFragment)
        binding.listAnnounce.adapter = adapter

        viewModel.getAnnouncements()
        viewModel.announcements.observe(viewLifecycleOwner, Observer {
            try {
                adapter.list = it
                adapter.notifyDataSetChanged()
            } catch (ex : Exception) {

            }
        })
        return binding.root
    }
}