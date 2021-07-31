package com.ziven.easygo.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * @author :Ziven
 * @date :2020/11/25
 * @since V1.0
 */
public final class Condition {
    private static final Condition TRUE_CONDITION = Condition.newCondition(true);
    private static final Condition FALSE_CONDITION = Condition.newCondition(false);

    private final boolean mRight;

    private Condition(boolean b) {
        mRight = b;
    }

    private static Condition newCondition(boolean b) {
        return new Condition(b);
    }

    @NonNull
    public static Condition ofTrue() {
        return TRUE_CONDITION;
    }

    @NonNull
    public static Condition ofFalse() {
        return FALSE_CONDITION;
    }

    @NonNull
    public static Condition of() {
        return ofFalse();
    }

    @NonNull
    public static Condition of(Boolean b) {
        return ofFalse().ofBoolean(b);
    }

    @NonNull
    public static Condition ofJust(@Nullable Object o) {
        return of(o != null);
    }

    @NonNull
    public static Condition of(@Nullable Object o) {
        if(o instanceof String) {
            return of((String) o);
        } else if(o instanceof Collection<?>) {
            return of((Collection<?>) o);
        } else if(o instanceof Map<?, ?>) {
            return of((Map<?, ?>) o);
        }
        return ofFalse().ofNotNull(o);
    }

    @NonNull
    public static Condition of(@Nullable String s) {
        return ofFalse().ofNotNull(s);
    }

    @NonNull
    public static <T> Condition of(@Nullable Collection<T> c) {
        return ofFalse().ofNotEmpty(c);
    }

    @NonNull
    public static <K, V> Condition of(@Nullable Map<K, V> m) {
        return ofFalse().ofNotEmpty(m);
    }

    @NonNull
    public Condition toTrue() {
        return ofTrue();
    }

    @NonNull
    public Condition toFalse() {
        return ofFalse();
    }

    @NonNull
    public Condition toNot() {
        return ofNot(this);
    }

    @NonNull
    public Condition ofBoolean(boolean b) {
        if(b) {
            return ofTrue();
        }
        return ofFalse();
    }

    @NonNull
    public Condition ofAnd(@NonNull boolean... bs) {
        for (boolean b : bs) {
            if(!b) {
                return ofFalse();
            }
        }
        return ofTrue();
    }

    @NonNull
    public Condition ofOr(@NonNull boolean... bs) {
        for (boolean b : bs) {
            if(b) {
                return ofTrue();
            }
        }
        return ofFalse();
    }

    @NonNull
    public Condition ofNot(boolean b) {
        return ofBoolean(!b);
    }

    @NonNull
    public Condition ofAnd(@NonNull Condition... cs) {
        for (Condition c : cs) {
            if(c.isFalse()) {
                return ofFalse();
            }
        }
        return ofTrue();
    }

    @NonNull
    public Condition ofOr(@NonNull Condition... cs) {
        for (Condition c : cs) {
            if(c.isTrue()) {
                return ofTrue();
            }
        }
        return ofFalse();
    }

