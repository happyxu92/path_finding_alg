package com.happy.algo.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vertex<T> {
    private T id;
    private List<Edge<?>> edges = new ArrayList<>();

    public Vertex(T id) {
        this.id = id;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}
