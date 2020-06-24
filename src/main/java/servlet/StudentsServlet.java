package servlet;


import javabean.Students;
import org.springframework.dao.DataIntegrityViolationException;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

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

        List<Students> a= new ArrayList<>();
        Students s = ss.selectBy(req.getParameter("search"),req.getParameter("search"));
        a.add(s);
        if(a.size()!=0){
            req.setAttribute("studentslist",a);
            req.getRequestDispatcher("students_list.jsp").forward(req,resp);
        }else{
            resp.getWriter().print("<script> alert('没有该学员！');location.href='students?dos=select'</script>");
        }
    }

    public void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pum = 0;
        if(req.getParameter("pum")!=""&&req.getParameter("pum")!=null){
            pum=Integer.parseInt(req.getParameter("pum"));
        }
        List<Students> list =ss.select();
        List<Students> l = new ArrayList<>();
        if(list!=null&&list.size()!=0){

            for(int i=pum*3;i<(pum+1)*3;i++){
                if(i<list.size()){
                    l.add(list.get(i));
                }else{
                    break;
                }
            }
            req.setAttribute("studentslist",l);
            req.setAttribute("pum",pum);
            req.setAttribute("sum",((list.size()-1)/3+1));

        }else{
            req.setAttribute("flag",true);
        }
        req.getRequestDispatcher("students_list.jsp").forward(req,resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            if(ss.delete(Integer.parseInt(req.getParameter("id")))){
                resp.getWriter().print("<script> alert('已删除！');location.href='students?dos=select'</script>");
            }else{
                resp.getWriter().print("<script> alert('删除失败！');location.href='students?dos=select'</script>");
            }
        }catch (DataIntegrityViolationException e){
            resp.getWriter().print("<script> alert('删除失败！该学员已约车，如要删除，请将其约车信息、考试信息、缴费信息删除！');location.href='students?dos=select'</script>");
        }


    }

   public  void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Students c = new Students();
        c.setName(req.getParameter("name"));
        c.setNum(req.getParameter("num"));
        c.setSex(req.getParameter("sex"));
       c.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        c.setIdnumber(req.getParameter("idnumber"));
        c.setTel(req.getParameter("tel"));
        c.setType(req.getParameter("type"));
       Part part = req.getPart("photo");
       String basepath = req.getServletContext().getRealPath("upload");
       String filename=UUID.randomUUID()+".jpg";
       part.write(basepath+"/"+filename);
       c.setPhoto("upload/"+filename);
        if(ss.insert(c)){
            resp.getWriter().print("<script> alert('添加成功！');location.href='students?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('添加失败！');location.href='students?dos=select'</script>");
        }
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Students c = new Students();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setName(req.getParameter("name"));
        c.setNum(req.getParameter("num"));
        c.setSex(req.getParameter("sex"));
        c.setTime(req.getParameter("time"));
        c.setIdnumber(req.getParameter("idnumber"));
        c.setTel(req.getParameter("tel"));
        c.setType(req.getParameter("type"));

        Part part = req.getPart("photo");
        if(part.getSize()!=0){
            String basepath = req.getServletContext().getRealPath("upload");
            String filename=UUID.randomUUID()+".jpg";
            part.write(basepath+"/"+filename);
            c.setPhoto("upload/"+filename);
        }else{
            c.setPhoto(req.getParameter("photo2"));
        }
        c.setSend(req.getParameter("send"));

        if(ss.update(c)){
            resp.getWriter().print("<script> alert('修改成功！');location.href='students?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('修改失败！');location.href='students?dos=select'</script>");
        }
    }
}
