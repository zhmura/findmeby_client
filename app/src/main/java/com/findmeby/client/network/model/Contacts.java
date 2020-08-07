package com.findmeby.client.network.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "contacts",
    "messageText",
    "userName",
    "sendPeriodicGeoData",
    "letContactsSeeGeoHistory",
    "currentTimestamp"
})
public class Contacts implements Serializable
{

    @JsonProperty("accountToken")
    private String accountToken;
    @JsonProperty("contacts")
    private List<String> contacts = new ArrayList<String>();
    @JsonProperty("messageText")
    private String messageText;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("sendPeriodicGeoData")
    private Boolean sendPeriodicGeoData;
    @JsonProperty("letContactsSeeGeoHistory")
    private Boolean letContactsSeeGeoHistory;
    @JsonProperty("currentTimestamp")
    private Long currentTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8165176120710818104L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Contacts() {
    }

    /**
     * 
     * @param messageText
     * @param letContactsSeeGeoHistory
     * @param currentTimestamp
     * @param accountToken
     * @param userName
     * @param contacts
     * @param sendPeriodicGeoData
     */
    public Contacts(String accountToken, List<String> contacts, String messageText, String userName, Boolean sendPeriodicGeoData, Boolean letContactsSeeGeoHistory, Long currentTimestamp) {
        this.accountToken = accountToken;
        this.contacts = contacts;
        this.messageText = messageText;
        this.userName = userName;
        this.sendPeriodicGeoData = sendPeriodicGeoData;
        this.letContactsSeeGeoHistory = letContactsSeeGeoHistory;
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

    public Contacts withAccountToken(String accountToken) {
        this.accountToken = accountToken;
        return this;
    }

    @JsonProperty("contacts")
    public List<String> getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public Contacts withContacts(List<String> contacts) {
        this.contacts = contacts;
        return this;
    }

    @JsonProperty("messageText")
    public String getMessageText() {
        return messageText;
    }

    @JsonProperty("messageText")
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Contacts withMessageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Contacts withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @JsonProperty("sendPeriodicGeoData")
    public Boolean getSendPeriodicGeoData() {
        return sendPeriodicGeoData;
    }

    @JsonProperty("sendPeriodicGeoData")
    public void setSendPeriodicGeoData(Boolean sendPeriodicGeoData) {
        this.sendPeriodicGeoData = sendPeriodicGeoData;
    }

    public Contacts withSendPeriodicGeoData(Boolean sendPeriodicGeoData) {
        this.sendPeriodicGeoData = sendPeriodicGeoData;
        return this;
    }

    @JsonProperty("letContactsSeeGeoHistory")
    public Boolean getLetContactsSeeGeoHistory() {
        return letContactsSeeGeoHistory;
    }

    @JsonProperty("letContactsSeeGeoHistory")
    public void setLetContactsSeeGeoHistory(Boolean letContactsSeeGeoHistory) {
        this.letContactsSeeGeoHistory = letContactsSeeGeoHistory;
    }

    public Contacts withLetContactsSeeGeoHistory(Boolean letContactsSeeGeoHistory) {
        this.letContactsSeeGeoHistory = letContactsSeeGeoHistory;
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

    public Contacts withCurrentTimestamp(Long currentTimestamp) {
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

    public Contacts withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
