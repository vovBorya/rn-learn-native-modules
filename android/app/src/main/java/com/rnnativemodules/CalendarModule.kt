package com.rnnativemodules;

import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.Arguments
import com.facebook.react.modules.core.DeviceEventManagerModule

import android.util.Log
import android.icu.util.Calendar
import android.content.Intent

class CalendarModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private var listenerCount = 0

    val params = Arguments.createMap().apply {
        putString("eventProperty", "someValue")
    }

    override fun getName() = "CalendarModule"

    override fun getConstants(): MutableMap<String, Any> =
        hashMapOf("DEFAULT_EVENT_NAME" to "New Event")

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun createCalendarEvent(name: String, location: String, onFailure: Callback, onSuccess: Callback) {
        Log.d("CalendarModule", "Create event called with name: $name and location: $location")

        val eventId = java.util.UUID.randomUUID().toString();
        Log.d("CalendarModule", "eventId: $eventId")

        try {
            val cal: Calendar = Calendar.getInstance()
            val intent = Intent(Intent.ACTION_EDIT)
            intent.setType("vnd.android.cursor.item/event")
            intent.putExtra("beginTime", cal.getTimeInMillis())
            intent.putExtra("allDay", true)
            intent.putExtra("rrule", "FREQ=YEARLY")
            intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000)
            intent.putExtra("title", name)

            Log.d("ReactContext", "this.reactContext: $this.reactContext")

//            if (reactContext) {
//                reactContext.getCurrentActivity().startActivity(intent);
//            }
        } catch (err: Throwable) {
            onFailure(err)
        }
    }

    private fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap?) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }

    @ReactMethod
    fun addListener(eventName: String) {
        if (listenerCount == 0) {
            // Set up any upstream listeners or background tasks as necessary
        }

        listenerCount += 1
    }

    @ReactMethod
    fun removeListeners(count: Int) {
        listenerCount -= count
        if (listenerCount == 0) {
            // Remove upstream listeners, stop unnecessary background tasks
        }
    }
}
