package com.olivine.demo

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.olivine.demo.databinding.ActivityMainBinding
import com.olivine.demo.task.TaskMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.layoutManager = LinearLayoutManager(this)
        val adapter = TestAdapter()
        binding.rvList.adapter = adapter


        val data = mutableListOf(
            TestBean(
                "task",
                onCLick = { startActivity(Intent(this@MainActivity, TaskMainActivity::class.java)) }
            )
        )
        adapter.setData(data)
    }


}