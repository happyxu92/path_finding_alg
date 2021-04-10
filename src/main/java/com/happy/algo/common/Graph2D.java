package com.happy.algo.common;

import lombok.Data;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * rowPos: array stores start position of each row
 * distance: 2-d array, distance[0][0] stores column, distance[0][1] stores distance value
 *
 * @author: create by happy
 * @date: 2021/3/30
 */
@Data
public class Graph2D {
    private int[] rowPos;
    private double[][] distance;
    private int vertexNum;

    public Graph2D(List<Triple<Integer, Integer, Double>> edges) {
        if (edges.isEmpty()) {
            return;
        }
        edges.sort(Comparator.comparingInt((ToIntFunction<Triple<Integer, Integer, Double>>) Triple::getLeft)
                .thenComparing(Triple::getMiddle));

        int sourceVertexNum = getSourceVertexNum(edges);
        int count = 0;
        int lastSourceVertexId = 0;
        this.rowPos = new int[sourceVertexNum + 1];
        this.distance = new double[edges.size()][2];
        this.vertexNum = getVertexNum(edges);

        for (Triple<Integer, Integer, Double> edge: edges) {
            this.distance[count][0] = edge.getMiddle();
            this.distance[count][1] = edge.getRight();

            if (edge.getLeft() != lastSourceVertexId) {
                lastSourceVertexId = edge.getLeft();
                this.rowPos[edge.getLeft()] = count;
            }
            count++;
        }
        this.rowPos[sourceVertexNum] = edges.size();
    }

    private int getVertexNum(List<Triple<Integer, Integer, Double>> edges) {
        Set<Integer> idSet = edges.stream().map(Triple::getLeft).collect(Collectors.toSet());
        idSet.addAll(edges.stream().map(Triple::getMiddle).collect(Collectors.toSet()));

        return idSet.size();
    }

    private int getSourceVertexNum(List<Triple<Integer, Integer, Double>> edges) {
        return edges.get(edges.size() - 1).getLeft() + 1;
    }

    public int getRowEndIndex(int rowId) {
        return this.rowPos[rowId + 1] - 1;
    }

    public int getRowStartIndex(int rowId) {
        return this.rowPos[rowId];
    }

    public boolean hasToVertex(int vertexId) {
        return vertexId < this.rowPos.length - 1;
    }

    public double[][] getDistanceMatrix() {
        double[][] distanceMatrix = new double[vertexNum][vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                distanceMatrix[i][j] = Double.MAX_VALUE;
            }
        }

        for (int i = 0; i < this.rowPos.length - 1; i++) {
            int rowStartIndex = getRowStartIndex(i);
            int rowEndIndex = getRowEndIndex(i);

            for (int r = rowStartIndex; r <= rowEndIndex; r++) {
                int toId = (int) this.distance[r][0];
                double length = this.distance[r][1];
                distanceMatrix[i][toId] = length;
            }
        }

        return distanceMatrix;
    }
}
