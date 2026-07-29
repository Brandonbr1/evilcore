package com.jerios.evilMinecraftFixes.fasterMath;

// https://github.com/JOML-CI/JOML/blob/main/src/main/java/org/joml/Math.java#L473
public class FastTrigno {

    public static final double PI = java.lang.Math.PI;
    public static final double PI_OVER_2 = PI * 0.5;

    public static double fma(double a, double b, double c) {
        return a * b + c;
    }

    public static double fastAtan2(double y, double x) {
        double ax = x >= 0.0 ? x : -x, ay = y >= 0.0 ? y : -y;
        double a = ay > ax ? ax / ay : ay / ax;
        double s = a * a;
        double r = fma(fma(fma(-0.0464964749, s, 0.15931422), s, -0.327622764) * s, a, a);
        if (ay > ax) r = PI_OVER_2 - r;
        if (x < 0.0) r = PI - r;
        return y >= 0 ? r : -r;
    }
}
