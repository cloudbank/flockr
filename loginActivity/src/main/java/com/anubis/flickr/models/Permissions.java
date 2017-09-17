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
    "permcomment",
    "permaddmeta"
})
public class Permissions {
  @JsonProperty("permcomment")
  private Integer permcomment;
  @JsonProperty("permaddmeta")
  private Integer permaddmeta;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * @return The permcomment
   */
  @JsonProperty("permcomment")
  public Integer getPermcomment() {
    return permcomment;
  }

  /**
   * @param permcomment The permcomment
   */
  @JsonProperty("permcomment")
  public void setPermcomment(Integer permcomment) {
    this.permcomment = permcomment;
  }

  /**
   * @return The permaddmeta
   */
  @JsonProperty("permaddmeta")
  public Integer getPermaddmeta() {
    return permaddmeta;
  }

  /**
   * @param permaddmeta The permaddmeta
   */
  @JsonProperty("permaddmeta")
  public void setPermaddmeta(Integer permaddmeta) {
    this.permaddmeta = permaddmeta;
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
