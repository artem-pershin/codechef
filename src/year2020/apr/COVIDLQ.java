package year2020.apr;

import java.util.Scanner;

public class COVIDLQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();

            int prevInd = -1;
            String answer = "YES";

            int a[] = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = sc.nextInt();
            }

            for (int i = 0; i < N; i++) {
                int curVal = a[i];

                if (curVal == 1) {
                    if ((prevInd != -1) && (i - prevInd) < 6) {
                        answer = "NO";
                        break;
                    }

                    prevInd = i;
                }
            }

            System.out.println(answer);
        }
    }
}
