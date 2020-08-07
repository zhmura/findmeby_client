package com.findmeby.client.network;

import com.findmeby.client.network.model.Alarm;
import com.findmeby.client.network.model.CancelAlarm;
import com.findmeby.client.network.model.Contacts;
import com.findmeby.client.network.model.Response;

import java.util.concurrent.CompletableFuture;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.schedulers.Schedulers;


public class BackendService {

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://back.findme-by.org/api/v1/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    CallService service  = retrofit.create(CallService.class);

    private static volatile BackendService instance;

    // private constructor : singleton access
    private BackendService() {
    }

    public static BackendService getInstance() {
        if (instance == null) {
            instance = new BackendService();
        }
        return instance;
    }


    public CompletableFuture<Response> registerContacts(Contacts contacts) {
        return service.registerContacts(contacts);
    }

    public CompletableFuture<Response> triggerAlarm(Alarm alarm) {
        return service.triggerAlarm(alarm);
    }

    public CompletableFuture<Response> cancelAlarm(CancelAlarm request) {
        return service.cancelAlarm(request);
    }
}
