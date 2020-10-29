package year2019.july;

import java.util.Scanner;

public class CHFM {
    private static final double EPS = 0.0000000001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            long sum = 0;
            int N = sc.nextInt();
            int[] a = new int[N];

            for (int j = 0; j < N; j++) {
                a[j] = sc.nextInt();
                sum += a[j];
            }

            double avg = (double) sum / N;
            boolean fnd = false;

            for (int j = 0; j < N; j++) {
                double curAvg = (double) (sum - a[j]) / (N - 1);
                if (Math.abs(avg - curAvg) < EPS) {
                    System.out.println(j + 1);
                    fnd = true;
                    break;
                }
            }

            if (!fnd) {
                System.out.println("Impossible");
            }
        }
    }
}
