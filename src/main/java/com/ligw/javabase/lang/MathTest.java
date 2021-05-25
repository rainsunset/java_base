package com.ligw.javabase.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName Math
 * @Description: TODO
 * @Author: ligangwei
 * @CreateDate: 2021/5/13 14:32
 */
public class MathTest {
    public static void main(String[] args) {
        int abs = Math.abs(-1);

        // 幂运算
        double pow = Math.pow(3, 2); // 9.0
        double random = Math.random();
        // 四舍五入
        long round = Math.round(20.2); // 20
        long round1 = Math.round(-20.2); // -20
        // 向下取整
        double floor = Math.floor(20.7); // 20.0
        double floor1 = Math.floor(-20.7); // -21.0
        // 向上取整
        double ceil = Math.ceil(23.3); // 24.0
        double ceil1 = Math.ceil(-20.3); // -20.0
        // 分页计算总页数(向上取整)
        Integer totalPage = (int) Math.ceil((double) 10 / (double) 3); // 4
        Math.min(1, 2);
        Math.max(1, 2);
        // 平方根
        double sqrt = Math.sqrt(1.1); // 1.0488088481701516

        // BigDecimal
        BigDecimal bd1 = new BigDecimal("105.5555");
        BigDecimal bd2 = new BigDecimal("-105.5555");
        // +
        BigDecimal bsAdd = bd1.add(new BigDecimal(1.2222));
        // -
        BigDecimal bdSubtract = bd1.subtract(new BigDecimal("22.22"));
        // *
        BigDecimal bsMultiply1 = bd1.multiply(new BigDecimal("100")); // 10555.5500
        // 除(保留小数)
        // - 直接除 ！除不尽会报错
        BigDecimal bdDivide1 = bd1.divide(new BigDecimal("2")); // 52.77775
        // - 除法 自定义取整规则
        BigDecimal bdDivide2 = bd1.divide(new BigDecimal("3"), RoundingMode.DOWN); // 35.1851
        // - 除法 自定义保留小数位 自定义取整规则
        BigDecimal bdDivide3 = bd1.divide(new BigDecimal("3"), 3, RoundingMode.DOWN); // 35.185

        // 保留小数
        // !当出现精度丢失 又未指定roundingMode时会报错(默认roundingMode：ROUND_UNNECESSARY)
        BigDecimal bdScale = bd1.setScale(4);
        // 进位处理
        BigDecimal bdScale00 = bd1.setScale(1, BigDecimal.ROUND_UP); // 105.6
        BigDecimal bdScale01 = bd2.setScale(1, BigDecimal.ROUND_UP); // -105.6
        // 直接删除多余的小数位
        BigDecimal bdScale10 = bd1.setScale(1, BigDecimal.ROUND_DOWN); // 105.5
        BigDecimal bdScale11 = bd2.setScale(1, BigDecimal.ROUND_DOWN); // -105.5
        // 向上取整
        BigDecimal bdScale20 = bd1.setScale(1, BigDecimal.ROUND_CEILING); // 105.6
        BigDecimal bdScale21 = bd2.setScale(1, BigDecimal.ROUND_CEILING); // -105.5
        // 向下取整
        BigDecimal bdScale30 = bd1.setScale(1, BigDecimal.ROUND_FLOOR); // 105.5
        BigDecimal bdScale31 = bd2.setScale(1, BigDecimal.ROUND_FLOOR); // -105.6
        // 四舍五入
        BigDecimal bdScale40 = bd1.setScale(1, BigDecimal.ROUND_HALF_UP); // 105.6
        BigDecimal bdScale41 = bd2.setScale(1, BigDecimal.ROUND_HALF_UP); // -105.6
        // 四舍五入
        BigDecimal bdScale50 = bd1.setScale(1, BigDecimal.ROUND_HALF_DOWN); // 105.6
        BigDecimal bdScale51 = bd2.setScale(1, BigDecimal.ROUND_HALF_DOWN); // -105.6
        // 四舍五入 如果第newScale位是奇数,则做ROUND_HALF_UP 如果第newScale位是偶数,则做ROUND_HALF_DOWN
        BigDecimal bdScale60 = bd1.setScale(1, BigDecimal.ROUND_HALF_EVEN); // 105.6
        BigDecimal bdScale61 = bd2.setScale(1, BigDecimal.ROUND_HALF_EVEN); // -105.6
    }
}
