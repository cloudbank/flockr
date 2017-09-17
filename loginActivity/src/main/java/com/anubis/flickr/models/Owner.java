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
    "nsid",
    "username",
    "realname",
    "location",
    "iconserver",
    "iconfarm",
    "path_alias"
})
public class Owner {
  @JsonProperty("nsid")
  private String nsid;
  @JsonProperty("username")
  private String username;
  @JsonProperty("realname")
  private String realname;
  @JsonProperty("location")
  private String location;
  @JsonProperty("iconserver")
  private Integer iconserver;
  @JsonProperty("iconfarm")
  private Integer iconfarm;
  @JsonProperty("path_alias")
  private String pathAlias;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * @return The nsid
   */
  @JsonProperty("nsid")
  public String getNsid() {
    return nsid;
  }

  /**
   * @param nsid The nsid
   */
  @JsonProperty("nsid")
  public void setNsid(String nsid) {
    this.nsid = nsid;
  }

  /**
   * @return The username
   */
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  /**
   * @param username The username
   */
  @JsonProperty("username")
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return The realname
   */
  @JsonProperty("realname")
  public String getRealname() {
    return realname;
  }

  /**
   * @param realname The realname
   */
  @JsonProperty("realname")
  public void setRealname(String realname) {
    this.realname = realname;
  }

  /**
   * @return The location
   */
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  /**
   * @param location The location
   */
  @JsonProperty("location")
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return The iconserver
   */
  @JsonProperty("iconserver")
  public Integer getIconserver() {
    return iconserver;
  }

  /**
   * @param iconserver The iconserver
   */
  @JsonProperty("iconserver")
  public void setIconserver(Integer iconserver) {
    this.iconserver = iconserver;
  }

  /**
   * @return The iconfarm
   */
  @JsonProperty("iconfarm")
  public Integer getIconfarm() {
    return iconfarm;
  }

  /**
   * @param iconfarm The iconfarm
   */
  @JsonProperty("iconfarm")
  public void setIconfarm(Integer iconfarm) {
    this.iconfarm = iconfarm;
  }

  /**
   * @return The pathAlias
   */
  @JsonProperty("path_alias")
  public String getPathAlias() {
    return pathAlias;
  }

  /**
   * @param pathAlias The path_alias
   */
  @JsonProperty("path_alias")
  public void setPathAlias(String pathAlias) {
    this.pathAlias = pathAlias;
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
