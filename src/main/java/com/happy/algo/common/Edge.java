package com.happy.algo.common;

import lombok.Data;

@Data
public class Edge<R> {
    private R id;
    private Vertex<?> from;
    private Vertex<?> to;
    private double length;

    public Edge(R id, Vertex from, Vertex to, double length) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.length = length;
    }
}
