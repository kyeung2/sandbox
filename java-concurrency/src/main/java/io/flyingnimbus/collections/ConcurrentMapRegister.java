package io.flyingnimbus.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * - uses lock stripping, less contention when writing, so faster than the alternatives:
 * - HashTable
 * - Collections.synchronizedMap() wrapper
 * <p>
 * - various Map methods are implemented to support atomicity
 * - putIfAbsent
 * - remove
 * - replace(key, oldValue, newValue)
 * - replace(key, value)
 * <p>
 * - does not allow null key/values as in
 */
public class ConcurrentMapRegister implements Register {

    private final ConcurrentMap<String, Integer> people = new ConcurrentHashMap<>();

    @Override
    public void registerPerson(String name, Integer age) {
        people.putIfAbsent(name, age);
    }
}
