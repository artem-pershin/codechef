package year2020.october;

import java.util.Scanner;

class CHEFEZQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            long N = sc.nextInt();
            long k = sc.nextInt();

            long ans = 0;
            long questionsNum = 0;
            for (int j = 1; j <= N; j++) {
                questionsNum += sc.nextInt();
                questionsNum -= k;

                if (ans == 0 && questionsNum < 0) ans = j;
            }

            if (ans == 0) {
                ans = (N + (questionsNum / k + 1));
            }

            System.out.println(ans);
        }
    }
}
