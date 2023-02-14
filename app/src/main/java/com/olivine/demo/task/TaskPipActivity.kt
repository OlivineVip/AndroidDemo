package com.olivine.demo.task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olivine.demo.databinding.ActivityTaskPipBinding

class TaskPipActivity : AppCompatActivity() {

    private val bindings by lazy { ActivityTaskPipBinding.inflate(layoutInflater) }

    private val pipController by lazy { PIPController(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        bindings.tvPip.setOnClickListener {
            val result = pipController.enterPIP()
            if (!result) {
                pipController.requestPIPPermission()
            }
        }



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