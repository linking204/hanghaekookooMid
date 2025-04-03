public class App {
    
    public int[] solution(String[] wallpaper) {
        
        int minRow = Integer.MAX_VALUE; // 가장 위쪽 행
        int minCol = Integer.MAX_VALUE; // 가장 왼쪽 열
        int maxRow = Integer.MIN_VALUE; // 가장 아래쪽 행
        int maxCol = Integer.MIN_VALUE; // 가장 오른쪽 열

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    minRow = Math.min(minRow, i);
                    minCol = Math.min(minCol, j);
                    maxRow = Math.max(maxRow, i);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }
        
        int[] answer = {minRow, minCol, maxRow + 1, maxCol + 1};
        
        return answer;
    }

        
    
    
    
    
    
    public static void main(String[] args) throws Exception {
       
    }
}
