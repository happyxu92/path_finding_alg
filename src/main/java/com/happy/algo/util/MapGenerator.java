package com.happy.algo.util;

import com.happy.algo.common.Edge;
import com.happy.algo.common.Graph;
import com.happy.algo.common.Graph2D;
import com.happy.algo.common.Vertex;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: create by happy
 * @date: 2021/4/7
 */
public class MapGenerator {

    public static Graph<Integer, Integer> graph1() {
        Graph<Integer, Integer> graph = new Graph<>();
        for (int i=1; i<=6; i++) {
            graph.addVertex(new Vertex<>(i));
        }

        graph.addEdge(new Edge<>(1, graph.getVertex(1), graph.getVertex(6), 100));
        graph.addEdge(new Edge<>(2, graph.getVertex(1), graph.getVertex(5), 30));
        graph.addEdge(new Edge<>(3, graph.getVertex(1), graph.getVertex(3), 10));
        graph.addEdge(new Edge<>(4, graph.getVertex(2), graph.getVertex(3), 5));
        graph.addEdge(new Edge<>(5, graph.getVertex(3), graph.getVertex(4), 50));
        graph.addEdge(new Edge<>(6, graph.getVertex(4), graph.getVertex(6), 10));
        graph.addEdge(new Edge<>(7, graph.getVertex(5), graph.getVertex(4), 20));
        graph.addEdge(new Edge<>(8, graph.getVertex(5), graph.getVertex(6), 60));

        return graph;
    }

    public static Graph<String, Integer> graph2() {
        Graph<String, Integer> graph = new Graph<>();
        graph.addVertex(new Vertex<>("A"));
        graph.addVertex(new Vertex<>("B"));
        graph.addVertex(new Vertex<>("C"));
        graph.addVertex(new Vertex<>("D"));
        graph.addVertex(new Vertex<>("E"));
        graph.addVertex(new Vertex<>("F"));
        graph.addVertex(new Vertex<>("G"));

        graph.addEdge(new Edge<>(1, graph.getVertex("D"), graph.getVertex("C"), 3));
        graph.addEdge(new Edge<>(2, graph.getVertex("D"), graph.getVertex("E"), 4));

        graph.addEdge(new Edge<>(3, graph.getVertex("C"), graph.getVertex("B"), 10));
        graph.addEdge(new Edge<>(4, graph.getVertex("C"), graph.getVertex("F"), 6));
        graph.addEdge(new Edge<>(5, graph.getVertex("C"), graph.getVertex("E"), 5));

        graph.addEdge(new Edge<>(6, graph.getVertex("E"), graph.getVertex("C"), 5));
        graph.addEdge(new Edge<>(7, graph.getVertex("E"), graph.getVertex("F"), 2));
        graph.addEdge(new Edge<>(8, graph.getVertex("E"), graph.getVertex("G"), 8));

        graph.addEdge(new Edge<>(9, graph.getVertex("F"), graph.getVertex("B"), 7));
        graph.addEdge(new Edge<>(10, graph.getVertex("F"), graph.getVertex("A"), 16));
        graph.addEdge(new Edge<>(11, graph.getVertex("F"), graph.getVertex("G"), 9));

        graph.addEdge(new Edge<>(12, graph.getVertex("B"), graph.getVertex("A"), 12));
        graph.addEdge(new Edge<>(13, graph.getVertex("G"), graph.getVertex("A"), 14));

        return graph;
    }

    public static Graph2D graph2DCase1() {
        List<Triple<Integer, Integer, Double>> edges = new ArrayList<>();
        edges.add(Triple.of(1, 3, 10.0));
        edges.add(Triple.of(1, 5, 30.0));
        edges.add(Triple.of(1, 6, 100.0));
        edges.add(Triple.of(2, 3, 5.0));
        edges.add(Triple.of(3, 4, 50.0));
        edges.add(Triple.of(4, 6, 10.0));
        edges.add(Triple.of(5, 4, 20.0));
        edges.add(Triple.of(5, 6, 60.0));

        Map<Integer, Integer> encodeMap = IdEncoder.encodeIds(edges);
        System.out.println(encodeMap);

        List<Triple<Integer, Integer, Double>> encodedEdges = encodeEdges(edges, encodeMap);

        Graph2D graph2D = new Graph2D(encodedEdges);
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        return graph2D;
    }

    public static Graph2D graph2DCase2() {
        List<Triple<String, String, Double>> edges = new ArrayList<>();
        edges.add(Triple.of("D", "C", 3.0));
        edges.add(Triple.of("D", "E", 4.0));

        edges.add(Triple.of("C", "B", 10.0));
        edges.add(Triple.of("C", "F", 6.0));
        edges.add(Triple.of("C", "E", 5.0));

        edges.add(Triple.of("E", "C", 5.0));
        edges.add(Triple.of("E", "F", 2.0));
        edges.add(Triple.of("E", "G", 8.0));

        edges.add(Triple.of("F", "B", 7.0));
        edges.add(Triple.of("F", "A", 16.0));
        edges.add(Triple.of("F", "G", 9.0));

        edges.add(Triple.of("B", "A", 12.0));
        edges.add(Triple.of("G", "A", 14.0));

        Map<String, Integer> encodeMap = IdEncoder.encodeIds(edges);
        System.out.println(encodeMap);

        List<Triple<Integer, Integer, Double>> encodedEdges = encodeEdges(edges, encodeMap);
        Graph2D graph2D = new Graph2D(encodedEdges);
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        return graph2D;
    }

    public static Graph2D graph2DCase3() {
        List<Triple<Integer, Integer, Double>> edges = new ArrayList<>();
        edges.add(Triple.of(1, 2, -3.0));
        edges.add(Triple.of(1, 5, 5.0));

        edges.add(Triple.of(2, 3, 2.0));
        edges.add(Triple.of(3, 4, 3.0));
        edges.add(Triple.of(4, 5, 2.0));

        Map<Integer, Integer> encodeMap = IdEncoder.encodeIds(edges);
        System.out.println(encodeMap);

        List<Triple<Integer, Integer, Double>> encodedEdges = encodeEdges(edges, encodeMap);

        Graph2D graph2D = new Graph2D(encodedEdges);
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        return graph2D;
    }

    public static Graph2D graph2DCase4() {
        List<Triple<String, String, Double>> edges = new ArrayList<>();
        edges.add(Triple.of("A", "B", 5.0));
        edges.add(Triple.of("B", "C", 3.0));
        edges.add(Triple.of("C", "A", -10.0));

        Map<String, Integer> encodeMap = IdEncoder.encodeIds(edges);
        System.out.println(encodeMap);

        List<Triple<Integer, Integer, Double>> encodedEdges = encodeEdges(edges, encodeMap);

        Graph2D graph2D = new Graph2D(encodedEdges);
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        return graph2D;
    }

    private static <T> List<Triple<Integer, Integer, Double>> encodeEdges(List<Triple<T, T, Double>> edges,
                                                                          Map<T, Integer> encodeMap) {
        List<Triple<Integer, Integer, Double>> encodedEdges = new ArrayList<>();
        for (Triple<T, T, Double> edge: edges) {
            Integer from = encodeMap.get(edge.getLeft());
            Integer to = encodeMap.get(edge.getMiddle());
            encodedEdges.add(Triple.of(from, to, edge.getRight()));
        }
        return encodedEdges;
    }
}
