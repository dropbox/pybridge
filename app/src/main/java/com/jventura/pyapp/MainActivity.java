package com.jventura.pyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jventura.pybridge.AssetExtractor;
import com.jventura.pybridge.PyBridge;

import org.json.JSONException;
import org.json.JSONObject;

import static com.jventura.pyapp.R.id.textView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Extract python files from assets
        AssetExtractor assetExtractor = new AssetExtractor(this);
        assetExtractor.removeAssets("python");
        assetExtractor.copyAssets("python");

        // Get the extracted assets directory
        String pythonPath = assetExtractor.getAssetsDataDir() + "python";

        // Start the Python interpreter
        PyBridge.timedStart(pythonPath);

        // Call a Python function
        try {
            final String answer1;
            {
                JSONObject json = new JSONObject();
                json.put("function", "greet");
                json.put("name", "atwyman");

                JSONObject result = PyBridge.call(json);
                Log.d("ART_DBG", result.toString());
                answer1 = result.getString("result");
            }

            final int answer2;
            {
                JSONObject json = new JSONObject();
                json.put("function", "add");
                json.put("a", 3);
                json.put("b", 4);

                JSONObject result = PyBridge.call(json);
                Log.d("ART_DBG", result.toString());
                answer2 = result.getInt("result");
            }

            TextView outputTextView = (TextView) findViewById(textView);
            outputTextView.setText(answer1 + "\n" + answer2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Stop the interpreter
        PyBridge.timedStop();
    }
}
