package year2019.feb;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MANRECTTEST {
    private static final int MAX_X = (int) 100;
    private static final int MAX_Y = (int) 100;

    private static final List<Rectangle> tests =
            Arrays.asList(new Rectangle(new RectPoint(0, (int) 1e9), new RectPoint(0, (int) 1e9)),
                    new Rectangle(new RectPoint(500_000_000, 500_000_000), new RectPoint(800_000_000, 800_000_000)));

    private static int correct = 0;

    private static class RectPoint {
        int x;
        int y;

        RectPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Rectangle {
        private RectPoint leftLower;
        private RectPoint rightUpper;

        Rectangle(RectPoint leftLower, RectPoint rightUpper) {
            this.leftLower = leftLower;
            this.rightUpper = rightUpper;
        }

        @Override
        public String toString() {
            return String.format("Rectangle bounds: (%d, %d) - (%d, %d)",
                    leftLower.x, leftLower.y, rightUpper.x, rightUpper.y);
        }
    }

    private static class Problem {
        static final Random rnd = new Random();
        final Rectangle secretRect;

        Problem(Rectangle r) {
            this.secretRect = r;
        }

        Problem() {
            int x1 = rnd.nextInt(MAX_X);
            int x2 = rnd.nextInt(MAX_X);
            int y1 = rnd.nextInt(MAX_Y);
            int y2 = rnd.nextInt(MAX_Y);

            this.secretRect = new Rectangle(
                    new RectPoint(x1 <= x2 ? x1 : x2, y1 <= y2 ? y1 : y2),
                    new RectPoint(x2 >= x1 ? x2 : x1, y2 >= y1 ? y2 : y1)
            );

            System.out.println(this.secretRect);
        }

        void receiveResponse(Request r) {
            if (r.x > MAX_X || r.y > MAX_Y) {
                System.out.println(String.format("Illegal query coordinates: x: %s, y: %d", r.x, r.y));
                throw new NullPointerException();
            }

            if (r.x >= secretRect.leftLower.x && r.x <= secretRect.leftLower.x &&
                    r.y >= secretRect.rightUpper.y && r.y <= secretRect.rightUpper.y
            ) r.ans = 0;
            else if (r.x >= secretRect.leftLower.x && r.x <= secretRect.rightUpper.x) {
                r.ans =
                        Math.min(Math.abs(secretRect.leftLower.y - r.y), Math.abs(secretRect.rightUpper.y - r.y));
            } else if (r.y >= secretRect.leftLower.y && r.y <= secretRect.rightUpper.y) {
                r.ans =
                        Math.min(Math.abs(secretRect.leftLower.x - r.x), Math.abs(secretRect.rightUpper.x - r.x));
            } else {
                r.ans =
                        Math.min(Math.abs(secretRect.leftLower.y - r.y), Math.abs(secretRect.rightUpper.y - r.y))
                                + Math.min(Math.abs(secretRect.leftLower.x - r.x), Math.abs(secretRect.rightUpper.x - r.x));
            }
//            System.out.print("Getting distance to point: ");
//            r.print();
//            System.out.println("Distance is: " + r.ans);
        }

        boolean checkAnswer(Answer a) {
            RectPoint left = secretRect.leftLower;
            RectPoint right = secretRect.rightUpper;

            if (left.x == a.xL && left.y == a.yL && right.x == a.xR && right.y == a.yR) {
                //System.out.println("Your answer is right");
                return true;
            } else {
                System.out.println("Illegal answer");
                a.print();
                return false;
            }
        }
    }

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

    private static Point findIntersection(Segment l1, Segment l2) {
        long a1 = l1.e.y - l1.s.y;
        long b1 = l1.s.x - l1.e.x;
//        long c1 = a1 * l1.s.x + b1 * l1.s.y;

        BigDecimal c1 =
                new BigDecimal(a1).multiply(new BigDecimal(l1.s.x))
                        .add(new BigDecimal(b1).multiply(new BigDecimal(l1.s.y)));


        long a2 = l2.e.y - l2.s.y;
        long b2 = l2.s.x - l2.e.x;
        //long c2 = a2 * l2.s.x + b2 * l2.s.y;

        BigDecimal c2 =
                new BigDecimal(a2).multiply(new BigDecimal(l2.s.x))
                        .add(new BigDecimal(b2).multiply(new BigDecimal(l2.s.y)));

        BigDecimal delta = new BigDecimal(a1).multiply(new BigDecimal(b2))
                .subtract(new BigDecimal(a2).multiply(new BigDecimal(b1)));


        BigDecimal bd1 = new BigDecimal(b2).multiply(c1)
                .subtract(new BigDecimal(b1).multiply(c2));

        BigDecimal bd2 = new BigDecimal(a1).multiply(c2)
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
        int T = sc.nextInt();
        //int T = tests.size();

        //for (Rectangle r : tests) {
        for (int x = 1; x <= T; x++) {
            Problem p = new Problem();
            //Problem p = new Problem(r);

            Request r1 = new Request(0, 0);
            p.receiveResponse(r1);

            if (tryAnswer(r1, p)) {
                correct++;
                continue;
            }

            long xReq1 = Math.min(MAX_X, r1.ans);
            long yReq1 = r1.ans > MAX_X ? r1.ans - MAX_X : 0;

            Point p2;

            if (r1.ans > MAX_X) {
                p2 = new Point(r1.ans - MAX_X, MAX_Y);
            } else {
                p2 = new Point(0, xReq1);
            }

            // Request r2 = new Request(r1.ans, 0);
            Request r2 = new Request(xReq1, yReq1);

            //receiveResponse(r2, sc);

            //Request r2 = new Request(r1.ans, 0);
            p.receiveResponse(r2);

            if (tryAnswer(r2, p)) {
                correct++;
                continue;
            }


            // (r1.ans, r2.ans) - (r1.ans - r2.ans, 0)
            // (0, r1.ans) - (r1.ans, 0)
//            Segment s1 = new Segment(new Point(r1.ans, r2.ans), new Point(r1.ans - r2.ans, 0));
//            Segment s2 = new Segment(new Point(0, r1.ans), new Point(r1.ans, 0));

            Segment s1 = new Segment(new Point(r2.x, r2.y), p2);
            Segment s2 = new Segment(new Point(r2.x, r2.ans + r2.y), new Point(r2.x - r2.ans, r2.y));

            Point intersection = findIntersection(s1, s2);

            Request r3 = new Request(intersection.x, intersection.y);

            p.receiveResponse(r3);
            //System.out.print("Intersection point: ");
            //r3.print();
            //System.out.println(" ans: " + r3.ans);

            r3.y += r3.ans;
            r3.ans = 0;

            if (tryAnswer(r3, p)) {
                correct++;
            }
        }

        System.out.println(String.format("%d/%d correct", correct, T));

        System.exit(0);
    }

    private static boolean tryAnswer(Request r, Problem p) {
        if (r.ans == 0) {
            Answer a = solve(r, p);
            return p.checkAnswer(a);
            //a.print();
        }
        return false;
    }

    private static Answer solve(Request cr, Problem p) {
        Request r4 = new Request(0, cr.y);
        Request r5 = new Request(cr.x, 0);
        Request r6 = new Request(cr.x, MAX_Y);
        Request r7 = new Request(MAX_X, cr.y);

        p.receiveResponse(r4);
        p.receiveResponse(r5);
        p.receiveResponse(r6);
        p.receiveResponse(r7);

        return new Answer(r4.ans, r5.ans, MAX_X - r7.ans, MAX_Y - r6.ans);
    }
}
