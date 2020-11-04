package com.company.util;

import com.company.domain.Vertex;

public class DijkstraResult {

    double totalDistance;
    Vertex previousVertex;

    public DijkstraResult(double totalDistance, Vertex previousVertex){
        this.totalDistance = totalDistance;
        this.previousVertex = previousVertex;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }
}
