package servlet;

import javabean.Car;
import javabean.Students;
import javabean.TakeCar;
import org.springframework.dao.DataIntegrityViolationException;
import service.CarService;
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

@WebServlet("/takecar")
public class TakeCarServlet extends HttpServlet {
    TakeCarService ts = new TakeCarService();
    CarService cs = new CarService();
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

        List<TakeCar> c = ts.selectBy(req.getParameter("lpnumber"),"0");

        if(c!=null){
            int pum = 0;
            if(req.getParameter("pum")!=""&&req.getParameter("pum")!=null){
                pum=Integer.parseInt(req.getParameter("pum"));
            }
            List<TakeCar> l = new ArrayList<>();
            List<Car> carlist =cs.select();
            List<Students> studentlist =ss.select();
            List<Students> studentsList1=new ArrayList<>();
            for(Students s:studentlist){
                if(s.getSend().equals("0")){
                    studentsList1.add(s);
                }
            }
            if(c!=null&&c.size()!=0){

                for(int i=pum*3;i<(pum+1)*3;i++) {
                    if (i < c.size()) {
                        l.add(c.get(i));
                    } else {
                        break;
                    }
                }
                req.setAttribute("takecarlist",l);
                req.setAttribute("lpnumber",l.get(0).getCarid());
                req.setAttribute("selectby",true);
                req.setAttribute("pum",pum);
                req.setAttribute("sum",((c.size()-1)/3+1));

            }
            req.setAttribute("studentslist",studentsList1);
            req.setAttribute("carlist",carlist);
            req.getRequestDispatcher("takecar_list.jsp").forward(req,resp);
        }else{
            resp.getWriter().print("<script> alert('没有该车信息！');location.href='takecar_list.jsp'</script>");
        }
    }

    public void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pum = 0;
        if(req.getParameter("pum")!=""&&req.getParameter("pum")!=null){
            pum=Integer.parseInt(req.getParameter("pum"));
        }
        List<TakeCar> list =ts.select();
        List<TakeCar> l = new ArrayList<>();
        List<Car> carlist =cs.select();
        List<Students> studentlist =ss.select();
        List<Students> studentsList1=new ArrayList<>();
        for(Students s:studentlist){
            if(s.getSend().equals("0")){
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
            req.setAttribute("takecarlist",l);
            req.setAttribute("pum",pum);
            req.setAttribute("sum",((list.size()-1)/3+1));

        }else{
            req.setAttribute("flag",true);
        }
        req.setAttribute("studentslist",studentsList1);
        req.setAttribute("carlist",carlist);
        req.getRequestDispatcher("takecar_list.jsp").forward(req,resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(ts.delete(Integer.parseInt(req.getParameter("id")))){
                resp.getWriter().print("<script> alert('已删除！');location.href='takecar?dos=select'</script>");
            }else{
                resp.getWriter().print("<script> alert('删除失败！');location.href='takecar?dos=select'</script>");
            }
        }catch (DataIntegrityViolationException e){
            resp.getWriter().print("<script> alert('删除失败！该学员已出考试成绩，如要删除，请删除其考试信息！');location.href='takecar?dos=select'</script>");
        }

    }

    public  void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TakeCar c = new TakeCar();
        c.setStuid(Integer.parseInt(req.getParameter("stuid")));
        List<Students> studentlist =ss.select();
        for(Students s:studentlist){
            if(s.getId()==c.getStuid()){
                s.setSend("1");
                ss.update(s);
            }
        }
        c.setCarid(Integer.parseInt(req.getParameter("carid")));
        c.setType(req.getParameter("type"));
        c.setStarttime(req.getParameter("starttime"));
        c.setEndtime(req.getParameter("endtime"));
        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(ts.insert(c)){
            resp.getWriter().print("<script> alert('添加成功！');location.href='takecar?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('添加失败！');location.href='takecar?dos=select'</script>");
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TakeCar c = new TakeCar();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setStuid(Integer.parseInt(req.getParameter("stuid")));
        c.setCarid(Integer.parseInt(req.getParameter("carid")));
        c.setType(req.getParameter("type"));
        c.setStarttime(req.getParameter("starttime"));
        c.setEndtime(req.getParameter("endtime"));
        c.setTime(req.getParameter("time"));
        if(ts.update(c)){
            resp.getWriter().print("<script> alert('修改成功！');location.href='takecar?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('修改失败！');location.href='takecar?dos=select'</script>");
        }
    }
}
