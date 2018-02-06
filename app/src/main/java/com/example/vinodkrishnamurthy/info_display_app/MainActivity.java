package com.example.vinodkrishnamurthy.info_display_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String DATA_URL = "https://guidebook.com/service/v2/upcomingGuides/";

    // recycler view to display guides
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    // list of guides
    private List<Guides> guidesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initialize list of Guides and adapter for recycler view
        guidesList = new ArrayList<>();
        adapter = new GuidesAdapter(guidesList, this);
        recyclerView.setAdapter(adapter);

        // retrieve JSON formatted data
        loadURLdata();
    }

    private void loadURLdata(){

        // show progress dialog to user
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("LOADING");
        dialog.show();

        // use Volley to request JSON data as a string
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                DATA_URL ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    // retrieve data array
                    JSONObject rootObject = new JSONObject(response);
                    JSONArray array = rootObject.getJSONArray("data");
                    // loop through array and parse JSON
                    for (int i = 0; i < array.length(); i++){
                        JSONObject guide_obj = array.getJSONObject(i);
                        // get nested venue object
                        JSONObject venue_object = guide_obj.getJSONObject("venue");

                        // if venue object is empty, print N/A when applicable
                        if (venue_object.length() == 0) {
                            Guides guide = new Guides(guide_obj.getString("name"),
                                    "City: N/A",
                                    "State: N/A",
                                    "End date: "+ guide_obj.getString("endDate"),
                                    guide_obj.getString("icon"));
                            guidesList.add(guide);
                        } else { // else retrieve city and state data from venue object
                            Guides guide = new Guides(guide_obj.getString("name"),
                                    "City: " + venue_object.getString("city"),
                                    "State: " + venue_object.getString("state"),
                                    "End date: " + guide_obj.getString("endDate"),
                                    guide_obj.getString("icon"));
                            guidesList.add(guide);
                        }
                    }
                    // set adapter again after data retrieval
                    adapter = new GuidesAdapter(guidesList, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // show error text when there is a problem with retrieving the data
                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

//    public static void main(String[] args) throws IOException {
//
//        // Connect to Guidebook URL
//        //String string_URL = "https://guidebook.com/service/v2/upcomingGuides/";
//
//        URL url = new URL(DATA_URL);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.connect();
//
//        // Convert to a JSON object to print data
//        JsonParser jp = new JsonParser(); //from gson
//        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
//
//        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
//
//        // get name
//        String name = rootobj.get("name").getAsString();
//
//        // get end date
//        String endDate = rootobj.get("endDate").getAsString();
//
//        // get icon
//        String img_url = rootobj.get("icon").getAsString();
//
//    }

}
