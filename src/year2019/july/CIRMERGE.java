package year2019.july;

import java.util.*;

public class CIRMERGE {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int T = sc.nextInt();
//
//        for (int i = 1; i <= T; i++) {
//            int N = sc.nextInt();
//
//            LinkedList<Long> ll = new LinkedList<>();
//
//            for (int j = 0; j < N; j++) {
//                ll.add(sc.nextLong());
//            }
//            sc.nextLine();
//
//            if (N <= 10) {
//                System.out.println(solveMinimalPenaltyRec(ll));
//            } else {
//                System.out.println(solve2(ll));
//            }
//        }
        randomTest();
    }

    private static void randomTest() {
        Random r = new Random();

        // int T = 1 + r.nextInt(20);
        int T = 100;

        for (int i = 1; i <= T; i++) {
            LinkedList<Long> ll = new LinkedList<>();
            // int N = 2 + r.nextInt(12);
            int N = 10;
            long a[] = new long[N + 1];
            for (int j = 0; j < N; j++) {
                long ri = (long) (1 + r.nextInt(10));
                ll.add(ri);
                a[j + 1] = ri;
            }

            CirMergeSolver cmr = new CirMergeSolver(new LinkedList<>(ll), a);

            long s1 = cmr.solveMinimalPenalty();
            // long s1 = 0;
            //long s2 = solve2(new LinkedList<>(ll));
            long t1 = System.currentTimeMillis();
            long s2 = cmr.solveMinimalPenalty2();
            long t2 = System.currentTimeMillis();

            // System.out.println("time taken: " + (t2 - t1));
            if (s1 != s2) {
                System.out.print("list: ");
                for (Long l: ll) {
                    System.out.print(l + " ");
                }
                System.out.println("solve_1: " + s1 + " solve_2: " + s2 + " equals: " + (s1 == s2));
            }
            // System.out.println("solve_1: " + s1 + " solve_2: " + s2 + " equals: " + (s1 == s2));
        }
    }

    private static long solve2(LinkedList<Long> ll) {
        long penaltyScore = 0;

        while (ll.size() > 1) {
            int N = ll.size();
            int ffi = 0;
            int ssi = 1;
            long minSum2 = ll.get(0) + ll.get(1);
            long minSum3 = minSum2 + ll.get(2 % N);

            for (int i = 0 ; i < N; i++) {
                int fi = i;
                int si = (i + 1) % N;
                int ti = (i + 2) % N;

                long curSum2 = ll.get(fi) + ll.get(si);
                long curSum3 = curSum2 + ll.get(ti);

                if (curSum2 < minSum2 || (curSum2 == minSum2 && (curSum3 < minSum3))) {
                    minSum2 = curSum2;
                    ffi = fi;
                    ssi = si;
                }
            }

            ll.set(ffi, minSum2);
            ll.remove(ssi);

            penaltyScore += minSum2;
        }

        return penaltyScore;
    }

    private static long solveMinimalPenaltyRec(LinkedList<Long> list) {
        if (list.size() == 2) {
            return list.get(0) + list.get(1);
        }

        int N = list.size();
        Long min = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int nextInd = (i + 1) % N;
            long sum = list.get(i) + list.get(nextInd);
            LinkedList<Long> nextList = new LinkedList<>(list);
            nextList.set(i, sum);
            nextList.remove(nextInd);

            long rres = solveMinimalPenaltyRec(nextList);
            if (sum + rres < min) {
                min = sum + rres;
            }
        }

        return min;
    }
}
