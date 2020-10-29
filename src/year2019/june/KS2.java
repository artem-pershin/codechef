package year2019.june;

import java.util.Scanner;

public class KS2 {
    public static void main(String[] args) {
        int numOfInt = 0;

        int T;
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            long N = sc.nextLong();

            System.out.println(makeNthRoundInteger(N));
        }

//        for (int i = 1; i < 100000; i++) {
//            if (isIntegerRound(i)) {
//                numOfInt++;
//
//                int ansInt = Integer.parseInt(makeNthRoundInteger(numOfInt));
//
//                if (ansInt != i) {
//                    System.out.println(String.format("expected: %d, actual: %d", i, ansInt));
//                }
//
//                //System.out.println(String.format("%d: %d", numOfInt, i));
//            }
//        }
    }

    private static boolean isIntegerRound(long l) {
        int sumOfdigits = Long.toString(l).chars().map(i -> i - '0').sum();

        return sumOfdigits % 10 == 0;
    }

    private static String makeNthRoundInteger(long n) {
        String longAsString = Long.toString(n);

        int sumOfdigits = Long.toString(n).chars().map(i -> i - '0').sum();

        int lastDigit;

        if (sumOfdigits < 10) {
            lastDigit = 10 - sumOfdigits;
        } else {
            lastDigit = sumOfdigits % 10 == 0 ? 0 : 10 - (sumOfdigits % 10);
        }

        longAsString += lastDigit;

        return longAsString;
    }
}
