package user;

/**
 * @Description: user
 * @author: Xu Yuwen
 * @Date: 2022-05-27 13:52
 */
public class User {

    private int id;
    private String name;
    private int age;
    private String sex;
    private String remark;

    public User(int id, String name, int age, String sex ,String remark) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.remark = remark;
    }

    @Override
    public String toString(){
        return "info{" +
                "id="+id+",name="+name+",age="+age+",sex="+sex+"}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
