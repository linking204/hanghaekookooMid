import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    
    static int N;
    static int[][] map;
    static boolean[][] visited;
    // 상하좌우 이동용 배열
    static int[] vRow = {-1, 1, 0, 0}; // 행 이동 세로 
    static int[] vCol = {0, 0, -1, 1}; // 열 이동 가로 
    
    // [ TIL 에 정리합시다 !!! 1 ] 

    // Q. 이렇게 위와 같이 배열을 구성한 이유가 뭘까? 
    
    // 상하좌우 네 방향 모두 쉽게 탐색하기 위해서다. 
    // vRow[0] = -1, vCol[0] =  0   → 위로 이동
    // vRow[1] =  1, vCol[1] =  0   → 아래로 이동
    // vRow[2] =  0, vCol[2] = -1   → 왼쪽으로 이동
    // vRow[3] =  0, vCol[3] =  1   → 오른쪽으로 이동
    // 원래는 dx, dy 배열이었는데 기존 x 와 y축 좌표 생각하자니 헷갈려서 일부러 vRow 와 vCol이라는 배열명으로 변경함! 
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        // 지도 크기 N 입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 가장 낮은 높이부터 가장 높은 높이까지 모든 높이에 대해서 잠기는 시나리오
        // 가장 높은 높이를 구한 다음에 반복문 돌리면 되는 거 아님? 
        int maxHeight = 0;
    
        // 지도 정보 입력받기
        for(int row = 0; row < N; row++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[row][col]);
            }
        }
        
        int result = 0;

        // 높이별로 이차원 배열(맵) 다 뒤져봐 
        for (int height = 0; height < maxHeight+1; height++) {
            visited = new boolean[N][N]; //현재 위치를 방문했다고 표시하는 이유는 같은 지역 중복 방문을 막기 위함 
            int cnt = 0;
            
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if(!visited[row][col] && map[row][col] > height){ 
                        dfs(row, col, height);
                        cnt++;
                    }
                }
            }

            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }
    
    // DFS 탐색 메서드 (x, y는 현재 위치, height는 물에 잠기는 높이)
    static void dfs(int row, int col, int height) {
        visited[row][col] = true;

        // [ TIL 에 정리합시다 !!! 2 ] 

        // 왜 굳이 i < 4 냐면 상하좌우 4방향으로 탐색하기 위함이다. 
        for (int i = 0; i < 4; i++) {
            // 다음위치의 행과 열입니다. 
            int nextRow = row + vRow[i];
            int nextCol = col + vCol[i];

            // 지도 범위 내 + 방문하지 않음 + 물 높이보다 크다면 다음 위치로 이동해서 다시 탐색한다. 
            if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
                if (!visited[nextRow][nextCol] && map[nextRow][nextCol] > height) {
                    dfs(nextRow, nextCol, height);
                }
            }
        }
    }
    
    // [ TIL 에 정리합시다 !!! 3 ]

    // DFS 알고리즘이 이번 문제에서 작동하는 방식 
    // 일단 DFS 알고리즘의 특성에 대해서 정리해 봤습니다. 

    // 깊이 우선 탐색은 출발점에서 깊은 곳까지 한 길을 따라 쭉 내려가며 탐색하는 방법입니다. 
    // 영어로 Depth-First Search라고 해서 DFS라고 부릅니다. 
    // DFS에서는 출발점에서 한 방향으로 갈 수 있을 만큼 계속 깊이 내려갔다가, 
    // 더 이상 갈 수 없게 되면 (막다른 길에 다다르면) 되돌아와서 다른 길을 탐색합니다. 
    // 이렇게 최대한 깊이 내려간 뒤에 다시 갈림길로 돌아와서 또 다른 길을 따라 들어가는 과정을 반복하는 방식이 DFS 입니다. 
    
    // [6] [8] [2]
    // [3] [2] [3]
    // [7] [2] [5]
    
    // 물에 잠기는 높이를 일시적으로 4로 정해보자. 
    
    // 4 아래에 해당하는 건 다 물에 잠긴다. 그럼 아래처럼 된다. 
    // O   O   X
    // X   X   X
    // O   X   O
    
    // 1단계 DFS 최초 호출 (0, 0, 4) -- 현재 행, 현재 열, 현재 물의 높이 
    
    // 방문했으니 0,0의 visited는 true로 업데이트합니다. 
    //  [true false false]
    // [false false false]
    // [false false false]
    
    // 갈 수 있는 곳은 어디일까요? 
    // 위는 -1, 0 못가죠 
    // 아래 1, 0 는 높이가 3이라 물에 잠기죠 못가죠 
    // 왼쪽 0, -1 역시 못가죠 
    // 오른쪽 1, 0 높이 8이니 갈수 있죠 
    
    // 2단계 DFS 최초 호출 (0, 1, 4) -- 현재 행, 현재 열, 현재 물의 높이
    //  [true true false]
    // [false false false]
    // [false false false]
    
    // 위, 아래, 왼쪽, 오른쪽 다 보면 갈 수 있는 데가 없어요! 
    
    // dfs 호출 종료 이후 행 변경 후 열에 따라 다시 호출합니다. 
    
    // (2,0) - (2,2) 순으로 방문 진행 ... 계속 이어서 진행 

    // 높이별로 DFS호출한 후 그 카운팅의 MAX 값을 구합니다. 
    // 그럼 끝! 
}
