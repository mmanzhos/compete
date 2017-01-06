import java.util.*;

class Coord {
    int row;
    int col;
    Coord(int r, int c) {
        row = r;
        col = c;
    }
}

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    int[][] grid;
    int[][] killed;
    int d;
    int SIZE = 1025;
    
    void solve() {
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();
        while (tests-- > 0) {
            d = in.nextInt();
            int n = in.nextInt();
            grid = new int[SIZE][SIZE];
            killed = new int[SIZE][SIZE];
            List<Coord> list = new ArrayList<>();
            while (n-- > 0) {
                int row = in.nextInt();
                int col = in.nextInt();
                int population = in.nextInt();
                list.add(new Coord(row, col));
                grid[row][col] = population;
            }
            
            // O(n * d^2) = 20k * 2500
            // pre-processing
            
            int max = -1;
            Coord maxC = null;
            
            
            for (int i = 0; i < list.size(); i++) {
                Coord el = list.get(i);
                int population = grid[el.row][el.col];
                for (int row = Math.max(el.row - d, 0);
                     row <= Math.min(el.row + d, SIZE - 1)
                             && row >= 0 && row < SIZE; row++) {
                    for (int col = Math.max(el.col - d, 0);
                         col <= Math.min(el.col + d, SIZE - 1)
                                 && col >= 0 && col < SIZE; col++) {
                        killed[row][col] += population;
                        if (killed[row][col] > max) {
                            max = killed[row][col];
                            maxC = new Coord(row, col);
                        } else if (killed[row][col] == max &&
                                maxC.row <= row && maxC.col < col) {
                            max = killed[row][col];
                            maxC = new Coord(row, col);
                        }
                    }
                }
            }

            if (maxC == null) {
                System.out.println("123");
            }
            
            System.out.println(maxC.row + " " + maxC.col + " " + max);
            
        }
    }

}
