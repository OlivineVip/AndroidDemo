package com.olivine.demo.task

import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Rational
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * 画中画
 */
class PIPController(var activity: AppCompatActivity) : DefaultLifecycleObserver {
    private val TAG = "MBPIPController"
    private var mRequestPIPPermission = false
    private var mMainScope = MainScope()
    private val mAspectRatio = Rational(9, 16)
    private var mSupportPIPMode: Boolean

    companion object {
        //是否点击进入过画中画模式--用于判断程序在后台时,由画中画返回全屏后退出,是否启动首页activity,以及onstop配合判断是否点击进入过画中画且在画中画模式
        var isEnteredPIPMode = false
    }


    init {
        activity.lifecycle.addObserver(this)
        mSupportPIPMode = isSupportPIPMode()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        enterPIPAfterPermission()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        isEnteredPIPMode = isPIPMode(activity)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        isEnteredPIPMode = isPIPMode(activity)

    }


    override fun onDestroy(owner: LifecycleOwner) {
        isEnteredPIPMode = false
        mMainScope.cancel()
        super.onDestroy(owner)
    }

    public fun isPIPMode(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return activity.isInPictureInPictureMode
        }
        return false
    }


    /**
     * 打开画中画权限后延迟1s 进入画中画
     */
    private fun enterPIPAfterPermission() {
        if (this.mRequestPIPPermission) {
            this.mRequestPIPPermission = false
            mMainScope.launch {
                delay(1000)
                enterPIP()
            }
        }
    }


    private fun isSupportPIPMode(): Boolean {
        return activity.packageManager
            .hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    fun enterPIP(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val builder = PictureInPictureParams.Builder()
                    .setAspectRatio(mAspectRatio)
                val enterPictureInPictureMode = activity.enterPictureInPictureMode(builder.build())
                return enterPictureInPictureMode
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }


    fun requestPIPPermission() {
        try {
            activity.startActivityForResult(
                Intent(
                    "android.settings.PICTURE_IN_PICTURE_SETTINGS",
                    Uri.parse("package:${activity.applicationContext.packageName}")
                ),
                10008
            )
            mRequestPIPPermission = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}