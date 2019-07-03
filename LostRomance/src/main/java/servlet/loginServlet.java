package servlet;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import entity.User;
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
import java.util.Enumeration;

/*
登陆请求处理

request:
    String username
    String password

response:
    登陆成功响应:
        JSONObject jsonObject:
                {
                String status:"success"(状态码)
                String username (用户名)

                }
    登陆失败响应:
        JSONObject jsonObject:
                {
                String status:"failure"(状态码)
                }
 */
public class loginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //信息显示1
        MS.begin(IP.getRemoteAddress(req));
        //转换编码方式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:"+username);
        System.out.println("password:"+password);

        //数据库操作
        UserDao ud = new UserDaoImpl();
        String status = ud.login(username, password);

        //将用户信息写入session
        if(status.equals("Success")){
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            //跳转页面去用户界面
            System.out.println("right");
            try {
                req.getRequestDispatcher("./mainPage.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else {
            //写入输出流
        PrintWriter pw = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        System.out.println(jsonObject);
        pw.write(jsonObject.toString());
        pw.close();
        }
        //信息显示2
        System.out.println("login");
        MS.end(status);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            doPost(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
