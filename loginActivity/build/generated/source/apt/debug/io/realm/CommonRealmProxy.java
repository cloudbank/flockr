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

public class CommonRealmProxy extends com.anubis.phlix.models.Common
    implements RealmObjectProxy, CommonRealmProxyInterface {

    static final class CommonColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long timestampIndex;
        public long commonPhotosIndex;

        CommonColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.idIndex = getValidColumnIndex(path, table, "Common", "id");
            indicesMap.put("id", this.idIndex);
            this.timestampIndex = getValidColumnIndex(path, table, "Common", "timestamp");
            indicesMap.put("timestamp", this.timestampIndex);
            this.commonPhotosIndex = getValidColumnIndex(path, table, "Common", "commonPhotos");
            indicesMap.put("commonPhotos", this.commonPhotosIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final CommonColumnInfo otherInfo = (CommonColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.timestampIndex = otherInfo.timestampIndex;
            this.commonPhotosIndex = otherInfo.commonPhotosIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final CommonColumnInfo clone() {
            return (CommonColumnInfo) super.clone();
        }

    }
    private CommonColumnInfo columnInfo;
    private ProxyState proxyState;
    private RealmList<com.anubis.phlix.models.Photo> commonPhotosRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("timestamp");
        fieldNames.add("commonPhotos");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CommonRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CommonColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.anubis.phlix.models.Common.class, this);
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

    public RealmList<com.anubis.phlix.models.Photo> realmGet$commonPhotos() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (commonPhotosRealmList != null) {
            return commonPhotosRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.commonPhotosIndex);
            commonPhotosRealmList = new RealmList<com.anubis.phlix.models.Photo>(com.anubis.phlix.models.Photo.class, linkView, proxyState.getRealm$realm());
            return commonPhotosRealmList;
        }
    }

    public void realmSet$commonPhotos(RealmList<com.anubis.phlix.models.Photo> value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("commonPhotos")) {
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
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.commonPhotosIndex);
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
        if (!realmSchema.contains("Common")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Common");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("timestamp", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            if (!realmSchema.contains("Photo")) {
                PhotoRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("commonPhotos", RealmFieldType.LIST, realmSchema.get("Photo")));
            return realmObjectSchema;
        }
        return realmSchema.get("Common");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Common")) {
            Table table = sharedRealm.getTable("class_Common");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.DATE, "timestamp", Table.NULLABLE);
            if (!sharedRealm.hasTable("class_Photo")) {
                PhotoRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.LIST, "commonPhotos", sharedRealm.getTable("class_Photo"));
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_Common");
    }

    public static CommonColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Common")) {
            Table table = sharedRealm.getTable("class_Common");
            final long columnCount = table.getColumnCount();
            if (columnCount != 3) {
                if (columnCount < 3) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 3 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 3 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 3 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final CommonColumnInfo columnInfo = new CommonColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("commonPhotos")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'commonPhotos'");
            }
            if (columnTypes.get("commonPhotos") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Photo' for field 'commonPhotos'");
            }
            if (!sharedRealm.hasTable("class_Photo")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Photo' for field 'commonPhotos'");
            }
            Table table_2 = sharedRealm.getTable("class_Photo");
            if (!table.getLinkTarget(columnInfo.commonPhotosIndex).hasSameSchema(table_2)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'commonPhotos': '" + table.getLinkTarget(columnInfo.commonPhotosIndex).getName() + "' expected - was '" + table_2.getName() + "'");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Common' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Common";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.anubis.phlix.models.Common createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.anubis.phlix.models.Common obj = null;
        if (update) {
            Table table = realm.getTable(com.anubis.phlix.models.Common.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Common.class), false, Collections.<String> emptyList());
                    obj = new io.realm.CommonRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("commonPhotos")) {
                excludeFields.add("commonPhotos");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.CommonRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Common.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.CommonRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Common.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("timestamp")) {
            if (json.isNull("timestamp")) {
                ((CommonRealmProxyInterface) obj).realmSet$timestamp(null);
            } else {
                Object timestamp = json.get("timestamp");
                if (timestamp instanceof String) {
                    ((CommonRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((CommonRealmProxyInterface) obj).realmSet$timestamp(new Date(json.getLong("timestamp")));
                }
            }
        }
        if (json.has("commonPhotos")) {
            if (json.isNull("commonPhotos")) {
                ((CommonRealmProxyInterface) obj).realmSet$commonPhotos(null);
            } else {
                ((CommonRealmProxyInterface) obj).realmGet$commonPhotos().clear();
                JSONArray array = json.getJSONArray("commonPhotos");
                for (int i = 0; i < array.length(); i++) {
                    com.anubis.phlix.models.Photo item = PhotoRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((CommonRealmProxyInterface) obj).realmGet$commonPhotos().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.anubis.phlix.models.Common createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.anubis.phlix.models.Common obj = new com.anubis.phlix.models.Common();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommonRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((CommonRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("timestamp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommonRealmProxyInterface) obj).realmSet$timestamp(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((CommonRealmProxyInterface) obj).realmSet$timestamp(new Date(timestamp));
                    }
                } else {
                    ((CommonRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("commonPhotos")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommonRealmProxyInterface) obj).realmSet$commonPhotos(null);
                } else {
                    ((CommonRealmProxyInterface) obj).realmSet$commonPhotos(new RealmList<com.anubis.phlix.models.Photo>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.anubis.phlix.models.Photo item = PhotoRealmProxy.createUsingJsonStream(realm, reader);
                        ((CommonRealmProxyInterface) obj).realmGet$commonPhotos().add(item);
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

    public static com.anubis.phlix.models.Common copyOrUpdate(Realm realm, com.anubis.phlix.models.Common object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Common) cachedRealmObject;
        } else {
            com.anubis.phlix.models.Common realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.anubis.phlix.models.Common.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((CommonRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Common.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.CommonRealmProxy();
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

    public static com.anubis.phlix.models.Common copy(Realm realm, com.anubis.phlix.models.Common newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Common) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.anubis.phlix.models.Common realmObject = realm.createObjectInternal(com.anubis.phlix.models.Common.class, ((CommonRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((CommonRealmProxyInterface) realmObject).realmSet$timestamp(((CommonRealmProxyInterface) newObject).realmGet$timestamp());

            RealmList<com.anubis.phlix.models.Photo> commonPhotosList = ((CommonRealmProxyInterface) newObject).realmGet$commonPhotos();
            if (commonPhotosList != null) {
                RealmList<com.anubis.phlix.models.Photo> commonPhotosRealmList = ((CommonRealmProxyInterface) realmObject).realmGet$commonPhotos();
                for (int i = 0; i < commonPhotosList.size(); i++) {
                    com.anubis.phlix.models.Photo commonPhotosItem = commonPhotosList.get(i);
                    com.anubis.phlix.models.Photo cachecommonPhotos = (com.anubis.phlix.models.Photo) cache.get(commonPhotosItem);
                    if (cachecommonPhotos != null) {
                        commonPhotosRealmList.add(cachecommonPhotos);
                    } else {
                        commonPhotosRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, commonPhotosList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.anubis.phlix.models.Common object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Common.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommonColumnInfo columnInfo = (CommonColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Common.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CommonRealmProxyInterface) object).realmGet$id();
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
        java.util.Date realmGet$timestamp = ((CommonRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        }

        RealmList<com.anubis.phlix.models.Photo> commonPhotosList = ((CommonRealmProxyInterface) object).realmGet$commonPhotos();
        if (commonPhotosList != null) {
            long commonPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commonPhotosIndex, rowIndex);
            for (com.anubis.phlix.models.Photo commonPhotosItem : commonPhotosList) {
                Long cacheItemIndexcommonPhotos = cache.get(commonPhotosItem);
                if (cacheItemIndexcommonPhotos == null) {
                    cacheItemIndexcommonPhotos = PhotoRealmProxy.insert(realm, commonPhotosItem, cache);
                }
                LinkView.nativeAdd(commonPhotosNativeLinkViewPtr, cacheItemIndexcommonPhotos);
            }
            LinkView.nativeClose(commonPhotosNativeLinkViewPtr);
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Common.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommonColumnInfo columnInfo = (CommonColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Common.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Common object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Common) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CommonRealmProxyInterface) object).realmGet$id();
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
                java.util.Date realmGet$timestamp = ((CommonRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                }

                RealmList<com.anubis.phlix.models.Photo> commonPhotosList = ((CommonRealmProxyInterface) object).realmGet$commonPhotos();
                if (commonPhotosList != null) {
                    long commonPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commonPhotosIndex, rowIndex);
                    for (com.anubis.phlix.models.Photo commonPhotosItem : commonPhotosList) {
                        Long cacheItemIndexcommonPhotos = cache.get(commonPhotosItem);
                        if (cacheItemIndexcommonPhotos == null) {
                            cacheItemIndexcommonPhotos = PhotoRealmProxy.insert(realm, commonPhotosItem, cache);
                        }
                        LinkView.nativeAdd(commonPhotosNativeLinkViewPtr, cacheItemIndexcommonPhotos);
                    }
                    LinkView.nativeClose(commonPhotosNativeLinkViewPtr);
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.anubis.phlix.models.Common object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Common.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommonColumnInfo columnInfo = (CommonColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Common.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CommonRealmProxyInterface) object).realmGet$id();
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
        java.util.Date realmGet$timestamp = ((CommonRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
        }

        long commonPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commonPhotosIndex, rowIndex);
        LinkView.nativeClear(commonPhotosNativeLinkViewPtr);
        RealmList<com.anubis.phlix.models.Photo> commonPhotosList = ((CommonRealmProxyInterface) object).realmGet$commonPhotos();
        if (commonPhotosList != null) {
            for (com.anubis.phlix.models.Photo commonPhotosItem : commonPhotosList) {
                Long cacheItemIndexcommonPhotos = cache.get(commonPhotosItem);
                if (cacheItemIndexcommonPhotos == null) {
                    cacheItemIndexcommonPhotos = PhotoRealmProxy.insertOrUpdate(realm, commonPhotosItem, cache);
                }
                LinkView.nativeAdd(commonPhotosNativeLinkViewPtr, cacheItemIndexcommonPhotos);
            }
        }
        LinkView.nativeClose(commonPhotosNativeLinkViewPtr);

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Common.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommonColumnInfo columnInfo = (CommonColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Common.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Common object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Common) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CommonRealmProxyInterface) object).realmGet$id();
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
                java.util.Date realmGet$timestamp = ((CommonRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
                }

                long commonPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.commonPhotosIndex, rowIndex);
                LinkView.nativeClear(commonPhotosNativeLinkViewPtr);
                RealmList<com.anubis.phlix.models.Photo> commonPhotosList = ((CommonRealmProxyInterface) object).realmGet$commonPhotos();
                if (commonPhotosList != null) {
                    for (com.anubis.phlix.models.Photo commonPhotosItem : commonPhotosList) {
                        Long cacheItemIndexcommonPhotos = cache.get(commonPhotosItem);
                        if (cacheItemIndexcommonPhotos == null) {
                            cacheItemIndexcommonPhotos = PhotoRealmProxy.insertOrUpdate(realm, commonPhotosItem, cache);
                        }
                        LinkView.nativeAdd(commonPhotosNativeLinkViewPtr, cacheItemIndexcommonPhotos);
                    }
                }
                LinkView.nativeClose(commonPhotosNativeLinkViewPtr);

            }
        }
    }

    public static com.anubis.phlix.models.Common createDetachedCopy(com.anubis.phlix.models.Common realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.anubis.phlix.models.Common unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.anubis.phlix.models.Common)cachedObject.object;
            } else {
                unmanagedObject = (com.anubis.phlix.models.Common)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.anubis.phlix.models.Common();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((CommonRealmProxyInterface) unmanagedObject).realmSet$id(((CommonRealmProxyInterface) realmObject).realmGet$id());
        ((CommonRealmProxyInterface) unmanagedObject).realmSet$timestamp(((CommonRealmProxyInterface) realmObject).realmGet$timestamp());

        // Deep copy of commonPhotos
        if (currentDepth == maxDepth) {
            ((CommonRealmProxyInterface) unmanagedObject).realmSet$commonPhotos(null);
        } else {
            RealmList<com.anubis.phlix.models.Photo> managedcommonPhotosList = ((CommonRealmProxyInterface) realmObject).realmGet$commonPhotos();
            RealmList<com.anubis.phlix.models.Photo> unmanagedcommonPhotosList = new RealmList<com.anubis.phlix.models.Photo>();
            ((CommonRealmProxyInterface) unmanagedObject).realmSet$commonPhotos(unmanagedcommonPhotosList);
            int nextDepth = currentDepth + 1;
            int size = managedcommonPhotosList.size();
            for (int i = 0; i < size; i++) {
                com.anubis.phlix.models.Photo item = PhotoRealmProxy.createDetachedCopy(managedcommonPhotosList.get(i), nextDepth, maxDepth, cache);
                unmanagedcommonPhotosList.add(item);
            }
        }
        return unmanagedObject;
    }

    static com.anubis.phlix.models.Common update(Realm realm, com.anubis.phlix.models.Common realmObject, com.anubis.phlix.models.Common newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((CommonRealmProxyInterface) realmObject).realmSet$timestamp(((CommonRealmProxyInterface) newObject).realmGet$timestamp());
        RealmList<com.anubis.phlix.models.Photo> commonPhotosList = ((CommonRealmProxyInterface) newObject).realmGet$commonPhotos();
        RealmList<com.anubis.phlix.models.Photo> commonPhotosRealmList = ((CommonRealmProxyInterface) realmObject).realmGet$commonPhotos();
        commonPhotosRealmList.clear();
        if (commonPhotosList != null) {
            for (int i = 0; i < commonPhotosList.size(); i++) {
                com.anubis.phlix.models.Photo commonPhotosItem = commonPhotosList.get(i);
                com.anubis.phlix.models.Photo cachecommonPhotos = (com.anubis.phlix.models.Photo) cache.get(commonPhotosItem);
                if (cachecommonPhotos != null) {
                    commonPhotosRealmList.add(cachecommonPhotos);
                } else {
                    commonPhotosRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, commonPhotosList.get(i), true, cache));
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
        StringBuilder stringBuilder = new StringBuilder("Common = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp() != null ? realmGet$timestamp() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{commonPhotos:");
        stringBuilder.append("RealmList<Photo>[").append(realmGet$commonPhotos().size()).append("]");
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
        CommonRealmProxy aCommon = (CommonRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aCommon.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCommon.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aCommon.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
