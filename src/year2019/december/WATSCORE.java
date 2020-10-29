package year2019.december;

import java.util.Arrays;
import java.util.Scanner;

public class WATSCORE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();

            int[] bestScores = new int[9];

            for (int j = 1; j <= N; j++) {
                int problemNumber = sc.nextInt();
                int problemScore  = sc.nextInt();

                if (problemNumber >= 9) continue;

                bestScores[problemNumber] = Math.max(bestScores[problemNumber], problemScore);
            }

            int ans = Arrays.stream(bestScores).sum();

            System.out.println(ans);
        }
    }
}
