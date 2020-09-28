package io.flyingnimbus.threadsafe;

/**
 * Immutable object, no shared mutable state, so thread safe
 */
public final class ImmutableObject {

    private final String firstName;
    private final String lastName;

    public ImmutableObject(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
