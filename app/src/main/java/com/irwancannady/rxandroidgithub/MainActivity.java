package com.irwancannady.rxandroidgithub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.irwancannady.rxandroidgithub.service.ApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://api.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView text = (TextView) findViewById(R.id.tes);
//        Example ex = new Example();
//        String username = ex.getName();
//        text.setText("name: " + username);
//
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        ApiService api =  retrofit.create(ApiService.class);
        Observable<Example> obj = api.getUserInfo("irwancannadys");

        obj.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(user -> "Github Username : " + user.getName() + "\nUrl : " + " " + user.getUrl())
                .subscribe(userInfo -> Toast.makeText(this, userInfo, Toast.LENGTH_LONG).show());

    }
}
