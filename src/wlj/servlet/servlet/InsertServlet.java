package wlj.servlet.servlet;

import wlj.servlet.pojo.LR;
import wlj.servlet.pojo.Room;
import wlj.servlet.service.StuService;
import wlj.servlet.service.StuServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InsertServlet",urlPatterns = {"/in"})
public class InsertServlet extends HttpServlet {
    StuService stuService = new StuServiceImp();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String per = req.getParameter("per");
        if(per.equals("lr")){
            lr(req,resp);
        } else if(per.equals("service")){
            ser(req,resp);
        } else if(per.equals("rec")){
            rec(req,resp);
        } else if(per.equals("exp")){
            exp(req,resp);
        }
    }

    private void exp(HttpServletRequest req, HttpServletResponse resp) {
        String sname = req.getParameter("sname");
        String dno = req.getParameter("dno");
        String marrive = req.getParameter("marrive");
        String mnumber = req.getParameter("mnumber");

        int index = stuService.insertExp(sname,dno,marrive,mnumber);

        if(index>0){
            System.out.println("插入成功");
            try {
                resp.sendRedirect("s?per=express");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void rec(HttpServletRequest req, HttpServletResponse resp) {
        String sno = req.getParameter("sno");
        String dno = req.getParameter("dno");
        String btime = req.getParameter("btime");
        String breason = req.getParameter("breason");

        int index = stuService.insertREC(sno,dno,btime,breason);
        if(index>0){
            System.out.println("添加成功");
            try {
                resp.sendRedirect("s?per=rec");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void ser(HttpServletRequest req, HttpServletResponse resp) {
        String dno = ((Room)req.getSession().getAttribute("room")).getDno();
        int pno = Integer.parseInt(req.getParameter("pno"));
        String rsubmit = req.getParameter("rsubmit");
        String reason = req.getParameter("reason");

        int index = stuService.insertThing(dno,pno,rsubmit,reason);
        if(index >0){
            System.out.println("添加成功");
            try {
                resp.sendRedirect("s?per=service");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void lr(HttpServletRequest req, HttpServletResponse resp) {
        String sno = ((Room)req.getSession().getAttribute("room")).getSon();
        String dno = req.getParameter("dno");
        String Ltime = req.getParameter("Ltime");
        String Lreturn = req.getParameter("Lreturn");
        int index = stuService.insertLR(sno,dno,Ltime,Lreturn);
        if(index>0){
            try {
                resp.sendRedirect("s?per=lr");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
