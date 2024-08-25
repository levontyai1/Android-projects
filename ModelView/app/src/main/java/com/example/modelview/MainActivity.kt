package com.example.modelview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.modelview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        bind.button1.setOnClickListener {
                viewModel.addNumber()
                bind.textView.text = viewModel.number.toString()
        }
    }
    override fun onStop() {
        super.onStop()
    }
}