package year2019.feb;

import java.util.*;

public class ARTBALAN {
    private static class FreqProperty {
        FreqProperty(int needAbs, int needVal, int freq) {
            this.needAbs = needAbs;
            this.needVal = needVal;
            this.freq = freq;
        }

        private int freq;
        private int needAbs;
        private int needVal;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for (int x = 1; x <= T; x++) {
            String s = sc.nextLine();
            int ans = solve(s);
            System.out.println(ans);
        }

//        Random r = new Random();
//
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < 5_000_000; i++) {
//            sb.append((char) ('A' + r.nextInt(26)));
//        }
//
//        System.out.println(solve(sb.toString()));
    }

    private static int solve(String s) {
        int[] freqs = new int[26];
        int len = s.length();

        for (int i = 0; i < len; i++) {
            freqs[s.charAt(i) - 'A']++;
        }

        int countDistinct = 0;

        for (int i = 0; i < 26; i++) {
            if (freqs[i] != 0) countDistinct++;
        }

        int ans = len;

        for (int leftLetters = 26; leftLetters >= 1; leftLetters--) {
            if (leftLetters > len || len % leftLetters != 0) {
                continue;
            }

            int changeCount = 0;
            int singleLetterCount = len / leftLetters;

            List<FreqProperty> fp = new ArrayList<>(26);

            for (int i = 0; i < 26; i++) {
                int notEnough = singleLetterCount - freqs[i];

                if (freqs[i] > 0) {
                    fp.add(new FreqProperty(Math.abs(notEnough), notEnough, freqs[i]));
                }
            }

            fp.sort(Comparator.comparingInt(o -> o.needVal));

            for (int i = 1; i <= 26 - countDistinct; i++) {
                fp.add(new FreqProperty(singleLetterCount, singleLetterCount /* freq = 0 */, 0));
            }

            int loan = 0;

            for (int i = 0; i < leftLetters; i++) {
                FreqProperty fprop = fp.get(i);

                if (fprop.needVal == 0) continue;

                if (fprop.needVal > 0) {
                    if (loan < 0) {
                        int forLoan = Math.min(fprop.needVal, Math.abs(loan));
                        loan += forLoan;

                        int diff = fprop.needVal - forLoan;
                        changeCount += diff;

                        loan += diff;
                    } else {
                        changeCount += fprop.needVal;
                        loan += fprop.needVal;
                    }
                } else {
                    // needVal < 0
                    if (loan > 0) {
                        int forLoan = Math.min(fprop.needAbs, loan);
                        loan -= forLoan;

                        int diff = fprop.needAbs - forLoan;

                        changeCount += diff;
                        loan -= diff;
                    } else {
                        changeCount += fprop.needAbs;
                        loan -= fprop.needAbs;
                    }
                }
            }

            for (int i = leftLetters; i < countDistinct; i++) {
                loan -= fp.get(i).freq;
            }

            if (loan == 0) {
                ans = Math.min(changeCount, ans);
            }
        }

        return ans;
    }
}
