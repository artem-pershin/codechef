package year2019.feb;

import java.math.BigDecimal;
import java.util.Scanner;

public class MANRECT {
    private static final long MAX_X = (long) 1e9;
    private static final long MAX_Y = (long) 1e9;

    private static class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Segment {
        Point s, e;

        Segment(Point s, Point e) {
            this.s = s;
            this.e = e;
        }
    }

    private static Point findIntersection2(Segment l1, Segment l2) {
        long a1 = l1.e.y - l1.s.y;
        long b1 = l1.s.x - l1.e.x;

        BigDecimal c1 =
                new BigDecimal(a1).multiply(new BigDecimal(l1.s.x))
                        .add(new BigDecimal(b1).multiply(new BigDecimal(l1.s.y)));


        long a2 = l2.e.y - l2.s.y;
        long b2 = l2.s.x - l2.e.x;

        BigDecimal c2 =
                new BigDecimal(a2).multiply(new BigDecimal(l2.s.x))
                        .add(new BigDecimal(b2).multiply(new BigDecimal(l2.s.y)));

        BigDecimal delta = new BigDecimal(a1).multiply(new BigDecimal(b2))
                .subtract(new BigDecimal(a2).multiply(new BigDecimal(b1)));


        BigDecimal bd1= new BigDecimal(b2).multiply(c1)
                .subtract(new BigDecimal(b1).multiply(c2));

        BigDecimal bd2= new BigDecimal(a1).multiply(c2)
                .subtract(new BigDecimal(a2).multiply(c1));

        long x_intersect = bd1.divide(delta, BigDecimal.ROUND_FLOOR).longValue();
        long y_intersect = bd2.divide(delta, BigDecimal.ROUND_FLOOR).longValue();

        return new Point(x_intersect, y_intersect);
    }

    static class Request {
        long x;
        long y;
        long ans;

        Request(long x, long y) {
            this.x = x;
            this.y = y;
        }

        void print() {
            System.out.println(this);
            System.out.flush();
        }

        @Override
        public String toString() {
            return String.format("Q %d %d", x, y);
        }
    }

    static class Answer {
        long xL;
        long yL;
        long xR;
        long yR;

        Answer(long xL, long yL, long xR, long yR) {
            this.xL = xL;
            this.yL = yL;
            this.xR = xR;
            this.yR = yR;
        }

        void print() {
            System.out.println(this);
            System.out.flush();
        }

        @Override
        public String toString() {
            return String.format("A %d %d %d %d", xL, yL, xR, yR);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int x = 1; x <= T; x++) {
            Request r1 = new Request(0, 0);
            receiveResponse(r1, sc);

            if (answerCanBePrinted(r1, sc, 1)) {
                continue;
            }

            long xReq1 = Math.min(MAX_X, r1.ans);
            long yReq1 = r1.ans > MAX_X ? r1.ans - MAX_X : 0;

            Point p2;

            if (r1.ans > MAX_X) {
                p2 = new Point( r1.ans - MAX_X, MAX_Y);
            } else {
                p2 = new Point(0, xReq1);
            }

            // Request r2 = new Request(r1.ans, 0);
            Request r2 = new Request(xReq1, yReq1);

            receiveResponse(r2, sc);

            if (answerCanBePrinted(r2, sc, 2)) {
                continue;
            }

            // (r1.ans, r2.ans) - (r1.ans - r2.ans, 0)
            // (0, r1.ans) - (r1.ans, 0)
            // Segment s1 = new Segment(new Point(r1.ans, r2.ans), new Point(r1.ans - r2.ans, 0));
            // Segment s2 = new Segment(new Point(0, r1.ans), new Point(r1.ans, 0));

            Segment s1 = new Segment(new Point(r2.x, r2.y), p2);
            Segment s2 = new Segment(new Point(r2.x, r2.ans + r2.y), new Point(r2.x - r2.ans, r2.y));

            Point intersection = findIntersection2(s1, s2);

            Request r3 = new Request(intersection.x, intersection.y);

            receiveResponse(r3, sc);

            if (answerCanBePrinted(r3, sc, 3)) {
                continue;
            }

            if (r3.ans != 0) {
                r3.y += r3.ans;
            }

            receiveResponse(r3, sc);

            answerCanBePrinted(r3, sc, 4);
        }
    }

    private static boolean answerCanBePrinted(Request r, Scanner sc, int tryNum) {
        boolean isLastTry = tryNum == 4;

        if (r.ans == 0) {
            Answer a = solve(r, sc, isLastTry);
            a.print();

            int judge = Integer.parseInt(sc.nextLine());

            if (judge != 1) {
                System.exit(0);
            }

            return true;
        }

        return false;
    }

    private static void receiveResponse(Request r, Scanner sc) {
        if (r.x > MAX_X || r.y > MAX_Y) throw new NullPointerException();

        r.print();
        r.ans = Integer.parseInt(sc.nextLine());
    }

    private static Answer solve(Request cr, Scanner sc, boolean isLastTry) {
        Request r4 = new Request(0, cr.y);
        Request r5 = null;

        if (!isLastTry) {
            r5 = new Request(cr.x, 0);
        }
        Request r6 = new Request(cr.x, MAX_Y);
        Request r7 = new Request(MAX_X, cr.y);

        receiveResponse(r4, sc);

        if (!isLastTry) {
            receiveResponse(r5, sc);
        }

        receiveResponse(r6, sc);
        receiveResponse(r7, sc);

        return new Answer(r4.ans, isLastTry ? cr.y : r5.ans, MAX_X - r7.ans, MAX_Y - r6.ans);
    }
}
