
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
    "ispublic",
    "isfriend",
    "isfamily"
})
public class Visibility {

    @JsonProperty("ispublic")
    private Integer ispublic;
    @JsonProperty("isfriend")
    private Integer isfriend;
    @JsonProperty("isfamily")
    private Integer isfamily;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ispublic
     */
    @JsonProperty("ispublic")
    public Integer getIspublic() {
        return ispublic;
    }

    /**
     * 
     * @param ispublic
     *     The ispublic
     */
    @JsonProperty("ispublic")
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * 
     * @return
     *     The isfriend
     */
    @JsonProperty("isfriend")
    public Integer getIsfriend() {
        return isfriend;
    }

    /**
     * 
     * @param isfriend
     *     The isfriend
     */
    @JsonProperty("isfriend")
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    /**
     * 
     * @return
     *     The isfamily
     */
    @JsonProperty("isfamily")
    public Integer getIsfamily() {
        return isfamily;
    }

    /**
     * 
     * @param isfamily
     *     The isfamily
     */
    @JsonProperty("isfamily")
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
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
