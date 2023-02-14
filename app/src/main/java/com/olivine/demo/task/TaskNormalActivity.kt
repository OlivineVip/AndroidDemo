package com.olivine.demo.task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olivine.demo.databinding.ActivityTaskNormalBinding

class TaskNormalActivity : AppCompatActivity() {

    private val bindings by lazy { ActivityTaskNormalBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(bindings.root)

        bindings.btnNormal.setOnClickListener {
            startActivity(Intent(this, TaskNormalActivity::class.java))
        }

        bindings.btnSingleTask.setOnClickListener {
            startActivity(Intent(this, TaskSingleTaskActivity::class.java))
        }
        bindings.btnSingleInstance.setOnClickListener {
            startActivity(Intent(this, TaskSingleInstanceActivity::class.java))
        }

        bindings.btnPip.setOnClickListener {
            startActivity(Intent(this, TaskPipActivity::class.java))
        }
    }
}