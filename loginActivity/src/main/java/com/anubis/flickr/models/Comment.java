
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
import io.realm.annotations.PrimaryKey;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "author",
    "author_is_deleted",
    "authorname",
    "iconserver",
    "iconfarm",
    "datecreate",
    "permalink",
    "path_alias",
    "realname",
    "_content"
})
public class Comment extends RealmObject {
    @PrimaryKey
    @JsonProperty("id")
    private String id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("author_is_deleted")
    private Integer authorIsDeleted;
    @JsonProperty("authorname")
    private String authorname;
    @JsonProperty("iconserver")
    private String iconserver;
    @JsonProperty("iconfarm")
    private Integer iconfarm;
    @JsonProperty("datecreate")
    private String datecreate;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("path_alias")
    private String pathAlias;
    @JsonProperty("realname")
    private String realname;

    @JsonProperty("_content")
    private String content;
    @JsonIgnore
    @Ignore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The authorIsDeleted
     */
    @JsonProperty("author_is_deleted")
    public Integer getAuthorIsDeleted() {
        return authorIsDeleted;
    }

    /**
     * 
     * @param authorIsDeleted
     *     The author_is_deleted
     */
    @JsonProperty("author_is_deleted")
    public void setAuthorIsDeleted(Integer authorIsDeleted) {
        this.authorIsDeleted = authorIsDeleted;
    }

    /**
     * 
     * @return
     *     The authorname
     */
    @JsonProperty("authorname")
    public String getAuthorname() {
        return authorname;
    }

    /**
     * 
     * @param authorname
     *     The authorname
     */
    @JsonProperty("authorname")
    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    /**
     * 
     * @return
     *     The iconserver
     */
    @JsonProperty("iconserver")
    public String getIconserver() {
        return iconserver;
    }

    /**
     * 
     * @param iconserver
     *     The iconserver
     */
    @JsonProperty("iconserver")
    public void setIconserver(String iconserver) {
        this.iconserver = iconserver;
    }

    /**
     * 
     * @return
     *     The iconfarm
     */
    @JsonProperty("iconfarm")
    public Integer getIconfarm() {
        return iconfarm;
    }

    /**
     * 
     * @param iconfarm
     *     The iconfarm
     */
    @JsonProperty("iconfarm")
    public void setIconfarm(Integer iconfarm) {
        this.iconfarm = iconfarm;
    }

    /**
     * 
     * @return
     *     The datecreate
     */
    @JsonProperty("datecreate")
    public String getDatecreate() {
        return datecreate;
    }

    /**
     * 
     * @param datecreate
     *     The datecreate
     */
    @JsonProperty("datecreate")
    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    /**
     * 
     * @return
     *     The permalink
     */
    @JsonProperty("permalink")
    public String getPermalink() {
        return permalink;
    }

    /**
     * 
     * @param permalink
     *     The permalink
     */
    @JsonProperty("permalink")
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * 
     * @return
     *     The pathAlias
     */
    @JsonProperty("path_alias")
    public String getPathAlias() {
        return pathAlias;
    }

    /**
     * 
     * @param pathAlias
     *     The path_alias
     */
    @JsonProperty("path_alias")
    public void setPathAlias(String pathAlias) {
        this.pathAlias = pathAlias;
    }

    /**
     * 
     * @return
     *     The realname
     */
    @JsonProperty("realname")
    public String getRealname() {
        return realname;
    }

    /**
     * 
     * @param realname
     *     The realname
     */
    @JsonProperty("realname")
    public void setRealname(String realname) {
        this.realname = realname;
    }

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
