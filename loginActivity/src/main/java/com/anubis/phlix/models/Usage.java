
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
    "candownload",
    "canblog",
    "canprint",
    "canshare"
})
public class Usage {

    @JsonProperty("candownload")
    private Integer candownload;
    @JsonProperty("canblog")
    private Integer canblog;
    @JsonProperty("canprint")
    private Integer canprint;
    @JsonProperty("canshare")
    private Integer canshare;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The candownload
     */
    @JsonProperty("candownload")
    public Integer getCandownload() {
        return candownload;
    }

    /**
     * 
     * @param candownload
     *     The candownload
     */
    @JsonProperty("candownload")
    public void setCandownload(Integer candownload) {
        this.candownload = candownload;
    }

    /**
     * 
     * @return
     *     The canblog
     */
    @JsonProperty("canblog")
    public Integer getCanblog() {
        return canblog;
    }

    /**
     * 
     * @param canblog
     *     The canblog
     */
    @JsonProperty("canblog")
    public void setCanblog(Integer canblog) {
        this.canblog = canblog;
    }

    /**
     * 
     * @return
     *     The canprint
     */
    @JsonProperty("canprint")
    public Integer getCanprint() {
        return canprint;
    }

    /**
     * 
     * @param canprint
     *     The canprint
     */
    @JsonProperty("canprint")
    public void setCanprint(Integer canprint) {
        this.canprint = canprint;
    }

    /**
     * 
     * @return
     *     The canshare
     */
    @JsonProperty("canshare")
    public Integer getCanshare() {
        return canshare;
    }

    /**
     * 
     * @param canshare
     *     The canshare
     */
    @JsonProperty("canshare")
    public void setCanshare(Integer canshare) {
        this.canshare = canshare;
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
