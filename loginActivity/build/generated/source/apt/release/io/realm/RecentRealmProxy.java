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

public class RecentRealmProxy extends com.anubis.phlix.models.Recent
    implements RealmObjectProxy, RecentRealmProxyInterface {

    static final class RecentColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long timestampIndex;
        public long recentPhotosIndex;
        public long hotTagListIndex;

        RecentColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(4);
            this.idIndex = getValidColumnIndex(path, table, "Recent", "id");
            indicesMap.put("id", this.idIndex);
            this.timestampIndex = getValidColumnIndex(path, table, "Recent", "timestamp");
            indicesMap.put("timestamp", this.timestampIndex);
            this.recentPhotosIndex = getValidColumnIndex(path, table, "Recent", "recentPhotos");
            indicesMap.put("recentPhotos", this.recentPhotosIndex);
            this.hotTagListIndex = getValidColumnIndex(path, table, "Recent", "hotTagList");
            indicesMap.put("hotTagList", this.hotTagListIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final RecentColumnInfo otherInfo = (RecentColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.timestampIndex = otherInfo.timestampIndex;
            this.recentPhotosIndex = otherInfo.recentPhotosIndex;
            this.hotTagListIndex = otherInfo.hotTagListIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final RecentColumnInfo clone() {
            return (RecentColumnInfo) super.clone();
        }

    }
    private RecentColumnInfo columnInfo;
    private ProxyState proxyState;
    private RealmList<com.anubis.phlix.models.Photo> recentPhotosRealmList;
    private RealmList<com.anubis.phlix.models.Tag> hotTagListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("timestamp");
        fieldNames.add("recentPhotos");
        fieldNames.add("hotTagList");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RecentRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (RecentColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.anubis.phlix.models.Recent.class, this);
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

    public RealmList<com.anubis.phlix.models.Photo> realmGet$recentPhotos() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (recentPhotosRealmList != null) {
            return recentPhotosRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.recentPhotosIndex);
            recentPhotosRealmList = new RealmList<com.anubis.phlix.models.Photo>(com.anubis.phlix.models.Photo.class, linkView, proxyState.getRealm$realm());
            return recentPhotosRealmList;
        }
    }

    public void realmSet$recentPhotos(RealmList<com.anubis.phlix.models.Photo> value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("recentPhotos")) {
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
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.recentPhotosIndex);
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

    public RealmList<com.anubis.phlix.models.Tag> realmGet$hotTagList() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (hotTagListRealmList != null) {
            return hotTagListRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.hotTagListIndex);
            hotTagListRealmList = new RealmList<com.anubis.phlix.models.Tag>(com.anubis.phlix.models.Tag.class, linkView, proxyState.getRealm$realm());
            return hotTagListRealmList;
        }
    }

    public void realmSet$hotTagList(RealmList<com.anubis.phlix.models.Tag> value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("hotTagList")) {
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
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.hotTagListIndex);
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
        if (!realmSchema.contains("Recent")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Recent");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("timestamp", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            if (!realmSchema.contains("Photo")) {
                PhotoRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("recentPhotos", RealmFieldType.LIST, realmSchema.get("Photo")));
            if (!realmSchema.contains("Tag")) {
                TagRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("hotTagList", RealmFieldType.LIST, realmSchema.get("Tag")));
            return realmObjectSchema;
        }
        return realmSchema.get("Recent");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Recent")) {
            Table table = sharedRealm.getTable("class_Recent");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.DATE, "timestamp", Table.NULLABLE);
            if (!sharedRealm.hasTable("class_Photo")) {
                PhotoRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.LIST, "recentPhotos", sharedRealm.getTable("class_Photo"));
            if (!sharedRealm.hasTable("class_Tag")) {
                TagRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.LIST, "hotTagList", sharedRealm.getTable("class_Tag"));
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_Recent");
    }

    public static RecentColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Recent")) {
            Table table = sharedRealm.getTable("class_Recent");
            final long columnCount = table.getColumnCount();
            if (columnCount != 4) {
                if (columnCount < 4) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 4; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final RecentColumnInfo columnInfo = new RecentColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("timestamp")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'timestamp' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("timestamp") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'timestamp' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.timestampIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'timestamp' is required. Either set @Required to field 'timestamp' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("recentPhotos")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'recentPhotos'");
            }
            if (columnTypes.get("recentPhotos") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Photo' for field 'recentPhotos'");
            }
            if (!sharedRealm.hasTable("class_Photo")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Photo' for field 'recentPhotos'");
            }
            Table table_2 = sharedRealm.getTable("class_Photo");
            if (!table.getLinkTarget(columnInfo.recentPhotosIndex).hasSameSchema(table_2)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'recentPhotos': '" + table.getLinkTarget(columnInfo.recentPhotosIndex).getName() + "' expected - was '" + table_2.getName() + "'");
            }
            if (!columnTypes.containsKey("hotTagList")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'hotTagList'");
            }
            if (columnTypes.get("hotTagList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Tag' for field 'hotTagList'");
            }
            if (!sharedRealm.hasTable("class_Tag")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Tag' for field 'hotTagList'");
            }
            Table table_3 = sharedRealm.getTable("class_Tag");
            if (!table.getLinkTarget(columnInfo.hotTagListIndex).hasSameSchema(table_3)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'hotTagList': '" + table.getLinkTarget(columnInfo.hotTagListIndex).getName() + "' expected - was '" + table_3.getName() + "'");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Recent' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Recent";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.anubis.phlix.models.Recent createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(2);
        com.anubis.phlix.models.Recent obj = null;
        if (update) {
            Table table = realm.getTable(com.anubis.phlix.models.Recent.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Recent.class), false, Collections.<String> emptyList());
                    obj = new io.realm.RecentRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("recentPhotos")) {
                excludeFields.add("recentPhotos");
            }
            if (json.has("hotTagList")) {
                excludeFields.add("hotTagList");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.RecentRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Recent.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.RecentRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Recent.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("timestamp")) {
            if (json.isNull("timestamp")) {
                ((RecentRealmProxyInterface) obj).realmSet$timestamp(null);
            } else {
                Object timestamp = json.get("timestamp");
                if (timestamp instanceof String) {
                    ((RecentRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((RecentRealmProxyInterface) obj).realmSet$timestamp(new Date(json.getLong("timestamp")));
                }
            }
        }
        if (json.has("recentPhotos")) {
            if (json.isNull("recentPhotos")) {
                ((RecentRealmProxyInterface) obj).realmSet$recentPhotos(null);
            } else {
                ((RecentRealmProxyInterface) obj).realmGet$recentPhotos().clear();
                JSONArray array = json.getJSONArray("recentPhotos");
                for (int i = 0; i < array.length(); i++) {
                    com.anubis.phlix.models.Photo item = PhotoRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((RecentRealmProxyInterface) obj).realmGet$recentPhotos().add(item);
                }
            }
        }
        if (json.has("hotTagList")) {
            if (json.isNull("hotTagList")) {
                ((RecentRealmProxyInterface) obj).realmSet$hotTagList(null);
            } else {
                ((RecentRealmProxyInterface) obj).realmGet$hotTagList().clear();
                JSONArray array = json.getJSONArray("hotTagList");
                for (int i = 0; i < array.length(); i++) {
                    com.anubis.phlix.models.Tag item = TagRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((RecentRealmProxyInterface) obj).realmGet$hotTagList().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.anubis.phlix.models.Recent createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.anubis.phlix.models.Recent obj = new com.anubis.phlix.models.Recent();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RecentRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((RecentRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("timestamp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RecentRealmProxyInterface) obj).realmSet$timestamp(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((RecentRealmProxyInterface) obj).realmSet$timestamp(new Date(timestamp));
                    }
                } else {
                    ((RecentRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("recentPhotos")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RecentRealmProxyInterface) obj).realmSet$recentPhotos(null);
                } else {
                    ((RecentRealmProxyInterface) obj).realmSet$recentPhotos(new RealmList<com.anubis.phlix.models.Photo>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.anubis.phlix.models.Photo item = PhotoRealmProxy.createUsingJsonStream(realm, reader);
                        ((RecentRealmProxyInterface) obj).realmGet$recentPhotos().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("hotTagList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RecentRealmProxyInterface) obj).realmSet$hotTagList(null);
                } else {
                    ((RecentRealmProxyInterface) obj).realmSet$hotTagList(new RealmList<com.anubis.phlix.models.Tag>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.anubis.phlix.models.Tag item = TagRealmProxy.createUsingJsonStream(realm, reader);
                        ((RecentRealmProxyInterface) obj).realmGet$hotTagList().add(item);
                    }
                    reader.endArray();
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

    public static com.anubis.phlix.models.Recent copyOrUpdate(Realm realm, com.anubis.phlix.models.Recent object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Recent) cachedRealmObject;
        } else {
            com.anubis.phlix.models.Recent realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.anubis.phlix.models.Recent.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((RecentRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Recent.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.RecentRealmProxy();
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

    public static com.anubis.phlix.models.Recent copy(Realm realm, com.anubis.phlix.models.Recent newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Recent) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.anubis.phlix.models.Recent realmObject = realm.createObjectInternal(com.anubis.phlix.models.Recent.class, ((RecentRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((RecentRealmProxyInterface) realmObject).realmSet$timestamp(((RecentRealmProxyInterface) newObject).realmGet$timestamp());

            RealmList<com.anubis.phlix.models.Photo> recentPhotosList = ((RecentRealmProxyInterface) newObject).realmGet$recentPhotos();
            if (recentPhotosList != null) {
                RealmList<com.anubis.phlix.models.Photo> recentPhotosRealmList = ((RecentRealmProxyInterface) realmObject).realmGet$recentPhotos();
                for (int i = 0; i < recentPhotosList.size(); i++) {
                    com.anubis.phlix.models.Photo recentPhotosItem = recentPhotosList.get(i);
                    com.anubis.phlix.models.Photo cacherecentPhotos = (com.anubis.phlix.models.Photo) cache.get(recentPhotosItem);
                    if (cacherecentPhotos != null) {
                        recentPhotosRealmList.add(cacherecentPhotos);
                    } else {
                        recentPhotosRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, recentPhotosList.get(i), update, cache));
                    }
                }
            }


            RealmList<com.anubis.phlix.models.Tag> hotTagListList = ((RecentRealmProxyInterface) newObject).realmGet$hotTagList();
            if (hotTagListList != null) {
                RealmList<com.anubis.phlix.models.Tag> hotTagListRealmList = ((RecentRealmProxyInterface) realmObject).realmGet$hotTagList();
                for (int i = 0; i < hotTagListList.size(); i++) {
                    com.anubis.phlix.models.Tag hotTagListItem = hotTagListList.get(i);
                    com.anubis.phlix.models.Tag cachehotTagList = (com.anubis.phlix.models.Tag) cache.get(hotTagListItem);
                    if (cachehotTagList != null) {
                        hotTagListRealmList.add(cachehotTagList);
                    } else {
                        hotTagListRealmList.add(TagRealmProxy.copyOrUpdate(realm, hotTagListList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.anubis.phlix.models.Recent object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Recent.class);
        long tableNativePtr = table.getNativeTablePointer();
        RecentColumnInfo columnInfo = (RecentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Recent.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RecentRealmProxyInterface) object).realmGet$id();
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
        java.util.Date realmGet$timestamp = ((RecentRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        }

        RealmList<com.anubis.phlix.models.Photo> recentPhotosList = ((RecentRealmProxyInterface) object).realmGet$recentPhotos();
        if (recentPhotosList != null) {
            long recentPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.recentPhotosIndex, rowIndex);
            for (com.anubis.phlix.models.Photo recentPhotosItem : recentPhotosList) {
                Long cacheItemIndexrecentPhotos = cache.get(recentPhotosItem);
                if (cacheItemIndexrecentPhotos == null) {
                    cacheItemIndexrecentPhotos = PhotoRealmProxy.insert(realm, recentPhotosItem, cache);
                }
                LinkView.nativeAdd(recentPhotosNativeLinkViewPtr, cacheItemIndexrecentPhotos);
            }
            LinkView.nativeClose(recentPhotosNativeLinkViewPtr);
        }


        RealmList<com.anubis.phlix.models.Tag> hotTagListList = ((RecentRealmProxyInterface) object).realmGet$hotTagList();
        if (hotTagListList != null) {
            long hotTagListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.hotTagListIndex, rowIndex);
            for (com.anubis.phlix.models.Tag hotTagListItem : hotTagListList) {
                Long cacheItemIndexhotTagList = cache.get(hotTagListItem);
                if (cacheItemIndexhotTagList == null) {
                    cacheItemIndexhotTagList = TagRealmProxy.insert(realm, hotTagListItem, cache);
                }
                LinkView.nativeAdd(hotTagListNativeLinkViewPtr, cacheItemIndexhotTagList);
            }
            LinkView.nativeClose(hotTagListNativeLinkViewPtr);
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Recent.class);
        long tableNativePtr = table.getNativeTablePointer();
        RecentColumnInfo columnInfo = (RecentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Recent.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Recent object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Recent) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RecentRealmProxyInterface) object).realmGet$id();
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
                java.util.Date realmGet$timestamp = ((RecentRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                }

                RealmList<com.anubis.phlix.models.Photo> recentPhotosList = ((RecentRealmProxyInterface) object).realmGet$recentPhotos();
                if (recentPhotosList != null) {
                    long recentPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.recentPhotosIndex, rowIndex);
                    for (com.anubis.phlix.models.Photo recentPhotosItem : recentPhotosList) {
                        Long cacheItemIndexrecentPhotos = cache.get(recentPhotosItem);
                        if (cacheItemIndexrecentPhotos == null) {
                            cacheItemIndexrecentPhotos = PhotoRealmProxy.insert(realm, recentPhotosItem, cache);
                        }
                        LinkView.nativeAdd(recentPhotosNativeLinkViewPtr, cacheItemIndexrecentPhotos);
                    }
                    LinkView.nativeClose(recentPhotosNativeLinkViewPtr);
                }


                RealmList<com.anubis.phlix.models.Tag> hotTagListList = ((RecentRealmProxyInterface) object).realmGet$hotTagList();
                if (hotTagListList != null) {
                    long hotTagListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.hotTagListIndex, rowIndex);
                    for (com.anubis.phlix.models.Tag hotTagListItem : hotTagListList) {
                        Long cacheItemIndexhotTagList = cache.get(hotTagListItem);
                        if (cacheItemIndexhotTagList == null) {
                            cacheItemIndexhotTagList = TagRealmProxy.insert(realm, hotTagListItem, cache);
                        }
                        LinkView.nativeAdd(hotTagListNativeLinkViewPtr, cacheItemIndexhotTagList);
                    }
                    LinkView.nativeClose(hotTagListNativeLinkViewPtr);
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.anubis.phlix.models.Recent object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Recent.class);
        long tableNativePtr = table.getNativeTablePointer();
        RecentColumnInfo columnInfo = (RecentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Recent.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RecentRealmProxyInterface) object).realmGet$id();
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
        java.util.Date realmGet$timestamp = ((RecentRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
        }

        long recentPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.recentPhotosIndex, rowIndex);
        LinkView.nativeClear(recentPhotosNativeLinkViewPtr);
        RealmList<com.anubis.phlix.models.Photo> recentPhotosList = ((RecentRealmProxyInterface) object).realmGet$recentPhotos();
        if (recentPhotosList != null) {
            for (com.anubis.phlix.models.Photo recentPhotosItem : recentPhotosList) {
                Long cacheItemIndexrecentPhotos = cache.get(recentPhotosItem);
                if (cacheItemIndexrecentPhotos == null) {
                    cacheItemIndexrecentPhotos = PhotoRealmProxy.insertOrUpdate(realm, recentPhotosItem, cache);
                }
                LinkView.nativeAdd(recentPhotosNativeLinkViewPtr, cacheItemIndexrecentPhotos);
            }
        }
        LinkView.nativeClose(recentPhotosNativeLinkViewPtr);


        long hotTagListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.hotTagListIndex, rowIndex);
        LinkView.nativeClear(hotTagListNativeLinkViewPtr);
        RealmList<com.anubis.phlix.models.Tag> hotTagListList = ((RecentRealmProxyInterface) object).realmGet$hotTagList();
        if (hotTagListList != null) {
            for (com.anubis.phlix.models.Tag hotTagListItem : hotTagListList) {
                Long cacheItemIndexhotTagList = cache.get(hotTagListItem);
                if (cacheItemIndexhotTagList == null) {
                    cacheItemIndexhotTagList = TagRealmProxy.insertOrUpdate(realm, hotTagListItem, cache);
                }
                LinkView.nativeAdd(hotTagListNativeLinkViewPtr, cacheItemIndexhotTagList);
            }
        }
        LinkView.nativeClose(hotTagListNativeLinkViewPtr);

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Recent.class);
        long tableNativePtr = table.getNativeTablePointer();
        RecentColumnInfo columnInfo = (RecentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Recent.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Recent object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Recent) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RecentRealmProxyInterface) object).realmGet$id();
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
                java.util.Date realmGet$timestamp = ((RecentRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
                }

                long recentPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.recentPhotosIndex, rowIndex);
                LinkView.nativeClear(recentPhotosNativeLinkViewPtr);
                RealmList<com.anubis.phlix.models.Photo> recentPhotosList = ((RecentRealmProxyInterface) object).realmGet$recentPhotos();
                if (recentPhotosList != null) {
                    for (com.anubis.phlix.models.Photo recentPhotosItem : recentPhotosList) {
                        Long cacheItemIndexrecentPhotos = cache.get(recentPhotosItem);
                        if (cacheItemIndexrecentPhotos == null) {
                            cacheItemIndexrecentPhotos = PhotoRealmProxy.insertOrUpdate(realm, recentPhotosItem, cache);
                        }
                        LinkView.nativeAdd(recentPhotosNativeLinkViewPtr, cacheItemIndexrecentPhotos);
                    }
                }
                LinkView.nativeClose(recentPhotosNativeLinkViewPtr);


                long hotTagListNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.hotTagListIndex, rowIndex);
                LinkView.nativeClear(hotTagListNativeLinkViewPtr);
                RealmList<com.anubis.phlix.models.Tag> hotTagListList = ((RecentRealmProxyInterface) object).realmGet$hotTagList();
                if (hotTagListList != null) {
                    for (com.anubis.phlix.models.Tag hotTagListItem : hotTagListList) {
                        Long cacheItemIndexhotTagList = cache.get(hotTagListItem);
                        if (cacheItemIndexhotTagList == null) {
                            cacheItemIndexhotTagList = TagRealmProxy.insertOrUpdate(realm, hotTagListItem, cache);
                        }
                        LinkView.nativeAdd(hotTagListNativeLinkViewPtr, cacheItemIndexhotTagList);
                    }
                }
                LinkView.nativeClose(hotTagListNativeLinkViewPtr);

            }
        }
    }

    public static com.anubis.phlix.models.Recent createDetachedCopy(com.anubis.phlix.models.Recent realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.anubis.phlix.models.Recent unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.anubis.phlix.models.Recent)cachedObject.object;
            } else {
                unmanagedObject = (com.anubis.phlix.models.Recent)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.anubis.phlix.models.Recent();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((RecentRealmProxyInterface) unmanagedObject).realmSet$id(((RecentRealmProxyInterface) realmObject).realmGet$id());
        ((RecentRealmProxyInterface) unmanagedObject).realmSet$timestamp(((RecentRealmProxyInterface) realmObject).realmGet$timestamp());

        // Deep copy of recentPhotos
        if (currentDepth == maxDepth) {
            ((RecentRealmProxyInterface) unmanagedObject).realmSet$recentPhotos(null);
        } else {
            RealmList<com.anubis.phlix.models.Photo> managedrecentPhotosList = ((RecentRealmProxyInterface) realmObject).realmGet$recentPhotos();
            RealmList<com.anubis.phlix.models.Photo> unmanagedrecentPhotosList = new RealmList<com.anubis.phlix.models.Photo>();
            ((RecentRealmProxyInterface) unmanagedObject).realmSet$recentPhotos(unmanagedrecentPhotosList);
            int nextDepth = currentDepth + 1;
            int size = managedrecentPhotosList.size();
            for (int i = 0; i < size; i++) {
                com.anubis.phlix.models.Photo item = PhotoRealmProxy.createDetachedCopy(managedrecentPhotosList.get(i), nextDepth, maxDepth, cache);
                unmanagedrecentPhotosList.add(item);
            }
        }

        // Deep copy of hotTagList
        if (currentDepth == maxDepth) {
            ((RecentRealmProxyInterface) unmanagedObject).realmSet$hotTagList(null);
        } else {
            RealmList<com.anubis.phlix.models.Tag> managedhotTagListList = ((RecentRealmProxyInterface) realmObject).realmGet$hotTagList();
            RealmList<com.anubis.phlix.models.Tag> unmanagedhotTagListList = new RealmList<com.anubis.phlix.models.Tag>();
            ((RecentRealmProxyInterface) unmanagedObject).realmSet$hotTagList(unmanagedhotTagListList);
            int nextDepth = currentDepth + 1;
            int size = managedhotTagListList.size();
            for (int i = 0; i < size; i++) {
                com.anubis.phlix.models.Tag item = TagRealmProxy.createDetachedCopy(managedhotTagListList.get(i), nextDepth, maxDepth, cache);
                unmanagedhotTagListList.add(item);
            }
        }
        return unmanagedObject;
    }

    static com.anubis.phlix.models.Recent update(Realm realm, com.anubis.phlix.models.Recent realmObject, com.anubis.phlix.models.Recent newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((RecentRealmProxyInterface) realmObject).realmSet$timestamp(((RecentRealmProxyInterface) newObject).realmGet$timestamp());
        RealmList<com.anubis.phlix.models.Photo> recentPhotosList = ((RecentRealmProxyInterface) newObject).realmGet$recentPhotos();
        RealmList<com.anubis.phlix.models.Photo> recentPhotosRealmList = ((RecentRealmProxyInterface) realmObject).realmGet$recentPhotos();
        recentPhotosRealmList.clear();
        if (recentPhotosList != null) {
            for (int i = 0; i < recentPhotosList.size(); i++) {
                com.anubis.phlix.models.Photo recentPhotosItem = recentPhotosList.get(i);
                com.anubis.phlix.models.Photo cacherecentPhotos = (com.anubis.phlix.models.Photo) cache.get(recentPhotosItem);
                if (cacherecentPhotos != null) {
                    recentPhotosRealmList.add(cacherecentPhotos);
                } else {
                    recentPhotosRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, recentPhotosList.get(i), true, cache));
                }
            }
        }
        RealmList<com.anubis.phlix.models.Tag> hotTagListList = ((RecentRealmProxyInterface) newObject).realmGet$hotTagList();
        RealmList<com.anubis.phlix.models.Tag> hotTagListRealmList = ((RecentRealmProxyInterface) realmObject).realmGet$hotTagList();
        hotTagListRealmList.clear();
        if (hotTagListList != null) {
            for (int i = 0; i < hotTagListList.size(); i++) {
                com.anubis.phlix.models.Tag hotTagListItem = hotTagListList.get(i);
                com.anubis.phlix.models.Tag cachehotTagList = (com.anubis.phlix.models.Tag) cache.get(hotTagListItem);
                if (cachehotTagList != null) {
                    hotTagListRealmList.add(cachehotTagList);
                } else {
                    hotTagListRealmList.add(TagRealmProxy.copyOrUpdate(realm, hotTagListList.get(i), true, cache));
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
        StringBuilder stringBuilder = new StringBuilder("Recent = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp() != null ? realmGet$timestamp() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{recentPhotos:");
        stringBuilder.append("RealmList<Photo>[").append(realmGet$recentPhotos().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{hotTagList:");
        stringBuilder.append("RealmList<Tag>[").append(realmGet$hotTagList().size()).append("]");
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
        RecentRealmProxy aRecent = (RecentRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aRecent.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aRecent.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aRecent.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
