package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 使用JDK中自带的函数式接口
 */
public class MyFunction {
    public static void main(String[] args) {
        //1. Predicate: 需要一个涉及类型T的布尔表达式时可以使用
        List<String> list = Arrays.asList("asc", "", "lxy");
        List<String> res = filter(list, (String s) -> !s.isEmpty());
        System.out.println(res);

        //2. Consumer: 提供一个没有参数和返回值的accept方法,适用于对列表中的元素执行一些操作
        forEach(list, (String i) -> System.out.println(i));

        //3. Function: 可以实现一个map,如把字符串列表转换为其长度列表
        List<Integer> lens = map(list, (String s) -> s.length());
        System.out.println(lens);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T s: list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i: list) {
            c.accept(i);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s: list) {
            result.add(f.apply(s));
        }
        return result;
    }
}
