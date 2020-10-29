package year2019.srm758_10may;

import java.util.Arrays;

public class LongJump2 {
    public static int countNewLeaders(int N, int[] jumpLengths) {
        int ans = 1;
        int curMax = jumpLengths[0];
        int leaderNum = 0;

        int[] curLengths = new int[N];
        Arrays.fill(curLengths, 0);

        for (int i = 0; i < N; i++) {
            curLengths[i] += jumpLengths[i];

            if (curMax < curLengths[i] && leaderNum != i) {
                System.out.println("curMax: " + curMax + " newMax:" + curLengths[i] + " old leader: " + leaderNum + " new leader: " + i);
                ans++;
                leaderNum = i;
                curMax = curLengths[i];
            }
        }

        Arrays.fill(curLengths, 0);

        for (int i = 0; i < N; i++) {
            curLengths[i] += jumpLengths[N + i];

            if (curMax < curLengths[i] && leaderNum != i) {
                System.out.println("curMax: " + curMax + " newMax:" + curLengths[i] + " old leader: " + leaderNum + " new leader: " + i);
                ans++;
                leaderNum = i;
                curMax = curLengths[i];
            }
        }

        Arrays.fill(curLengths, 0);

        for (int i = 0; i < N; i++) {
            curLengths[i] += jumpLengths[2 * N + i];

            if (curMax < curLengths[i] && leaderNum != i) {
                System.out.println("curMax: " + curMax + " newMax:" + curLengths[i] + " old leader: " + leaderNum + " new leader: " + i);
                ans++;
                leaderNum = i;
                curMax = curLengths[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        //System.out.println(countNewLeaders(3, new int[]{800, 10, 20, 810, 30, 40, 50, 830, 830}));
        System.out.println(countNewLeaders(5, new int[]{1, 0, 6, 6, 6, 6, 9, 2, 2, 6, 8, 10, 7, 4, 10}));
    }
}
