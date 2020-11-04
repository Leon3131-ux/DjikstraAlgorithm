package com.company;

import com.company.domain.Vertex;
import com.company.domain.WeightedGraph;
import com.company.util.AlgorithmResolver;
import com.company.util.DijkstraResult;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph();
        AlgorithmResolver algorithmResolver = new AlgorithmResolver();
        Scanner scan = new Scanner (System.in);

        List<Vertex> vertices = new ArrayList<>();

        boolean addingVertices = true;
        while (addingVertices) {
            System.out.println("enter a Vertex name: ");
            String vertexName = scan.nextLine();
            vertices.add(new Vertex(vertexName));
            System.out.println("add another Vertex?");
            String answer = scan.nextLine();
            if (!answer.equals("yes") && !answer.equals("y")) {
                addingVertices = false;
            }
        }

        for (Vertex vertex : vertices) {
            weightedGraph.addVertex(vertex);
        }

        boolean addingEdges = true;
        System.out.println("Start adding Edges");
        while (addingEdges) {
            System.out.println("enter first Vertex name: ");
            String firstVertexName = scan.nextLine();
            Optional<Vertex> existingFirstVertex = vertices.stream().filter(vertex -> vertex.getName().equals(firstVertexName)).findFirst();
            if (!existingFirstVertex.isPresent()) {
                System.out.println("First Vertex not found");
                continue;
            }

            System.out.println("enter second Vertex name: ");
            String secondVertexName = scan.nextLine();
            Optional<Vertex> existingSecondVertex = vertices.stream().filter(vertex -> vertex.getName().equals(secondVertexName)).findFirst();
            if (!existingSecondVertex.isPresent()) {
                System.out.println("Second Vertex not found");
                continue;
            }
            double weight;
            System.out.println("Define Weight");
            try {
                weight = Double.parseDouble(scan.nextLine());
            } catch (Exception e) {
                System.out.println("Weight invalid");
                continue;
            }

            weightedGraph.addEdge(existingFirstVertex.get(), existingSecondVertex.get(), weight);

            System.out.println("add another Edge?");
            String answer = scan.nextLine();
            if (!answer.equals("yes") && !answer.equals("y")) {
                addingEdges = false;
            }
        }

        boolean addingStartVertex = true;
        Vertex startVertex = new Vertex("");
        while (addingStartVertex) {
            System.out.println("Define starting Vertex: ");
            String firstVertexName = scan.nextLine();
            Optional<Vertex> existingStartVertex = vertices.stream().filter(vertex -> vertex.getName().equals(firstVertexName)).findFirst();
            if (!existingStartVertex.isPresent()) {
                System.out.println("Starting Vertex not found");
                continue;
            }
            addingStartVertex = false;
            startVertex = existingStartVertex.get();
        }

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
                continue;
            }
            System.out.print("Distance: "+result.get(key).getTotalDistance()+" ");
            if(result.get(key).getPreviousVertex() == null) {
                System.out.print("Previous Vertex: null ");
                continue;
            }
            System.out.print("Previous Vertex: "+result.get(key).getPreviousVertex().getName()+" ");
            System.out.println();
        }
    }
}
