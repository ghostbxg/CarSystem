package javabean;

public class Students {
    private int id;
    private String name;
    private String num;
    private String sex;
    private String time;
    private String idnumber;
    private String tel;
    private String type;
    private String photo;
    private String send;
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


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", sex='" + sex + '\'' +
                ", time='" + time + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", tel='" + tel + '\'' +
                ", type='" + type + '\'' +
                ", photo='" + photo + '\'' +
                ", send='" + send + '\'' +
                '}';
    }
}
