package year2019.august;

import java.util.Scanner;

public class DSTAPLS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T ; i++) {
            long N = sc.nextLong();
            long K = sc.nextLong();

            long iterNum = N / K;

            if (iterNum % K == 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
