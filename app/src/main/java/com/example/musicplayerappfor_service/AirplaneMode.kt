package com.example.musicplayerappfor_service

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneMode: BroadcastReceiver(){

//AirPlaneMode Receiver
    override fun onReceive(context: Context?, intent: Intent?) {

        Toast.makeText(
            context,
            "Broadcast received!",
            Toast.LENGTH_SHORT
        ).show()

        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val isAirplaneModeEnabled =
                intent.getBooleanExtra("state", false)

            //qui dichiaro che se si attiva la modalità AirPlane allora blocco la musica.
            if (isAirplaneModeEnabled){
                Toast.makeText(context,
                    "Airplane Mode Enabled",
                    Toast.LENGTH_LONG)
                    .show()
                context?.let {
                    val intent2 = Intent(it, MyCustomService::class.java)
                    it.stopService(intent2)
                }
            }else{
                Toast.makeText(context,
                    "Airplane Mode Disabled",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

}