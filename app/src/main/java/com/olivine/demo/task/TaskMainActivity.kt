package com.olivine.demo.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olivine.demo.databinding.ActivityMainBinding
import com.olivine.demo.databinding.ActivityTaskMainBinding

class TaskMainActivity : AppCompatActivity() {
    private val bindings by lazy { ActivityTaskMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        bindings.btnNormal.setOnClickListener {
            startActivity(Intent(this@TaskMainActivity, TaskNormalActivity::class.java))
        }

        bindings.btnSingleTask.setOnClickListener {
            startActivity(Intent(this@TaskMainActivity, TaskSingleTaskActivity::class.java))
        }
        bindings.btnSingleInstance.setOnClickListener {
            startActivity(Intent(this@TaskMainActivity, TaskSingleInstanceActivity::class.java))
        }

        bindings.btnPip.setOnClickListener {
            startActivity(Intent(this@TaskMainActivity, TaskPipActivity::class.java))
        }

    }
}