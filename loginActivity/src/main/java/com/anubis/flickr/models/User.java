
package com.anubis.flickr.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "user",
    "stat"
})
public class User  {

   
    @JsonProperty("user")
    private UserModel user;
    @JsonProperty("stat")
    private String stat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



    /**
     * 
     * @return
     *     The user
     */
    @JsonProperty("user")
    public UserModel getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    @JsonProperty("user")
    public void setUser(UserModel user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The stat
     */
    @JsonProperty("stat")
    public String getStat() {
        return stat;
    }

    /**
     * 
     * @param stat
     *     The stat
     */
    @JsonProperty("stat")
    public void setStat(String stat) {
        this.stat = stat;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
