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
    "userId",
    "tags"
})
public class Who_ {
  public Who_() {
  }

  @JsonProperty("id")
  private String id;
  @JsonProperty("tags")
  private Tags tags;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * @return The userId
   */
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  /**
   * @param id The userId
   */
  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return The tags
   */
  @JsonProperty("tags")
  public Tags getTags() {
    return tags;
  }

  /**
   * @param tags The tags
   */
  @JsonProperty("tags")
  public void setTags(Tags tags) {
    this.tags = tags;
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
