
package com.anubis.flickr.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "username"
})
public class UserModel extends RealmObject  implements RealmModel {

    @JsonProperty("id")
    @PrimaryKey
    public String userId;

    @Ignore
    @JsonProperty("username")
    public Username username;

    public String name;

    public String getName() {
        return name;
    }



    public Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RealmList<Photo> friendsList;
    public RealmList<Tag>  tagsList;


    public RealmList<Photo> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(RealmList<Photo> friendsList) {
        this.friendsList = friendsList;
    }

    public RealmList<Tag> getTagsList() {
        return tagsList;
    }

    public void setTagsList(RealmList<Tag> tagsList) {
        this.tagsList = tagsList;
    }

    @Ignore
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     * @returnid
     *
     *     The userId
     */
    @JsonProperty("id")
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The userId
     */
    @JsonProperty("id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The username
     */
    @JsonProperty("username")
    public Username getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    @JsonProperty("username")
    public void setUsername(Username username) {
        this.username = username;
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
