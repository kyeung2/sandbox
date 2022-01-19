package io.nimbus.algorithms.search.graph;

import java.util.Objects;

class Vertex {
    String label;
    Vertex(String label) {
        this.label = label;
    }

    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}