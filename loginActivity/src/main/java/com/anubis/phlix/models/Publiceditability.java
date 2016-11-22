
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
    "cancomment",
    "canaddmeta"
})
public class Publiceditability {

    @JsonProperty("cancomment")
    private Integer cancomment;
    @JsonProperty("canaddmeta")
    private Integer canaddmeta;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The cancomment
     */
    @JsonProperty("cancomment")
    public Integer getCancomment() {
        return cancomment;
    }

    /**
     * 
     * @param cancomment
     *     The cancomment
     */
    @JsonProperty("cancomment")
    public void setCancomment(Integer cancomment) {
        this.cancomment = cancomment;
    }

    /**
     * 
     * @return
     *     The canaddmeta
     */
    @JsonProperty("canaddmeta")
    public Integer getCanaddmeta() {
        return canaddmeta;
    }

    /**
     * 
     * @param canaddmeta
     *     The canaddmeta
     */
    @JsonProperty("canaddmeta")
    public void setCanaddmeta(Integer canaddmeta) {
        this.canaddmeta = canaddmeta;
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
