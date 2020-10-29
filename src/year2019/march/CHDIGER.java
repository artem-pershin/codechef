package year2019.march;

import java.util.Scanner;

public class CHDIGER {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            long N = sc.nextLong();
            int d = sc.nextInt();

            long nextN = N;
            long prevN;

            do {
                prevN = nextN;
                nextN = lowerIfPossible(nextN, d);
            } while (prevN != nextN);

            System.out.println(nextN);
        }
    }

    private static long lowerIfPossible(long N, int d) {
        String stringRep = Long.toString(N);
        stringRep += Integer.toString(d);

        int charToDelete = -1;

        for (int i = 0; i < stringRep.length() - 1; i++) {
            if (stringRep.charAt(i) > stringRep.charAt(i + 1)) {
                charToDelete = i;
                break;
            }
        }

        if (charToDelete != -1) {
            StringBuilder sb = new StringBuilder(stringRep);
            sb.deleteCharAt(charToDelete);

            return Long.parseLong(sb.toString());
        } else {
            return N;
        }
    }
}
