package com.ziven.easygo.design.mvvm;

import androidx.annotation.NonNull;

import java.util.HashMap;

/**
 * @author Ziven
 */
public final class ModelViewUtil {

    private static final HashMap<String, Class<?>> MODEL_VIEW = new HashMap<>();

    private ModelViewUtil() {}

    static {
        put("action", ActionModelView.class);
        put("empty", EmptyModelView.class);
        put("image", ImageModelView.class);
        put("text", TextModelView.class);
        put("recycler", RecyclerModelView.class);
        put("dataModel", DataViewModel.class);
        put("emptyModel", EmptyViewModel.class);
    }

    public static void put(@NonNull String key,
                           @NonNull Class<?> cls) {
        MODEL_VIEW.put(key, cls);
    }

    public static Class<?> get(@NonNull String key) {
        return MODEL_VIEW.get(key);
    }
}
