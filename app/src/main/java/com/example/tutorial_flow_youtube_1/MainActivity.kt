package com.example.tutorial_flow_youtube_1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.tutorial_flow_youtube_1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnCountDown.setOnClickListener {
            lifecycleScope.launch {
                viewModel.countDownFlow.collect() {
                    binding.tvCountDown.text = it.toString()
                }
            }
        }

    }

}