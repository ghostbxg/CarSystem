package javabean;

public class Exam {
    private int id;
    private int takecarid;
    private String name;
    private String num;
    private String starttime;
    private String subject;
    private String grade;
    private String time;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTakecarid() {
        return takecarid;
    }

    public void setTakecarid(int takecarid) {
        this.takecarid = takecarid;
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", takecarid=" + takecarid +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", starttime='" + starttime + '\'' +
                ", subject='" + subject + '\'' +
                ", grade='" + grade + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
