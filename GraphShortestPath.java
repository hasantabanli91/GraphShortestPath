import java.util.*;

public class GraphShortestPath {
    
    public static void main(String[] args) {
        int V = 8; // Number of vertices
        Graph graph = new Graph(V);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        
        int startVertex = 0; // Starting vertex
        
        int[] paths = countShortestPaths(graph, startVertex);
        
        System.out.println("Number of distinct shortest paths from vertex " + startVertex + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To vertex " + i + ": " + paths[i]);
        }
    }

    static class Graph {
        int V;
        List<List<Integer>> adj;
        
        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++)
                adj.add(new ArrayList<>());
        }
        
        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u); // Since it's an undirected graph
        }
    }
    
    public static int[] countShortestPaths(Graph graph, int start) {
        int[] distances = new int[graph.V];
        int[] paths = new int[graph.V];
        Arrays.fill(distances, -1);
        distances[start] = 0;
        paths[start] = 1;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.adj.get(node)) {
                if (distances[neighbor] == -1) {
                    distances[neighbor] = distances[node] + 1;
                    paths[neighbor] = paths[node];
                    queue.add(neighbor);
                } else if (distances[neighbor] == distances[node] + 1) {
                    paths[neighbor] += paths[node];
                }
            }
        }
        
        return paths;
    }
    
}