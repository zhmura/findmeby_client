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
    "accountToken",
    "location",
    "originalTimestamp",
    "currentTimestamp"
})
public class Alarm implements Serializable
{

    @JsonProperty("accountToken")
    private String accountToken;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("originalTimestamp")
    private Long originalTimestamp;
    @JsonProperty("currentTimestamp")
    private Long currentTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6691495119983736993L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Alarm() {
    }

    /**
     * 
     * @param currentTimestamp
     * @param location
     * @param accountToken
     * @param originalTimestamp
     */
    public Alarm(String accountToken, Location location, Long originalTimestamp, Long currentTimestamp) {
        super();
        this.accountToken = accountToken;
        this.location = location;
        this.originalTimestamp = originalTimestamp;
        this.currentTimestamp = currentTimestamp;
    }

    @JsonProperty("accountToken")
    public String getAccountToken() {
        return accountToken;
    }

    @JsonProperty("accountToken")
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public Alarm withAccountToken(String accountToken) {
        this.accountToken = accountToken;
        return this;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public Alarm withLocation(Location location) {
        this.location = location;
        return this;
    }

    @JsonProperty("originalTimestamp")
    public Long getOriginalTimestamp() {
        return originalTimestamp;
    }

    @JsonProperty("originalTimestamp")
    public void setOriginalTimestamp(Long originalTimestamp) {
        this.originalTimestamp = originalTimestamp;
    }

    public Alarm withOriginalTimestamp(Long originalTimestamp) {
        this.originalTimestamp = originalTimestamp;
        return this;
    }

    @JsonProperty("currentTimestamp")
    public Long getCurrentTimestamp() {
        return currentTimestamp;
    }

    @JsonProperty("currentTimestamp")
    public void setCurrentTimestamp(Long currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public Alarm withCurrentTimestamp(Long currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
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

    public Alarm withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
