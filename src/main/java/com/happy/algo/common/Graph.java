package com.happy.algo.common;


import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Graph<T, R> {
    private Integer id;
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    private Map<T, Vertex<T>> vertexMap = new HashMap<>();
    private Map<R, Edge<R>> edgeMap = new HashMap<>();

    public Graph() {
    }

    public void addVertex(Vertex<T> vertex) {
        this.vertices.add(vertex);
        this.vertexMap.put(vertex.getId(), vertex);
    }

    public void addEdge(Edge<R> edge) {
        edge.getFrom().addEdge(edge);
        this.edges.add(edge);
        this.edgeMap.put(edge.getId(), edge);
    }

    public Vertex<T> getVertex(T t) {
        return this.vertexMap.get(t);
    }
}
