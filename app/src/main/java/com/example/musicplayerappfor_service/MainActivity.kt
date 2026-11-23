package com.example.musicplayerappfor_service

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var startBtn: Button
    lateinit var stopBtn: Button
    lateinit var titleApp: TextView

    val airplaneReceiver = AirplaneMode()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        startBtn = findViewById(R.id.start_Btn)
        stopBtn = findViewById(R.id.stop_Btn)
        titleApp = findViewById(R.id.title_app)


        startBtn.setOnClickListener {
            val intent = Intent(this, MyCustomService::class.java)
            startService(intent)
        }

        stopBtn.setOnClickListener {
            val intent = Intent(this, MyCustomService::class.java)
            stopService(intent)
        }

    }

    override fun onStart() {
        super.onStart()

        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                airplaneReceiver,
                filter,
                Context.RECEIVER_EXPORTED
            )
        } else {
            registerReceiver(airplaneReceiver, filter)
        }
    }

    override fun onStop() {
        unregisterReceiver(airplaneReceiver)
        super.onStop()
    }
}