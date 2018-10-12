package com.abid.admin.bikeservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PickUpList extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_list);

        new getPickuplist().execute();

        //ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,)
        listView = findViewById(R.id.picklist);
        new getPickuplist().execute();


    }

    class  getPickuplist extends AsyncTask<Void,Void, List<Network>>{
    httpCalls httpcall;
    List<Network> list;

        @Override
        protected List<Network> doInBackground(Void... voids) {

             httpcall = new httpCalls();
           try {
               String str = httpcall.getPickData();
               list = httpcall.getpickuplist(str);

           }catch(Exception e){
               e.printStackTrace();
           }return list;
        }

        @Override
        protected void onPostExecute(List<Network> list) {
            super.onPostExecute(list);
            List<String> citylist = new ArrayList<String>();
            for(Network net : list){
                String city =net.getLocation().getCity();
                city =city+","+net.getLocation().getCountry();
                citylist.add(city);
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(PickUpList.this,R.layout.activity_listview,citylist);
            listView.setAdapter(arrayAdapter);

         }
    }
}
