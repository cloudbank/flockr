package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;

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

public class UserModelRealmProxy extends com.anubis.phlix.models.UserModel
    implements RealmObjectProxy, UserModelRealmProxyInterface {

    static final class UserModelColumnInfo extends ColumnInfo
        implements Cloneable {

        public long userIdIndex;
        public long nameIndex;
        public long timestampIndex;
        public long friendsListIndex;
        public long tagsListIndex;

        UserModelColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.userIdIndex = getValidColumnIndex(path, table, "UserModel", "userId");
            indicesMap.put("userId", this.userIdIndex);
            this.nameIndex = getValidColumnIndex(path, table, "UserModel", "name");
            indicesMap.put("name", this.nameIndex);
            this.timestampIndex = getValidColumnIndex(path, table, "UserModel", "timestamp");
            indicesMap.put("timestamp", this.timestampIndex);
            this.friendsListIndex = getValidColumnIndex(path, table, "UserModel", "friendsList");
            indicesMap.put("friendsList", this.friendsListIndex);
            this.tagsListIndex = getValidColumnIndex(path, table, "UserModel", "tagsList");
            indicesMap.put("tagsList", this.tagsListIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final UserModelColumnInfo otherInfo = (UserModelColumnInfo) other;
            this.userIdIndex = otherInfo.userIdIndex;
            this.nameIndex = otherInfo.nameIndex;
            this.timestampIndex = otherInfo.timestampIndex;
            this.friendsListIndex = otherInfo.friendsListIndex;
            this.tagsListIndex = otherInfo.tagsListIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final UserModelColumnInfo clone() {
            return (UserModelColumnInfo) super.clone();
        }

    }
    private UserModelColumnInfo columnInfo;
    private ProxyState proxyState;
    private RealmList<com.anubis.phlix.models.Photo> friendsListRealmList;
    private RealmList<com.anubis.phlix.models.Tag> tagsListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("userId");
        fieldNames.add("name");
        fieldNames.add("timestamp");
        fieldNames.add("friendsList");
        fieldNames.add("tagsList");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserModelRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserModelColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.anubis.phlix.models.UserModel.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$userId() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.userIdIndex);
    }

    public void realmSet$userId(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'userId' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public String realmGet$name() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
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
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public Date realmGet$timestamp() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.timestampIndex)) {
            return null;
        }
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.timestampIndex);
    }

    public void realmSet$timestamp(Date value) {
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
                row.getTable().setNull(columnInfo.timestampIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDate(columnInfo.timestampIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.timestampIndex);
            return;
        }
        proxyState.getRow$realm().setDate(columnInfo.timestampIndex, value);
    }

    public RealmList<com.anubis.phlix.models.Photo> realmGet$friendsList() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (friendsListRealmList != null) {
            return friendsListRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.friendsListIndex);
            friendsListRealmList = new RealmList<com.anubis.phlix.models.Photo>(com.anubis.phlix.models.Photo.class, linkView, proxyState.getRealm$realm());
            return friendsListRealmList;
        }
    }

    public void realmSet$friendsList(RealmList<com.anubis.phlix.models.Photo> value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("friendsList")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.anubis.phlix.models.Photo> original = value;
                value = new RealmList<com.anubis.phlix.models.Photo>();
                for (com.anubis.phlix.models.Photo item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.friendsListIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!(RealmObject.isManaged(linkedObject) && RealmObject.isValid(linkedObject))) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public RealmList<com.anubis.phlix.models.Tag> realmGet$tagsList() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (tagsListRealmList != null) {
            return tagsListRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.tagsListIndex);
            tagsListRealmList = new RealmList<com.anubis.phlix.models.Tag>(com.anubis.phlix.models.Tag.class, linkView, proxyState.getRealm$realm());
            return tagsListRealmList;
        }
    }

    public void realmSet$tagsList(RealmList<com.anubis.phlix.models.Tag> value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("tagsList")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.anubis.phlix.models.Tag> original = value;
                value = new RealmList<com.anubis.phlix.models.Tag>();
                for (com.anubis.phlix.models.Tag item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.tagsListIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!(RealmObject.isManaged(linkedObject) && RealmObject.isValid(linkedObject))) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("UserModel")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("UserModel");
            realmObjectSchema.add(new Property("userId", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("timestamp", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            if (!realmSchema.contains("Photo")) {
                PhotoRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("friendsList", RealmFieldType.LIST, realmSchema.get("Photo")));
            if (!realmSchema.contains("Tag")) {
                TagRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("tagsList", RealmFieldType.LIST, realmSchema.get("Tag")));
            return realmObjectSchema;
        }
        return realmSchema.get("UserModel");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_UserModel")) {
            Table table = sharedRealm.getTable("class_UserModel");
            table.addColumn(RealmFieldType.STRING, "userId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.DATE, "timestamp", Table.NULLABLE);
            if (!sharedRealm.hasTable("class_Photo")) {
                PhotoRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.LIST, "friendsList", sharedRealm.getTable("class_Photo"));
            if (!sharedRealm.hasTable("class_Tag")) {
                TagRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.LIST, "tagsList", sharedRealm.getTable("class_Tag"));
            table.addSearchIndex(table.getColumnIndex("userId"));
            table.setPrimaryKey("userId");
            return table;
        }
        return sharedRealm.getTable("class_UserModel");
    }

    public static UserModelColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_UserModel")) {
            Table table = sharedRealm.getTable("class_UserModel");
            final long columnCount = table.getColumnCount();
            if (columnCount != 5) {
                if (columnCount < 5) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 5 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 5 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 5 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 5; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final UserModelColumnInfo columnInfo = new UserModelColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("userId")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'userId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("userId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'userId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.userIdIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'userId' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("userId")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'userId' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("userId"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'userId' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("timestamp")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'timestamp' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("timestamp") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'timestamp' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.timestampIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'timestamp' is required. Either set @Required to field 'timestamp' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("friendsList")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'friendsList'");
            }
            if (columnTypes.get("friendsList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Photo' for field 'friendsList'");
            }
            if (!sharedRealm.hasTable("class_Photo")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Photo' for field 'friendsList'");
            }
            Table table_3 = sharedRealm.getTable("class_Photo");
            if (!table.getLinkTarget(columnInfo.friendsListIndex).hasSameSchema(table_3)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'friendsList': '" + table.getLinkTarget(columnInfo.friendsListIndex).getName() + "' expected - was '" + table_3.getName() + "'");
            }
            if (!columnTypes.containsKey("tagsList")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'tagsList'");
            }
            if (columnTypes.get("tagsList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Tag' for field 'tagsList'");
            }
            if (!sharedRealm.hasTable("class_Tag")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Tag' for field 'tagsList'");
            }
            Table table_4 = sharedRealm.getTable("class_Tag");
            if (!table.getLinkTarget(columnInfo.tagsListIndex).hasSameSchema(table_4)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'tagsList': '" + table.getLinkTarget(columnInfo.tagsListIndex).getName() + "' expected - was '" + table_4.getName() + "'");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'UserModel' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_UserModel";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.anubis.phlix.models.UserModel createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(2);
        com.anubis.phlix.models.UserModel obj = null;
        if (update) {
            Table table = realm.getTable(com.anubis.phlix.models.UserModel.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("userId")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("userId"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.UserModel.class), false, Collections.<String> emptyList());
                    obj = new io.realm.UserModelRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("friendsList")) {
                excludeFields.add("friendsList");
            }
            if (json.has("tagsList")) {
                excludeFields.add("tagsList");
            }
            if (json.has("userId")) {
                if (json.isNull("userId")) {
                    obj = (io.realm.UserModelRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.UserModel.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.UserModelRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.UserModel.class, json.getString("userId"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'userId'.");
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((UserModelRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((UserModelRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("timestamp")) {
            if (json.isNull("timestamp")) {
                ((UserModelRealmProxyInterface) obj).realmSet$timestamp(null);
            } else {
                Object timestamp = json.get("timestamp");
                if (timestamp instanceof String) {
                    ((UserModelRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((UserModelRealmProxyInterface) obj).realmSet$timestamp(new Date(json.getLong("timestamp")));
                }
            }
        }
        if (json.has("friendsList")) {
            if (json.isNull("friendsList")) {
                ((UserModelRealmProxyInterface) obj).realmSet$friendsList(null);
            } else {
                ((UserModelRealmProxyInterface) obj).realmGet$friendsList().clear();
                JSONArray array = json.getJSONArray("friendsList");
                for (int i = 0; i < array.length(); i++) {
                    com.anubis.phlix.models.Photo item = PhotoRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((UserModelRealmProxyInterface) obj).realmGet$friendsList().add(item);
                }
            }
        }
        if (json.has("tagsList")) {
            if (json.isNull("tagsList")) {
                ((UserModelRealmProxyInterface) obj).realmSet$tagsList(null);
            } else {
                ((UserModelRealmProxyInterface) obj).realmGet$tagsList().clear();
                JSONArray array = json.getJSONArray("tagsList");
                for (int i = 0; i < array.length(); i++) {
                    com.anubis.phlix.models.Tag item = TagRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((UserModelRealmProxyInterface) obj).realmGet$tagsList().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.anubis.phlix.models.UserModel createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.anubis.phlix.models.UserModel obj = new com.anubis.phlix.models.UserModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("userId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserModelRealmProxyInterface) obj).realmSet$userId(null);
                } else {
                    ((UserModelRealmProxyInterface) obj).realmSet$userId((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserModelRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((UserModelRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("timestamp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserModelRealmProxyInterface) obj).realmSet$timestamp(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((UserModelRealmProxyInterface) obj).realmSet$timestamp(new Date(timestamp));
                    }
                } else {
                    ((UserModelRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("friendsList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserModelRealmProxyInterface) obj).realmSet$friendsList(null);
                } else {
                    ((UserModelRealmProxyInterface) obj).realmSet$friendsList(new RealmList<com.anubis.phlix.models.Photo>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.anubis.phlix.models.Photo item = PhotoRealmProxy.createUsingJsonStream(realm, reader);
                        ((UserModelRealmProxyInterface) obj).realmGet$friendsList().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("tagsList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserModelRealmProxyInterface) obj).realmSet$tagsList(null);
                } else {
                    ((UserModelRealmProxyInterface) obj).realmSet$tagsList(new RealmList<com.anubis.phlix.models.Tag>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.anubis.phlix.models.Tag item = TagRealmProxy.createUsingJsonStream(realm, reader);
                        ((UserModelRealmProxyInterface) obj).realmGet$tagsList().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'userId'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.anubis.phlix.models.UserModel copyOrUpdate(Realm realm, com.anubis.phlix.models.UserModel object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.UserModel) cachedRealmObject;
        } else {
            com.anubis.phlix.models.UserModel realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.anubis.phlix.models.UserModel.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((UserModelRealmProxyInterface) object).realmGet$userId();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.UserModel.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.UserModelRealmProxy();
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

    public static com.anubis.phlix.models.UserModel copy(Realm realm, com.anubis.phlix.models.UserModel newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.UserModel) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.anubis.phlix.models.UserModel realmObject = realm.createObjectInternal(com.anubis.phlix.models.UserModel.class, ((UserModelRealmProxyInterface) newObject).realmGet$userId(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((UserModelRealmProxyInterface) realmObject).realmSet$name(((UserModelRealmProxyInterface) newObject).realmGet$name());
            ((UserModelRealmProxyInterface) realmObject).realmSet$timestamp(((UserModelRealmProxyInterface) newObject).realmGet$timestamp());

            RealmList<com.anubis.phlix.models.Photo> friendsListList = ((UserModelRealmProxyInterface) newObject).realmGet$friendsList();
            if (friendsListList != null) {
                RealmList<com.anubis.phlix.models.Photo> friendsListRealmList = ((UserModelRealmProxyInterface) realmObject).realmGet$friendsList();
                for (int i = 0; i < friendsListList.size(); i++) {
                    com.anubis.phlix.models.Photo friendsListItem = friendsListList.get(i);
                    com.anubis.phlix.models.Photo cachefriendsList = (com.anubis.phlix.models.Photo) cache.get(friendsListItem);
                    if (cachefriendsList != null) {
                        friendsListRealmList.add(cachefriendsList);
                    } else {
                        friendsListRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, friendsListList.get(i), update, cache));
                    }
                }
            }


            RealmList<com.anubis.phlix.models.Tag> tagsListList = ((UserModelRealmProxyInterface) newObject).realmGet$tagsList();
            if (tagsListList != null) {
                RealmList<com.anubis.phlix.models.Tag> tagsListRealmList = ((UserModelRealmProxyInterface) realmObject).realmGet$tagsList();
                for (int i = 0; i < tagsListList.size(); i++) {
                    com.anubis.phlix.models.Tag tagsListItem = tagsListList.get(i);
                    com.anubis.phlix.models.Tag cachetagsList = (com.anubis.phlix.models.Tag) cache.get(tagsListItem);
                    if (cachetagsList != null) {
                        tagsListRealmList.add(cachetagsList);
                    } else {
                        tagsListRealmList.add(TagRealmProxy.copyOrUpdate(realm, tagsListList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.anubis.phlix.models.UserModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.UserModel.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserModelColumnInfo columnInfo = (UserModelColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.UserModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((UserModelRealmProxyInterface) object).realmGet$userId();
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
        String realmGet$name = ((UserModelRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        java.util.Date realmGet$timestamp = ((UserModelRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        }

        RealmList<com.anubis.phlix.models.Photo> friendsListList = ((UserModelRealmProxyInterface) object).realmGet$friendsList();
        if (friendsListList != null) {
            long friendsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.friendsListIndex, rowIndex);
            for (com.anubis.phlix.models.Photo friendsListItem : friendsListList) {
                Long cacheItemIndexfriendsList = cache.get(friendsListItem);
                if (cacheItemIndexfriendsList == null) {
                    cacheItemIndexfriendsList = PhotoRealmProxy.insert(realm, friendsListItem, cache);
                }
                LinkView.nativeAdd(friendsListNativeLinkViewPtr, cacheItemIndexfriendsList);
            }
            LinkView.nativeClose(friendsListNativeLinkViewPtr);
        }


        RealmList<com.anubis.phlix.models.Tag> tagsListList = ((UserModelRealmProxyInterface) object).realmGet$tagsList();
        if (tagsListList != null) {
            long tagsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.tagsListIndex, rowIndex);
            for (com.anubis.phlix.models.Tag tagsListItem : tagsListList) {
                Long cacheItemIndextagsList = cache.get(tagsListItem);
                if (cacheItemIndextagsList == null) {
                    cacheItemIndextagsList = TagRealmProxy.insert(realm, tagsListItem, cache);
                }
                LinkView.nativeAdd(tagsListNativeLinkViewPtr, cacheItemIndextagsList);
            }
            LinkView.nativeClose(tagsListNativeLinkViewPtr);
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.UserModel.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserModelColumnInfo columnInfo = (UserModelColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.UserModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.UserModel object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.UserModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((UserModelRealmProxyInterface) object).realmGet$userId();
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
                String realmGet$name = ((UserModelRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                java.util.Date realmGet$timestamp = ((UserModelRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                }

                RealmList<com.anubis.phlix.models.Photo> friendsListList = ((UserModelRealmProxyInterface) object).realmGet$friendsList();
                if (friendsListList != null) {
                    long friendsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.friendsListIndex, rowIndex);
                    for (com.anubis.phlix.models.Photo friendsListItem : friendsListList) {
                        Long cacheItemIndexfriendsList = cache.get(friendsListItem);
                        if (cacheItemIndexfriendsList == null) {
                            cacheItemIndexfriendsList = PhotoRealmProxy.insert(realm, friendsListItem, cache);
                        }
                        LinkView.nativeAdd(friendsListNativeLinkViewPtr, cacheItemIndexfriendsList);
                    }
                    LinkView.nativeClose(friendsListNativeLinkViewPtr);
                }


                RealmList<com.anubis.phlix.models.Tag> tagsListList = ((UserModelRealmProxyInterface) object).realmGet$tagsList();
                if (tagsListList != null) {
                    long tagsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.tagsListIndex, rowIndex);
                    for (com.anubis.phlix.models.Tag tagsListItem : tagsListList) {
                        Long cacheItemIndextagsList = cache.get(tagsListItem);
                        if (cacheItemIndextagsList == null) {
                            cacheItemIndextagsList = TagRealmProxy.insert(realm, tagsListItem, cache);
                        }
                        LinkView.nativeAdd(tagsListNativeLinkViewPtr, cacheItemIndextagsList);
                    }
                    LinkView.nativeClose(tagsListNativeLinkViewPtr);
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.anubis.phlix.models.UserModel object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.UserModel.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserModelColumnInfo columnInfo = (UserModelColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.UserModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((UserModelRealmProxyInterface) object).realmGet$userId();
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
        String realmGet$name = ((UserModelRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        java.util.Date realmGet$timestamp = ((UserModelRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
        }

        long friendsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.friendsListIndex, rowIndex);
        LinkView.nativeClear(friendsListNativeLinkViewPtr);
        RealmList<com.anubis.phlix.models.Photo> friendsListList = ((UserModelRealmProxyInterface) object).realmGet$friendsList();
        if (friendsListList != null) {
            for (com.anubis.phlix.models.Photo friendsListItem : friendsListList) {
                Long cacheItemIndexfriendsList = cache.get(friendsListItem);
                if (cacheItemIndexfriendsList == null) {
                    cacheItemIndexfriendsList = PhotoRealmProxy.insertOrUpdate(realm, friendsListItem, cache);
                }
                LinkView.nativeAdd(friendsListNativeLinkViewPtr, cacheItemIndexfriendsList);
            }
        }
        LinkView.nativeClose(friendsListNativeLinkViewPtr);


        long tagsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.tagsListIndex, rowIndex);
        LinkView.nativeClear(tagsListNativeLinkViewPtr);
        RealmList<com.anubis.phlix.models.Tag> tagsListList = ((UserModelRealmProxyInterface) object).realmGet$tagsList();
        if (tagsListList != null) {
            for (com.anubis.phlix.models.Tag tagsListItem : tagsListList) {
                Long cacheItemIndextagsList = cache.get(tagsListItem);
                if (cacheItemIndextagsList == null) {
                    cacheItemIndextagsList = TagRealmProxy.insertOrUpdate(realm, tagsListItem, cache);
                }
                LinkView.nativeAdd(tagsListNativeLinkViewPtr, cacheItemIndextagsList);
            }
        }
        LinkView.nativeClose(tagsListNativeLinkViewPtr);

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.UserModel.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserModelColumnInfo columnInfo = (UserModelColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.UserModel.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.UserModel object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.UserModel) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((UserModelRealmProxyInterface) object).realmGet$userId();
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
                String realmGet$name = ((UserModelRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                java.util.Date realmGet$timestamp = ((UserModelRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
                }

                long friendsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.friendsListIndex, rowIndex);
                LinkView.nativeClear(friendsListNativeLinkViewPtr);
                RealmList<com.anubis.phlix.models.Photo> friendsListList = ((UserModelRealmProxyInterface) object).realmGet$friendsList();
                if (friendsListList != null) {
                    for (com.anubis.phlix.models.Photo friendsListItem : friendsListList) {
                        Long cacheItemIndexfriendsList = cache.get(friendsListItem);
                        if (cacheItemIndexfriendsList == null) {
                            cacheItemIndexfriendsList = PhotoRealmProxy.insertOrUpdate(realm, friendsListItem, cache);
                        }
                        LinkView.nativeAdd(friendsListNativeLinkViewPtr, cacheItemIndexfriendsList);
                    }
                }
                LinkView.nativeClose(friendsListNativeLinkViewPtr);


                long tagsListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.tagsListIndex, rowIndex);
                LinkView.nativeClear(tagsListNativeLinkViewPtr);
                RealmList<com.anubis.phlix.models.Tag> tagsListList = ((UserModelRealmProxyInterface) object).realmGet$tagsList();
                if (tagsListList != null) {
                    for (com.anubis.phlix.models.Tag tagsListItem : tagsListList) {
                        Long cacheItemIndextagsList = cache.get(tagsListItem);
                        if (cacheItemIndextagsList == null) {
                            cacheItemIndextagsList = TagRealmProxy.insertOrUpdate(realm, tagsListItem, cache);
                        }
                        LinkView.nativeAdd(tagsListNativeLinkViewPtr, cacheItemIndextagsList);
                    }
                }
                LinkView.nativeClose(tagsListNativeLinkViewPtr);

            }
        }
    }

    public static com.anubis.phlix.models.UserModel createDetachedCopy(com.anubis.phlix.models.UserModel realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.anubis.phlix.models.UserModel unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.anubis.phlix.models.UserModel)cachedObject.object;
            } else {
                unmanagedObject = (com.anubis.phlix.models.UserModel)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.anubis.phlix.models.UserModel();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((UserModelRealmProxyInterface) unmanagedObject).realmSet$userId(((UserModelRealmProxyInterface) realmObject).realmGet$userId());
        ((UserModelRealmProxyInterface) unmanagedObject).realmSet$name(((UserModelRealmProxyInterface) realmObject).realmGet$name());
        ((UserModelRealmProxyInterface) unmanagedObject).realmSet$timestamp(((UserModelRealmProxyInterface) realmObject).realmGet$timestamp());

        // Deep copy of friendsList
        if (currentDepth == maxDepth) {
            ((UserModelRealmProxyInterface) unmanagedObject).realmSet$friendsList(null);
        } else {
            RealmList<com.anubis.phlix.models.Photo> managedfriendsListList = ((UserModelRealmProxyInterface) realmObject).realmGet$friendsList();
            RealmList<com.anubis.phlix.models.Photo> unmanagedfriendsListList = new RealmList<com.anubis.phlix.models.Photo>();
            ((UserModelRealmProxyInterface) unmanagedObject).realmSet$friendsList(unmanagedfriendsListList);
            int nextDepth = currentDepth + 1;
            int size = managedfriendsListList.size();
            for (int i = 0; i < size; i++) {
                com.anubis.phlix.models.Photo item = PhotoRealmProxy.createDetachedCopy(managedfriendsListList.get(i), nextDepth, maxDepth, cache);
                unmanagedfriendsListList.add(item);
            }
        }

        // Deep copy of tagsList
        if (currentDepth == maxDepth) {
            ((UserModelRealmProxyInterface) unmanagedObject).realmSet$tagsList(null);
        } else {
            RealmList<com.anubis.phlix.models.Tag> managedtagsListList = ((UserModelRealmProxyInterface) realmObject).realmGet$tagsList();
            RealmList<com.anubis.phlix.models.Tag> unmanagedtagsListList = new RealmList<com.anubis.phlix.models.Tag>();
            ((UserModelRealmProxyInterface) unmanagedObject).realmSet$tagsList(unmanagedtagsListList);
            int nextDepth = currentDepth + 1;
            int size = managedtagsListList.size();
            for (int i = 0; i < size; i++) {
                com.anubis.phlix.models.Tag item = TagRealmProxy.createDetachedCopy(managedtagsListList.get(i), nextDepth, maxDepth, cache);
                unmanagedtagsListList.add(item);
            }
        }
        return unmanagedObject;
    }

    static com.anubis.phlix.models.UserModel update(Realm realm, com.anubis.phlix.models.UserModel realmObject, com.anubis.phlix.models.UserModel newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((UserModelRealmProxyInterface) realmObject).realmSet$name(((UserModelRealmProxyInterface) newObject).realmGet$name());
        ((UserModelRealmProxyInterface) realmObject).realmSet$timestamp(((UserModelRealmProxyInterface) newObject).realmGet$timestamp());
        RealmList<com.anubis.phlix.models.Photo> friendsListList = ((UserModelRealmProxyInterface) newObject).realmGet$friendsList();
        RealmList<com.anubis.phlix.models.Photo> friendsListRealmList = ((UserModelRealmProxyInterface) realmObject).realmGet$friendsList();
        friendsListRealmList.clear();
        if (friendsListList != null) {
            for (int i = 0; i < friendsListList.size(); i++) {
                com.anubis.phlix.models.Photo friendsListItem = friendsListList.get(i);
                com.anubis.phlix.models.Photo cachefriendsList = (com.anubis.phlix.models.Photo) cache.get(friendsListItem);
                if (cachefriendsList != null) {
                    friendsListRealmList.add(cachefriendsList);
                } else {
                    friendsListRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, friendsListList.get(i), true, cache));
                }
            }
        }
        RealmList<com.anubis.phlix.models.Tag> tagsListList = ((UserModelRealmProxyInterface) newObject).realmGet$tagsList();
        RealmList<com.anubis.phlix.models.Tag> tagsListRealmList = ((UserModelRealmProxyInterface) realmObject).realmGet$tagsList();
        tagsListRealmList.clear();
        if (tagsListList != null) {
            for (int i = 0; i < tagsListList.size(); i++) {
                com.anubis.phlix.models.Tag tagsListItem = tagsListList.get(i);
                com.anubis.phlix.models.Tag cachetagsList = (com.anubis.phlix.models.Tag) cache.get(tagsListItem);
                if (cachetagsList != null) {
                    tagsListRealmList.add(cachetagsList);
                } else {
                    tagsListRealmList.add(TagRealmProxy.copyOrUpdate(realm, tagsListList.get(i), true, cache));
                }
            }
        }
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("UserModel = [");
        stringBuilder.append("{userId:");
        stringBuilder.append(realmGet$userId() != null ? realmGet$userId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp() != null ? realmGet$timestamp() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{friendsList:");
        stringBuilder.append("RealmList<Photo>[").append(realmGet$friendsList().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tagsList:");
        stringBuilder.append("RealmList<Tag>[").append(realmGet$tagsList().size()).append("]");
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
        UserModelRealmProxy aUserModel = (UserModelRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUserModel.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserModel.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUserModel.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
