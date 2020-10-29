package year2019.june;

import java.util.Scanner;

public class PROXYC {
    public static void main(String[] args) {
        int T;
        int D;

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            D = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            int presentTimes = 0;
            int proxyPresent = 0;

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);

                if (c == 'A') {
                    if (j < 2 || j >= s.length() - 2) continue;

                    if ((s.charAt(j - 1) == 'P' || s.charAt(j - 2) == 'P') &&
                            (s.charAt(j + 1) == 'P' || s.charAt(j + 2) == 'P')) {
                        proxyPresent++;
                    }
                } else {
                    presentTimes++;
                }
            }

            int needAtLeastTimesPresent;

            if (D % 4 == 0) {
                needAtLeastTimesPresent = (D / 4) * 3;
            } else {
                needAtLeastTimesPresent = ((int) Math.floor(D * 0.75f)) + 1;
            }

            //System.out.println("need at least present: " + needAtLeastTimesPresent);

            if ((presentTimes + proxyPresent) >= needAtLeastTimesPresent) {
                System.out.println(Math.max(needAtLeastTimesPresent - presentTimes, 0));
            } else {
                System.out.println("-1");
            }
        }
    }
}
