package com.example.studentclass.utils

import android.os.CountDownTimer

class CountdownTimer(val durationMilis : Long,val onFinished: onFinished ) : CountDownTimer(durationMilis, 1000) {
    override fun onTick(millisUntilFinished: Long) {
        val hours = millisUntilFinished / 3600000
        val minutes = (millisUntilFinished % 3600000) / 60000
        val seconds = (millisUntilFinished % 60000) / 1000

        val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        onFinished.onTick(formattedTime)
    }

    override fun onFinish() {
            onFinished.onFinished()
    }
}

interface onFinished {
    fun onFinished()
    fun onTick(time : String)
}