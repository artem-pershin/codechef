package year2019.feb;

import java.util.Scanner;

public class HMAPPY2 {
    public static void main(String[] args) {
        int T, A, B;
        long N, K;

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            N = sc.nextLong();
            A = sc.nextInt();
            B = sc.nextInt();
            K = sc.nextLong();

            String answerToCase = solve(N, A, B, K);

            System.out.println(answerToCase);
        }
    }

    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static String solve(long N, int A, int B, long K) {
        long numberOfDivisibleByA = N / A;
        long numberOfDivisibleByB = N / B;
        long numberOfDivisibleByBoth = N / (lcm(A, B));

        long numberTheyCanSolve = (numberOfDivisibleByA + numberOfDivisibleByB) - 2 * numberOfDivisibleByBoth;

        return numberTheyCanSolve >= K ? "Win" : "Lose";
    }
}