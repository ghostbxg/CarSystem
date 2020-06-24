package servlet;

import javabean.Coach;
import service.CoachService;

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

@WebServlet("/coach")
public class CoachServlet extends HttpServlet {
    CoachService cs = new CoachService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String dos = req.getParameter("dos");
        if(dos.equals("logn")){
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


        Coach c = cs.selectBy(req.getParameter("name"),req.getParameter("password"));
        if(c!=null){
            req.getSession().setAttribute("coach",c);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else{
            resp.getWriter().print("<script> alert('密码或用户名错误！请确认后重新输入！');location.href='logn.html'</script>");
        }
    }

    public void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pum = 0;
        if(req.getParameter("pum")!=""&&req.getParameter("pum")!=null){
            pum=Integer.parseInt(req.getParameter("pum"));
        }
        List<Coach> list =cs.select();
        List<Coach> l = new ArrayList<>();
        if(list!=null&&list.size()!=0){

            for(int i=pum*3;i<(pum+1)*3;i++){
                if(i<list.size()){
                    l.add(list.get(i));
                }else{
                    break;
                }
            }
            req.setAttribute("coachlist",l);
            req.setAttribute("pum",pum);
            req.setAttribute("sum",((list.size()-1)/3+1));

        }else{
            req.setAttribute("flag",true);
        }
        req.getRequestDispatcher("coach_list.jsp").forward(req,resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if(cs.delete(Integer.parseInt(req.getParameter("id")))){
                resp.getWriter().print("<script> alert('已删除！');location.href='coach?dos=select'</script>");
            }else{
                resp.getWriter().print("<script> alert('删除失败！');location.href='coach?dos=select'</script>");
            }
    }

    public  void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Coach c = new Coach();
        c.setName(req.getParameter("name"));
        c.setPassword(req.getParameter("password"));
        c.setNum(req.getParameter("number"));
        c.setIdnumber(req.getParameter("idnumber"));
        c.setType(req.getParameter("type"));
        c.setTel(req.getParameter("tel"));
        c.setSalary(req.getParameter("salary"));
        c.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if(cs.insert(c)){
            resp.getWriter().print("<script> alert('添加成功！');location.href='coach?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('添加失败！');location.href='coach?dos=select'</script>");
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Coach c = new Coach();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setName(req.getParameter("name"));
        c.setPassword(req.getParameter("password"));
        c.setNum(req.getParameter("number"));
        c.setIdnumber(req.getParameter("idnumber"));
        c.setType(req.getParameter("type"));
        c.setTel(req.getParameter("tel"));
        c.setSalary(req.getParameter("salary"));
        c.setTime(req.getParameter("time"));

        if(cs.update(c)){
            resp.getWriter().print("<script> alert('修改成功！');location.href='coach?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('修改失败！');location.href='coach?dos=select'</script>");
        }
    }

}
