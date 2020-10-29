package year2019.july;

import java.util.Arrays;
import java.util.LinkedList;

public class CirMergeSolver {
    private long[] _a;
    private LinkedList<Long> _list;
    private long[][] sums;
    private long[][] pens;

    public CirMergeSolver(LinkedList<Long> ll, long[] a) {
        _a = a;
        init();
        _list = ll;
    }

    private void init() {
        int N = _a.length - 1;

        pens = new long[2 * N + 1][2 * N + 1];
        sums = new long[2 * N + 1][2 * N + 1];

        for (int i = 0; i <= 2 * N; i++) {
            Arrays.fill(pens[i], Long.MAX_VALUE);
            Arrays.fill(sums[i], Long.MAX_VALUE);
        }

        for (int i = 1, j = N + 1; i < N; i++, j++) {
            long s = _a[i] + _a[i + 1];
            sums[i][i + 1] = s;
            pens[i][i + 1] = s;

            sums[j][j + 1] = s;
            pens[j][j + 1] = s;
        }

        for (int i = 1, j = N + 1; i <= N ; i++, j++) {
            sums[i][i] = _a[i];
            pens[i][i] = 0;

            sums[j][j] = _a[i];
            pens[j][j] = 0;
        }

//        sums[0][1] = _a[1] + _a[N];
//        sums[0][0] = _a[N];
//        pens[0][0] = 0;
    }

    public long solveMinimalPenalty2() {
        int N = _a.length - 1;
        solveMinimalPenaltyRec2(1, N + N / 2);

        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= N / 2; i++) {
            ans = Math.min(ans, pens[i][i + N - 1]);
        }

        return ans;
    }

    private long solveMinimalPenaltyRec2(int startInd, int endInd) {
        //System.out.println("start " + startInd + " end: " + endInd);

        if (pens[startInd][endInd] != Long.MAX_VALUE) {
            return pens[startInd][endInd];
        }

        for (int k = startInd; k < endInd; k++) {
            pens[startInd][k] = solveMinimalPenaltyRec2(startInd, k);
            pens[k + 1][endInd] = solveMinimalPenaltyRec2(k + 1, endInd);

            long newSum = sums[startInd][k] + sums[k + 1][endInd];
            long newPen = pens[startInd][k] + pens[k + 1][endInd] + newSum;

            if (newPen < pens[startInd][endInd]) {
                pens[startInd][endInd] = newPen;
                sums[startInd][endInd] = newSum;
            }
        }

        return pens[startInd][endInd];
    }

    public long solveMinimalPenalty() {
        return solveMinimalPenaltyRec(_list);
    }

    private long solveMinimalPenaltyRec(LinkedList<Long> list) {
        if (list.size() == 2) {
            return list.get(0) + list.get(1);
        }

        int N = list.size();
        Long min = Long.MAX_VALUE;
        Long minSum = Long.MAX_VALUE;
        Long minChosen = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int nextInd = (i + 1) % N;
            long sum = list.get(i) + list.get(nextInd);
            LinkedList<Long> nextList = new LinkedList<>(list);
            nextList.set(i, sum);
            nextList.remove(nextInd);

            long rres = solveMinimalPenaltyRec(nextList);
            if (sum < minSum) {
                minSum = sum;
            }

            if (sum + rres < min) {
                min = sum + rres;
                minChosen = sum;
            }
        }

//        if (minChosen != minSum && N > 5) {
//            System.out.println("chosen: " + minChosen + " minSum: " + minSum);
//        }

        return min;
    }
}
