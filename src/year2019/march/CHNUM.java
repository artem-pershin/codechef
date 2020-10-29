package year2019.march;

import java.util.Scanner;

public class CHNUM {
    public static void main(String[] args) {
        int T;
        int N;

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int numNegative = 0;
            int numPositive = 0;
            N = sc.nextInt();

            for (int j = 0; j < N; j++) {
                int Ai = sc.nextInt();

                if (Ai < 0) {
                    numNegative++;
                } else {
                    numPositive++;
                }
            }

            boolean oneOfGroupsEmpty = false;

            if (numNegative == 0 || numPositive == 0) {
                oneOfGroupsEmpty = true;
            }

            int maxGroupSize = Math.max(numPositive, numNegative);
            String res = maxGroupSize + " " + (oneOfGroupsEmpty ? maxGroupSize : Math.min(numPositive, numNegative));

            System.out.println(res);
        }
    }
}