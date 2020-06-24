package javabean;

public class Pay {
    private int id;
    private int stuid;
    private String name;
    private String num;
    private String type;
    private String money;
    private String memoney;
    private String summoney;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMemoney() {
        return memoney;
    }

    public void setMemoney(String memoney) {
        this.memoney = memoney;
    }

    public String getSummoney() {
        return summoney;
    }

    public void setSummoney(String summoney) {
        this.summoney = summoney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", stuid=" + stuid +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", type='" + type + '\'' +
                ", money='" + money + '\'' +
                ", memoney='" + memoney + '\'' +
                ", summoney='" + summoney + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
