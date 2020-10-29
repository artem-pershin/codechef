package year2019.feb;

import java.util.Scanner;

public class DEPCHEF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();

            int[] a = new int[N];
            int[] d = new int[N];

            for (int y = 0; y < N; y++) {
                a[y] = sc.nextInt();
            }

            for (int y = 0; y < N; y++) {
                d[y] = sc.nextInt();
            }

            int ans = -1;

            for (int i = 0; i < N; i++) {
                boolean isAlive = true;

                int rightAttack = a[(i + 1) % N];
                int leftAttack = a[i == 0 ? N - 1 : i - 1];

                if (rightAttack + leftAttack >= d[i]) {
                    isAlive = false;
                }

                if (isAlive) {
                    ans = Math.max(d[i], ans);
                }
            }

            System.out.println(ans);
        }
    }
}
