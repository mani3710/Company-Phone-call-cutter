package com.phonecallcutter

import android.content.IntentFilter
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class PhoneCallModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "PhoneCallModule"
    }

    @ReactMethod
    fun listenForIncomingCall() {
        
        val receiver = PhoneCallReceiver()
        val filter = IntentFilter()
        filter.addAction("android.intent.action.PHONE_STATE")
        reactApplicationContext.registerReceiver(receiver, filter)
    }
}
