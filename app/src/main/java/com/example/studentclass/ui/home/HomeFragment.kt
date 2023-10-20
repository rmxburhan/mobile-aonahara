package com.example.studentclass.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.studentclass.databinding.FragmentHomeBinding
import com.example.studentclass.repository.Repository
import com.example.studentclass.adapter.MilestoneAdapter

import com.example.studentclass.adapter.AnnouncementAdapter
import com.example.studentclass.adapter.MyClassAdapter
import com.example.studentclass.models.ClassEntity
import com.example.studentclass.utils.Constant
import com.example.studentclass.utils.CountdownTimer
import com.example.studentclass.utils.onFinished
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class HomeFragment : Fragment(), onFinished {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter : AnnouncementAdapter
    private lateinit var milestoneAdapter: MilestoneAdapter

    private var timer : CountdownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val factory = HomeViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        adapter = AnnouncementAdapter(requireActivity(), listOf(), this@HomeFragment)
        milestoneAdapter = MilestoneAdapter(requireActivity(), listOf())
        binding.listAnnounce.adapter = adapter
        binding.listMilestone.adapter = milestoneAdapter
        viewModel.isExpanded.observe(viewLifecycleOwner) {
            if (it) {
                binding.listMyClass.visibility = VISIBLE
                binding.actionExpand.setText("SHOW LESS")
            } else {
                binding.listMyClass.visibility = GONE
                binding.actionExpand.setText("EXPAND")
            }
        }

        binding.actionExpand.setOnClickListener {
            viewModel.updateExpanded()
        }

        viewModel.getAnnouncements()
        viewModel.getMyProfile()
        viewModel.getMyClass()


        viewModel.myClass.observe(viewLifecycleOwner) {
            try {
                if (it != null) {
                    val dayWeek = getDayNameArray()
                    val data = when(dayWeek.get(0)) {
                        "Sunday" -> it.data.Sunday ;
                        "Monday" -> it.data.Monday;
                        "Tuesday" -> it.data.Tuesday;
                        "Wednesday" -> it.data.Wednesday;
                        "Thursday" -> it.data.Thursday;
                        "Friday" -> it.data.Friday;
                        "Saturday" -> it.data.Saturday;
                        else -> null
                    }
                    if (data != null) {
                        loadTimer(data)
                    }
                    binding.listMyClass.adapter = MyClassAdapter(requireActivity(), it, this@HomeFragment, dayWeek.toList())
                } else {
                    binding.listMyClass.adapter = null
                }
            }catch (ex : Exception) {
                ex.printStackTrace()
                Log.d("err-getMyclass", ex.toString())
            }

        }
        viewModel.studentData.observe(viewLifecycleOwner) {
            try {
                milestoneAdapter.list = it.milestones
                milestoneAdapter.notifyDataSetChanged()
            } catch (ex: Exception) {
                Log.d("err-getstudentdata", ex.toString())
            }
        }

        viewModel.announcements.observe(viewLifecycleOwner) {
            try {
                    adapter.list = it.data
                adapter.notifyDataSetChanged()
            } catch (ex: Exception) {
                Log.d("err-get", ex.toString())
            }
        }
        return binding.root
    }

    private fun loadTimer(data : List<ClassEntity>) {
        if (data.size == 0) {
            binding.txtTimer.setText("No class today")
        } else {
            val imgUrl = data.sortedBy { it.time }.get(0).teacher.profilePicture
            if (!imgUrl.isNullOrEmpty())
                Glide.with(this)
                    .load("${Constant.BASE_URL}/${imgUrl}")
                    .into(binding.imgUser)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val currentUtcTime = Date()
            val startDate = dateFormat.format(currentUtcTime)
            startTimer(startDate, data.sortedBy { it.createdAt }.get(0).time)
        }
    }

    fun getDayNameArray(): Array<String> {
        val dateFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
        val daysOfWeek = Array(7) { "" }

        val calendar = Calendar.getInstance()
        calendar.time = Calendar.getInstance().time

        for (i in 0 until 7) {
            daysOfWeek[i] = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_WEEK, 1)
        }

        return daysOfWeek
    }

    fun startTimer(startdate : String, endDate : String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date1 = dateFormat.parse(startdate)
        val date2 = dateFormat.parse(endDate)

        val milis = date2.time - date1.time

        timer = CountdownTimer(milis, this@HomeFragment)
        timer?.start()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMyProfile()
        viewModel.getAnnouncements()
        viewModel.getMyClass()
    }

    override fun onFinished() {
        timer = null
        viewModel.getMyClass()
    }

    override fun onTick(time: String) {
        binding.txtTimer.setText(time)
    }

    override fun onPause() {
        super.onPause()
        timer?.cancel()
        timer = null
    }
}