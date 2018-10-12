package com.abid.admin.bikeservice;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class httpCalls {

    private static String BASE_URL = "http://api.citybik.es/v2/networks?fields=location,href";

    public List<Network> getpickuplist(String data) throws JSONException {

        List<Network> list = new ArrayList<Network>();
        Location loc = new Location();
        Network network = new Network();

        JSONObject jsonObject = new JSONObject(data);



        JSONArray jarr = jsonObject.getJSONArray("networks");

        for(int i=0;i<jarr.length();i++){

            JSONObject jobj = jarr.getJSONObject(i);
            JSONObject job = getObject("location",jobj);
            loc.setCity(getString("city",job));
            loc.setCountry(getString("country",job));
            loc.setLatitude(getdouble("latitude",job));
            loc.setLongitude(getdouble("longitude",job));
            //network.setId(getString("id",jobj));
            //network.setCompany(getString("company",jobj));
            network.setHref(getString("href",jobj));
            network.setLocation(loc);
            list.add(network);
        }

        return list;
    }


    public  String getPickData() {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(BASE_URL)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            //con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            System.out.println("data-------------"+buffer.toString());
            return buffer.toString();
        }
        catch(Exception t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
    private static double  getdouble(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getDouble(tagName);
    }
}

