package javabean;

public class Car {
    private int id;
    private String lpnumber;
    private String mtype;
    private String brand;
    private String enumber;
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

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEnumber() {
        return enumber;
    }

    public void setEnumber(String enumber) {
        this.enumber = enumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", lpnumber='" + lpnumber + '\'' +
                ", mtype='" + mtype + '\'' +
                ", brand='" + brand + '\'' +
                ", enumber='" + enumber + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
