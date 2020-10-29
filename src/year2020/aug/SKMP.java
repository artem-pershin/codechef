package year2020.aug;

import java.util.Scanner;

class SKMP {
    public static void main(String[] args) {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner reader = new Scanner(System.in);

        int T = reader.nextInt();
        reader.nextLine();

        for (int t = 1; t <= T; t++) {
            String S = reader.nextLine();
            String P = reader.nextLine();

            int[] sChars = new int[26];

            for (char c : S.toCharArray()) {
                sChars[c - 'a']++;
            }

            int firstChar = P.charAt(0) - 'a';
            boolean printBefore = true;

            for (char c : P.toCharArray()) {
                sChars[c - 'a']--;

                if ((c - 'a') > firstChar) {
                    printBefore = false;
                }
            }

            for (int i = 0; i < firstChar; i++) {
                for (int j = 0; j < sChars[i]; j++) {
                    System.out.print((char) (i + 'a'));
                }
            }

            if (printBefore) {
                System.out.print(P);

                for (int i = 0; i < sChars[firstChar]; i++) {
                    System.out.print((char) (firstChar + 'a'));
                }
            } else {
                for (int i = 0; i < sChars[firstChar]; i++) {
                    System.out.print((char) (firstChar + 'a'));
                }

                System.out.print(P);
            }

            for (int i = firstChar + 1; i < 26; i++) {
                for (int j = 0; j < sChars[i]; j++) {
                    System.out.print((char) (i + 'a'));
                }
            }

            System.out.println();
            System.out.flush();
        }
    }
}
