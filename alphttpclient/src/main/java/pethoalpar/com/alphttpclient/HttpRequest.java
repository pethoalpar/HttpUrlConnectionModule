package pethoalpar.com.alphttpclient;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import pethoalpar.com.alphttpclient.util.RequestUtil;

/**
 * Created by pethoalpar on 4/16/2016.
 */
public class HttpRequest extends AsyncTask<HttpCall, String, InputStream>{

    private static final String UTF_8 = "UTF-8";
    private static String session;
    private HttpCall httpCall = null;

    @Override
    protected InputStream doInBackground(HttpCall... params) {
        HttpURLConnection urlConnection = null;
        httpCall = params[0];
        InputStream retIs = null;
        try{
            String dataParams = getDataString(httpCall.getParams(), httpCall.getMethodtype());
            URL url = new URL(httpCall.getMethodtype() == HttpCall.GET ? httpCall.getUrl() + dataParams : httpCall.getUrl());
            urlConnection = (HttpURLConnection) url.openConnection();
            if (session != null) {
                urlConnection.setRequestProperty("Cookie", URLEncoder.encode(session, "UTF-8"));
            }
            urlConnection.setRequestMethod(httpCall.getMethodtype() == HttpCall.GET ? "GET":"POST");
            urlConnection.setReadTimeout(100000 /* milliseconds */);
            urlConnection.setConnectTimeout(150000 /* milliseconds */);

            if(httpCall.getParams() != null && httpCall.getMethodtype() == HttpCall.POST){
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, UTF_8));
                writer.append(dataParams);
                writer.flush();
                writer.close();
                os.close();
            }
            int responseCode = urlConnection.getResponseCode();
            getSession(urlConnection);
            if(responseCode == HttpURLConnection.HTTP_OK){
                retIs=urlConnection.getInputStream();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return retIs;
    }

    private void getSession(HttpURLConnection urlConnection) {
        String key = "";
        String id = "";
        for (int i = 1;(key = urlConnection.getHeaderFieldKey(i)) != null; i++)
        {
            if (key.equalsIgnoreCase("set-cookie"))
            {
                id = urlConnection.getHeaderField(key);
                id = id.substring(0, id.indexOf(";"));
                if(session == null){
                    session = id;
                }
            }
        }
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        super.onPostExecute(inputStream);
        if(httpCall != null && HttpCall.BYTE_ARRAY == httpCall.getReturnTye()){
            onResponseByteArray(RequestUtil.getBytes(inputStream));
        }else{
            onResponseString(RequestUtil.getString(inputStream));
        }
    }

    public void onResponseString(String response) {

    }

    public void onResponseByteArray(byte[] response) {

    }

    private String getDataString(HashMap<String,String> params, int methodType) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;
        if(params == null){
            return result.toString();
        }
        for(Map.Entry<String,String> entry : params.entrySet()){
            if (isFirst){
                isFirst = false;
                if(methodType == HttpCall.GET){
                    result.append("?");
                }
            }else{
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), UTF_8));
        }
        return result.toString();
    }
}