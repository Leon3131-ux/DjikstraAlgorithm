package com.company.util;

import com.company.domain.Edge;
import com.company.domain.Vertex;
import com.company.domain.WeightedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmResolver {

    public Map<Vertex, DijkstraResult> solveWithDijkstra(WeightedGraph weightedGraph, Vertex startVertex){
        Map<Vertex, DijkstraResult> resultMap = new HashMap<>();
        List<Vertex> visited = new ArrayList<>();
        List<Vertex> queue = new ArrayList<>(weightedGraph.getVertices());

        for(Vertex vertex : queue){
            if(vertex.equals(startVertex)){
                resultMap.put(vertex, new DijkstraResult(0, startVertex));
                continue;
            }
            resultMap.put(vertex, new DijkstraResult(Double.POSITIVE_INFINITY, null));
        }

        Vertex currentVertex = startVertex;
        do {
            List<Edge> edges = weightedGraph.getEdges(currentVertex);
            for(Edge edge : edges){
                Vertex neighbour = edge.getTo();
                if(!visited.contains(neighbour)){
                    if(resultMap.get(currentVertex).getTotalDistance() + edge.getWeight() < resultMap.get(neighbour).getTotalDistance()){
                        resultMap.put(neighbour, new DijkstraResult(resultMap.get(currentVertex).getTotalDistance() + edge.getWeight(), currentVertex));
                    }
                }
            }
            visited.add(currentVertex);
            queue.remove(currentVertex);
            currentVertex = dijkstraGetMinValue(resultMap, queue);
        }while (queue.size() > 1);
        return resultMap;
    }

    private Vertex dijkstraGetMinValue(Map<Vertex, DijkstraResult> map, List<Vertex> queue){
        Vertex minValueVertex = queue.get(0);
        for(Vertex vertex : queue){
            if(map.get(vertex).getTotalDistance() < map.get(minValueVertex).getTotalDistance()){
                minValueVertex = vertex;
            }
        }
        return minValueVertex;
    }

}
