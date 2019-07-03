package servlet;


import dao.UserDao;
import daoImpl.UserDaoImpl;
import org.json.JSONObject;
import util.IP;
import util.MS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/*
注册请求处理

request:
    String username 用户名
    String P1~P9 九个密像码
response:
    注册成功响应:
            JSONObject jsonObject:
            {
            String status:"success" 状态码
            }
    注册失败响应:
            JSONObject
            {
            String status 状态码,响应失败原因
            }

 */
public class registerByPhotoServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //信息显示1
        MS.begin(IP.getRemoteAddress(req));
        //转换编码方式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username = req.getParameter("username");
        String P1 = req.getParameter("P1");
        String P2 = req.getParameter("P2");
        String P3 = req.getParameter("P3");
        String P4 = req.getParameter("P4");
        String P5 = req.getParameter("P5");
        String P6 = req.getParameter("P6");
        String P7 = req.getParameter("P7");
        String P8 = req.getParameter("P8");
        String P9 = req.getParameter("P9");
        //尝试注册
        UserDao ud = new UserDaoImpl();
        String status = ud.RegisterByPhoto(username, P1, P2, P3, P4, P5, P6, P7, P8, P9);
        //页面跳转
        //写入输出流
        PrintWriter pw = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("return", status);
        pw.write(jsonObject.toString());
        pw.close();
        System.out.println("register");
        MS.end(jsonObject.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
