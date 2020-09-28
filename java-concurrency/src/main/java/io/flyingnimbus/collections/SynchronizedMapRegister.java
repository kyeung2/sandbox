package io.flyingnimbus.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SynchronizedMapRegister implements Register {

    private final Map<String, Integer> people = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void registerPerson(String name, Integer age) {
        // this should perform slower than the ConcurrentHashMap version since the entire Map will be locked.
        people.putIfAbsent(name, age);
    }
}
