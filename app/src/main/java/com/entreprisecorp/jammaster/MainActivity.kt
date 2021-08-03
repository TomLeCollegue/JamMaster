package com.entreprisecorp.jammaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.entreprisecorp.jammaster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setIcon(R.drawable.ic_guitar)
        supportActionBar?.show()
    }
}