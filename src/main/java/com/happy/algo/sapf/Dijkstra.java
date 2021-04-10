package com.happy.algo.sapf;

import com.happy.algo.common.Graph2D;
import com.happy.algo.util.MapGenerator;
import com.happy.algo.util.Printer;

import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {

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
        graph2DCase1();
        graph2DCase2();
    }

    private static void graph2DCase1() {
        Graph2D graph2D = MapGenerator.graph2DCase1();
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        double[] resDist = dijkstra(graph2D, 0);
        Printer.printArray(resDist);

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
        Printer.printArray(resDist);
    }
}
