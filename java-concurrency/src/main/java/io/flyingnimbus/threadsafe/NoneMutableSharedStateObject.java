package io.flyingnimbus.threadsafe;

import java.util.HashMap;
import java.util.Map;

public final class NoneMutableSharedStateObject {

    private final Map<String, String> names;

    public NoneMutableSharedStateObject() {
        names = new HashMap<>();
        names.put("Kye", "Yeung");
        names.put("Rick", "Sanchez");
    }

    // state is shared but it is not mutable, therefore thread safe
    public String getLastName(String firstName) {
        return names.get(firstName);
    }
}
