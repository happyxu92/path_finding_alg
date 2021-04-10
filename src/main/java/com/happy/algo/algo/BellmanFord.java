package com.happy.algo.algo;

import com.happy.algo.common.Graph2D;
import com.happy.algo.util.MapGenerator;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author: create by happy
 * @date: 2021/4/7
 */
public class BellmanFord {

    private static double[] bellmanFord(Graph2D graph, int source) {
        double[] distance = new double[graph.getVertexNum()];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Double.MAX_VALUE;
        }
        distance[source] = 0;

        int i = 1;
        for (; i <= distance.length; i++) {
            boolean updated = relax(graph, distance);
            if (!updated) {
                break;
            }
        }

        if (i > distance.length) {
            System.out.println("There is negative circle!");
        }
        return distance;
    }

    private static boolean relax(Graph2D graph, double[] distance) {
        boolean updated = false;

        for (int i = 0; i < graph.getRowPos().length - 1; i++) {
            if (distance[i] != Double.MAX_VALUE) {
                int rowStartIndex = graph.getRowStartIndex(i);
                int rowEndIndex = graph.getRowEndIndex(i);

                for (int r = rowStartIndex; r <= rowEndIndex; r++) {
                    int toId = (int) graph.getDistance()[r][0];
                    double length = graph.getDistance()[r][1];
                    if (length + distance[i] < distance[toId]) {
                        distance[toId] = length + distance[i];
                        updated = true;
                    }
                    distance[toId] = Math.min(length + distance[i], distance[toId]);
                }
            }
        }

        return updated;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() {
        Graph2D graph2D = MapGenerator.graph2DCase1();
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        double[] resDist = bellmanFord(graph2D, 0);
        System.out.println(Arrays.stream(resDist).boxed().collect(Collectors.toList()));
    }

    private static void test2() {
        Graph2D graph2D = MapGenerator.graph2DCase2();
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        double[] resDist = bellmanFord(graph2D, 2);
        System.out.println(Arrays.stream(resDist).boxed().collect(Collectors.toList()));
    }

    private static void test3() {
        Graph2D graph2D = MapGenerator.graph2DCase3();
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        double[] resDist = bellmanFord(graph2D, 0);
        System.out.println(Arrays.stream(resDist).boxed().collect(Collectors.toList()));
    }

    private static void test4() {
        Graph2D graph2D = MapGenerator.graph2DCase4();
        System.out.println(Arrays.stream(graph2D.getRowPos()).boxed().collect(Collectors.toList()));

        double[] resDist = bellmanFord(graph2D, 0);
        System.out.println(Arrays.stream(resDist).boxed().collect(Collectors.toList()));
    }
}
