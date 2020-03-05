package lambdasinaction.chap3;

import java.util.*;
import java.util.stream.Collectors;

import lambdasinaction.chap3.Lambdas.Apple;

public class MyLambdas {
    public static void main(String[] args) {
        Runnable r1 = () -> {
            System.out.println("Lambda for Runnable");
        };

        r1.run();

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

        //lambda表达式筛选绿色苹果
        List<Apple> greenApple = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApple);

        //在函数式接口上使用lambda表达式
        Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        inventory.sort(c);
        System.out.println(inventory);

        //使用Java自带的stream中的filter处理数据
        List<Apple> weightApple = inventory.stream().filter((e -> e.getWeight() > 100))
                .collect(Collectors.toList());
        System.out.println(weightApple);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}

/**
 * 包含谓词的函数式接口
 */
interface ApplePredicate{
    public boolean test(Apple a);
}