package com.jventura.pybridge;

import android.util.Log;

/**
 * Created by atwyman on 2/16/17.
 */

public class Timer {
    private final long mStartTime;
    private final String mName;

    Timer(String name) {
        mName = name;
        mStartTime = System.nanoTime();
    }

    void logTime() {
        final long stopTime = System.nanoTime();
        final double millis = (stopTime - mStartTime)/1000000.0;
        Log.d("PyBridge.Timer", mName + " time " + millis + "ms");
    }
}
