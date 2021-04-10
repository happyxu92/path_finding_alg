package com.happy.algo.sapf;

import com.happy.algo.common.Graph2D;
import com.happy.algo.util.MapGenerator;
import com.happy.algo.util.Printer;

import java.util.Arrays;

/**
 * @author: create by happy
 * @date: 2021/4/7
 */
public class BellmanFord {

    private static double[] bellmanFord(Graph2D graph, int source) {
        double[] distance = new double[graph.getVertexNum()];
        Arrays.fill(distance, Double.MAX_VALUE);
        distance[source] = 0;

        int i = 1;
        for (; i <= distance.length; i++) {
            boolean updated = relax(graph, distance);
            if (!updated) {
                break;
            }
        }

        if (i > distance.length) {
            System.err.println("There is negative circle!");
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
        test(MapGenerator.graph2DCase1(), 0);
        test(MapGenerator.graph2DCase2(), 2);
        test(MapGenerator.graph2DCase3(), 0);
        test(MapGenerator.graph2DCase4(), 0);
    }

    private static void test(Graph2D graph2D, int source) {
        double[] resDist = bellmanFord(graph2D, source);
        Printer.printArray(resDist);
    }
}
