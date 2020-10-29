package year2019.august;

import java.util.Random;
import java.util.Scanner;

public class CHEFDIL {
    public static void main(String[] args) {
        // randomTest();
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= T; i++) {
            String s = sc.nextLine();

            System.out.println(isSolutionExists(s.toCharArray(), 0, s.length()) ? "WIN" : "LOSE");
        }
    }

    private static void randomTest() {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        int T = 1 + r.nextInt(100);

        for (int i = 1; i <= T; i++) {
            String s = Integer.toBinaryString(r.nextInt(Integer.MAX_VALUE) + 1);
            System.out.println(isSolutionExists(s.toCharArray(), 0, s.length()) ? "WIN" : "LOSE");
        }

//        String s = sb.toString();
//        System.out.println(isSolutionExists(s.toCharArray(), 0, s.length()));
    }

    private static boolean isSolutionExists(char[] c, int start, int end) {
        while (true) {
            if (c[start] == '0') {
                while (start < end && c[start] == '0') {
                    start++;
                }

                if (start == end) {
                    return false;
                } else if (start == end - 1 && c[start] == '1') {
                    return true;
                } else {
                    c[start + 1] = c[start + 1] == '1' ? '0' : '1';
                    start++;
                }
            } else {
                if (start == end - 1) {
                    return true;
                }

                c[start + 1] = c[start + 1] == '1' ? '0' : '1';

                start++;
            }
        }

//        if (c[start] == '0') {
//            // skip zeros till ones
//            while (start < end && c[start] == '0') {
//                start++;
//            }
//
//            if (start == end) {
//                return false;
//            } else if (start == end - 1 && c[start] == '1') {
//                return true;
//            } else {
//                c[start + 1] = c[start + 1] == '1' ? '0' : '1';
//                return isSolutionExists(c, start + 1, end);
//            }
//        } else {
//            c[start + 1] = c[start + 1] == '1' ? '0' : '1';
//            return isSolutionExists(c, start + 1, end);
//        }
    }
}
