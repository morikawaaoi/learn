import user.User;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Description: 1
 * @author: Xu Yuwen
 * @Date: 2022-05-19 10:13
 */
public class text {
    public static void main(String[] args) {

        User user = new User(1, "xyw", 18, "1", "立春");

        User user1 = new User(2, "lmt", 100, "0", "夏至");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        /*String str = userList.stream().map(User::getName).collect(Collectors.joining("baoda"));
        System.out.println(str);

        //boolean checkAge = userList.stream().allMatch(userx -> userx.getAge() > 18);
        List<String> a =    userList.stream().filter(us -> us.getAge() > 18 ).map(User::getName).collect(Collectors.toList());
        for(String ab : a){
            System.out.println("想想你为什么会被输出出来："+ab);
        }*/


        //List<Integer> iList = Arrays.asList(1,3,4,5,76,8,9);

       /* Optional<Integer> sumNum = userList.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(sumNum.get());

        Optional<Integer> maxNum = userList.stream().map(User::getAge).reduce(Integer::max);
        Integer maxInteNum = userList.stream().reduce(0,(max,ages) -> max > ages.getAge()?max:ages.getAge(),Integer::max);

        System.out.println(maxNum.get());
        System.out.println(maxInteNum);*/


        /*Integer[] arr = {1,3,6,8,10};
        List<Integer> intList = Arrays.asList(arr);
        //最大值
        Optional<Integer> maxInt = intList.stream().max((x,y) -> {
            return x-y;
        });
        //最小值
        Optional<Integer> minInt = intList.stream().min((x,y) -> {
            return x-y;
        });
        //也可以反过来使用
        //最大值
        Optional<Integer> maxIntOther = intList.stream().min((x,y) -> {
            return y-x;
        });
        //最小值
        Optional<Integer> minIntOther = intList.stream().max((x,y) -> {
            return y-x;
        });
        System.out.println(maxInt.get());
        System.out.println(minInt.get());
        System.out.println(maxIntOther.get());
        System.out.println(minIntOther.get());

        List<String> strList = Arrays.asList("jimoshazhouleng","dongtiandemimi","yuxitan","wodeyigedaogupengyou");
        Optional<String> strOptMax = strList.stream().max(Comparator.comparing(String::length));
        System.out.println(strOptMax.get());
        Optional<String> strOptMin = strList.stream().min(Comparator.comparing(String::length));
        System.out.println(strOptMin.get());*/

        List<User> userInfoList = new ArrayList<>();
        userInfoList.add(new User(10, "xyw", 20, "1", "立秋"));
        userInfoList.add(new User(10, "lmt", 30, "1", "冬至"));

        /*Optional<User> strOptUserMax = userInfoList.stream().max(Comparator.comparing(User::getAge));
        System.out.println(strOptUserMax.get().getAge());
        //计算年龄大于17的数量
        long count = userInfoList.stream().filter(x -> x.getAge() > 17).count();
        System.out.println(count);

        //reduce计算合计数
        List<Integer> rList = Arrays.asList(1,2,3,4,7,8,9);
        //1+rList总和
        Integer intReduce = rList.stream().reduce(1,Integer::sum);
        //另一种写法
        Integer intReduce2 = rList.stream().reduce(1,(x,y) -> x+y);
        System.out.println(intReduce);
        System.out.println(intReduce2);

        //如果不用初始值进行相加时  返回值为optional
        Optional<Integer> intOpt = rList.stream().reduce(Integer::sum);
        System.out.println(intOpt.get());
        //另一种写法
        Optional<Integer> intOpt1 = rList.stream().reduce((x,y) -> x+y);
        System.out.println(intOpt1.get());

        //使用reduce求最值
        Optional<Integer> intOptMax = rList.stream().reduce(Integer::max);
        System.out.println(intOptMax.get());

        //对象使用reduce计算最值
        Optional<Integer> strOptReduceMax = userInfoList.stream().map(x -> x.getAge()).reduce(Integer::max);
        System.out.println(strOptReduceMax.get());

        //求集合中年龄最大的对象
        Optional<User> strOptReduceMaxBody = userInfoList.stream().reduce((x,y) -> x.getAge() > y.getAge()?x:y);
        System.out.println(strOptReduceMaxBody.get().getName() + ":" + strOptReduceMaxBody.get().getAge());*/


        userInfoList.add(new User(13, "xsb", 28, "1", "芒种"));
        userInfoList.add(new User(14, "yyd", 25, "0", "大雨"));

        //使用collect计算数量
        /*Long ccount = userInfoList.stream().collect(Collectors.counting());
        System.out.println(ccount);

        //计算平均值
        Double avgCol = userInfoList.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println(avgCol);

        //计算最大值
        Optional<Integer> cOptMax = userInfoList.stream().map(User::getAge).collect(Collectors.maxBy(Integer::compare));
        System.out.println(cOptMax.get());

        //计算合计值
        Integer sumCol = userInfoList.stream().collect(Collectors.summingInt(User::getAge));
        System.out.println(sumCol);

        //统计
        DoubleSummaryStatistics summaryCol = userInfoList.stream().collect(Collectors.summarizingDouble(User::getAge));
        System.out.println(summaryCol);*/

        userInfoList.add(new User(16, "zzd", 23, "0", "小雨"));
        userInfoList.add(new User(17, "zjl", 24, "0", "惊蛰"));

        /*//分区 按年龄20分区 满足条件的情况为true 不满足的为false;
        Map<Boolean,List<User>> pbAge = userInfoList.stream().collect(Collectors.partitioningBy(x-> x.getAge() > 20));
        System.out.println("以年龄20为分界线进行分组："+pbAge);
        System.out.println("年龄小于20为："+pbAge.get(false).get(0).getName());

        //分组 以性别进行分组
        Map<String,List<User>> gbSex = userInfoList.stream().collect(Collectors.groupingBy(User::getSex));
        System.out.println(gbSex);

        //分组 先以年龄分组 后以性别分组
        Map<Integer,Map<String,List<User>>> doubleGroupByAgeSex = userInfoList.stream().collect(Collectors.groupingBy(User::getAge,Collectors.groupingBy(User::getSex)));
        System.out.println(doubleGroupByAgeSex);*/

        //排序 按年龄进行排序
        /*List<String> nameListByAge = userInfoList.stream().sorted(Comparator.comparing(User::getAge)).map(User::getName).collect(Collectors.toList());
        System.out.println(nameListByAge);

        //按年龄倒叙
        List<String> nameListByAgeReverse = userInfoList.stream().sorted(Comparator.comparing(User::getAge).reversed()).map(User::getName).collect(Collectors.toList());
        System.out.println(nameListByAgeReverse);

        //排序先按年龄排序 如果年龄相等则再按照id排序
        List<String> nameListByAgeId = userInfoList.stream().sorted(Comparator.comparing(User::getAge).thenComparing(User::getId)).map(User::getName).collect(Collectors.toList());
        System.out.println(nameListByAgeId);

        //排序先按年龄排序 如果年龄相等则再按照id降序
        List<String> nameListByAgeIdReverse = userInfoList.stream().sorted(Comparator.comparing(User::getAge).thenComparing(User::getId).reversed()).map(User::getName).collect(Collectors.toList());
        System.out.println(nameListByAgeIdReverse);

        //自定义排序方式
        List<String> newList = userInfoList.stream().sorted((x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getId() - y.getId();
            } else {
                return x.getAge() - y.getAge();
            }
        }).map(User::getName).collect(Collectors.toList());
        System.out.println(newList);

        //按首字母进行排序
        List<String> tempList = new ArrayList<>();
        for (User usera : userInfoList) {
            String temp = usera.getName();
            tempList.add(temp);
        }
        List<String> szmList = tempList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println(szmList);*/
        //Optional输出年龄平均值
        /*Optional.ofNullable(userInfoList.stream().collect(Collectors.averagingInt(User::getAge))).ifPresent(System.out::println);
        //以指定字符串输出年龄平均值
        Optional.ofNullable(userInfoList.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(User::getAge), x ->"这是最后的平均成绩："+x))).ifPresent(System.out::println);
        //计算数量
        Optional.ofNullable(userInfoList.stream().collect(Collectors.counting())).ifPresent(System.out::println);
        //根据性别进行分组
        Optional.ofNullable(userInfoList.stream().collect(Collectors.groupingBy(User::getSex))).ifPresent(System.out::println);
        //统计各个性别人数
        Optional.ofNullable(userInfoList.stream().collect(Collectors.groupingBy(User::getSex,Collectors.counting()))).ifPresent(System.out::println);
        Map<String, Long> personNum = Optional.ofNullable(userInfoList.stream().collect(Collectors.groupingBy(User::getSex,Collectors.counting()))).get();
        System.out.println(personNum);

        //统计各个性别的平均年龄，并有序输出
        Optional.ofNullable(userInfoList.stream().collect(
            Collectors.groupingBy(
                User::getSex,
                TreeMap::new,
                Collectors.averagingInt(User::getAge)))).ifPresent(System.out::println);*/

        //将名字拼接
        /*Optional.ofNullable(userInfoList.stream().map(User::getName).collect(Collectors.joining())).ifPresent(System.out::println);
        //将名字用，拼接
        Optional.ofNullable(userInfoList.stream().map(User::getName).collect(Collectors.joining(","))).ifPresent(System.out::println);
        //将名字用，拼接并添加前后缀
        Optional.ofNullable(userInfoList.stream().map(User::getName).collect(Collectors.joining(",","所有学生姓名：[","]"))).ifPresent(System.out::println);*/

        //根据性别是否为0进行分区
        /*Optional.ofNullable(userInfoList.stream().collect(Collectors.partitioningBy(x -> x.getSex().equals("0")))).ifPresent(System.out::println);
        //根据性别进行分区并计算其平均年龄
        Optional.ofNullable(userInfoList.stream().collect(Collectors.partitioningBy(x -> x.getSex().equals("0"),Collectors.averagingInt(User::getAge)))).ifPresent(System.out::println);
        //获取年龄最大的人信息
        Optional.ofNullable(userInfoList.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(User::getAge))))).ifPresent(System.out::println);
        //统计所有学生的总年龄
        Optional.ofNullable(userInfoList.stream().map(User::getAge).collect(Collectors.reducing(0,(x,y) -> x+y))).ifPresent(System.out::println);
        //统计所有学生的总年龄
        Optional.ofNullable(userInfoList.stream().collect(Collectors.reducing(0,User::getAge,(x,y)->x+y))).ifPresent(System.out::println);
        Integer allOther = userInfoList.stream().map(User::getAge).reduce(0,(x,y)->x+y);
        System.out.println(allOther);

        //统计所有人的年龄
        DoubleSummaryStatistics da = userInfoList.stream().collect(Collectors.summarizingDouble(User::getAge));
        Optional.ofNullable(da).ifPresent(System.out::println);
        //id之和
        Optional.ofNullable(userInfoList.stream().collect(Collectors.summingDouble(User::getId))).ifPresent(System.out::println);*/

        //统计年龄大于21的人放在linkedList中
        Optional.ofNullable(userInfoList.stream().filter(x -> x.getAge() > 21).collect(Collectors.toCollection(LinkedList::new))).ifPresent(System.out::println);
        //以学生姓名为键 总分为值 统计信息
        Optional.ofNullable(userInfoList.stream().collect(Collectors.toMap(User::getName,User::getAge))).ifPresent(System.out::println);

        Optional.ofNullable(userInfoList.stream().collect(Collectors.toConcurrentMap(User::getAge,User::getName,(s,a)->s+":"+a))).ifPresent(System.out::println);
    }
}
