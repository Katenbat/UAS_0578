package com.example.uaspcs_18120578.value.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uaspcs_18120578.R;
import com.example.uaspcs_18120578.adapter.HomeAdapter;
import com.example.uaspcs_18120578.model.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_riwayatpertandingan, container, false);

        final RecyclerView rcRiwayat = root.findViewById(R.id.rcRiwayat);


        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        String url ="https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328";

        final ArrayList<Home> home = new ArrayList<Home>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray events = obj.getJSONArray("events");
                            for(int x=0;x<events.length();x++){
                                JSONObject item = events.getJSONObject(x);
                                String matchTitle = item.getString("strEvent");
                                String awayScore = item.getString("intAwayScore");
                                String homeScore = item.getString("intHomeScore");
                                String matchDescription = item.getString("strFilename");
                                String image = item.getString("strThumb");
                                String matchId = item.getString("idEvent");
                                home.add(new Home(matchTitle,awayScore,homeScore,matchDescription,image,matchId));

                                HomeAdapter homeAdapter = new HomeAdapter(container.getContext(), home);


                                rcRiwayat.setAdapter(homeAdapter);
                                rcRiwayat.setLayoutManager(new LinearLayoutManager(container.getContext()));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });


        queue.add(stringRequest);


        return root;
    }
}