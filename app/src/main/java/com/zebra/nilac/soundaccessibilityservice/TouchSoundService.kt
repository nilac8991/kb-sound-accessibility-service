package com.zebra.nilac.soundaccessibilityservice

import android.accessibilityservice.AccessibilityService
import android.media.MediaPlayer
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent


class TouchSoundService : AccessibilityService() {

    private lateinit var mMediaPlayer: MediaPlayer

    override fun onServiceConnected() {
        super.onServiceConnected()

        Log.d("TAG", "onServiceConnected")
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.action != KeyEvent.ACTION_DOWN)
            return super.onKeyEvent(event)

        Log.d(TAG, "onKeyEvent Called!")

        if (event.action != KeyEvent.KEYCODE_VOLUME_DOWN ||
            event.action != KeyEvent.KEYCODE_VOLUME_UP ||
            event.action != KeyEvent.KEYCODE_VOLUME_MUTE
        ) {
            mMediaPlayer = MediaPlayer.create(applicationContext, R.raw.keyboard_sound)
            mMediaPlayer.start()
            mMediaPlayer.setOnCompletionListener { mp1: MediaPlayer -> mp1.release() }
        }

        return super.onKeyEvent(event)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Log.d(TAG, "onAccessibilityEvent Called!")
    }

    override fun onInterrupt() {
        Log.d(TAG, "onInterrupt Called!")
    }

    companion object {
        const val TAG = "TouchSoundService"
    }
}