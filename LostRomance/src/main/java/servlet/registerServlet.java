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
    String password 密码
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
public class registerServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //信息显示1
        MS.begin(IP.getRemoteAddress(req));
        //转换编码方式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //尝试注册
        UserDao ud = new UserDaoImpl();
        String status = ud.Register(username, password);
        //页面跳转
        if (status.equals("Success")) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            //跳转页面去用户界面
            System.out.println("right");
            try {
                req.getRequestDispatcher("./mainPage.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } else {
            //写入输出流
            PrintWriter pw = resp.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("return", status);
            pw.write(jsonObject.toString());
            pw.close();
            System.out.println("register");
            MS.end(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
