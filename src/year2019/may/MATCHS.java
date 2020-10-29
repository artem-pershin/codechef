package year2019.may;

import java.util.Scanner;

public class MATCHS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        long N, M;

        for (int i = 1; i <= T; i++) {
            N = sc.nextLong();
            M = sc.nextLong();

            System.out.println(solve(N, M) ? "Ari" : "Rich");
        }
    }

    private static boolean solve(long n, long m) {
        long greater = Math.max(n, m);
        long lesser = Math.min(n, m);

        long rem = greater % lesser;
        long div = greater / lesser;

        if (rem == 0) {
            return true;
        }

        if (div > 1) {
            return true;
        }

        return !solve(rem, lesser);
    }
}
