package com.company.domain;

import java.util.*;

public class WeightedGraph {

    private final Map<Vertex, List<Edge>> graphMap;

    public WeightedGraph(){
        graphMap = new HashMap<>();
    }

    public void addVertex(Vertex vertex){
        if(graphMap.containsKey(vertex)){
            throw new IllegalArgumentException();
        }
        graphMap.put(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex v1, Vertex v2, double weight){
        if(!v1.equals(v2)){
            if(graphMap.containsKey(v1) && graphMap.containsKey(v2)){
                List<Edge> v1EdgeList = graphMap.get(v1);
                List<Edge> v2EdgeList = graphMap.get(v2);

                Optional<Edge> v1ExistingEdge = v1EdgeList.stream().filter(edge -> edge.getTo().equals(v2)).findFirst();
                Optional<Edge> v2ExistingEdge = v2EdgeList.stream().filter(edge -> edge.getTo().equals(v1)).findFirst();

                v1ExistingEdge.ifPresentOrElse(edge -> edge.setWeight(weight), () -> v1EdgeList.add(new Edge(v2, weight)));
                v2ExistingEdge.ifPresentOrElse(edge -> edge.setWeight(weight), () -> v2EdgeList.add(new Edge(v1, weight)));
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public List<Vertex> getVertices(){
        return new ArrayList<>(graphMap.keySet());
    }

    public List<Edge> getEdges(Vertex vertex){
        return graphMap.get(vertex);
    }
}

