package year2019.august;

import java.util.*;

public class ZOMCAV {
    private enum POINT_TYPE { START, END }

    private static class Point implements Comparable {
        public int x;
        POINT_TYPE type;
        public int num;


        Point(int x, POINT_TYPE type) {
            this.x = x;
            this.type = type;
        }

        Point(int x, int num) {
            this.x = x;
            this.num = num;
        }

        @Override
        public int compareTo(Object o) {
            if (this.x == ((Point) o).x) {
                return this.type.ordinal() - ((Point) o).type.ordinal();
            }

            return this.x - ((Point) o).x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    type == point.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, type);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", type=" + type +
                    ", num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            sc.nextLine();

            int[] c = new int[N + 1];
            int[] h = new int[N + 1];
            String s = sc.nextLine();

            String[] splitted = s.split(" ");

            for (int j = 0; j < splitted.length; j++) {
                c[j + 1] = Integer.valueOf(splitted[j]);
            }

            s = sc.nextLine();

            splitted = s.split(" ");

            for (int j = 0; j < splitted.length; j++) {
                h[j + 1] = Integer.valueOf(splitted[j]);
            }

            // System.out.println(hasSolutionSimulate(c, h) ? "YES" : "NO");
            System.out.println(hasSolutionFast(c, h) ? "YES" : "NO");
        }

        // randomTest();
    }

    private static boolean hasSolutionFast(int[] c, int[] h) {
        int N = c.length - 1;

        List<Point> points = new ArrayList<>(2 * N);

        for (int i = 1; i <= N; i++) {
            int minIdx = Math.max(i - c[i], 1);
            int maxIdx = Math.min(i + c[i], N);

            points.add(new Point(minIdx, POINT_TYPE.START));
            points.add(new Point(maxIdx + 1, POINT_TYPE.END));
        }

        Collections.sort(points);

        List<Point> pointsSummed = new ArrayList<>(N);

        int i = 0;
        while (i < 2 * N) {
            int curX = points.get(i).x;
            int curNum = 0;

            while (i < 2 * N && points.get(i).x == curX) {
                if (points.get(i).type == POINT_TYPE.END) {
                    curNum--;
                } else {
                    curNum++;
                }

                i++;
            }

            pointsSummed.add(new Point(curX, curNum));
        }

        int[] rad = new int[N + 1];
        int curI = 0;
        int curSum = 0;

        int shrinkN = pointsSummed.size();

        while (curI < shrinkN - 1) {
            Point p1 = pointsSummed.get(curI);
            Point p2 = pointsSummed.get(curI + 1);

            int sx = p1.x;
            int ex = p2.x;
            curSum += p1.num;

            for (int j = sx; j <= ex && j <= N; j++) {
                rad[j] = curSum;
            }

            curI++;
        }

        // printArray(rad, 1);

        return isZombieCanBeKilled(rad, h);
    }

    private static boolean hasSolutionSimulate(int[] c, int[] h) {
        int N = c.length - 1;

        int[] rad = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int minIdx = Math.max(i - c[i], 1);
            int maxIdx = Math.min(i + c[i], N);

            for (int j = minIdx; j <= maxIdx; j++) {
                rad[j]++;
            }
        }

        // printArray(rad, 2);
        return isZombieCanBeKilled(rad, h);
    }

    private static void printArray(int[] a, int n) {
        System.out.print("Array " + n + ": ");
        for (int i = 1; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    private static boolean isZombieCanBeKilled(int[] rad, int h[]) {
        int N = rad.length - 1;
        HashMap<Integer, Integer> counts = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            if (!counts.containsKey(rad[i])) {
                counts.put(rad[i], 1);
            } else {
                counts.put(rad[i], counts.get(rad[i]) + 1);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (counts.containsKey(h[i])) {
                Integer cnt = counts.get(h[i]);

                if (cnt <= 0) return false;

                counts.put(h[i], counts.get(h[i]) - 1);
            } else {
                return false;
            }
        }

        return true;
    }

    private static void randomTest() {
        Random r = new Random();
        int T = 1 + r.nextInt(100);

        for (int i = 0; i < T; i++) {
            int N = 1 + r.nextInt(100);
            int[] c = generateRandomAr(N);
            int[] h = generateRandomAr(N);

            System.out.println(hasSolutionFast(c, h) == hasSolutionSimulate(c, h));
        }
    }

    private static int[] generateRandomAr(int N) {
        Random r = new Random();

        int[] res = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            res[i] = 1 + r.nextInt(N);
        }

        return res;
    }
}
