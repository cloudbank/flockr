
package com.anubis.flickr.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmList;
import io.realm.annotations.Ignore;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "period",
    "count",
    "tag"
})
public class Hottags_  {

    public Hottags_() {
    }

    @JsonProperty("period")

    private String period;
    @JsonProperty("count")
    private Integer count;

    private RealmList<Tag> tagsList;

    @Ignore
    @JsonProperty("tag")
    private List<Tag> tag = new ArrayList<Tag>();
    @JsonIgnore
    @Ignore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The period
     */
    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    /**
     * 
     * @param period
     *     The period
     */
    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * 
     * @return
     *     The count
     */
    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The tag
     */
    @JsonProperty("tag")
    public List<Tag> getTag() {
        return tag;
    }

    /**
     * 
     * @param tag
     *     The tag
     */
    @JsonProperty("tag")
    public void setTag(List<Tag> tag) {
        this.tag = tag;
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
