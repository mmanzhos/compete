import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }
    
    void solve() {
        Scanner in = new Scanner(System.in);
        int N;
        while (in.hasNextInt()) {
            N = in.nextInt();
            if (N == 0) break;
            in.nextLine(); // == cin.ignore()
            char[][] board = new char[N][N];
            for (int i = 0; i < N; i++) {
                String line = in.nextLine();
                for (int j = 0; j < N; j++) {
                    board[i][j] = line.charAt(j);
                }
            }
            
            // generate combinations
            List<Integer> set = new ArrayList<>();
            int lim = N * N;
            for (int num = 0; num < lim; num++) {
                set.add(num);
            }
            List<List<Integer>> combs = new ArrayList<>();
            combs.add(new ArrayList<>());
            
            // {0, 1, 2, 3}
            
            for (int i = 0; i < lim; i++) {
                int el = set.get(i);
                int combSize = combs.size();
                for (int j = -1; j < combSize; j++) {
                    if (j == -1) {
                        List<Integer> list = Arrays.asList(el);
                        combs.add(list);
                    } else {
                        List<Integer> base = combs.get(j);
                        List<Integer> baseDup = new ArrayList<>(base);
                        baseDup.add(el);
                        combs.add(baseDup);
                    }
                }
            }

            int maxRooks = 0;
            for (int i = 0; i < combs.size(); i++) {
                char[][] boardDup = new char[N][N];
                for (int ccc = 0; ccc < N; ccc++) {
                    boardDup[ccc] = Arrays.copyOf(board[ccc], N);
                }
                List<Integer> combination = combs.get(i);
                boolean wrong = false;
                for (int posIndex = 0; posIndex < combination.size(); posIndex++) {
                    int pos = combination.get(posIndex);
                    int row = pos / N;
                    int col = pos % N;
                    if (boardDup[row][col] == 'X') {
                        wrong = true;
                        break;
                    } else {
                        boardDup[row][col] = 'R';
                    }
                }
                if (wrong) continue;
                
                // check if legal board
                boolean isLegal = true;
                for (int posIndex = 0; posIndex < combination.size(); posIndex++) {
                    int pos = combination.get(posIndex);
                    int row = pos / N;
                    int col = pos % N;
                    
                    // left
                    for (int bi = col - 1; bi >= 0; bi--) {
                        if (boardDup[row][bi] == 'X') break;
                        else if (boardDup[row][bi] == 'R') {
                            isLegal= false;
                            break;
                        }
                    }
                    if (!isLegal) break;

                    // right
                    for (int bi = col + 1; bi < N; bi++) {
                        if (boardDup[row][bi] == 'X') break;
                        else if (boardDup[row][bi] == 'R') {
                            isLegal= false;
                            break;
                        }
                    }
                    if (!isLegal) break;

                    // up
                    for (int bi = row - 1; bi >= 0; bi--) {
                        if (boardDup[bi][col] == 'X') break;
                        else if (boardDup[bi][col] == 'R') {
                            isLegal= false;
                            break;
                        }
                    }
                    if (!isLegal) break;

                    // down
                    for (int bi = row + 1; bi < N; bi++) {
                        if (boardDup[bi][col] == 'X') break;
                        else if (boardDup[bi][col] == 'R') {
                            isLegal= false;
                            break;
                        }
                    }
                    if (!isLegal) break;
                    
                }
                if (!isLegal) continue;
                
                if (combination.size() > maxRooks) {
                    maxRooks = combination.size();
                }
            }

            System.out.println(maxRooks);
            
            
        }
    }

}
