package com.levin.commons.service.support;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 进制互转工具类
 *
 * @author
 */
public class DecimalConvertor {

    private final char[] charArray;

    public DecimalConvertor(char... charArray) {
        this.charArray = charArray;
    }

    public DecimalConvertor(char start, char to) {

        if (start > to) {
            char temp = start;
            start = to;
            to = temp;
        }

        //0-9
        this.charArray = new char[to - start + 1];

        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = start++;
        }

    }

    /**
     * 默认 62进制
     *
     * @param hasLower
     * @param hasUpper
     * @param hasNum
     * @return
     */
    public static DecimalConvertor default62(boolean hasLower, boolean hasUpper, boolean hasNum, char... excludes) {

        List<Character> characterList = new ArrayList<>(62);

        if (hasLower) {
            IntStream.range('a', 'z' + 1).forEach((c) -> characterList.add((char) c));
        }

        if (hasUpper) {
            IntStream.range('A', 'Z' + 1).forEach((c) -> characterList.add((char) c));
        }

        if (hasNum) {
            IntStream.range('0', '9' + 1).forEach((c) -> characterList.add((char) c));
        }

        //排除部分
        if (excludes != null) {
            for (char exclude : excludes) {
                characterList.remove((Character) exclude);
            }
        }

        char[] chars = new char[characterList.size()];

        int idx = 0;

        for (Character c : characterList) {
            chars[idx++] = c;
        }

        return new DecimalConvertor(chars);
    }

    private int indexOf(char c) {

        int idx = 0;

        for (char c1 : charArray) {
            if (c1 == c) {
                return idx;
            }
            idx++;
        }

        return -1;
    }

    /**
     * @return
     * @version 1.1
     * @Description N进制转10进制<br />
     * null非法字符转换
     */
    public BigInteger toDecimal(String str) {

        while (str.contains(" "))
            str = str.replace(" ", "");

//        FF
//        15 * 16 + 15

        BigInteger num = BigInteger.valueOf(0);

        int length = str.length();

        for (char c : str.toCharArray()) {

            int index = indexOf(c);

            //如果不是正确的数值
            if (index < 0)
                throw new NumberFormatException("not expect " + c);

            num = num.add(
                    BigInteger.valueOf(index).multiply(pow(--length))
            );
        }

        return num;
    }


    private BigInteger pow(int n) {

        if (n < 0) {
            throw new UnsupportedOperationException("pow " + n);
        }

        if (n == 0) {
            return BigInteger.ONE;
        }

        //16
        BigInteger base = BigInteger.valueOf(charArray.length);

        BigInteger result = BigInteger.valueOf(1);

        while (n-- > 0) {
            result = result.multiply(base);
        }

        return result;
    }

    /**
     * @return
     * @version 1.0.0
     * @Description 10进制转N进制
     */
    public String toScale(String decimal) {
        return toScale(new BigInteger(decimal));
    }

    /**
     * @return
     * @version 1.0.0
     * @Description 10进制转N进制
     */
    public String toScale(BigInteger num) {

        StringBuilder sb = new StringBuilder();

        BigInteger size = BigInteger.valueOf(charArray.length);

        while ((num.compareTo(BigInteger.ZERO) > 0)) {

            BigInteger[] divideAndRemainder = num.divideAndRemainder(size);

            sb.append(charArray[divideAndRemainder[1].intValue()]);

            num = divideAndRemainder[0];
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        DecimalConvertor convertor = default62(true, true, true);

        String decimal = "1234567890987654321";

        String scale = convertor.toScale(decimal);

        String d2 = convertor.toDecimal(scale).toString();

        System.out.println(decimal + " --> " + scale + " --> " + d2);

        assert d2.contentEquals(decimal);


        ////////////////////////////////////////////////////////////////////

        convertor = new DecimalConvertor('9', '2');

        scale = convertor.toScale(decimal);

        convertor.toDecimal(scale).toString();

        System.out.println(decimal + " --> " + scale + " --> " + d2);

        assert d2.contentEquals(decimal);

    }

}
