package wlj.servlet.pojo;

public class Test {
    private int id;
    private int num;

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Test() {
    }

    public Test(int id, int num) {
        this.id = id;
        this.num = num;
    }
}
