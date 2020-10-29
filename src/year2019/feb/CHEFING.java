package year2019.feb;

import java.util.Scanner;

public class CHEFING {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();
            sc.nextLine();
            boolean[][] hasSymbolInString = new boolean[N + 1][26];

            for (int z = 1; z <= N; z++) {
                String s = sc.nextLine();

                for (int i = 0; i < s.length(); i++) {
                    hasSymbolInString[z][s.charAt(i) - 'a'] = true;
                }
            }

            int ans = 0;

            for (int i = 0; i < 26; i++) {
                boolean includedInAllStrings = true;

                for (int j = 1; j <= N; j++) {
                    includedInAllStrings &= hasSymbolInString[j][i];
                }

                if (includedInAllStrings) ans++;
            }

            System.out.println(ans);
        }
    }
}