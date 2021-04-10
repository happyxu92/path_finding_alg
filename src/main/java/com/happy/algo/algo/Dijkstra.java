package com.happy.algo.algo;

import com.happy.algo.common.Edge;
import com.happy.algo.common.Graph;
import com.happy.algo.common.Graph2D;
import com.happy.algo.common.Vertex;
import com.happy.algo.util.MapGenerator;


import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {

    private static <T> Map<T, Double> dijkstra(Graph<T, ?> graph, Vertex<T> source) {
        List<T> vertexIds = new ArrayList<>(graph.getVertexMap().keySet());
        if (!vertexIds.contains(source.getId())) {
            System.err.println("graph do not contain vertex " + source.getId());
        }

        // init distance map
        Map<T, Double> distanceMap = new HashMap<>();
        distanceMap.put(source.getId(), 0.0);

        Map<T, Double> resMap = new HashMap<>();
        while (!distanceMap.isEmpty()) {
            // pop
            Optional<Map.Entry<T, Double>> minNodeOp = distanceMap.entrySet().stream()
                    .min(Comparator.comparing(Map.Entry::getValue));
            if (!minNodeOp.isPresent()) {
                break;
            }
            resMap.put(minNodeOp.get().getKey(), minNodeOp.get().getValue());
            distanceMap.remove(minNodeOp.get().getKey());

            // update
            for (Edge<?> edge: graph.getVertex(minNodeOp.get().getKey()).getEdges()) {
                if (!resMap.containsKey(edge.getTo().getId())) {
                    double distance = Math.min(distanceMap.getOrDefault(edge.getTo().getId(), Double.MAX_VALUE),
                            resMap.get(minNodeOp.get().getKey()) + edge.getLength());
                    distanceMap.put((T)edge.getTo().getId(), distance);
                }
            }
        }
        return resMap;
    }

    private static double[] dijkstra(Graph2D graph, int source) {
        double[] distance = new double[graph.getVertexNum()];
        boolean[] passed = new boolean[graph.getVertexNum()];
        for (int i=0; i<distance.length; i++) {
            distance[i] = Double.MAX_VALUE;
            passed[i] = false;
        }
        distance[source] = 0;

        Integer currentMinVertexId = source;
        while (null != currentMinVertexId) {
            passed[currentMinVertexId] = true;

            if (graph.hasToVertex(currentMinVertexId)) {
                int startIndex = graph.getRowStartIndex(currentMinVertexId);
                int endIndex = graph.getRowEndIndex(currentMinVertexId);

                for (int i = startIndex; i <= endIndex; i++) {
                    int toId = (int)graph.getDistance()[i][0];
                    double length = graph.getDistance()[i][1];
                    if (!passed[toId]) {
                        distance[toId] = Math.min(distance[toId], distance[currentMinVertexId] + length);
                    }
                }
            }
            currentMinVertexId = getNoPassedShortestVertex(distance, passed);
        }

        return distance;
    }

    private static Integer getNoPassedShortestVertex(double[] distance, boolean[] passed) {
        Integer currentMinVertexId = null;
        for (int i = 0; i < distance.length; i++) {
            if (passed[i]) {
                continue;
            }

            if (null == currentMinVertexId) {
                currentMinVertexId = i;
            } else {
                currentMinVertexId = distance[i] < distance[currentMinVertexId] ? i : currentMinVertexId;
            }
        }
        return currentMinVertexId;
    }


    public static void main(String[] args) {
        testGraph1();
        graph2DCase1();

        testGraph2();
        graph2DCase2();
    }

    private static void testGraph1() {
        Graph<Integer, Integer> graph = MapGenerator.graph1();

        System.out.println(graph.getVertexMap().keySet());
        Map<Integer, Double> resMap = dijkstra(graph, graph.getVertex(1));
        System.out.println(resMap);
    }

    private static void testGraph2() {
        Graph<String, Integer> graph = MapGenerator.graph2();

        System.out.println(graph.getVertexMap().keySet());
        Map<String, Double> resMap = dijkstra(graph, graph.getVertex("D"));
        System.out.println(resMap);
    }

    private static void graph2DCase1() {
        Graph2D graph2D = MapGenerator.graph2DCase1();
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        double[] resDist = dijkstra(graph2D, 0);
        System.out.println(Arrays.stream(resDist).boxed().collect(Collectors.toList()));

        long start = System.nanoTime();
        for (int i=0; i<100000; i++) {
            dijkstra(graph2D, 0);
        }
        System.out.println((System.nanoTime() - start)/1000000);
    }

    private static void graph2DCase2() {
        Graph2D graph2D = MapGenerator.graph2DCase2();
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        double[] resDist = dijkstra(graph2D, 2);
        System.out.println(Arrays.stream(resDist).boxed().collect(Collectors.toList()));
    }
}
