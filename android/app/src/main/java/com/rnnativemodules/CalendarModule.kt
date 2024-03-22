package com.rnnativemodules;

import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Callback

import android.util.Log

class CalendarModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "CalendarModule"

    override fun getConstants(): MutableMap<String, Any> =
        hashMapOf("DEFAULT_EVENT_NAME" to "New Event")

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun createCalendarEvent(name: String, location: String, callback: Callback) {
        Log.d("CalendarModule", "Create event called with name: $name and location: $location")
    }
}
