package year2020.aug;

import java.util.Scanner;

public class LINCHESS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            int minSteps = Integer.MAX_VALUE;
            int ans = -1;

            for (int j = 1; j <= N; j++) {
                int P = sc.nextInt();

                if (P < K && ((K - P) % P == 0)) {
                    int curSteps = (K - P) / P;
                    if (curSteps < minSteps) {
                        ans = P;
                        minSteps = curSteps;
                    }
                }
            }

            System.out.println(ans);
        }
    }
}

