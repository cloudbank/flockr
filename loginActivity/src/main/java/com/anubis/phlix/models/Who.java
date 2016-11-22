
package com.anubis.phlix.models;

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
    "who",
    "stat"
})
public class Who {
    public Who() {
    }

    @JsonProperty("who")
    private Who_ who;
    @JsonProperty("stat")
    private String stat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The who
     */
    @JsonProperty("who")
    public Who_ getWho() {
        return who;
    }

    /**
     * 
     * @param who
     *     The who
     */
    @JsonProperty("who")
    public void setWho(Who_ who) {
        this.who = who;
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
