package com.example.indicator;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Context;
import android.content.Intent;
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

public class WebServiceGet extends AsyncTask<String, Void, String> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;

    Context context;
    WebServiceGet(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    protected String doInBackground(String... paramsForLogin) {

        String jsonObjectAuthorization = new String();

        String url = "http://192.168.1.66:8080/LogLet?";
        String urlWithParametres = compilingUrl(url, paramsForLogin);
        //Create a URL object holding our url
        try {
            InputStream getResponseInputStream = getResponseServer(urlWithParametres);
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

            Intent intent = new Intent(context, HeadActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String compilingUrl(String url, String... paramsForLogin){
        return  url + "username=" + paramsForLogin[0] + "&password=" + paramsForLogin[1];
    }

    private InputStream getResponseServer(String url) throws IOException {
        URL myUrl = new URL(url);
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
