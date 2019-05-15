package com.example.movieomdbapp;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieomdbapp.api.MovieService;
import com.example.movieomdbapp.model.Filme;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView title, year;
    ImageView image;
    EditText name;
    Button button;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        year = findViewById(R.id.year);
        name = findViewById(R.id.movie);
    }

    public void search(View view) {
//      USANDO LIB VOLLEY NATIVA DO ANDROID
//        String mName = name.getText().toString();
//
//        if(mName.isEmpty()){
//            name.setError("por favor escreva um nome de filme");
//            return;
//        }
//
//        String url = "http://www.omdbapi.com/?t=" + mName + "&plot=full&apikey=dfc3c601";
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, url,
//
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject movie = new JSONObject(response);
//
//                            String result = movie.getString("Response");
//
//                            if(result.equals("true")){
//
//                                Toast.makeText(MainActivity.this, "Filme encontrado", Toast.LENGTH_SHORT).show();
//
//                            }else {
//                                Toast.makeText(MainActivity.this, "Filme não encontrado", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//
//        queue.add(request);
        String mName = name.getText().toString();

        if(mName.isEmpty()){
            name.setError("por favor escreva um nome de filme");
            return;
        }

        String url = "http://www.omdbapi.com/";
        retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<Filme> call = movieService.recuperarFilme(mName);

        call.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                if(response.isSuccessful()){
                    Filme filme = response.body();
                    title.setText(filme.getTitle());
                    year.setText(filme.getYear());
                }
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {

            }
        });
    }
}
