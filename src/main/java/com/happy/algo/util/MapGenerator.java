package com.happy.algo.util;

import com.happy.algo.common.Graph2D;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: create by happy
 * @date: 2021/4/7
 */
public class MapGenerator {
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

        return new Graph2D(encodedEdges);
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

        return new Graph2D(encodedEdges);
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

        return new Graph2D(encodedEdges);
    }

    public static Graph2D graph2DCase4() {
        List<Triple<String, String, Double>> edges = new ArrayList<>();
        edges.add(Triple.of("A", "B", 5.0));
        edges.add(Triple.of("B", "C", 3.0));
        edges.add(Triple.of("C", "A", -10.0));

        Map<String, Integer> encodeMap = IdEncoder.encodeIds(edges);
        System.out.println(encodeMap);

        List<Triple<Integer, Integer, Double>> encodedEdges = encodeEdges(edges, encodeMap);

        return new Graph2D(encodedEdges);
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
