package com.example.indicator;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WebServiceGet extends AsyncTask<ArrayList<String>, Void, String> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;



    @Override
    protected String doInBackground(ArrayList<String>... arrayLists) {
        String jsonObjectAuthorization = new String();
        //Create a URL object holding our url
        try {
            InputStream getResponseInputStream = getResponseServer();
            jsonObjectAuthorization = convertStreamToString(getResponseInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObjectAuthorization;
    }

    @Override
    protected void onPostExecute(String strAuthorizationData) {
        String NAME_FIELD_SUCCESSAUTHORIZATION = "isSuccessAuthorization";
        String NAME_FIELD_TOKENSESSION = "tokenSession";

        JSONObject jObj = new JSONObject();
        try {
            jObj = new JSONObject(strAuthorizationData);
            AuthorizationData  authorizationData = new AuthorizationData();
            authorizationData.setIsSuccessAuthorization((Boolean) jObj.get(NAME_FIELD_SUCCESSAUTHORIZATION));
            authorizationData.setTokenSession((String) jObj.get(NAME_FIELD_TOKENSESSION));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private InputStream getResponseServer() throws IOException {
        URL myUrl = new URL("http://192.168.1.66:8080/LogLet?username=username&password=password");
        //Create a connection
        HttpURLConnection connection =(HttpURLConnection)
                myUrl.openConnection();
        //Set methods and timeouts
        connection.setRequestMethod(REQUEST_METHOD);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.getResponseCode();
        return connection.getInputStream();
    }

    private String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
