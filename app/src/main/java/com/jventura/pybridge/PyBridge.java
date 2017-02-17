package com.jventura.pybridge;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class PyBridge {

    /**
     * Initializes the Python interpreter.
     *
     * @param datapath the location of the extracted python files
     * @return error code
     */
    private static native int start(String datapath);
    public static int timedStart(String datapath) {
        Timer timer = new Timer("start");
        int result = start(datapath);
        timer.logTime();
        return result;
    }

    /**
     * Stops the Python interpreter.
     *
     * @return error code
     */
    private static native int stop();
    public static int timedStop() {
        Timer timer = new Timer("stop");
        int result = stop();
        timer.logTime();
        return result;
    }

    /**
     * Sends a string payload to the Python interpreter.
     *
     * @param payload the payload string
     * @return a string with the result
     */
    private static native String call(String payload);
    private static String timedCall(String payload) {
        Timer timer = new Timer("call");
        String result = call(payload);
        timer.logTime();
        return result;
    }

    /**
     * Sends a JSON payload to the Python interpreter.
     *
     * @param payload JSON payload
     * @return JSON response
     */
    public static JSONObject call(JSONObject payload) {
        String result = timedCall(payload.toString());
        if (result == null) {
            throw new RuntimeException("Python call failed.");
        }
        try {
            return new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load library
    static {
        System.loadLibrary("pybridge");
    }
}
