import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    
    public static long fibonacci(int n) {
        
        if(n <= 3){ 
            return 1;
        }
        
        // DP 배열 선언 
        long[] dp = new long[n + 1]; 
        
        // 초기값 설정
        dp[1] = dp[2] = dp[3] = 1;

        // 반복문을 이용
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];  
        }

        return dp[n];
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정수형으로 변환하려면
        int number = Integer.parseInt(br.readLine());
        br.close();

        long result = fibonacci(number);
       
        System.out.println(result); 
    }
}

// 1. 피보나치 함수 호출 기억
// 2. dp[i] = dp[i - 1] + dp[i - 2];  // 점화식 이용 
// 3. 단, 피보나치 비스무리한 수열이고 
// 1~3항은 1이기 때문에 그에 대한 조건을 줬음 
// 4. 4항부터 시작함 
// 5. 116번째 항까지 가면 int 표현 범위를 넘어버리기 때문에 Long 으로 처리 여기서 한번 틀림 