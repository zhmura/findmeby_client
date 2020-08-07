package com.findmeby.client.network.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lat",
    "lng",
    "timestamp"
})
public class Location implements Serializable
{

    @JsonProperty("lat")
    private Float lat;
    @JsonProperty("lng")
    private Float lng;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6929969771908486485L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location() {
    }

    /**
     * 
     * @param lng
     * @param lat
     * @param timestamp
     */
    public Location(Float lat, Float lng, Long timestamp) {
        super();
        this.lat = lat;
        this.lng = lng;
        this.timestamp = timestamp;
    }

    @JsonProperty("lat")
    public Float getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Location withLat(Float lat) {
        this.lat = lat;
        return this;
    }

    @JsonProperty("lng")
    public Float getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Location withLng(Float lng) {
        this.lng = lng;
        return this;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Location withTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Location withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
