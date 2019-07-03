package servlet;

import dao.PeopleDao;
import dao.UserDao;
import daoImpl.PeopleDaoImpl;
import daoImpl.UserDaoImpl;
import entity.People;
import org.json.JSONArray;
import org.json.JSONObject;
import util.IP;
import util.MS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class showPeopleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //信息显示1
        MS.begin(IP.getRemoteAddress(req));
        //转换编码方式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username = req.getParameter("username");
        //数据库操作
        PeopleDao pd = new PeopleDaoImpl();
        List<People> peopleList = pd.showPeople(username);
        //写入输出流
        PrintWriter pw = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        JSONArray answers = new JSONArray();

        if (peopleList == null) {
            jsonObject.put("return", "failure");
        } else {
            jsonObject.put("return", "success");
            for (People x : peopleList) {
                JSONObject people = new JSONObject();
                people.put("peopleId", x.getPeopleId());
                people.put("username", x.getUsername());
                people.put("name", x.getName());
                people.put("relationship", x.getRelationship());
                people.put("birthday", x.getBirthday());
                people.put("address", x.getAddress());
                people.put("career", x.getCareer());
                //添加contact
                JSONArray contacts = new JSONArray();
                for (String method : x.getContact().keySet()) {
                    JSONObject contact = new JSONObject();
                    contact.put("method", method);
                    contact.put("value", x.getContact().get(method));
                    contacts.put(contact);
                }
                people.put("contact", contacts);
                //添加skill
                JSONArray skills = new JSONArray();
                for (String ability : x.getSkill().keySet()) {
                    JSONObject skill = new JSONObject();
                    skill.put("ability", ability);
                    skill.put("proficiency", x.getSkill().get(ability));
                    skills.put(skill);
                }
                people.put("skill", skills);
                //添加PS
                JSONArray PSs = new JSONArray();
                for (String title : x.getPS().keySet()) {
                    JSONObject PS = new JSONObject();
                    PS.put("title", title);
                    PS.put("content", x.getPS().get(title));
                    PSs.put(PS);
                }
                people.put("PS", PSs);
                answers.put(people);
            }
        }

        jsonObject.put("answers", answers);
        System.out.println(jsonObject);

        pw.write(jsonObject.toString());
        pw.close();
        //信息显示2
        System.out.println("showPeople");

        MS.end(jsonObject.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
