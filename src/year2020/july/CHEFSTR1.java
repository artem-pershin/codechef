package year2020.july;

import java.util.Scanner;

public class CHEFSTR1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();

            int prevString = -1;
            int curString;
            long totalSkippedStrings = 0L;

            for (int i = 0; i < N; i++) {
                curString = sc.nextInt();

                if (prevString != -1) {
                    totalSkippedStrings += Math.max(curString, prevString) - Math.min(curString, prevString) - 1;
                }

                prevString = curString;
            }

            System.out.println(totalSkippedStrings);
        }
    }
}
