package com.example.smsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import java.lang.ref.WeakReference

class SmsBroadcastReceiver(smsListener: SmsListener) : BroadcastReceiver() {

    private var smsListener: WeakReference<SmsListener> = WeakReference(smsListener)

    private val regex = Regex("\\d+")

    override fun onReceive(context: Context?, intent: Intent?) {
        val messages: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        val messageText: String = messages.getOrNull(0)?.messageBody.orEmpty()
        smsListener.get()?.onReceive(regex.find(messageText)?.value.orEmpty())
        Log.d("SmsBroadcastReceiver", "SMS received: $messageText")
    }
}

fun interface SmsListener {
    fun onReceive(sms: String)
}