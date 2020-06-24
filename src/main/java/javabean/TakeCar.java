package javabean;

public class TakeCar {
    private int id;
    private String name;
    private  int stuid;
    private int carid;
    private  String num;
    private String lpnumber;
    private String type;
    private String starttime;
    private String endtime;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLpnumber() {
        return lpnumber;
    }

    public void setLpnumber(String lpnumber) {
        this.lpnumber = lpnumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    @Override
    public String toString() {
        return "TakeCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stuid=" + stuid +
                ", carid=" + carid +
                ", num='" + num + '\'' +
                ", lpnumber='" + lpnumber + '\'' +
                ", type='" + type + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
