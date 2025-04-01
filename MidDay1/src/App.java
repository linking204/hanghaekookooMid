import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
      

        for(int i = start; i <= end; i++){ 
            int cnt = 0;

            for (int j = 1; j <= i; j++) {
                if(i % j == 0){
                    cnt++;
                }
            }

            if(cnt == 2){ 
                System.out.println(i + " ");
            }

            cnt = 0;
        }    
    }
}
