package com.company.util;

import com.company.domain.Vertex;
import com.company.domain.WeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInputReader {

    Scanner scanner = new Scanner(System.in);

    public WeightedGraph readWeightedGraphFromUser(){
        WeightedGraph graph = new WeightedGraph();
        getVertices(graph);
        getEdges(graph);
        return graph;
    }

    public Vertex getStartVertex(WeightedGraph graph){
        List<Vertex> vertices = graph.getVertices();
        boolean addingStartVertex = true;
        Vertex startVertex = new Vertex("");
        while (addingStartVertex) {
            System.out.println("Define starting Vertex: ");
            String firstVertexName = scanner.nextLine();
            Optional<Vertex> existingStartVertex = vertices.stream().filter(vertex -> vertex.getName().equals(firstVertexName)).findFirst();
            if (existingStartVertex.isEmpty()) {
                System.out.println("Starting Vertex not found");
                continue;
            }
            addingStartVertex = false;
            startVertex = existingStartVertex.get();
        }
        return startVertex;
    }

    private WeightedGraph getEdges(WeightedGraph graph){
        List<Vertex> vertices = graph.getVertices();
        System.out.println("Start adding Edges");
        boolean addingEdges = true;
        while (addingEdges) {
            System.out.println("enter first Vertex name: ");
            String firstVertexName = scanner.nextLine();
            Optional<Vertex> existingFirstVertex = vertices.stream().filter(vertex -> vertex.getName().equals(firstVertexName)).findFirst();
            if (existingFirstVertex.isEmpty()) {
                System.out.println("First Vertex not found");
                continue;
            }

            System.out.println("enter second Vertex name: ");
            String secondVertexName = scanner.nextLine();
            Optional<Vertex> existingSecondVertex = vertices.stream().filter(vertex -> vertex.getName().equals(secondVertexName)).findFirst();
            if (existingSecondVertex.isEmpty()) {
                System.out.println("Second Vertex not found");
                continue;
            }
            double weight;
            System.out.println("Define Weight");
            try {
                weight = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Weight invalid");
                continue;
            }

            try {
                graph.addEdge(existingFirstVertex.get(), existingSecondVertex.get(), weight);
            } catch (IllegalArgumentException e) {
                System.out.println("You cant make an edge between a vertex and itself");
                continue;
            }

            System.out.println("add another Edge?");
            String answer = scanner.nextLine();
            if (!answer.equals("yes") && !answer.equals("y")) {
                addingEdges = false;
            }
        }
        return graph;
    }

    private WeightedGraph getVertices(WeightedGraph graph){
        boolean addingVertices = true;
        while (addingVertices) {
            System.out.println("enter a Vertex name: ");
            String vertexName = scanner.nextLine();
            graph.addVertex(new Vertex(vertexName));
            System.out.println("add another Vertex?");
            String answer = scanner.nextLine();
            if (!answer.equals("yes") && !answer.equals("y")) {
                addingVertices = false;
            }
        }
        return graph;
    }

}
