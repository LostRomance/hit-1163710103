package servlet;

import dao.PeopleDao;
import dao.UserDao;
import daoImpl.PeopleDaoImpl;
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

public class deletePeople extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //信息显示1
        MS.begin(IP.getRemoteAddress(req));
        //转换编码方式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        System.out.println("username"+username);
        System.out.println("name"+name);


        //数据库操作
        PeopleDao pd = new PeopleDaoImpl();
        String status = pd.deletePeople(username, name);
//        //写入输出流
        PrintWriter pw = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("return", status);
        pw.write(jsonObject.toString());
        pw.close();

        //信息显示2
        System.out.println("deletePeople");
        MS.end(status);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
