package year2020.sep;

import java.util.Scanner;

class COVID19B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            int[] runnerVelocities = new int[N];

            for (int j = 0; j < N; j++) {
                runnerVelocities[j] = sc.nextInt();
            }

            int ansHigh = 1;
            int ansLow  = N;

            for (int j = 0; j < N; j++) {
                ansHigh = Math.max(ansHigh, countInfected(runnerVelocities, j));
                ansLow =  Math.min(ansLow,  countInfected(runnerVelocities, j));
            }

            System.out.println(ansLow + " " + ansHigh);
        }
    }

    private static int countInfected(int[] runnerVelocities, int firstInfected) {
        int res = 1;

        for (int i = firstInfected - 1; i >= 0; i--) {
            if (runnerVelocities[i] > runnerVelocities[firstInfected]) {
                res++;
            }
        }

        for (int i = firstInfected + 1; i < runnerVelocities.length; i++) {
            if (runnerVelocities[i] < runnerVelocities[firstInfected]) {
                res++;
            }
        }

        return res;
    }
}
