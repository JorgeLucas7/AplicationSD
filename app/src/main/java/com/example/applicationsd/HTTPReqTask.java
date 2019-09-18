package com.example.applicationsd;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

class HTTPReqTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
                HttpURLConnection urlConnection = null;

                try {
                        JsonObject postData = new JsonObject();
                        postData.addProperty("nome", params[0]);
                        postData.addProperty("sobrenome", params[1]);
                        postData.addProperty("email", params[2]);

                        URL url = new URL("http://192.168.43.52/server.php");
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestProperty("Content-Type", "application/json");
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);
                        urlConnection.setDoInput(true);
                        urlConnection.setChunkedStreamingMode(0);

                        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                        writer.write(postData.toString());
                        writer.flush();

                        int code = urlConnection.getResponseCode();
                        if (code !=  201 && code != 200) {
                                throw new IOException("Invalid response from server: " + code);
                        }

                        BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));

                        String line;
                        while ((line = rd.readLine()) != null) {
                                EventBus.getDefault().post(line);
                                Log.i("data", line);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if (urlConnection != null) {
                        urlConnection.disconnect();
                        }
                }

                return null;
        }
}