
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
    "userId",
    "secret",
    "server",
    "farm",
    "dateuploaded",
    "isfavorite",
    "license",
    "safety_level",
    "rotation",
    "originalsecret",
    "originalformat",
    "owner",
    "title",
    "description",
    "visibility",
    "dates",
    "permissions",
    "views",
    "editability",
    "publiceditability",
    "usage",
    "comments",
    "notes",
    "people",
    "tags",
    "urls",
    "media"
})
public class PhotoInfo_ {

    @JsonProperty("id")
    private String id;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("server")
    private String server;
    @JsonProperty("farm")
    private Integer farm;
    @JsonProperty("dateuploaded")
    private String dateuploaded;
    @JsonProperty("isfavorite")
    private Integer isfavorite;
    @JsonProperty("license")
    private Integer license;
    @JsonProperty("safety_level")
    private Integer safetyLevel;
    @JsonProperty("rotation")
    private Integer rotation;
    @JsonProperty("originalsecret")
    private String originalsecret;
    @JsonProperty("originalformat")
    private String originalformat;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("description")
    private Description description;
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonProperty("dates")
    private Dates dates;
    @JsonProperty("permissions")
    private Permissions permissions;
    @JsonProperty("views")
    private Integer views;
    @JsonProperty("editability")
    private Editability editability;
    @JsonProperty("publiceditability")
    private Publiceditability publiceditability;
    @JsonProperty("usage")
    private Usage usage;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("notes")
    private Notes notes;
    @JsonProperty("people")
    private People people;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("urls")
    private Urls urls;
    @JsonProperty("media")
    private String media;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The userId
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The userId
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The secret
     */
    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    /**
     * 
     * @param secret
     *     The secret
     */
    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 
     * @return
     *     The server
     */
    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    /**
     * 
     * @param server
     *     The server
     */
    @JsonProperty("server")
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * 
     * @return
     *     The farm
     */
    @JsonProperty("farm")
    public Integer getFarm() {
        return farm;
    }

    /**
     * 
     * @param farm
     *     The farm
     */
    @JsonProperty("farm")
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    /**
     * 
     * @return
     *     The dateuploaded
     */
    @JsonProperty("dateuploaded")
    public String getDateuploaded() {
        return dateuploaded;
    }

    /**
     * 
     * @param dateuploaded
     *     The dateuploaded
     */
    @JsonProperty("dateuploaded")
    public void setDateuploaded(String dateuploaded) {
        this.dateuploaded = dateuploaded;
    }

    /**
     * 
     * @return
     *     The isfavorite
     */
    @JsonProperty("isfavorite")
    public Integer getIsfavorite() {
        return isfavorite;
    }

    /**
     * 
     * @param isfavorite
     *     The isfavorite
     */
    @JsonProperty("isfavorite")
    public void setIsfavorite(Integer isfavorite) {
        this.isfavorite = isfavorite;
    }

    /**
     * 
     * @return
     *     The license
     */
    @JsonProperty("license")
    public Integer getLicense() {
        return license;
    }

    /**
     * 
     * @param license
     *     The license
     */
    @JsonProperty("license")
    public void setLicense(Integer license) {
        this.license = license;
    }

    /**
     * 
     * @return
     *     The safetyLevel
     */
    @JsonProperty("safety_level")
    public Integer getSafetyLevel() {
        return safetyLevel;
    }

    /**
     * 
     * @param safetyLevel
     *     The safety_level
     */
    @JsonProperty("safety_level")
    public void setSafetyLevel(Integer safetyLevel) {
        this.safetyLevel = safetyLevel;
    }

    /**
     * 
     * @return
     *     The rotation
     */
    @JsonProperty("rotation")
    public Integer getRotation() {
        return rotation;
    }

    /**
     * 
     * @param rotation
     *     The rotation
     */
    @JsonProperty("rotation")
    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    /**
     * 
     * @return
     *     The originalsecret
     */
    @JsonProperty("originalsecret")
    public String getOriginalsecret() {
        return originalsecret;
    }

    /**
     * 
     * @param originalsecret
     *     The originalsecret
     */
    @JsonProperty("originalsecret")
    public void setOriginalsecret(String originalsecret) {
        this.originalsecret = originalsecret;
    }

    /**
     * 
     * @return
     *     The originalformat
     */
    @JsonProperty("originalformat")
    public String getOriginalformat() {
        return originalformat;
    }

    /**
     * 
     * @param originalformat
     *     The originalformat
     */
    @JsonProperty("originalformat")
    public void setOriginalformat(String originalformat) {
        this.originalformat = originalformat;
    }

    /**
     * 
     * @return
     *     The owner
     */
    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public Description getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The visibility
     */
    @JsonProperty("visibility")
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * 
     * @param visibility
     *     The visibility
     */
    @JsonProperty("visibility")
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    /**
     * 
     * @return
     *     The dates
     */
    @JsonProperty("dates")
    public Dates getDates() {
        return dates;
    }

    /**
     * 
     * @param dates
     *     The dates
     */
    @JsonProperty("dates")
    public void setDates(Dates dates) {
        this.dates = dates;
    }

    /**
     * 
     * @return
     *     The permissions
     */
    @JsonProperty("permissions")
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * 
     * @param permissions
     *     The permissions
     */
    @JsonProperty("permissions")
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    /**
     * 
     * @return
     *     The views
     */
    @JsonProperty("views")
    public Integer getViews() {
        return views;
    }

    /**
     * 
     * @param views
     *     The views
     */
    @JsonProperty("views")
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 
     * @return
     *     The editability
     */
    @JsonProperty("editability")
    public Editability getEditability() {
        return editability;
    }

    /**
     * 
     * @param editability
     *     The editability
     */
    @JsonProperty("editability")
    public void setEditability(Editability editability) {
        this.editability = editability;
    }

    /**
     * 
     * @return
     *     The publiceditability
     */
    @JsonProperty("publiceditability")
    public Publiceditability getPubliceditability() {
        return publiceditability;
    }

    /**
     * 
     * @param publiceditability
     *     The publiceditability
     */
    @JsonProperty("publiceditability")
    public void setPubliceditability(Publiceditability publiceditability) {
        this.publiceditability = publiceditability;
    }

    /**
     * 
     * @return
     *     The usage
     */
    @JsonProperty("usage")
    public Usage getUsage() {
        return usage;
    }

    /**
     * 
     * @param usage
     *     The usage
     */
    @JsonProperty("usage")
    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    /**
     * 
     * @return
     *     The comments
     */
    @JsonProperty("comments")
    public Comments getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    @JsonProperty("comments")
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    /**
     * 
     * @return
     *     The notes
     */
    @JsonProperty("notes")
    public Notes getNotes() {
        return notes;
    }

    /**
     * 
     * @param notes
     *     The notes
     */
    @JsonProperty("notes")
    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    /**
     * 
     * @return
     *     The people
     */
    @JsonProperty("people")
    public People getPeople() {
        return people;
    }

    /**
     * 
     * @param people
     *     The people
     */
    @JsonProperty("people")
    public void setPeople(People people) {
        this.people = people;
    }

    /**
     * 
     * @return
     *     The tags
     */
    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The urls
     */
    @JsonProperty("urls")
    public Urls getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    @JsonProperty("urls")
    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    /**
     * 
     * @return
     *     The media
     */
    @JsonProperty("media")
    public String getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    @JsonProperty("media")
    public void setMedia(String media) {
        this.media = media;
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
