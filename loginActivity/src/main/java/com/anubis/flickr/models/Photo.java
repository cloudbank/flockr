
package com.anubis.flickr.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "secret",
        "server",
        "farm",
        "owner",
        "username",
        "title",
        "ispublic",
        "isfriend",
        "isfamily",
        "datetaken",
        "datetakengranularity",
        "datetakenunknown",
        "ownername",
        "tags",
        "url_s",
        "height_s",
        "width_s"
})

public class Photo extends RealmObject implements Serializable {

    @JsonProperty("id")
    @PrimaryKey
    private String id;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("server")
    private String server;
    @JsonProperty("farm")
    private Integer farm;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("username")
    private String username;
    @JsonProperty("title")
    private String title;
    @JsonProperty("ispublic")
    private Integer ispublic;
    @JsonProperty("isfriend")
    private Integer isfriend;
    @JsonProperty("isfamily")
    private Integer isfamily;
    @JsonProperty("datetaken")
    private String datetaken;
    @JsonProperty("datetakengranularity")
    private String datetakengranularity;
    @JsonProperty("datetakenunknown")
    private Integer datetakenunknown;
    @JsonProperty("ownername")
    private String ownername;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("url_s")
    private String url_s;
    @JsonProperty("height_s")
    private String height;
    @JsonProperty("width_s")
    private String width;

    public boolean isCommon = false;
    public boolean isInteresting = false;

    public String getHeight() {
        return height;
    }

    public String getUrl_s() {
        return url_s;
    }

    public void setUrl_s(String url_s) {
        this.url_s = url_s;
    }

    public void setHeight(String height) {
        this.height = height;

    }

    public String getWidth() {
        return width;
    }

    private String url;


    @Ignore
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    void setUrl(Photo p) {

        this.url = "http://farm" + p.getFarm()
                + ".staticflickr.com/" + p.getServer() + "/"
                + getId() + "_" + p.getSecret() + ".jpg";


    }

    public String getUrl() {
        return "http://farm" + this.getFarm()
                + ".staticflickr.com/" + this.getServer() + "/"
                + getId() + "_" + this.getSecret() + ".jpg";

    }



    public String getPhotoPage() {
        return "https://www.flickr.com/photos/" + this.getOwner() + "/" + this.getId();

    }

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
     * @return The secret
     */
    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret The secret
     */
    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return The server
     */
    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    /**
     * @param server The server
     */
    @JsonProperty("server")
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return The farm
     */
    @JsonProperty("farm")
    public Integer getFarm() {
        return farm;
    }

    /**
     * @param farm The farm
     */
    @JsonProperty("farm")
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    /**
     * @return The owner
     */
    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner The owner
     */
    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
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
     * @return The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The ispublic
     */
    @JsonProperty("ispublic")
    public Integer getIspublic() {
        return ispublic;
    }

    /**
     * @param ispublic The ispublic
     */
    @JsonProperty("ispublic")
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * @return The isfriend
     */
    @JsonProperty("isfriend")
    public Integer getIsfriend() {
        return isfriend;
    }

    /**
     * @param isfriend The isfriend
     */
    @JsonProperty("isfriend")
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    /**
     * @return The isfamily
     */
    @JsonProperty("isfamily")
    public Integer getIsfamily() {
        return isfamily;
    }

    /**
     * @param isfamily The isfamily
     */
    @JsonProperty("isfamily")
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    /**
     * @return The datetaken
     */
    @JsonProperty("datetaken")
    public String getDatetaken() {
        return datetaken;
    }

    /**
     * @param datetaken The datetaken
     */
    @JsonProperty("datetaken")
    public void setDatetaken(String datetaken) {
        this.datetaken = datetaken;
    }

    /**
     * @return The datetakengranularity
     */
    @JsonProperty("datetakengranularity")
    public String getDatetakengranularity() {
        return datetakengranularity;
    }

    /**
     * @param datetakengranularity The datetakengranularity
     */
    @JsonProperty("datetakengranularity")
    public void setDatetakengranularity(String datetakengranularity) {
        this.datetakengranularity = datetakengranularity;
    }

    /**
     * @return The datetakenunknown
     */
    @JsonProperty("datetakenunknown")
    public Integer getDatetakenunknown() {
        return datetakenunknown;
    }

    /**
     * @param datetakenunknown The datetakenunknown
     */
    @JsonProperty("datetakenunknown")
    public void setDatetakenunknown(Integer datetakenunknown) {
        this.datetakenunknown = datetakenunknown;
    }

    /**
     * @return The tags
     */
    @JsonProperty("tags")
    public String getTags() {
        return tags;
    }

    @JsonProperty("tags")
    /**
     * @param
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return The ownername
     */
    @JsonProperty("ownername")
    public String getOwnername() {
        return ownername;
    }

    /**
     * @param ownername The ownername
     */
    @JsonProperty("ownername")
    public void setOwnername(String ownername) {
        this.ownername = ownername;
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
