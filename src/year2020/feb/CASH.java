package year2020.feb;

import java.util.Arrays;
import java.util.Scanner;

public class CASH {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            long[] a = new long[N];

            for (int i = 0; i < N; i++) {
                a[i] = sc.nextLong();
            }

            long answer = solve(a, K);
            // long maximumSumOfDiameters = // Math.min(a[0], b[0]) * N;
            System.out.println(answer);
        }
    }

    private static long solve(long[] a, int K) {
        int N = a.length;

        long ans = K - 1;
        int c = 0;

        long remainderSumLeft = 0;
        long canBeBorrowedFromLeft = 0;

        long mustBeAddedToRight = Arrays.stream(a).map(A ->  howMuchAddToBeDivisible(A, K)).sum();

        do {
            if (canBeBorrowedFromLeft >= mustBeAddedToRight) {
                long leftCoins = remainderSumLeft - mustBeAddedToRight;
                if (leftCoins < 0) {
                    leftCoins = Math.abs(leftCoins);
                    leftCoins = howMuchAddToBeDivisible(leftCoins, K);
                } else {
                    leftCoins = howMuchBorrowToBeDivisible(leftCoins, K);
                }

                ans = Math.min(ans, leftCoins);
            }

            if (ans == 0) {
                // the least remainder could not be less than 0
                break;
            }

            if (c < N) {
                remainderSumLeft += howMuchBorrowToBeDivisible(a[c], K);
                canBeBorrowedFromLeft += a[c];
                mustBeAddedToRight -= howMuchAddToBeDivisible(a[c], K);
            }

            c++;
        } while (c <= N);

        return ans;
    }

    private static long howMuchBorrowToBeDivisible(long borrowFrom, long divisor) {
        return borrowFrom % divisor;
    }

    private static long howMuchAddToBeDivisible(long addTo, long divisor) {
        return addTo % divisor == 0 ? 0 : divisor - (addTo % divisor);
    }
}
