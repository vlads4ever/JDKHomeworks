package org.example;

/**
 * Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(),
 * divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 */

public class Calculator {
    public static <T1 extends Number, T2 extends Number, T3 extends Number> T3 sum(T1 a, T2 b) {
        if (a instanceof Double || b instanceof Double) {
            return (T3) Double.valueOf(a.doubleValue() + b.doubleValue());
        } else if (a instanceof Float || b instanceof Float) {
            return (T3) Float.valueOf(a.floatValue() + b.floatValue());
        } else if (a instanceof Long || b instanceof Long) {
            return (T3) Long.valueOf(a.longValue() + b.longValue());
        } else {
            return (T3) Integer.valueOf(a.intValue() + b.intValue());
        }


//        if (a instanceof Double || b instanceof Double) {
//            return (T1) Double.valueOf(a.doubleValue() + b.doubleValue());
//        } else if (a instanceof Float || b instanceof Float) {
//            return (T1) Float.valueOf(a.floatValue() + b.floatValue());
//        } else if (a instanceof Long || b instanceof Long) {
//            return (T1) Long.valueOf(a.longValue() + b.longValue());
//        } else {
//            return (T1) Integer.valueOf(a.intValue() + b.intValue());
//        }
    }
//    public static <T1 extends Number, T2 extends Number, T3 extends Number> T3 multiply(T1 num1, T2 num2) {}
//    public static <T1 extends Number, T2 extends Number, T3 extends Number> T3 divide(T1 num1, T2 num2) {}
//    public static <T1 extends Number, T2 extends Number, T3 extends Number> T3 subtract(T1 num1, T2 num2) {}


}
