package year2019.feb;

import java.util.Scanner;

public class MAGICJAR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();

            long ans = 0;

            for (int i = 0; i < N; i++) {
                ans += sc.nextInt();
            }

            ans -= N;
            ans += 1;

            System.out.println(ans);
        }
    }
}
