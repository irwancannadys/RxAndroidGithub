package com.irwancannady.rxandroidgithub.service;

import com.irwancannady.rxandroidgithub.Example;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiService {

    @GET("users/{username}")
    Observable<Example> getUserInfo(@Path("username") String username);
}
