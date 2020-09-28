package io.nimbus.math;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class SetTheory<T> {

    public Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return unmodifiableSet(result);
    }

    public Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return unmodifiableSet(result);
    }

    public Set<T> difference(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.removeAll(b);
        return unmodifiableSet(result);
    }
}