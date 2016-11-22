
package com.anubis.phlix.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

import io.realm.annotations.Ignore;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hottags",
    "stat"
})
public class Hottags {


    public Hottags() {
    }

    @JsonProperty("hottags")

    private Hottags_ hottags;
    @JsonProperty("stat")
    private String stat;
    @JsonIgnore
    @Ignore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The hottags
     */
    @JsonProperty("hottags")
    public Hottags_ getHottags() {
        return hottags;
    }

    /**
     * 
     * @param hottags
     *     The hottags
     */
    @JsonProperty("hottags")
    public void setHottags(Hottags_ hottags) {
        this.hottags = hottags;
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
