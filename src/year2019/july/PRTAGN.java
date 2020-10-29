package year2019.july;

import java.util.*;

public class PRTAGN {
    private static final byte[] cache = new byte[132001];

    static {
        for (int i = 1; i < 132000; i++) {
            cache[i] = (byte) Integer.bitCount(i);
        }
    }

    public static void main(String[] args) {
        //interactiveSolve();

        List<Integer> xs = new LinkedList<>();
        Random r = new Random();

        for (int i = 0; i < 100000; i++) {
            xs.add(1 + r.nextInt(100000));
        }

        //solve(xs);
        long c1 = System.currentTimeMillis();
        solve2(xs);
        long c2 = System.currentTimeMillis();

        System.out.println("time: " + (c2 - c1));

        //System.out.println(solve(xs).equals(solve2(xs)));
    }

    private static void interactiveSolve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int prevX = 0;
            int Q = sc.nextInt();

            int[] seti = new int[150000];
            int sz = 0;
            boolean[] alr = new boolean[100_001];
            boolean[] sa = new boolean[132_001];
            int even = 0;
            int odd = 0;

            for (int j = 0; j < Q; j++) {
                int x = sc.nextInt();

                if (even == 65535) {
                    System.out.println(even + " " + odd);
                    continue;
                }

                if (alr[x]) {
                    System.out.println(even + " " + odd);
                    prevX = x;
                    continue;
                }

                alr[x] = true;

                int bc = cache[x];

                if (j == 0) {
                    // set.add(x);
                    seti[sz++] = x;
                    if (bc % 2 == 0) even++; else odd++;
                } else {
                    int xorWithFirst = prevX ^ x;

                    if (!sa[xorWithFirst]) {
                        if (even == 32767) {
                            even = 65535;
                            odd = 65536;
                            System.out.println(even + " " + odd);
                            continue;
                        }

                        int sz2 = sz;
                        for (int z = 0; z < sz; z++) {
                            int y = seti[z];
                            if (y != x) {
                                int nx = y ^ x;
                                sa[nx] = true;
                                seti[sz2++] = nx;
                                if (cache[nx] % 2 == 0) even++; else odd++;
                            }
                        }

                        sz = sz2;
                    }

                    if (!sa[x]) {
                        // set.add(x);
                        seti[sz++] = x;
                        if (bc % 2 == 0) even++; else odd++;
                    }
                }

                sa[x] = true;
                prevX = x;
                System.out.println(even + " " + odd);
            }
        }
    }

    private static List<Integer> solve2(List<Integer> xs) {
        int prevX = 0;
        int[] seti = new int[150_000];
        int sz = 0;
        boolean[] alr = new boolean[100_001];
        boolean[] sa = new boolean[132_001];
        int even = 0;
        int odd = 0;

        for (Integer x: xs) {
            if (even == 65535) {
                //System.out.println(even + " " + odd);
                continue;
            }

            if (alr[x]) {
                //System.out.println(even + " " + odd);
                prevX = x;
                continue;
            }

            alr[x] = true;

            int bc = cache[x];

            if (sz == 0) {
                // set.add(x);
                seti[sz++] = x;
                if (bc % 2 == 0) even++; else odd++;
            } else {
                int xorWithFirst = prevX ^ x;

                if (!sa[xorWithFirst]) {
                    if (even == 32767) {
                        even = 65535;
                        odd = 65536;
                        // System.out.println(even + " " + odd);
                        continue;
                    }

                    int sz2 = sz;
                    for (int z = 0; z < sz; z++) {
                        int y = seti[z];
                        if (y != x) {
                            int nx = y ^ x;
                            sa[nx] = true;
                            seti[sz2++] = nx;
                            if (cache[nx] % 2 == 0) even++; else odd++;
                        }
                    }

                    sz = sz2;
                }

                if (!sa[x]) {
                    // set.add(x);
                    seti[sz++] = x;
                    if (bc % 2 == 0) even++; else odd++;
                }
            }

            sa[x] = true;
            prevX = x;
            //System.out.println(even + " " + odd);
        }

        return Collections.emptyList();
    }

    private static List<Integer> solve(List<Integer> xs) {
//        if (bc % 2 == 0) {
//            even += oe;
//            odd += oo;
//            even++;
//        } else {
//            // x - odd here
//            even += oo;
//            odd += oe;
//            odd++;
//        }

        TreeSet<Integer> set = new TreeSet<>();
        int even = 0;
        int odd = 0;

        List<Integer> res = new ArrayList<>();

        for (Integer x: xs) {
            Set<Integer> la = new TreeSet<>();

            for (Integer s: set) {
                if (s.equals(x)) continue;

                int toadd = s ^ x;

                if (toadd < 0) {
                    System.out.println(Integer.toBinaryString(s) + ": " + s);
                    System.out.println(Integer.toBinaryString(x) + ": " + x);
                    System.out.println(toadd);
                    break;
                }

                if (!set.contains(toadd)) {
                    la.add(toadd);
                }
            }

            if (!set.contains(x)) {
                la.add(x);
            }

            for (Integer toadd: la) {
                int bc = Integer.bitCount(toadd);
                if (bc % 2 == 0) even++; else odd++;
            }

            set.addAll(la);

            res.add(even);
            res.add(odd);

            //System.out.println(even + " " + odd + " x: " + Integer.toBinaryString(x));
        }

        return res;
    }
}
