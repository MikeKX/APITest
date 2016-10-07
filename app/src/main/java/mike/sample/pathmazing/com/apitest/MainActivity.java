package mike.sample.pathmazing.com.apitest;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ADSAdapter adapter;
    private ArrayList<ADS> mArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String URL="http://api.sellfasting.com/ads/ads_all";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this,"ABC",Toast.LENGTH_SHORT).show();
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, "http://api.sellfasting.com/ads/ads_all", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getApplicationContext(),"abc",Toast.LENGTH_SHORT).show();
               JSONArray jsonArray=response;

                if(null != jsonArray){
                    mArrayList=new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject item_ads;
                      try{
                         item_ads=(JSONObject)jsonArray.get(i);

                          //String ads_name=item_ads.getString("Ads_name");
//                          Log.d("","");
//                          ADS ads=new ADS();
//                          ads.setAds_name(ads_name);
//                          mArrayList.add(ads);

                          Gson gson = new GsonBuilder().create();
                          ADS mADds= gson.fromJson(item_ads.toString(),ADS.class);
                          mArrayList.add(mADds);
                          Toast.makeText(getApplicationContext(),"123",Toast.LENGTH_SHORT).show();
                          Log.d("data","name_Log" +mADds.getAds_url());

                        }catch (JSONException e){
                          e.printStackTrace();
                      }
                        Log.d("MainActivity","Result:" +response.toString());
                    }

                    listView=(ListView)findViewById(R.id.listview);
                    adapter=new ADSAdapter(mArrayList,getApplicationContext());
                    listView.setAdapter(adapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MainActivity", "Error" +error.toString());
            }
        });
       /* JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, "http://api.sellfasting.com/ads/ads_all",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Toast.makeText(getApplicationContext(),"abc",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });*/
       AppController.getInstance().addToRequestQueue(jsonArrayRequest);



    }
    public class ADSMian{
        public ADS[] ads;
        }
    public class ADS{
        public int id;
        public String ads_name;

        public String getAds_name() {
            return ads_name;
        }

        public void setAds_name(String ads_name) {
            this.ads_name = ads_name;
        }

        public String getAds_url() {
            return ads_url;
        }

        public void setAds_url(String ads_url) {
            this.ads_url = ads_url;
        }

        public String getAds_type() {
            return ads_type;
        }

        public void setAds_type(String ads_type) {
            this.ads_type = ads_type;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getStatus_cd() {
            return status_cd;
        }

        public void setStatus_cd(String status_cd) {
            this.status_cd = status_cd;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String ads_url;
        public String ads_type;
        public String created_date;
        public String status_cd;
    }


}
