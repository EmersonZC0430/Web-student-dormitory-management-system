package wlj.servlet.servlet;

import wlj.servlet.pojo.User;
import wlj.servlet.service.StuService;
import wlj.servlet.service.StuServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet",urlPatterns = {"/up"})
public class UpdateServlet extends HttpServlet {
    StuService stuService = new StuServiceImp();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String value = req.getParameter("value");
        String name = ((User)req.getSession().getAttribute("user")).getUname();
        String per = req.getParameter("per");
        if(per.equals("ser")){
            upSer(req,resp);
        }
        int index = stuService.updateTime(name,value);
        if(index>0){
            System.out.println("修改成功");
            resp.getWriter().write(value);
        }
    }

    private void upSer(HttpServletRequest req, HttpServletResponse resp) {
        String dno = req.getParameter("dno");
        String pno = req.getParameter("pno");
        String rsolve = req.getParameter("rsolve");
        int index = stuService.updateSer(dno,pno,rsolve);
        if(index>0){
            try {
                resp.sendRedirect("adminSer.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
