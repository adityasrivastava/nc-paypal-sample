package com.sample.paypal.impl;

import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CreateToken {
    private static final String FIELD_DELIM = "&";
    private static final String KV_DELIM = "=";

    public static void main(String []args) throws IOException {

        String requestString=createParametersForTokenGeneration();
        String response=sendRequest(requestString);
        Map<String,String> responseMap=createMapFromString(response);
        System.out.println("map: "+responseMap);
        System.out.println("response: "+response);
    }

    private static String createParametersForTokenGeneration() throws UnsupportedEncodingException {
        StringBuilder requestString = new StringBuilder();
        Hashtable<String, String> transKeyValues = new Hashtable<String, String>();
        transKeyValues.put("PARTNER","PayPal");
        transKeyValues.put("VENDOR","adityasrivastavanewcycle");
        transKeyValues.put("USER","adityasrivastavanewcycle");
        transKeyValues.put("PWD","aditya123");
        transKeyValues.put("TRXTYPE","A");
        transKeyValues.put("AMT","1");
        transKeyValues.put("CREATESECURETOKEN","Y");
        transKeyValues.put("SECURETOKENID","12528467de89789api3d60236cb15");


        Enumeration<String> keys = transKeyValues.keys();
        while( keys.hasMoreElements() )
        {
            String key = URLEncoder.encode(keys.nextElement().toString(),"UTF-8");
            String value = URLEncoder.encode(transKeyValues.get(key).toString(),"UTF-8");
            requestString.append(key);
            requestString.append(KV_DELIM);
            requestString.append(value);
            requestString.append(FIELD_DELIM);
        }
        return requestString.toString();
    }

    private static Map<String,String> createMapFromString(String str){
        Map<String,String> map=new HashMap<String, String>();
        String [] strings=str.split("&");
        if(strings.length>0){
            for(String splitted:strings){
                map.put(splitted.split("=")[0],splitted.split("=")[1]);
            }
        }
        return map;
    }

    private static String sendRequest(String _requestString) throws UnsupportedEncodingException, IOException
    {
        // Create the connection
        URL url = new URL("https://pilot-payflowpro.paypal.com");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setAllowUserInteraction(false);

        // Post the data
        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
        osw.write(_requestString);
        osw.flush();
        osw.close();

        // Get the response
        InputStream resultStream = conn.getInputStream();
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(resultStream, "UTF-8"));
        String responseString = bufRead.readLine();
        resultStream.close();

        return responseString;
    }

}
