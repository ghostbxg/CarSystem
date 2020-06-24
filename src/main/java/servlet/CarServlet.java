package servlet;

import javabean.Car;
import org.springframework.dao.DataIntegrityViolationException;
import service.CarService;

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

@WebServlet("/car")
public class CarServlet extends HttpServlet {

    CarService cs = new CarService();
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


        List<Car> c = cs.selectBy(req.getParameter("lpnumber"),null);

        if(c!=null){
            req.setAttribute("carlist",c);
            req.getRequestDispatcher("car_list.jsp").forward(req,resp);
        }else{
            resp.getWriter().print("<script> alert('没有该车信息！');location.href='car_list.jsp'</script>");
        }
    }

    public void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pum = 0;
        if(req.getParameter("pum")!=""&&req.getParameter("pum")!=null){
            pum=Integer.parseInt(req.getParameter("pum"));
        }
        List<Car> list =cs.select();
        List<Car> l = new ArrayList<>();
        if(list!=null&&list.size()!=0){

            for(int i=pum*3;i<(pum+1)*3;i++){
                if(i<list.size()){
                    l.add(list.get(i));
                }else{
                    break;
                }
            }
            req.setAttribute("carlist",l);
            req.setAttribute("pum",pum);
            req.setAttribute("sum",((list.size()-1)/3+1));

        }else{
            req.setAttribute("flag",true);
        }
        req.getRequestDispatcher("car_list.jsp").forward(req,resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(cs.delete(Integer.parseInt(req.getParameter("id")))){
                resp.getWriter().print("<script> alert('已删除！');location.href='car?dos=select'</script>");
            }else{
                resp.getWriter().print("<script> alert('删除失败！');location.href='car?dos=select'</script>");
            }
        }catch (DataIntegrityViolationException e){
            resp.getWriter().print("<script> alert('删除失败！该车已被学员约车，如要删除，请其约车信息删除！');location.href='car?dos=select'</script>");
        }

    }

    public  void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car c = new Car();
        c.setLpnumber(req.getParameter("lpnumber"));
        c.setMtype(req.getParameter("mtype"));
        c.setBrand(req.getParameter("brand"));
        c.setEnumber(req.getParameter("enumber"));
        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(cs.insert(c)){
            resp.getWriter().print("<script> alert('添加成功！');location.href='car?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('添加失败！');location.href='car?dos=select'</script>");
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car c = new Car();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setLpnumber(req.getParameter("lpnumber"));
        c.setMtype(req.getParameter("mtype"));
        c.setBrand(req.getParameter("brand"));
        c.setEnumber(req.getParameter("enumber"));
        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        if(cs.update(c)){
            resp.getWriter().print("<script> alert('修改成功！');location.href='car?dos=select'</script>");
        }else{
            resp.getWriter().print("<script> alert('修改失败！');location.href='car?dos=select'</script>");
        }
    }
}
