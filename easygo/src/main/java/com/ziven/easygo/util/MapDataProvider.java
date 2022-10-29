package com.ziven.easygo.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MapDataProvider<K, V> {

    private final Map<K, V> provider;

    public static <K, V> MapDataProvider<K, V> of() {
        return new MapDataProvider<>(new HashMap<>(0));
    }

    public static <K, V> MapDataProvider<K, V> of(@NonNull K key, V value) {
        Map<K, V> map = new HashMap<>();
        map.put(key, value);
        return of(map);
    }

    public static <K, V> MapDataProvider<K, V> of(@NonNull Map<K, V> map) {
        return new MapDataProvider<>(map);
    }

    private MapDataProvider(@NonNull Map<K, V> map) {
        provider = map;
    }

    public MapDataProvider<K, V> put(K key, V value) {
        if(key != null) {
            provider.put(key, value);
        }
        return this;
    }

    public MapDataProvider<K, V> remove(K key) {
        if(key != null) {
            provider.remove(key);
        }
        return this;
    }

    @Nullable
    public V get(K key) {
        return key != null ? provider.get(key) : null;
    }

    public Nulls<V> getNulls(K key) {
        return Nulls.of(get(key));
    }

    @NonNull
    public Map<K, V> getProvider() {
        return provider;
    }
}
