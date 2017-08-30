
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
    "posted",
    "taken",
    "takengranularity",
    "takenunknown",
    "lastupdate"
})
public class Dates {

    @JsonProperty("posted")
    private String posted;
    @JsonProperty("taken")
    private String taken;
    @JsonProperty("takengranularity")
    private Integer takengranularity;
    @JsonProperty("takenunknown")
    private Integer takenunknown;
    @JsonProperty("lastupdate")
    private String lastupdate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The posted
     */
    @JsonProperty("posted")
    public String getPosted() {
        return posted;
    }

    /**
     * 
     * @param posted
     *     The posted
     */
    @JsonProperty("posted")
    public void setPosted(String posted) {
        this.posted = posted;
    }

    /**
     * 
     * @return
     *     The taken
     */
    @JsonProperty("taken")
    public String getTaken() {
        return taken;
    }

    /**
     * 
     * @param taken
     *     The taken
     */
    @JsonProperty("taken")
    public void setTaken(String taken) {
        this.taken = taken;
    }

    /**
     * 
     * @return
     *     The takengranularity
     */
    @JsonProperty("takengranularity")
    public Integer getTakengranularity() {
        return takengranularity;
    }

    /**
     * 
     * @param takengranularity
     *     The takengranularity
     */
    @JsonProperty("takengranularity")
    public void setTakengranularity(Integer takengranularity) {
        this.takengranularity = takengranularity;
    }

    /**
     * 
     * @return
     *     The takenunknown
     */
    @JsonProperty("takenunknown")
    public Integer getTakenunknown() {
        return takenunknown;
    }

    /**
     * 
     * @param takenunknown
     *     The takenunknown
     */
    @JsonProperty("takenunknown")
    public void setTakenunknown(Integer takenunknown) {
        this.takenunknown = takenunknown;
    }

    /**
     * 
     * @return
     *     The lastupdate
     */
    @JsonProperty("lastupdate")
    public String getLastupdate() {
        return lastupdate;
    }

    /**
     * 
     * @param lastupdate
     *     The lastupdate
     */
    @JsonProperty("lastupdate")
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
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
