package com.ziven.easygo.util;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ziven
 */
@Keep
public class MapDataProvider<K, V> {

    private final Map<K, V> provider;

    public static <K, V> MapDataProvider<K, V> of() {
        return new MapDataProvider<>(new HashMap<>(8));
    }

    public static <K, V> MapDataProvider<K, V> of(K key, V value) {
        MapDataProvider<K, V> p = of();
        p.put(key, value);
        return p;
    }

    public static <K, V> MapDataProvider<K, V> of(@NonNull Map<K, V> map) {
        return new MapDataProvider<>(map);
    }

    private MapDataProvider(@NonNull Map<K, V> map) {
        provider = map;
    }

    public MapDataProvider<K, V> put(K key, V value) {
        if(key != null) {
            getProvider().put(key, value);
        }
        return this;
    }

    public MapDataProvider<K, V> remove(K key) {
        if(key != null) {
            getProvider().remove(key);
        }
        return this;
    }

    public MapDataProvider<K, V> clear() {
        getProvider().clear();
        return this;
    }

    @Nullable
    public V get(K key) {
        return key != null ? getProvider().get(key) : null;
    }

    public Nulls<V> getNulls(K key) {
        return Nulls.of(get(key));
    }

    public V getIfNullObtain(K key, @NonNull Obtain<V> obtain) {
        V value = get(key);
        if(value != null) {
            return value;
        }
        value = obtain.obtain();
        put(key, value);
        return value;
    }

    @NonNull
    public Map<K, V> getProvider() {
        return provider;
    }

    @NonNull
    public Map<K, V> getMap() {
        return getProvider();
    }

    public MapDataProvider<K, V> forEach(@NonNull BiCarry<K, V> carry) {
        EasyUtils.forEach(getProvider(), carry);
        return this;
    }

    public MapDataProvider<K, V> forEach(@NonNull Carry<V> carry) {
        EasyUtils.forEach(getProvider(), carry);
        return this;
    }

    public MapDataProvider<K, V> forEachKey(@NonNull Carry<K> carry) {
        EasyUtils.forEachKey(getProvider(), carry);
        return this;
    }
}
