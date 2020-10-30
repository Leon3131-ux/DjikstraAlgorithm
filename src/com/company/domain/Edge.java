package com.company.domain;

public class Edge {

    private final Vertex to;
    private double weight;

    public Edge(Vertex to, double weight){
        this.to = to;
        this.weight = weight;
    }

    public Vertex getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

}
