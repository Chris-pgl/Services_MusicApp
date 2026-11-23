package com.example.musicplayerappfor_service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class MyCustomService: Service() {

    //To play music we need a media player object
    lateinit var player: MediaPlayer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(
            this,
            Settings.System.DEFAULT_NOTIFICATION_URI
        )

        player.isLooping = true
        player.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::player.isInitialized){
            player.release()
        }

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }




}