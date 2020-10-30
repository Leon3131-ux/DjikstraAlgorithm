package com.company;

import com.company.domain.Vertex;
import com.company.domain.WeightedGraph;
import com.company.util.AlgorithmResolver;
import com.company.util.DijkstraResult;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph();
        AlgorithmResolver algorithmResolver = new AlgorithmResolver();

        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");

        weightedGraph.addVertex(a);
        weightedGraph.addVertex(b);
        weightedGraph.addVertex(c);
        weightedGraph.addVertex(d);
        weightedGraph.addVertex(e);


        Map<Vertex, DijkstraResult> result = algorithmResolver.solveWithDijkstra(weightedGraph, a);
    }

}
