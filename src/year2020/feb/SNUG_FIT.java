package year2020.feb;

import java.util.Arrays;
import java.util.Scanner;

public class SNUG_FIT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();

            long[] a = new long[N];
            long[] b = new long[N];

            for (int i = 0; i < N; i++) {
                a[i] = sc.nextLong();
            }

            for (int i = 0; i < N; i++) {
                b[i] = sc.nextLong();
            }
            
            long maximumSumOfDiameters = solve(a, b);
            // long maximumSumOfDiameters = // Math.min(a[0], b[0]) * N;
            System.out.println(maximumSumOfDiameters);
        }
    }

    private static long solve(long[] a, long[] b) {
        int N = a.length;

        Arrays.sort(a);
        Arrays.sort(b);

        long sumOfDiameters = 0;
        for (int i = 0; i < N; i++) {
            sumOfDiameters += Math.min(a[i], b[i]);
        }

        return sumOfDiameters;
    }
}
