
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
    "_content"
})
public class Username   {

    @JsonProperty("_content")
    public String content;
    @Ignore
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The content
     */
    @JsonProperty("_content")
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The _content
     */
    @JsonProperty("_content")
    public void setContent(String content) {
        this.content = content;
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
