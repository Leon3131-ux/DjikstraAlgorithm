package com.company;

import com.company.domain.Vertex;
import com.company.domain.WeightedGraph;
import com.company.util.AlgorithmResolver;
import com.company.util.DijkstraResult;
import com.company.util.UserInputReader;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        UserInputReader userInputReader = new UserInputReader();
        WeightedGraph weightedGraph = userInputReader.readWeightedGraphFromUser();
        Vertex startVertex = userInputReader.getStartVertex(weightedGraph);
        AlgorithmResolver algorithmResolver = new AlgorithmResolver();

        Map<Vertex, DijkstraResult> result = algorithmResolver.solveWithDijkstra(weightedGraph, startVertex);
        printDijkstra(result);
    }

    private static void printDijkstra(Map<Vertex, DijkstraResult> result) {
        Set<Vertex> keys = result.keySet();
        for (Vertex key: keys) {
            System.out.print("Vertex: "+key.getName()+" ");
            if(result.get(key) == null) {
                System.out.print("Distance: null ");
                System.out.print("Previous Vertex: null ");
                System.out.println();
                continue;
            }
            System.out.print("Distance: "+result.get(key).getTotalDistance()+" ");
            if(result.get(key).getPreviousVertex() == null) {
                System.out.print("Previous Vertex: null ");
                System.out.println();
                continue;
            }
            System.out.print("Previous Vertex: "+result.get(key).getPreviousVertex().getName()+" ");
            System.out.println();
        }
    }
}