    @NonNull
    public Condition ofNot(@NonNull Condition b) {
        return ofBoolean(b.isFalse());
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofMore(@NonNull T a, @NonNull T b) {
        return ofBoolean(a.compareTo(b) > 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofMoreAndEqual(@NonNull T a, @NonNull T b) {
        return ofBoolean(a.compareTo(b) >= 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofLess(@NonNull T a, @NonNull T b) {
        return ofBoolean(a.compareTo(b) < 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofLessAndEqual(@NonNull T a, @NonNull T b) {
        return ofBoolean(a.compareTo(b) <= 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofEqual(@NonNull T a, @NonNull T b) {
        return ofBoolean(a.compareTo(b) == 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofMoreEqualAndLess(@NonNull T a, @NonNull T b, @NonNull T c) {
        return ofBoolean(b.compareTo(a) >= 0 && b.compareTo(c) < 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofMoreAndLessEqual(@NonNull T a, @NonNull T b, @NonNull T c) {
        return ofBoolean(b.compareTo(a) > 0 && b.compareTo(c) <= 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofMoreEqualAndLessEqual(@NonNull T a, @NonNull T b, @NonNull T c) {
        return ofBoolean(b.compareTo(a) >= 0 && b.compareTo(c) <= 0);
    }

    @NonNull
    public <T extends Comparable<T>> Condition ofMoreAndLess(@NonNull T a, @NonNull T b, @NonNull T c) {
        return ofBoolean(b.compareTo(a) > 0 && b.compareTo(c) < 0);
    }

    @NonNull
    public  Condition ofJustEqual(@NonNull Object a, @NonNull Object b) {
        return ofBoolean(a.equals(b));
    }

    @NonNull
    public  Condition ofJustNotEqual(@NonNull Object a, @NonNull Object b) {
        return ofBoolean(!a.equals(b));
    }

    @NonNull
    public Condition ofNull(@Nullable Object o) {
        return ofBoolean(o == null);
    }

    public Condition ofNotNull(@Nullable Object o) {
        return ofBoolean(o != null);
    }

    @NonNull
    public Condition ofNull(@Nullable String s) {
        return ofBoolean(s == null || s.isEmpty());
    }

    @NonNull
    public Condition ofNotNull(@Nullable String s) {
        return ofBoolean(s != null && !s.isEmpty());
    }

    @NonNull
    public <T> Condition ofEmpty(@Nullable Collection<T> c) {
        return ofBoolean(c == null || c.isEmpty());
    }

    @NonNull
    public <T> Condition ofNotEmpty(Collection<T> c) {
        return ofBoolean(c != null && !c.isEmpty());
    }

    @NonNull
    public <K, V> Condition ofEmpty(@Nullable Map<K, V> c) {
        return ofBoolean(c == null || c.isEmpty());
    }

    @NonNull
    public <K, V> Condition ofNotEmpty(@Nullable Map<K, V> m) {
        return ofBoolean(m != null && !m.isEmpty());
    }

    @NonNull
    public <T> Condition ofData (@NonNull Collection<T> collection, @NonNull T data) {
        return ofBoolean(collection.contains(data));
    }

    @NonNull
    public <K, V> Condition ofKey (@NonNull Map<K, V> map, @NonNull K key) {
        return ofBoolean(map.containsKey(key));
    }

    @NonNull
    public <K, V> Condition ofValue (@NonNull Map<K, V> map, @NonNull V value) {
        return ofBoolean(map.containsValue(value));
    }

    @NonNull
    public <K, V> Condition ofKeyOrValue (@NonNull Map<K, V> map, @NonNull K key,  @NonNull V value) {
        return ofBoolean(map.containsKey(key) || map.containsValue(value));
    }

    @NonNull
    public <K, V> Condition ofKeyAndValue (@NonNull Map<K, V> map, @NonNull K key,  @NonNull V value) {
        return ofBoolean(value.equals(map.get(key)));
    }

    @NonNull
    public Condition ofCondition(@NonNull ICondition condition) {
        return ofBoolean(condition.condition());
    }

    public <T> Condition ofInstanceOf(@NonNull Object target, @NonNull Class<T> cls) {
        return ofBoolean(cls.isInstance(target));
    }

    @NonNull
    public Condition and(@Nullable Object o) {
        return ofAnd(isTrue(), of(o).isTrue());
    }

    @NonNull
    public Condition or(@Nullable Object o) {
        return ofOr(isTrue(), of(o).isTrue());
    }

    @NonNull
    public Condition and(boolean b) {
        return ofAnd(isTrue(), b);
    }

    @NonNull
    public Condition or(boolean b) {
        return ofOr(isTrue(), b);
    }

    @NonNull
    public Condition not() {
        return ofNot(isTrue());
    }

    public boolean isTrue() {
        return mRight;
    }

    public boolean isFalse() {
        return !mRight;
    }

    @NonNull
    public Condition doJust(@NonNull Doo d) {
        d.doo();
        return this;
    }

    @NonNull
    public Condition doTrue(@NonNull Doo d) {
        if(isTrue()) {
            return doJust(d);
        }
        return this;
    }

    @NonNull
    public Condition doFalse(@NonNull Doo d) {
        if(isFalse()) {
            return doJust(d);
        }
        return this;
    }

    @NonNull
    public Condition doAll(@NonNull Doo... ds) {
        return doTrue(() -> {
            for(Doo d : ds) {
                d.doo();
            }
        });
    }

    @NonNull
    public Condition doThen(@NonNull Gee... gs) {
        if(isFalse()) {
            return ofFalse();
        }
        for(Gee g : gs) {
            if(!g.gee()) {
                return ofFalse();
            }
        }
        return ofTrue();
    }

    @NonNull
    public Condition then(@NonNull Gee g) {
        if(isTrue()) {
            return ofBoolean(g.gee());
        }
        return ofFalse();
    }

    @NonNull
    public Condition doNot(@NonNull Gee... gs) {
        if(isTrue()) {
            return ofTrue();
        }
        for(Gee g : gs) {
            if(g.gee()) {
                return ofTrue();
            }
        }
        return ofFalse();
    }

    @NonNull
    public Condition not(@NonNull Gee g) {
        if(isFalse()) {
            return ofBoolean(g.gee());
        }
        return ofTrue();
    }

    @FunctionalInterface
    public interface Doo {
        /**
         * Do something
         */
        void doo();
    }

    @FunctionalInterface
    public interface Gee {
        /**
         * Do something
         * @return true or false
         */
        boolean gee();
    }

    @FunctionalInterface
    public interface ICondition {
        /**
         * Condition
         * @return Condition
         */
        boolean condition();
    }
}
