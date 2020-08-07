package com.findmeby.client.network;

import com.findmeby.client.network.model.Alarm;
import com.findmeby.client.network.model.CancelAlarm;
import com.findmeby.client.network.model.Contacts;
import com.findmeby.client.network.model.Response;

import java.util.concurrent.CompletableFuture;

import retrofit2.http.Body;
import retrofit2.http.POST;

interface CallService {
    @POST("registerContacts")
    CompletableFuture<Response> registerContacts(@Body Contacts contacts);

    @POST("triggerAlarm")
    CompletableFuture<Response> triggerAlarm(@Body Alarm alarm);

    @POST("cancelAlarm")
    CompletableFuture<Response> cancelAlarm(@Body CancelAlarm cancelAlarm);
}
