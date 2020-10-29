package year2019.august;

import java.util.Scanner;

public class MSNSADM1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();

            int maxPoints = 0;

            int a[] = new int[N];
            int b[] = new int[N];

            for (int j = 0; j < N; j++) {
                a[j] = sc.nextInt();
            }

            for (int j = 0; j < N; j++) {
                b[j] = sc.nextInt();
            }

            for (int j = 0; j < N; j++) {
                maxPoints = Math.max(maxPoints, a[j] * 20 - b[j] * 10);
            }

            System.out.println(maxPoints);
        }
    }
}
