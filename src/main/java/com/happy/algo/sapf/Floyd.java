package com.happy.algo.sapf;

import com.happy.algo.common.Graph2D;
import com.happy.algo.util.MapGenerator;
import com.happy.algo.util.Printer;

/**
 * @author: create by happy
 * @date: 2021/4/10
 */
public class Floyd {

    private static double[][] floyd(Graph2D graph) {
        double[][] distanceMatrix = graph.getDistanceMatrix();
        Printer.printDouble2DArray(distanceMatrix);

        for (int i = 0; i < graph.getVertexNum(); i++) {
            for (int j = 0; j < graph.getVertexNum(); j++) {
                for (int k = 0; k < graph.getVertexNum(); k++) {
                    distanceMatrix[j][k] = Math.min(distanceMatrix[j][i] + distanceMatrix[i][k], distanceMatrix[j][k]);
                }
            }
        }
        return distanceMatrix;
    }

    public static void main(String[] args) {
        double[][] floyd = floyd(MapGenerator.graph2DCase1());
        Printer.printDouble2DArray(floyd);
    }
}
