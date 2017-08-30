package com.anubis.flickr.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "author",
        "authorname",
        "raw",
        "_content",
        "machine_tag"
})
public class Tag extends RealmObject {

    @JsonProperty("id")
    private String id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("authorname")
    private String authorname;
    @JsonProperty("raw")
    private String raw;
    @JsonProperty("_content")
    private String content;
    @JsonProperty("machine_tag")
    private boolean machineTag;

    @JsonIgnore
    @Ignore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The userId
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The userId
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The authorname
     */
    @JsonProperty("authorname")
    public String getAuthorname() {
        return authorname;
    }

    /**
     *
     * @param authorname
     * The authorname
     */
    @JsonProperty("authorname")
    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    /**
     *
     * @return
     * The raw
     */
    @JsonProperty("raw")
    public String getRaw() {
        return raw;
    }

    /**
     *
     * @param raw
     * The raw
     */
    @JsonProperty("raw")
    public void setRaw(String raw) {
        this.raw = raw;
    }

    /**
     *
     * @return
     * The content
     */
    @JsonProperty("_content")
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The _content
     */
    @JsonProperty("_content")
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     * The machineTag
     */
    @JsonProperty("machine_tag")
    public boolean getMachineTag() {
        return machineTag;
    }

    /**
     *
     * @param machineTag
     * The machine_tag
     */
    @JsonProperty("machine_tag")
    public void setMachineTag(boolean machineTag) {
        this.machineTag = machineTag;
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
