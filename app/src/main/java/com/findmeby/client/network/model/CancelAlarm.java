
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
    "reason",
    "location",
    "originalTimestamp",
    "currentTimestamp"
})
public class CancelAlarm implements Serializable
{

    @JsonProperty("accountToken")
    private String accountToken;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("originalTimestamp")
    private Long originalTimestamp;
    @JsonProperty("currentTimestamp")
    private Long currentTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3256382293046808618L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CancelAlarm() {
    }

    /**
     * 
     * @param reason
     * @param currentTimestamp
     * @param location
     * @param accountToken
     * @param originalTimestamp
     */
    public CancelAlarm(String accountToken, String reason, Location location, Long originalTimestamp, Long currentTimestamp) {
        super();
        this.accountToken = accountToken;
        this.reason = reason;
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

    public CancelAlarm withAccountToken(String accountToken) {
        this.accountToken = accountToken;
        return this;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    public CancelAlarm withReason(String reason) {
        this.reason = reason;
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

    public CancelAlarm withLocation(Location location) {
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

    public CancelAlarm withOriginalTimestamp(Long originalTimestamp) {
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

    public CancelAlarm withCurrentTimestamp(Long currentTimestamp) {
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

    public CancelAlarm withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
