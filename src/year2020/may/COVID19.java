package year2020.may;

import java.util.Scanner;

public class COVID19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();

            int a[] = new int[N];

            for (int j = 0; j < N; j++) {
                a[j] = sc.nextInt();
            }

            int minInfected = N;
            int maxInfected = 1;

            for (int j = 0; j < N; j++) {
                // j - index of infected man
                int curInfected = 1;
                for (int k = j + 1; k < N; k++) {
                    if (a[k] - a[k - 1] <= 2) {
                        curInfected++;
                    } else {
                        break;
                    }
                }

                for (int k = j - 1; k >= 0; k--) {
                    if (a[k + 1] - a[k] <= 2) {
                        curInfected++;
                    } else {
                        break;
                    }
                }

                minInfected = Math.min(curInfected, minInfected);
                maxInfected = Math.max(curInfected, maxInfected);
            }

            System.out.println(String.format("%d %d", minInfected, maxInfected));
        }
    }
}
