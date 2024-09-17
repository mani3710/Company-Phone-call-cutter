package com.phonecallcutter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.telecom.TelecomManager
import android.util.Log
import java.lang.reflect.Method;
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.regex.Pattern

class PhoneCallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
          Log.d("PhoneCallReceiver", "onReceive triggered")
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

        if (state == TelephonyManager.EXTRA_STATE_RINGING && incomingNumber != null) {
            Log.d("PhoneCallReceiver", "Incoming call from: $incomingNumber")

            // Normalize the incoming number
            val normalizedNumber = incomingNumber.replace("[^+0-9]".toRegex(), "")

            // Check if the normalized number starts with "911"
            if (normalizedNumber.startsWith("+911") || normalizedNumber.startsWith("911")) {
                Log.d("PhoneCallReceiver", "Rejecting call from: $normalizedNumber")
                endCall(context)
            }
        }
        // val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

        // if (state == TelephonyManager.EXTRA_STATE_RINGING) {
        //     val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
        //     Log.d("PhoneCallReceiver", "Incoming call from: $incomingNumber")
        //      Toast.makeText(context, "Incoming call from: $incomingNumber", Toast.LENGTH_LONG).show()

        //     // If the number starts with "9111", disconnect the call
            
        //     if (incomingNumber != null && incomingNumber.startsWith("+9962")) {
        //         Log.d("PhoneCallReceiver", "Disconnecting call from: $incomingNumber")
        //         endCall(context)
        //     }
        // }
    }

    // Function to end the call (works only for system apps on Android 9+)
   private fun endCall(context: Context) {
        val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        try {
            val success = telecomManager.endCall()
            if (success) {
                Log.d("PhoneCallReceiver", "Call ended successfully")
            } else {
                Log.d("PhoneCallReceiver", "Failed to end the call")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PhoneCallReceiver", "Error ending the call: ${e.message}")
        }
    }
}
