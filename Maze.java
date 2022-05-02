import java.util.Arrays;

class Maze
{

    // Check if cell (x, y) is valid or not
    private static boolean isValidCell(int x, int y, int Border) {
        return (x >= 0 && y >= 0 && x < Border && y < Border);
    }

    private static int countPaths(int[][] maze, int x, int y, int findX, int findY,
                                  boolean[][] visited, int filled, int limit)
    {
        if (filled > limit) return 0;

        // if destination is found
        if (x == findX && y == findY){
            visited[findX][findY] = true;
            for (boolean[] booleans : visited) {
                System.out.println(Arrays.toString(booleans));
            }
            visited[findX][findY] = false;
            System.out.println("=======");
            return 1;
        }



        // stores number of unique paths
        int count = 0;

        //mark as visited
        visited[x][y] = true;

        int len = maze.length;

        //  if current location is valid and can be accessed
        if (isValidCell(x, y, len) && maze[x][y] == 1)
        {
            // try going down
            if (x + 1 < len && !visited[x + 1][y]) {
                count += countPaths(maze, x + 1, y, findX, findY, visited, filled + 1, limit);
            }

            // try going up
            if (x - 1 >= 0 && !visited[x - 1][y]) {
                count += countPaths(maze, x - 1, y, findX, findY, visited, filled + 1, limit);
            }

            // try going right
            if (y + 1 < len && !visited[x][y + 1]) {
                count += countPaths(maze, x, y + 1, findX, findY, visited, filled + 1, limit);
            }

            // try going left
            if (y - 1 >= 0 && !visited[x][y - 1]) {
                count += countPaths(maze, x, y - 1, findX, findY, visited, filled + 1, limit);
            }
        }

        // if none of them work mark as false
        visited[x][y] = false;


        return count;
    }
//
//    public static int findCount(int[][] maze, int x, int y, int findX, int findY, int filled, int limit)
//    {
//        if (maze == null || maze.length == 0 || maze[x][y] == 0 || maze[findX][findY] == 0) {
//            return 0;
//        }
//
//        int len = maze.length;
//
//        //to see visited places
//        boolean[][] visited = new boolean[len][len];
//
//
//        return countPaths(maze, x, y, findX, findY, visited, filled, limit);
//    } used for finding every possible ways, not just optimal

    public static int findOptimal(int[][] maze, int x, int y, int findX, int findY){
        if (maze == null || maze.length == 0 || maze[x][y] == 0 || maze[findX][findY] == 0) {
            return 0;
        }

        int len = maze.length;

        //to see visited places
        boolean[][] visited = new boolean[len][len];

        for (int i = 0; i <= (maze.length * maze[0].length) - 1; i ++){
            int paths = countPaths(maze, x, y, findX, findY, visited, 0, i);
            if(paths > 0){
                System.out.println("Steps to achieve goal: " + i);
                return paths;
            }
        }
        return 0;
    }

    public static void main(String[] args)
    {
        int[][] maze =
                {
                        {1, 1, 1, 1},
                        {1, 0, 1, 1},
                        {0, 1, 1, 1},
                        {1, 1, 1, 1}
                };


        int count = findOptimal(maze, 0, 0, 3, 3);

        System.out.println("Total numbers of paths: " + count);
    }

}
