import java.util.*;

/**
 * A* Search Algorithm Implementation
 * 
 * Time Complexity: O(E) in best case, O(b^d) in worst case where b is branching factor and d is depth
 * Space Complexity: O(V) for storing nodes
 * 
 * A* uses a heuristic function to guide its search and is optimal when the heuristic is admissible
 */
public class AStar {
    // A node in the search space
    static class Node implements Comparable<Node> {
        int x, y;       // Coordinates
        int g;          // Cost from start to current node
        int h;          // Heuristic (estimated cost from current to goal)
        int f;          // f = g + h
        Node parent;    // Parent node for path reconstruction
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = 0;
            this.h = 0;
            this.f = 0;
            this.parent = null;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    // Grid directions (4-directional movement: up, right, down, left)
    private static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    /**
     * Calculate the Manhattan distance heuristic
     */
    private static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    /**
     * A* search algorithm to find path in a grid
     * 
     * @param grid The grid where 0 represents walkable and 1 represents obstacle
     * @param startX Starting x-coordinate
     * @param startY Starting y-coordinate
     * @param goalX Goal x-coordinate
     * @param goalY Goal y-coordinate
     * @return List of nodes representing the path from start to goal, or empty list if no path exists
     */
    public static List<Node> findPath(int[][] grid, int startX, int startY, int goalX, int goalY) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Check if start or goal is out of bounds or on obstacle
        if (startX < 0 || startX >= rows || startY < 0 || startY >= cols || 
            goalX < 0 || goalX >= rows || goalY < 0 || goalY >= cols ||
            grid[startX][startY] == 1 || grid[goalX][goalY] == 1) {
            return new ArrayList<>();
        }
        
        Node startNode = new Node(startX, startY);
        Node goalNode = new Node(goalX, goalY);
        
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        
        // Initialize the starting node
        startNode.g = 0;
        startNode.h = manhattanDistance(startX, startY, goalX, goalY);
        startNode.f = startNode.g + startNode.h;
        
        openSet.add(startNode);
        
        while (!openSet.isEmpty()) {
            // Get the node with the lowest f score
            Node current = openSet.poll();
            
            // If we've reached the goal, reconstruct and return the path
            if (current.x == goalX && current.y == goalY) {
                return reconstructPath(current);
            }
            
            closedSet.add(current);
            
            // Check all four directions
            for (int[] dir : DIRS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                
                // Check if the new position is valid
                if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || 
                    grid[newX][newY] == 1) {
                    continue;
                }
                
                Node neighbor = new Node(newX, newY);
                
                // Skip if we've already evaluated this node
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                
                // Calculate g score for the neighbor
                int tentativeG = current.g + 1; // Assuming cost of 1 to move
                
                // Check if this path to neighbor is better or if neighbor is not in openSet
                boolean inOpenSet = openSet.contains(neighbor);
                if (!inOpenSet || tentativeG < neighbor.g) {
                    // If the neighbor is already in openSet, we need to remove it and reinsert with updated values
                    if (inOpenSet) {
                        openSet.remove(neighbor);
                    }
                    
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = manhattanDistance(newX, newY, goalX, goalY);
                    neighbor.f = neighbor.g + neighbor.h;
                    
                    openSet.add(neighbor);
                }
            }
        }
        
        // No path found
        return new ArrayList<>();
    }
    
    /**
     * Reconstructs the path from start to goal by following parent pointers
     */
    private static List<Node> reconstructPath(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node current = goalNode;
        
        while (current != null) {
            path.add(0, current); // Add to the beginning of the list
            current = current.parent;
        }
        
        return path;
    }
    
    /**
     * Main method to demonstrate the A* algorithm
     */
    public static void main(String[] args) {
        // Create a simple grid
        // 0 = walkable, 1 = obstacle
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        
        // Find path from (0,0) to (4,4)
        List<Node> path = findPath(grid, 0, 0, 4, 4);
        
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
            System.out.println("Path length: " + (path.size() - 1));
        }
        
        // Print the grid with the path
        printGridWithPath(grid, path);
    }
    
    /**
     * Helper method to print the grid with the path
     */
    private static void printGridWithPath(int[][] grid, List<Node> path) {
        // Create a copy of the grid
        char[][] gridWithPath = new char[grid.length][grid[0].length];
        
        // Fill the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    gridWithPath[i][j] = '#'; // obstacle
                } else {
                    gridWithPath[i][j] = '.'; // walkable
                }
            }
        }
        
        // Mark the path
        for (Node node : path) {
            gridWithPath[node.x][node.y] = '*';
        }
        
        // Mark start and goal
        if (!path.isEmpty()) {
            Node start = path.get(0);
            Node goal = path.get(path.size() - 1);
            gridWithPath[start.x][start.y] = 'S';
            gridWithPath[goal.x][goal.y] = 'G';
        }
        
        // Print the grid
        System.out.println("\nGrid with path:");
        for (char[] row : gridWithPath) {
            System.out.println(new String(row));
        }
    }
}
