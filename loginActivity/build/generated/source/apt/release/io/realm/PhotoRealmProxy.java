package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhotoRealmProxy extends com.anubis.phlix.models.Photo
    implements RealmObjectProxy, PhotoRealmProxyInterface {

    static final class PhotoColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long secretIndex;
        public long serverIndex;
        public long farmIndex;
        public long ownerIndex;
        public long usernameIndex;
        public long titleIndex;
        public long ispublicIndex;
        public long isfriendIndex;
        public long isfamilyIndex;
        public long datetakenIndex;
        public long datetakengranularityIndex;
        public long datetakenunknownIndex;
        public long ownernameIndex;
        public long tagsIndex;
        public long url_sIndex;
        public long heightIndex;
        public long widthIndex;
        public long isCommonIndex;
        public long isInterestingIndex;
        public long urlIndex;

        PhotoColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(21);
            this.idIndex = getValidColumnIndex(path, table, "Photo", "id");
            indicesMap.put("id", this.idIndex);
            this.secretIndex = getValidColumnIndex(path, table, "Photo", "secret");
            indicesMap.put("secret", this.secretIndex);
            this.serverIndex = getValidColumnIndex(path, table, "Photo", "server");
            indicesMap.put("server", this.serverIndex);
            this.farmIndex = getValidColumnIndex(path, table, "Photo", "farm");
            indicesMap.put("farm", this.farmIndex);
            this.ownerIndex = getValidColumnIndex(path, table, "Photo", "owner");
            indicesMap.put("owner", this.ownerIndex);
            this.usernameIndex = getValidColumnIndex(path, table, "Photo", "username");
            indicesMap.put("username", this.usernameIndex);
            this.titleIndex = getValidColumnIndex(path, table, "Photo", "title");
            indicesMap.put("title", this.titleIndex);
            this.ispublicIndex = getValidColumnIndex(path, table, "Photo", "ispublic");
            indicesMap.put("ispublic", this.ispublicIndex);
            this.isfriendIndex = getValidColumnIndex(path, table, "Photo", "isfriend");
            indicesMap.put("isfriend", this.isfriendIndex);
            this.isfamilyIndex = getValidColumnIndex(path, table, "Photo", "isfamily");
            indicesMap.put("isfamily", this.isfamilyIndex);
            this.datetakenIndex = getValidColumnIndex(path, table, "Photo", "datetaken");
            indicesMap.put("datetaken", this.datetakenIndex);
            this.datetakengranularityIndex = getValidColumnIndex(path, table, "Photo", "datetakengranularity");
            indicesMap.put("datetakengranularity", this.datetakengranularityIndex);
            this.datetakenunknownIndex = getValidColumnIndex(path, table, "Photo", "datetakenunknown");
            indicesMap.put("datetakenunknown", this.datetakenunknownIndex);
            this.ownernameIndex = getValidColumnIndex(path, table, "Photo", "ownername");
            indicesMap.put("ownername", this.ownernameIndex);
            this.tagsIndex = getValidColumnIndex(path, table, "Photo", "tags");
            indicesMap.put("tags", this.tagsIndex);
            this.url_sIndex = getValidColumnIndex(path, table, "Photo", "url_s");
            indicesMap.put("url_s", this.url_sIndex);
            this.heightIndex = getValidColumnIndex(path, table, "Photo", "height");
            indicesMap.put("height", this.heightIndex);
            this.widthIndex = getValidColumnIndex(path, table, "Photo", "width");
            indicesMap.put("width", this.widthIndex);
            this.isCommonIndex = getValidColumnIndex(path, table, "Photo", "isCommon");
            indicesMap.put("isCommon", this.isCommonIndex);
            this.isInterestingIndex = getValidColumnIndex(path, table, "Photo", "isInteresting");
            indicesMap.put("isInteresting", this.isInterestingIndex);
            this.urlIndex = getValidColumnIndex(path, table, "Photo", "url");
            indicesMap.put("url", this.urlIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final PhotoColumnInfo otherInfo = (PhotoColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.secretIndex = otherInfo.secretIndex;
            this.serverIndex = otherInfo.serverIndex;
            this.farmIndex = otherInfo.farmIndex;
            this.ownerIndex = otherInfo.ownerIndex;
            this.usernameIndex = otherInfo.usernameIndex;
            this.titleIndex = otherInfo.titleIndex;
            this.ispublicIndex = otherInfo.ispublicIndex;
            this.isfriendIndex = otherInfo.isfriendIndex;
            this.isfamilyIndex = otherInfo.isfamilyIndex;
            this.datetakenIndex = otherInfo.datetakenIndex;
            this.datetakengranularityIndex = otherInfo.datetakengranularityIndex;
            this.datetakenunknownIndex = otherInfo.datetakenunknownIndex;
            this.ownernameIndex = otherInfo.ownernameIndex;
            this.tagsIndex = otherInfo.tagsIndex;
            this.url_sIndex = otherInfo.url_sIndex;
            this.heightIndex = otherInfo.heightIndex;
            this.widthIndex = otherInfo.widthIndex;
            this.isCommonIndex = otherInfo.isCommonIndex;
            this.isInterestingIndex = otherInfo.isInterestingIndex;
            this.urlIndex = otherInfo.urlIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final PhotoColumnInfo clone() {
            return (PhotoColumnInfo) super.clone();
        }

    }
    private PhotoColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("secret");
        fieldNames.add("server");
        fieldNames.add("farm");
        fieldNames.add("owner");
        fieldNames.add("username");
        fieldNames.add("title");
        fieldNames.add("ispublic");
        fieldNames.add("isfriend");
        fieldNames.add("isfamily");
        fieldNames.add("datetaken");
        fieldNames.add("datetakengranularity");
        fieldNames.add("datetakenunknown");
        fieldNames.add("ownername");
        fieldNames.add("tags");
        fieldNames.add("url_s");
        fieldNames.add("height");
        fieldNames.add("width");
        fieldNames.add("isCommon");
        fieldNames.add("isInteresting");
        fieldNames.add("url");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    PhotoRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PhotoColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.anubis.phlix.models.Photo.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$id() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public String realmGet$secret() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.secretIndex);
    }

    public void realmSet$secret(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.secretIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.secretIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.secretIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.secretIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$server() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.serverIndex);
    }

    public void realmSet$server(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.serverIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.serverIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.serverIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.serverIndex, value);
    }

    @SuppressWarnings("cast")
    public Integer realmGet$farm() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.farmIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.farmIndex);
    }

    public void realmSet$farm(Integer value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.farmIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.farmIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.farmIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.farmIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$owner() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.ownerIndex);
    }

    public void realmSet$owner(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.ownerIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.ownerIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.ownerIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.ownerIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$username() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.usernameIndex);
    }

    public void realmSet$username(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.usernameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.usernameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.usernameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.usernameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$title() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleIndex);
    }

    public void realmSet$title(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.titleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.titleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.titleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.titleIndex, value);
    }

    @SuppressWarnings("cast")
    public Integer realmGet$ispublic() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.ispublicIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.ispublicIndex);
    }

    public void realmSet$ispublic(Integer value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.ispublicIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.ispublicIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.ispublicIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.ispublicIndex, value);
    }

    @SuppressWarnings("cast")
    public Integer realmGet$isfriend() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.isfriendIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.isfriendIndex);
    }

    public void realmSet$isfriend(Integer value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.isfriendIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.isfriendIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.isfriendIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.isfriendIndex, value);
    }

    @SuppressWarnings("cast")
    public Integer realmGet$isfamily() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.isfamilyIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.isfamilyIndex);
    }

    public void realmSet$isfamily(Integer value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.isfamilyIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.isfamilyIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.isfamilyIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.isfamilyIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$datetaken() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.datetakenIndex);
    }

    public void realmSet$datetaken(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.datetakenIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.datetakenIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.datetakenIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.datetakenIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$datetakengranularity() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.datetakengranularityIndex);
    }

    public void realmSet$datetakengranularity(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.datetakengranularityIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.datetakengranularityIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.datetakengranularityIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.datetakengranularityIndex, value);
    }

    @SuppressWarnings("cast")
    public Integer realmGet$datetakenunknown() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.datetakenunknownIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.datetakenunknownIndex);
    }

    public void realmSet$datetakenunknown(Integer value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.datetakenunknownIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.datetakenunknownIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.datetakenunknownIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.datetakenunknownIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$ownername() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.ownernameIndex);
    }

    public void realmSet$ownername(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.ownernameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.ownernameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.ownernameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.ownernameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$tags() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.tagsIndex);
    }

    public void realmSet$tags(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.tagsIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.tagsIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.tagsIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.tagsIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$url_s() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.url_sIndex);
    }

    public void realmSet$url_s(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.url_sIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.url_sIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.url_sIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.url_sIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$height() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.heightIndex);
    }

    public void realmSet$height(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.heightIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.heightIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.heightIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.heightIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$width() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.widthIndex);
    }

    public void realmSet$width(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.widthIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.widthIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.widthIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.widthIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$isCommon() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isCommonIndex);
    }

    public void realmSet$isCommon(boolean value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isCommonIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isCommonIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$isInteresting() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.isInterestingIndex);
    }

    public void realmSet$isInteresting(boolean value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.isInterestingIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.isInterestingIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$url() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.urlIndex);
    }

    public void realmSet$url(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.urlIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.urlIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.urlIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.urlIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Photo")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Photo");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("secret", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("server", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("farm", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("owner", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("username", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("ispublic", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("isfriend", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("isfamily", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("datetaken", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("datetakengranularity", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("datetakenunknown", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("ownername", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("tags", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("url_s", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("height", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("width", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("isCommon", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("isInteresting", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("url", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Photo");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Photo")) {
            Table table = sharedRealm.getTable("class_Photo");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "secret", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "server", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "farm", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "owner", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "username", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "ispublic", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "isfriend", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "isfamily", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "datetaken", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "datetakengranularity", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "datetakenunknown", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "ownername", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "tags", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "url_s", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "height", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "width", Table.NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "isCommon", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "isInteresting", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "url", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_Photo");
    }

    public static PhotoColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Photo")) {
            Table table = sharedRealm.getTable("class_Photo");
            final long columnCount = table.getColumnCount();
            if (columnCount != 21) {
                if (columnCount < 21) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 21 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 21 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 21 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 21; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final PhotoColumnInfo columnInfo = new PhotoColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("secret")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'secret' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("secret") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'secret' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.secretIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'secret' is required. Either set @Required to field 'secret' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("server")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'server' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("server") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'server' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.serverIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'server' is required. Either set @Required to field 'server' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("farm")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'farm' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("farm") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'farm' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.farmIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'farm' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'farm' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("owner")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'owner' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("owner") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'owner' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.ownerIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'owner' is required. Either set @Required to field 'owner' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("username")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'username' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("username") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'username' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.usernameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'username' is required. Either set @Required to field 'username' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("title")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'title' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("title") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'title' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.titleIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'title' is required. Either set @Required to field 'title' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("ispublic")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'ispublic' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("ispublic") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'ispublic' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.ispublicIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'ispublic' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'ispublic' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isfriend")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isfriend' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isfriend") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'isfriend' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.isfriendIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'isfriend' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'isfriend' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isfamily")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isfamily' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isfamily") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'isfamily' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.isfamilyIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'isfamily' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'isfamily' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("datetaken")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'datetaken' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("datetaken") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'datetaken' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.datetakenIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'datetaken' is required. Either set @Required to field 'datetaken' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("datetakengranularity")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'datetakengranularity' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("datetakengranularity") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'datetakengranularity' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.datetakengranularityIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'datetakengranularity' is required. Either set @Required to field 'datetakengranularity' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("datetakenunknown")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'datetakenunknown' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("datetakenunknown") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'datetakenunknown' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.datetakenunknownIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'datetakenunknown' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'datetakenunknown' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("ownername")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'ownername' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("ownername") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'ownername' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.ownernameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'ownername' is required. Either set @Required to field 'ownername' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("tags")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'tags' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("tags") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'tags' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.tagsIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'tags' is required. Either set @Required to field 'tags' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("url_s")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'url_s' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("url_s") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'url_s' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.url_sIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'url_s' is required. Either set @Required to field 'url_s' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("height")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'height' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("height") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'height' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.heightIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'height' is required. Either set @Required to field 'height' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("width")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'width' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("width") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'width' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.widthIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'width' is required. Either set @Required to field 'width' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isCommon")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isCommon' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isCommon") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isCommon' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.isCommonIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isCommon' does support null values in the existing Realm file. Use corresponding boxed type for field 'isCommon' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("isInteresting")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'isInteresting' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isInteresting") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'isInteresting' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.isInterestingIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'isInteresting' does support null values in the existing Realm file. Use corresponding boxed type for field 'isInteresting' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("url")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'url' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("url") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'url' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.urlIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'url' is required. Either set @Required to field 'url' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Photo' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Photo";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.anubis.phlix.models.Photo createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.anubis.phlix.models.Photo obj = null;
        if (update) {
            Table table = realm.getTable(com.anubis.phlix.models.Photo.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Photo.class), false, Collections.<String> emptyList());
                    obj = new io.realm.PhotoRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.PhotoRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Photo.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.PhotoRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Photo.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("secret")) {
            if (json.isNull("secret")) {
                ((PhotoRealmProxyInterface) obj).realmSet$secret(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$secret((String) json.getString("secret"));
            }
        }
        if (json.has("server")) {
            if (json.isNull("server")) {
                ((PhotoRealmProxyInterface) obj).realmSet$server(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$server((String) json.getString("server"));
            }
        }
        if (json.has("farm")) {
            if (json.isNull("farm")) {
                ((PhotoRealmProxyInterface) obj).realmSet$farm(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$farm((int) json.getInt("farm"));
            }
        }
        if (json.has("owner")) {
            if (json.isNull("owner")) {
                ((PhotoRealmProxyInterface) obj).realmSet$owner(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$owner((String) json.getString("owner"));
            }
        }
        if (json.has("username")) {
            if (json.isNull("username")) {
                ((PhotoRealmProxyInterface) obj).realmSet$username(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$username((String) json.getString("username"));
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                ((PhotoRealmProxyInterface) obj).realmSet$title(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("ispublic")) {
            if (json.isNull("ispublic")) {
                ((PhotoRealmProxyInterface) obj).realmSet$ispublic(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$ispublic((int) json.getInt("ispublic"));
            }
        }
        if (json.has("isfriend")) {
            if (json.isNull("isfriend")) {
                ((PhotoRealmProxyInterface) obj).realmSet$isfriend(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$isfriend((int) json.getInt("isfriend"));
            }
        }
        if (json.has("isfamily")) {
            if (json.isNull("isfamily")) {
                ((PhotoRealmProxyInterface) obj).realmSet$isfamily(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$isfamily((int) json.getInt("isfamily"));
            }
        }
        if (json.has("datetaken")) {
            if (json.isNull("datetaken")) {
                ((PhotoRealmProxyInterface) obj).realmSet$datetaken(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$datetaken((String) json.getString("datetaken"));
            }
        }
        if (json.has("datetakengranularity")) {
            if (json.isNull("datetakengranularity")) {
                ((PhotoRealmProxyInterface) obj).realmSet$datetakengranularity(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$datetakengranularity((String) json.getString("datetakengranularity"));
            }
        }
        if (json.has("datetakenunknown")) {
            if (json.isNull("datetakenunknown")) {
                ((PhotoRealmProxyInterface) obj).realmSet$datetakenunknown(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$datetakenunknown((int) json.getInt("datetakenunknown"));
            }
        }
        if (json.has("ownername")) {
            if (json.isNull("ownername")) {
                ((PhotoRealmProxyInterface) obj).realmSet$ownername(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$ownername((String) json.getString("ownername"));
            }
        }
        if (json.has("tags")) {
            if (json.isNull("tags")) {
                ((PhotoRealmProxyInterface) obj).realmSet$tags(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$tags((String) json.getString("tags"));
            }
        }
        if (json.has("url_s")) {
            if (json.isNull("url_s")) {
                ((PhotoRealmProxyInterface) obj).realmSet$url_s(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$url_s((String) json.getString("url_s"));
            }
        }
        if (json.has("height")) {
            if (json.isNull("height")) {
                ((PhotoRealmProxyInterface) obj).realmSet$height(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$height((String) json.getString("height"));
            }
        }
        if (json.has("width")) {
            if (json.isNull("width")) {
                ((PhotoRealmProxyInterface) obj).realmSet$width(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$width((String) json.getString("width"));
            }
        }
        if (json.has("isCommon")) {
            if (json.isNull("isCommon")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isCommon' to null.");
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$isCommon((boolean) json.getBoolean("isCommon"));
            }
        }
        if (json.has("isInteresting")) {
            if (json.isNull("isInteresting")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isInteresting' to null.");
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$isInteresting((boolean) json.getBoolean("isInteresting"));
            }
        }
        if (json.has("url")) {
            if (json.isNull("url")) {
                ((PhotoRealmProxyInterface) obj).realmSet$url(null);
            } else {
                ((PhotoRealmProxyInterface) obj).realmSet$url((String) json.getString("url"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.anubis.phlix.models.Photo createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.anubis.phlix.models.Photo obj = new com.anubis.phlix.models.Photo();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("secret")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$secret(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$secret((String) reader.nextString());
                }
            } else if (name.equals("server")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$server(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$server((String) reader.nextString());
                }
            } else if (name.equals("farm")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$farm(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$farm((int) reader.nextInt());
                }
            } else if (name.equals("owner")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$owner(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$owner((String) reader.nextString());
                }
            } else if (name.equals("username")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$username(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$username((String) reader.nextString());
                }
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$title(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$title((String) reader.nextString());
                }
            } else if (name.equals("ispublic")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$ispublic(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$ispublic((int) reader.nextInt());
                }
            } else if (name.equals("isfriend")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$isfriend(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$isfriend((int) reader.nextInt());
                }
            } else if (name.equals("isfamily")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$isfamily(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$isfamily((int) reader.nextInt());
                }
            } else if (name.equals("datetaken")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$datetaken(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$datetaken((String) reader.nextString());
                }
            } else if (name.equals("datetakengranularity")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$datetakengranularity(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$datetakengranularity((String) reader.nextString());
                }
            } else if (name.equals("datetakenunknown")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$datetakenunknown(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$datetakenunknown((int) reader.nextInt());
                }
            } else if (name.equals("ownername")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$ownername(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$ownername((String) reader.nextString());
                }
            } else if (name.equals("tags")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$tags(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$tags((String) reader.nextString());
                }
            } else if (name.equals("url_s")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$url_s(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$url_s((String) reader.nextString());
                }
            } else if (name.equals("height")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$height(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$height((String) reader.nextString());
                }
            } else if (name.equals("width")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$width(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$width((String) reader.nextString());
                }
            } else if (name.equals("isCommon")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isCommon' to null.");
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$isCommon((boolean) reader.nextBoolean());
                }
            } else if (name.equals("isInteresting")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isInteresting' to null.");
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$isInteresting((boolean) reader.nextBoolean());
                }
            } else if (name.equals("url")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((PhotoRealmProxyInterface) obj).realmSet$url(null);
                } else {
                    ((PhotoRealmProxyInterface) obj).realmSet$url((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.anubis.phlix.models.Photo copyOrUpdate(Realm realm, com.anubis.phlix.models.Photo object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Photo) cachedRealmObject;
        } else {
            com.anubis.phlix.models.Photo realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.anubis.phlix.models.Photo.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((PhotoRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Photo.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.PhotoRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.anubis.phlix.models.Photo copy(Realm realm, com.anubis.phlix.models.Photo newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Photo) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.anubis.phlix.models.Photo realmObject = realm.createObjectInternal(com.anubis.phlix.models.Photo.class, ((PhotoRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((PhotoRealmProxyInterface) realmObject).realmSet$secret(((PhotoRealmProxyInterface) newObject).realmGet$secret());
            ((PhotoRealmProxyInterface) realmObject).realmSet$server(((PhotoRealmProxyInterface) newObject).realmGet$server());
            ((PhotoRealmProxyInterface) realmObject).realmSet$farm(((PhotoRealmProxyInterface) newObject).realmGet$farm());
            ((PhotoRealmProxyInterface) realmObject).realmSet$owner(((PhotoRealmProxyInterface) newObject).realmGet$owner());
            ((PhotoRealmProxyInterface) realmObject).realmSet$username(((PhotoRealmProxyInterface) newObject).realmGet$username());
            ((PhotoRealmProxyInterface) realmObject).realmSet$title(((PhotoRealmProxyInterface) newObject).realmGet$title());
            ((PhotoRealmProxyInterface) realmObject).realmSet$ispublic(((PhotoRealmProxyInterface) newObject).realmGet$ispublic());
            ((PhotoRealmProxyInterface) realmObject).realmSet$isfriend(((PhotoRealmProxyInterface) newObject).realmGet$isfriend());
            ((PhotoRealmProxyInterface) realmObject).realmSet$isfamily(((PhotoRealmProxyInterface) newObject).realmGet$isfamily());
            ((PhotoRealmProxyInterface) realmObject).realmSet$datetaken(((PhotoRealmProxyInterface) newObject).realmGet$datetaken());
            ((PhotoRealmProxyInterface) realmObject).realmSet$datetakengranularity(((PhotoRealmProxyInterface) newObject).realmGet$datetakengranularity());
            ((PhotoRealmProxyInterface) realmObject).realmSet$datetakenunknown(((PhotoRealmProxyInterface) newObject).realmGet$datetakenunknown());
            ((PhotoRealmProxyInterface) realmObject).realmSet$ownername(((PhotoRealmProxyInterface) newObject).realmGet$ownername());
            ((PhotoRealmProxyInterface) realmObject).realmSet$tags(((PhotoRealmProxyInterface) newObject).realmGet$tags());
            ((PhotoRealmProxyInterface) realmObject).realmSet$url_s(((PhotoRealmProxyInterface) newObject).realmGet$url_s());
            ((PhotoRealmProxyInterface) realmObject).realmSet$height(((PhotoRealmProxyInterface) newObject).realmGet$height());
            ((PhotoRealmProxyInterface) realmObject).realmSet$width(((PhotoRealmProxyInterface) newObject).realmGet$width());
            ((PhotoRealmProxyInterface) realmObject).realmSet$isCommon(((PhotoRealmProxyInterface) newObject).realmGet$isCommon());
            ((PhotoRealmProxyInterface) realmObject).realmSet$isInteresting(((PhotoRealmProxyInterface) newObject).realmGet$isInteresting());
            ((PhotoRealmProxyInterface) realmObject).realmSet$url(((PhotoRealmProxyInterface) newObject).realmGet$url());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.anubis.phlix.models.Photo object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Photo.class);
        long tableNativePtr = table.getNativeTablePointer();
        PhotoColumnInfo columnInfo = (PhotoColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Photo.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((PhotoRealmProxyInterface) object).realmGet$id();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$secret = ((PhotoRealmProxyInterface)object).realmGet$secret();
        if (realmGet$secret != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.secretIndex, rowIndex, realmGet$secret, false);
        }
        String realmGet$server = ((PhotoRealmProxyInterface)object).realmGet$server();
        if (realmGet$server != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.serverIndex, rowIndex, realmGet$server, false);
        }
        Number realmGet$farm = ((PhotoRealmProxyInterface)object).realmGet$farm();
        if (realmGet$farm != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.farmIndex, rowIndex, realmGet$farm.longValue(), false);
        }
        String realmGet$owner = ((PhotoRealmProxyInterface)object).realmGet$owner();
        if (realmGet$owner != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
        }
        String realmGet$username = ((PhotoRealmProxyInterface)object).realmGet$username();
        if (realmGet$username != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
        }
        String realmGet$title = ((PhotoRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        Number realmGet$ispublic = ((PhotoRealmProxyInterface)object).realmGet$ispublic();
        if (realmGet$ispublic != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.ispublicIndex, rowIndex, realmGet$ispublic.longValue(), false);
        }
        Number realmGet$isfriend = ((PhotoRealmProxyInterface)object).realmGet$isfriend();
        if (realmGet$isfriend != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.isfriendIndex, rowIndex, realmGet$isfriend.longValue(), false);
        }
        Number realmGet$isfamily = ((PhotoRealmProxyInterface)object).realmGet$isfamily();
        if (realmGet$isfamily != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.isfamilyIndex, rowIndex, realmGet$isfamily.longValue(), false);
        }
        String realmGet$datetaken = ((PhotoRealmProxyInterface)object).realmGet$datetaken();
        if (realmGet$datetaken != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.datetakenIndex, rowIndex, realmGet$datetaken, false);
        }
        String realmGet$datetakengranularity = ((PhotoRealmProxyInterface)object).realmGet$datetakengranularity();
        if (realmGet$datetakengranularity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.datetakengranularityIndex, rowIndex, realmGet$datetakengranularity, false);
        }
        Number realmGet$datetakenunknown = ((PhotoRealmProxyInterface)object).realmGet$datetakenunknown();
        if (realmGet$datetakenunknown != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.datetakenunknownIndex, rowIndex, realmGet$datetakenunknown.longValue(), false);
        }
        String realmGet$ownername = ((PhotoRealmProxyInterface)object).realmGet$ownername();
        if (realmGet$ownername != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownernameIndex, rowIndex, realmGet$ownername, false);
        }
        String realmGet$tags = ((PhotoRealmProxyInterface)object).realmGet$tags();
        if (realmGet$tags != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
        }
        String realmGet$url_s = ((PhotoRealmProxyInterface)object).realmGet$url_s();
        if (realmGet$url_s != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.url_sIndex, rowIndex, realmGet$url_s, false);
        }
        String realmGet$height = ((PhotoRealmProxyInterface)object).realmGet$height();
        if (realmGet$height != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.heightIndex, rowIndex, realmGet$height, false);
        }
        String realmGet$width = ((PhotoRealmProxyInterface)object).realmGet$width();
        if (realmGet$width != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.widthIndex, rowIndex, realmGet$width, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isCommonIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isCommon(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isInterestingIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isInteresting(), false);
        String realmGet$url = ((PhotoRealmProxyInterface)object).realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Photo.class);
        long tableNativePtr = table.getNativeTablePointer();
        PhotoColumnInfo columnInfo = (PhotoColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Photo.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Photo object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Photo) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((PhotoRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$secret = ((PhotoRealmProxyInterface)object).realmGet$secret();
                if (realmGet$secret != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.secretIndex, rowIndex, realmGet$secret, false);
                }
                String realmGet$server = ((PhotoRealmProxyInterface)object).realmGet$server();
                if (realmGet$server != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.serverIndex, rowIndex, realmGet$server, false);
                }
                Number realmGet$farm = ((PhotoRealmProxyInterface)object).realmGet$farm();
                if (realmGet$farm != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.farmIndex, rowIndex, realmGet$farm.longValue(), false);
                }
                String realmGet$owner = ((PhotoRealmProxyInterface)object).realmGet$owner();
                if (realmGet$owner != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
                }
                String realmGet$username = ((PhotoRealmProxyInterface)object).realmGet$username();
                if (realmGet$username != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
                }
                String realmGet$title = ((PhotoRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                }
                Number realmGet$ispublic = ((PhotoRealmProxyInterface)object).realmGet$ispublic();
                if (realmGet$ispublic != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.ispublicIndex, rowIndex, realmGet$ispublic.longValue(), false);
                }
                Number realmGet$isfriend = ((PhotoRealmProxyInterface)object).realmGet$isfriend();
                if (realmGet$isfriend != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.isfriendIndex, rowIndex, realmGet$isfriend.longValue(), false);
                }
                Number realmGet$isfamily = ((PhotoRealmProxyInterface)object).realmGet$isfamily();
                if (realmGet$isfamily != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.isfamilyIndex, rowIndex, realmGet$isfamily.longValue(), false);
                }
                String realmGet$datetaken = ((PhotoRealmProxyInterface)object).realmGet$datetaken();
                if (realmGet$datetaken != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.datetakenIndex, rowIndex, realmGet$datetaken, false);
                }
                String realmGet$datetakengranularity = ((PhotoRealmProxyInterface)object).realmGet$datetakengranularity();
                if (realmGet$datetakengranularity != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.datetakengranularityIndex, rowIndex, realmGet$datetakengranularity, false);
                }
                Number realmGet$datetakenunknown = ((PhotoRealmProxyInterface)object).realmGet$datetakenunknown();
                if (realmGet$datetakenunknown != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.datetakenunknownIndex, rowIndex, realmGet$datetakenunknown.longValue(), false);
                }
                String realmGet$ownername = ((PhotoRealmProxyInterface)object).realmGet$ownername();
                if (realmGet$ownername != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.ownernameIndex, rowIndex, realmGet$ownername, false);
                }
                String realmGet$tags = ((PhotoRealmProxyInterface)object).realmGet$tags();
                if (realmGet$tags != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
                }
                String realmGet$url_s = ((PhotoRealmProxyInterface)object).realmGet$url_s();
                if (realmGet$url_s != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.url_sIndex, rowIndex, realmGet$url_s, false);
                }
                String realmGet$height = ((PhotoRealmProxyInterface)object).realmGet$height();
                if (realmGet$height != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.heightIndex, rowIndex, realmGet$height, false);
                }
                String realmGet$width = ((PhotoRealmProxyInterface)object).realmGet$width();
                if (realmGet$width != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.widthIndex, rowIndex, realmGet$width, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isCommonIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isCommon(), false);
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isInterestingIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isInteresting(), false);
                String realmGet$url = ((PhotoRealmProxyInterface)object).realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.anubis.phlix.models.Photo object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Photo.class);
        long tableNativePtr = table.getNativeTablePointer();
        PhotoColumnInfo columnInfo = (PhotoColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Photo.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((PhotoRealmProxyInterface) object).realmGet$id();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        String realmGet$secret = ((PhotoRealmProxyInterface)object).realmGet$secret();
        if (realmGet$secret != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.secretIndex, rowIndex, realmGet$secret, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.secretIndex, rowIndex, false);
        }
        String realmGet$server = ((PhotoRealmProxyInterface)object).realmGet$server();
        if (realmGet$server != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.serverIndex, rowIndex, realmGet$server, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.serverIndex, rowIndex, false);
        }
        Number realmGet$farm = ((PhotoRealmProxyInterface)object).realmGet$farm();
        if (realmGet$farm != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.farmIndex, rowIndex, realmGet$farm.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.farmIndex, rowIndex, false);
        }
        String realmGet$owner = ((PhotoRealmProxyInterface)object).realmGet$owner();
        if (realmGet$owner != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ownerIndex, rowIndex, false);
        }
        String realmGet$username = ((PhotoRealmProxyInterface)object).realmGet$username();
        if (realmGet$username != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.usernameIndex, rowIndex, false);
        }
        String realmGet$title = ((PhotoRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        Number realmGet$ispublic = ((PhotoRealmProxyInterface)object).realmGet$ispublic();
        if (realmGet$ispublic != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.ispublicIndex, rowIndex, realmGet$ispublic.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ispublicIndex, rowIndex, false);
        }
        Number realmGet$isfriend = ((PhotoRealmProxyInterface)object).realmGet$isfriend();
        if (realmGet$isfriend != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.isfriendIndex, rowIndex, realmGet$isfriend.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.isfriendIndex, rowIndex, false);
        }
        Number realmGet$isfamily = ((PhotoRealmProxyInterface)object).realmGet$isfamily();
        if (realmGet$isfamily != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.isfamilyIndex, rowIndex, realmGet$isfamily.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.isfamilyIndex, rowIndex, false);
        }
        String realmGet$datetaken = ((PhotoRealmProxyInterface)object).realmGet$datetaken();
        if (realmGet$datetaken != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.datetakenIndex, rowIndex, realmGet$datetaken, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.datetakenIndex, rowIndex, false);
        }
        String realmGet$datetakengranularity = ((PhotoRealmProxyInterface)object).realmGet$datetakengranularity();
        if (realmGet$datetakengranularity != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.datetakengranularityIndex, rowIndex, realmGet$datetakengranularity, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.datetakengranularityIndex, rowIndex, false);
        }
        Number realmGet$datetakenunknown = ((PhotoRealmProxyInterface)object).realmGet$datetakenunknown();
        if (realmGet$datetakenunknown != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.datetakenunknownIndex, rowIndex, realmGet$datetakenunknown.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.datetakenunknownIndex, rowIndex, false);
        }
        String realmGet$ownername = ((PhotoRealmProxyInterface)object).realmGet$ownername();
        if (realmGet$ownername != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownernameIndex, rowIndex, realmGet$ownername, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ownernameIndex, rowIndex, false);
        }
        String realmGet$tags = ((PhotoRealmProxyInterface)object).realmGet$tags();
        if (realmGet$tags != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tagsIndex, rowIndex, false);
        }
        String realmGet$url_s = ((PhotoRealmProxyInterface)object).realmGet$url_s();
        if (realmGet$url_s != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.url_sIndex, rowIndex, realmGet$url_s, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.url_sIndex, rowIndex, false);
        }
        String realmGet$height = ((PhotoRealmProxyInterface)object).realmGet$height();
        if (realmGet$height != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.heightIndex, rowIndex, realmGet$height, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.heightIndex, rowIndex, false);
        }
        String realmGet$width = ((PhotoRealmProxyInterface)object).realmGet$width();
        if (realmGet$width != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.widthIndex, rowIndex, realmGet$width, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.widthIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isCommonIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isCommon(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.isInterestingIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isInteresting(), false);
        String realmGet$url = ((PhotoRealmProxyInterface)object).realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Photo.class);
        long tableNativePtr = table.getNativeTablePointer();
        PhotoColumnInfo columnInfo = (PhotoColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Photo.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Photo object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Photo) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((PhotoRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                String realmGet$secret = ((PhotoRealmProxyInterface)object).realmGet$secret();
                if (realmGet$secret != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.secretIndex, rowIndex, realmGet$secret, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.secretIndex, rowIndex, false);
                }
                String realmGet$server = ((PhotoRealmProxyInterface)object).realmGet$server();
                if (realmGet$server != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.serverIndex, rowIndex, realmGet$server, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.serverIndex, rowIndex, false);
                }
                Number realmGet$farm = ((PhotoRealmProxyInterface)object).realmGet$farm();
                if (realmGet$farm != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.farmIndex, rowIndex, realmGet$farm.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.farmIndex, rowIndex, false);
                }
                String realmGet$owner = ((PhotoRealmProxyInterface)object).realmGet$owner();
                if (realmGet$owner != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.ownerIndex, rowIndex, false);
                }
                String realmGet$username = ((PhotoRealmProxyInterface)object).realmGet$username();
                if (realmGet$username != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.usernameIndex, rowIndex, realmGet$username, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.usernameIndex, rowIndex, false);
                }
                String realmGet$title = ((PhotoRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
                }
                Number realmGet$ispublic = ((PhotoRealmProxyInterface)object).realmGet$ispublic();
                if (realmGet$ispublic != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.ispublicIndex, rowIndex, realmGet$ispublic.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.ispublicIndex, rowIndex, false);
                }
                Number realmGet$isfriend = ((PhotoRealmProxyInterface)object).realmGet$isfriend();
                if (realmGet$isfriend != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.isfriendIndex, rowIndex, realmGet$isfriend.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.isfriendIndex, rowIndex, false);
                }
                Number realmGet$isfamily = ((PhotoRealmProxyInterface)object).realmGet$isfamily();
                if (realmGet$isfamily != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.isfamilyIndex, rowIndex, realmGet$isfamily.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.isfamilyIndex, rowIndex, false);
                }
                String realmGet$datetaken = ((PhotoRealmProxyInterface)object).realmGet$datetaken();
                if (realmGet$datetaken != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.datetakenIndex, rowIndex, realmGet$datetaken, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.datetakenIndex, rowIndex, false);
                }
                String realmGet$datetakengranularity = ((PhotoRealmProxyInterface)object).realmGet$datetakengranularity();
                if (realmGet$datetakengranularity != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.datetakengranularityIndex, rowIndex, realmGet$datetakengranularity, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.datetakengranularityIndex, rowIndex, false);
                }
                Number realmGet$datetakenunknown = ((PhotoRealmProxyInterface)object).realmGet$datetakenunknown();
                if (realmGet$datetakenunknown != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.datetakenunknownIndex, rowIndex, realmGet$datetakenunknown.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.datetakenunknownIndex, rowIndex, false);
                }
                String realmGet$ownername = ((PhotoRealmProxyInterface)object).realmGet$ownername();
                if (realmGet$ownername != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.ownernameIndex, rowIndex, realmGet$ownername, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.ownernameIndex, rowIndex, false);
                }
                String realmGet$tags = ((PhotoRealmProxyInterface)object).realmGet$tags();
                if (realmGet$tags != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.tagsIndex, rowIndex, false);
                }
                String realmGet$url_s = ((PhotoRealmProxyInterface)object).realmGet$url_s();
                if (realmGet$url_s != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.url_sIndex, rowIndex, realmGet$url_s, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.url_sIndex, rowIndex, false);
                }
                String realmGet$height = ((PhotoRealmProxyInterface)object).realmGet$height();
                if (realmGet$height != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.heightIndex, rowIndex, realmGet$height, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.heightIndex, rowIndex, false);
                }
                String realmGet$width = ((PhotoRealmProxyInterface)object).realmGet$width();
                if (realmGet$width != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.widthIndex, rowIndex, realmGet$width, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.widthIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isCommonIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isCommon(), false);
                Table.nativeSetBoolean(tableNativePtr, columnInfo.isInterestingIndex, rowIndex, ((PhotoRealmProxyInterface)object).realmGet$isInteresting(), false);
                String realmGet$url = ((PhotoRealmProxyInterface)object).realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.urlIndex, rowIndex, realmGet$url, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.urlIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.anubis.phlix.models.Photo createDetachedCopy(com.anubis.phlix.models.Photo realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.anubis.phlix.models.Photo unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.anubis.phlix.models.Photo)cachedObject.object;
            } else {
                unmanagedObject = (com.anubis.phlix.models.Photo)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.anubis.phlix.models.Photo();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$id(((PhotoRealmProxyInterface) realmObject).realmGet$id());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$secret(((PhotoRealmProxyInterface) realmObject).realmGet$secret());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$server(((PhotoRealmProxyInterface) realmObject).realmGet$server());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$farm(((PhotoRealmProxyInterface) realmObject).realmGet$farm());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$owner(((PhotoRealmProxyInterface) realmObject).realmGet$owner());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$username(((PhotoRealmProxyInterface) realmObject).realmGet$username());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$title(((PhotoRealmProxyInterface) realmObject).realmGet$title());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$ispublic(((PhotoRealmProxyInterface) realmObject).realmGet$ispublic());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$isfriend(((PhotoRealmProxyInterface) realmObject).realmGet$isfriend());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$isfamily(((PhotoRealmProxyInterface) realmObject).realmGet$isfamily());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$datetaken(((PhotoRealmProxyInterface) realmObject).realmGet$datetaken());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$datetakengranularity(((PhotoRealmProxyInterface) realmObject).realmGet$datetakengranularity());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$datetakenunknown(((PhotoRealmProxyInterface) realmObject).realmGet$datetakenunknown());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$ownername(((PhotoRealmProxyInterface) realmObject).realmGet$ownername());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$tags(((PhotoRealmProxyInterface) realmObject).realmGet$tags());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$url_s(((PhotoRealmProxyInterface) realmObject).realmGet$url_s());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$height(((PhotoRealmProxyInterface) realmObject).realmGet$height());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$width(((PhotoRealmProxyInterface) realmObject).realmGet$width());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$isCommon(((PhotoRealmProxyInterface) realmObject).realmGet$isCommon());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$isInteresting(((PhotoRealmProxyInterface) realmObject).realmGet$isInteresting());
        ((PhotoRealmProxyInterface) unmanagedObject).realmSet$url(((PhotoRealmProxyInterface) realmObject).realmGet$url());
        return unmanagedObject;
    }

    static com.anubis.phlix.models.Photo update(Realm realm, com.anubis.phlix.models.Photo realmObject, com.anubis.phlix.models.Photo newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((PhotoRealmProxyInterface) realmObject).realmSet$secret(((PhotoRealmProxyInterface) newObject).realmGet$secret());
        ((PhotoRealmProxyInterface) realmObject).realmSet$server(((PhotoRealmProxyInterface) newObject).realmGet$server());
        ((PhotoRealmProxyInterface) realmObject).realmSet$farm(((PhotoRealmProxyInterface) newObject).realmGet$farm());
        ((PhotoRealmProxyInterface) realmObject).realmSet$owner(((PhotoRealmProxyInterface) newObject).realmGet$owner());
        ((PhotoRealmProxyInterface) realmObject).realmSet$username(((PhotoRealmProxyInterface) newObject).realmGet$username());
        ((PhotoRealmProxyInterface) realmObject).realmSet$title(((PhotoRealmProxyInterface) newObject).realmGet$title());
        ((PhotoRealmProxyInterface) realmObject).realmSet$ispublic(((PhotoRealmProxyInterface) newObject).realmGet$ispublic());
        ((PhotoRealmProxyInterface) realmObject).realmSet$isfriend(((PhotoRealmProxyInterface) newObject).realmGet$isfriend());
        ((PhotoRealmProxyInterface) realmObject).realmSet$isfamily(((PhotoRealmProxyInterface) newObject).realmGet$isfamily());
        ((PhotoRealmProxyInterface) realmObject).realmSet$datetaken(((PhotoRealmProxyInterface) newObject).realmGet$datetaken());
        ((PhotoRealmProxyInterface) realmObject).realmSet$datetakengranularity(((PhotoRealmProxyInterface) newObject).realmGet$datetakengranularity());
        ((PhotoRealmProxyInterface) realmObject).realmSet$datetakenunknown(((PhotoRealmProxyInterface) newObject).realmGet$datetakenunknown());
        ((PhotoRealmProxyInterface) realmObject).realmSet$ownername(((PhotoRealmProxyInterface) newObject).realmGet$ownername());
        ((PhotoRealmProxyInterface) realmObject).realmSet$tags(((PhotoRealmProxyInterface) newObject).realmGet$tags());
        ((PhotoRealmProxyInterface) realmObject).realmSet$url_s(((PhotoRealmProxyInterface) newObject).realmGet$url_s());
        ((PhotoRealmProxyInterface) realmObject).realmSet$height(((PhotoRealmProxyInterface) newObject).realmGet$height());
        ((PhotoRealmProxyInterface) realmObject).realmSet$width(((PhotoRealmProxyInterface) newObject).realmGet$width());
        ((PhotoRealmProxyInterface) realmObject).realmSet$isCommon(((PhotoRealmProxyInterface) newObject).realmGet$isCommon());
        ((PhotoRealmProxyInterface) realmObject).realmSet$isInteresting(((PhotoRealmProxyInterface) newObject).realmGet$isInteresting());
        ((PhotoRealmProxyInterface) realmObject).realmSet$url(((PhotoRealmProxyInterface) newObject).realmGet$url());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Photo = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{secret:");
        stringBuilder.append(realmGet$secret() != null ? realmGet$secret() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{server:");
        stringBuilder.append(realmGet$server() != null ? realmGet$server() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{farm:");
        stringBuilder.append(realmGet$farm() != null ? realmGet$farm() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{owner:");
        stringBuilder.append(realmGet$owner() != null ? realmGet$owner() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{username:");
        stringBuilder.append(realmGet$username() != null ? realmGet$username() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ispublic:");
        stringBuilder.append(realmGet$ispublic() != null ? realmGet$ispublic() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isfriend:");
        stringBuilder.append(realmGet$isfriend() != null ? realmGet$isfriend() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isfamily:");
        stringBuilder.append(realmGet$isfamily() != null ? realmGet$isfamily() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{datetaken:");
        stringBuilder.append(realmGet$datetaken() != null ? realmGet$datetaken() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{datetakengranularity:");
        stringBuilder.append(realmGet$datetakengranularity() != null ? realmGet$datetakengranularity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{datetakenunknown:");
        stringBuilder.append(realmGet$datetakenunknown() != null ? realmGet$datetakenunknown() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ownername:");
        stringBuilder.append(realmGet$ownername() != null ? realmGet$ownername() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tags:");
        stringBuilder.append(realmGet$tags() != null ? realmGet$tags() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{url_s:");
        stringBuilder.append(realmGet$url_s() != null ? realmGet$url_s() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{height:");
        stringBuilder.append(realmGet$height() != null ? realmGet$height() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{width:");
        stringBuilder.append(realmGet$width() != null ? realmGet$width() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isCommon:");
        stringBuilder.append(realmGet$isCommon());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isInteresting:");
        stringBuilder.append(realmGet$isInteresting());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{url:");
        stringBuilder.append(realmGet$url() != null ? realmGet$url() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoRealmProxy aPhoto = (PhotoRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPhoto.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPhoto.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPhoto.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
