package com.example.smsreceiver

import android.Manifest.permission.RECEIVE_SMS
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var smsReceiver: SmsBroadcastReceiver
    private lateinit var smsCodeView: TextView

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // registerReceiver()
            } else {
                Toast.makeText(applicationContext, "Sms cannot be received!", LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        smsCodeView = findViewById(R.id.sms_code)

        if (ContextCompat.checkSelfPermission(applicationContext, RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            // registerReceiver()
        } else {
            requestPermissionLauncher.launch(RECEIVE_SMS)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        smsCodeView.text = intent?.getStringExtra("SMS_CODE")
    }

    private fun registerReceiver() {
        // smsReceiver = SmsBroadcastReceiver { smsCode ->
        //     smsCodeView.text = smsCode
        // }
        // val intentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        //
        // registerReceiver(smsReceiver, intentFilter)
    }

    override fun onDestroy() {
        // unregisterReceiver(smsReceiver)
        super.onDestroy()
    }
}