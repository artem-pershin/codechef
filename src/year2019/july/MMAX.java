package year2019.july;

import java.math.BigInteger;
import java.util.Scanner;

public class MMAX {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            sc.nextLine();

            String K = sc.nextLine();
            BigInteger kBig = new BigInteger(K);
            BigInteger remBig = kBig.remainder(new BigInteger(Integer.toString(N)));
            int rem = remBig.intValue();

            int dots = rem;
            int spaces = N - rem;

            //int diff = Math.min(rem, N - rem);
            int ans;
            if (spaces == dots) {
                ans = spaces * 2 - 1;
            } else {
                ans = Math.min(spaces, dots) * 2;
            }

            System.out.println(ans);
        }

        //test100K();
    }

    private static void test100K() {
        StringBuilder sb = new StringBuilder("1");

        for (int i = 0; i < 100_000; i++) {
            sb.append("0");
        }

        int N = 9999;

        BigInteger kBig = new BigInteger(sb.toString());
        BigInteger remBig = kBig.remainder(new BigInteger(Integer.toString(N)));
        int rem = remBig.intValue();

        System.out.println("remainder: " + rem);
    }
}
