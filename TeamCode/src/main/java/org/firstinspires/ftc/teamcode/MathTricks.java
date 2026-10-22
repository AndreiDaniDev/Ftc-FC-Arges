package org.firstinspires.ftc.teamcode;

public class MathTricks {
    public static double angleWrap(double angle) {
        while (angle < -Math.PI) angle += 2 * Math.PI;
        while (angle >= Math.PI) angle -= 2 * Math.PI;
        return angle;
    }

    public static double doubleClamp(double valuee, double loww, double highh){
        return Math.min(Math.max(valuee, loww), highh);
    }

    public static boolean isClamped(double valuee, double loww, double highh){
        return (loww <= valuee && valuee <= highh);
    }
}
