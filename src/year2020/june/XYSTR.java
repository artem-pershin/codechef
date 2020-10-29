package year2020.june;

import java.util.Scanner;

public class XYSTR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for (int x = 1; x <= T; x++) {
            String xyStr = sc.nextLine();
            char[] xyA = xyStr.toCharArray();
            int ans = 0;
            int pos = 0;

            while (pos < xyA.length - 1) {
                if (xyA[pos] != xyA[pos + 1]) {
                    ans++;
                    pos += 2;
                } else {
                    pos++;
                }
            }

            System.out.println(ans);
        }
    }
}
