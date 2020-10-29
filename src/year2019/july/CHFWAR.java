package year2019.july;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CHFWAR {
    private static int MAX_ATTACK = 10_000;

    public static void main(String[] args) {
        // interactiveTest();
        randomTest();
    }

    private static void interactiveTest() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();

            int[] A = new int[N];

            A[0] = 0;
            for (int i = 1; i < N; i++) {
                A[i] = sc.nextInt();
            }

            int F = sc.nextInt();
            int minTookDmg = Integer.MAX_VALUE;
            int minJp = -1;
            int[] minA = new int[N];
            boolean[] minIsDead = new boolean[N];

            for (int jp = 0; jp < N; jp++) {
                if (jp != 0) {
                    int prevJp = jp - 1;
                    int curAi = A[jp];
                    A[prevJp] = curAi;
                    A[jp] = 0;
                }

                boolean[] is_dead = new boolean[N];

                List<Integer> a = new ArrayList<>();
                int[] ans = simulateKills(A, jp, is_dead, a);

                System.out.println("Josh pos: ");

                if (ans[1] <= F && ans[0] < minTookDmg) {
                    minTookDmg = ans[0];
                    minJp = jp;
                    minIsDead = is_dead;
                    System.arraycopy(A, 0, minA, 0, N);
                }
            }

            findAndPrintAnswer(minA, minJp, minTookDmg, minIsDead, F);
        }
    }

    private static void randomTest() {
        Random r = new Random();

        for (int N = 2; N <= 21; N++) {
            // int N = 1 + r.nextInt(10);

            int[] A = new int[N];

            for (int i = 0; i < N ; i++) {
                A[i] = 1 + r.nextInt(MAX_ATTACK);
            }

            int F = 1 + r.nextInt(MAX_ATTACK);
            int minTookDmg = Integer.MAX_VALUE;
            int minJp = -1;
            int[] minA = new int[N];
            boolean[] minIsDead = new boolean[N];

            System.out.println("N: " + N);
            for (int jp = 0; jp < N; jp++) {
                if (jp != 0) {
                    int prevJp = jp - 1;
                    int curAi = A[jp];
                    A[prevJp] = curAi;
                    A[jp] = 0;
                }

                boolean[] is_dead = new boolean[N];

                List<Integer> a = new ArrayList<>();
                int[] ans = simulateKills(A, jp, is_dead, a);

                System.out.println("Josh pos: " + (jp + 1) + ", Attackers: " + a.toString());

                if (ans[1] <= F && ans[0] < minTookDmg) {
                    minTookDmg = ans[0];
                    minJp = jp;
                    minIsDead = is_dead;
                    System.arraycopy(A, 0, minA, 0, N);
                }
            }

            findAndPrintAnswer(minA, minJp, minTookDmg, minIsDead, F);

            System.out.println("------------------------------------------");
            System.out.println();
        }
    }

    private static void findAndPrintAnswer(int[] a, int joshNum, int joshDamage, boolean[] isDead, int F) {
        if (joshDamage == Integer.MAX_VALUE) {
            System.out.println("impossible");
            return;
        }

        int N = a.length;
        int leftAttack = 0;

        for (int i = 0; i < N; i++) {
            if (!isDead[i] && (i != joshNum)) {
                leftAttack = a[i];
                break;
            }
        }

        if (leftAttack > F) {
            System.out.println("impossible");
        } else {
            System.out.println("possible");
            System.out.println(String.format("%d %d", joshNum + 1, joshDamage + F));
        }
    }

    private static int[] simulateKills(int[] a, int jp, boolean[] is_dead, List<Integer> attackers) {
        int[] ans = new int[2];
        int N = a.length;
        int aliveLeft = N;
        int curAttackIdx = jp == 0 ? 1 : 0;
        int joshDamage = 0;

        while (aliveLeft > 2) {
            int nextVictim = (curAttackIdx + 1) % N;

            while (is_dead[nextVictim]) {
                nextVictim = (nextVictim + 1) % N;
            }

            if (jp == nextVictim) {
                joshDamage += a[curAttackIdx];
                attackers.add(curAttackIdx + 1);
            } else {
                aliveLeft--;
                is_dead[nextVictim] = true;
            }

            ans[1] = a[curAttackIdx];

            curAttackIdx = (nextVictim + 1) % N;

            while (is_dead[curAttackIdx] || curAttackIdx == jp) {
                curAttackIdx = (curAttackIdx + 1) % N;
            }
        }

        ans[0] = joshDamage;

        return ans;
    }
}
