package servlet;

import javabean.Exam;
import javabean.Students;
import javabean.TakeCar;
import org.springframework.dao.DataIntegrityViolationException;
import service.ExamService;
import service.StudentService;
import service.TakeCarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/exam")
public class ExamServlet extends HttpServlet {
    ExamService es = new ExamService();
    TakeCarService ts = new TakeCarService();
    StudentService ss = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String dos = req.getParameter("dos");
        if(dos.equals("selectby")){
            selectBy(req, resp);
        }else if(dos.equals("select")){
            select(req,resp);
        }else if(dos.equals("delete")){
            delete(req, resp);
        }else if(dos.equals("insert")){
            insert(req, resp);
        }else if(dos.equals("update")){
            update(req, resp);
        }
    }

    public void selectBy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Exam> c = es.selectBy(req.getParameter("name"),null);

        if(c!=null){
            int pum = 0;
            if(req.getParameter("pum")!=""&&req.getParameter("pum")!=null){
                pum=Integer.parseInt(req.getParameter("pum"));
            }
            List<Exam> l = new ArrayList<>();
            List<Students> studentlist =ss.select();
            List<Students> studentsList1=new ArrayList<>();
            for(Students s:studentlist){
                if(s.getSend().equals("2")){
                    studentsList1.add(s);
                }
            }
            if(c!=null&&c.size()!=0){

                for(int i=pum*3;i<(pum+1)*3;i++){
                    if(i<c.size()){
                        l.add(c.get(i));
                    }else{
                        break;
                    }
                }
                req.setAttribute("examlist",l);
                req.setAttribute("pum",pum);
                req.setAttribute("sum",((c.size()-1)/3+1));

            }
            req.setAttribute("studentslist",studentsList1);
            req.getRequestDispatcher("exam_list.jsp").forward(req,resp);
        }else{
            resp.getWriter().print("<script> alert('没有该车信息！');location.href='exam_list.jsp'</script>");
        }
    }

    public void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pum = 0;
        if(req.getParameter("pum")!=""&&req.getParameter("pum")!=null){
            pum=Integer.parseInt(req.getParameter("pum"));
        }
        List<Exam> list =es.select();
        List<Exam> l = new ArrayList<>();
        List<Students> studentlist =ss.select();
        List<Students> studentsList1=new ArrayList<>();
        for(Students s:studentlist){
            if(s.getSend().equals("2")){
                studentsList1.add(s);
            }
        }
        if(list!=null&&list.size()!=0){

            for(int i=pum*3;i<(pum+1)*3;i++){
                if(i<list.size()){
                    l.add(list.get(i));
                }else{
                    break;
                }
            }
            req.setAttribute("examlist",l);
            req.setAttribute("pum",pum);
            req.setAttribute("sum",((list.size()-1)/3+1));

        }else{
            req.setAttribute("flag",true);
        }
        req.setAttribute("studentslist",studentsList1);
        req.getRequestDispatcher("exam_list.jsp").forward(req,resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(es.delete(Integer.parseInt(req.getParameter("id")))){
                resp.getWriter().print("<script> alert('已删除！');location.href='exam?dos=select'</script>");
            }else{
                resp.getWriter().print("<script> alert('删除失败！');location.href='exam?dos=select'</script>");
            }
        }catch (DataIntegrityViolationException e){
            resp.getWriter().print("<script> alert('删除失败！该学员已出考试成绩，如要删除，请删除其考试信息！');location.href='exam?dos=select'</script>");
        }

    }

    public  void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Exam c = new Exam();
        int studentid = Integer.parseInt(req.getParameter("stuid"));
        List<Students> studentlist =ss.select();
        for(Students s:studentlist){
            if(s.getId()==studentid){
                s.setSend("3");
                ss.update(s);
            }
        }
        List<TakeCar> t = ts.selectBy(null,studentid+"");

        c.setTakecarid(t.get(0).getId());
        c.setSubject(req.getParameter("subject"));
        c.setGrade(req.getParameter("grade"));
        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        if(es.insert(c)){
            resp.getWriter().print("<script> alert('添加成功！');location.href='exam?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('添加失败！');location.href='exam?dos=select'</script>");
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Exam c = new Exam();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setTakecarid(Integer.parseInt(req.getParameter("takecarid")));
        c.setSubject(req.getParameter("subject"));
        c.setGrade(req.getParameter("grade"));
        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(es.update(c)){
            resp.getWriter().print("<script> alert('修改成功！');location.href='exam?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('修改失败！');location.href='exam?dos=select'</script>");
        }
    }
}
