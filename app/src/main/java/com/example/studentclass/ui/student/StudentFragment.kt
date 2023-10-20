package com.example.studentclass.ui.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.studentclass.databinding.FragmentStudentBinding
import com.example.studentclass.repository.Repository
import com.example.studentclass.adapter.MilestoneAdapter
import com.example.studentclass.utils.Constant

class StudentFragment : Fragment() {
    private lateinit var binding : FragmentStudentBinding
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter : MilestoneAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentBinding.inflate(inflater, container, false)

        adapter = MilestoneAdapter(requireActivity(), listOf())
        binding.listMilestone.adapter = adapter

        val factory = StudentViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)

        viewModel.getStudentData()
        viewModel.studentData.observe(viewLifecycleOwner, Observer {
            try {
                binding.txtTestPassed.setText(it.testPassed.toString())
                binding.txtNamaUser.setText(it.fullName.toString())
                binding.txtTestPassed.setText(it.testPassed.toString())
                binding.txtClassAttended.setText(it.attendanceCuont.toString())
                if(!it.profilePicture.isNullOrEmpty())
                    Glide.with(requireActivity())
                        .load("${Constant.BASE_URL}/${it.profilePicture}")
                        .into(binding.imgProfile)

                if(!it.bannerPicture.isNullOrEmpty()){
                    Glide.with(requireActivity())
                        .load("${Constant.BASE_URL}/${it.bannerPicture}")
                        .into(binding.imgBanner)
                }
                adapter.list = it.milestones
                adapter.notifyDataSetChanged()
            } catch (ex : Exception) {
                ex.printStackTrace()
            }
        })
        return binding.root
    }
}