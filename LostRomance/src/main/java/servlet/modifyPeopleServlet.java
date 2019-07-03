package servlet;

import dao.PeopleDao;
import daoImpl.PeopleDaoImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.IP;
import util.MS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class modifyPeopleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //信息显示1
        MS.begin(IP.getRemoteAddress(req));
        //转换编码方式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取json
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }
        JSONObject inputJson;
        try {
            inputJson = new JSONObject(jb.toString());
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }
        String username = inputJson.getString("username");
        String name = inputJson.getString("name");
        String relationship = inputJson.getString("relationship");
        String birthday = inputJson.getString("birthday");
        String address = inputJson.getString("address");
        String career = inputJson.getString("career");
        Map<String, String> contact = new LinkedHashMap<>();
        Map<String, String> skill = new LinkedHashMap<>();
        Map<String, String> PS = new LinkedHashMap<>();

        JSONArray contactJson = inputJson.getJSONArray("contact");
        JSONArray skillJson = inputJson.getJSONArray("skill");
        JSONArray PSJson = inputJson.getJSONArray("PS");

        for (int i = 0; i < contactJson.length(); i++) {
            JSONObject x = contactJson.getJSONObject(i);
            contact.put(x.getString("method"), x.getString("value"));
        }
        for (int i = 0; i < skillJson.length(); i++) {
            JSONObject x = skillJson.getJSONObject(i);
            skill.put(x.getString("ability"), x.getString("proficiency"));
        }
        for (int i = 0; i < PSJson.length(); i++) {
            JSONObject x = PSJson.getJSONObject(i);
            PS.put(x.getString("title"), x.getString("content"));
        }

        //数据库操作
        PeopleDao pd = new PeopleDaoImpl();
        String status = pd.modifyPeople(username, name, relationship, birthday, address, career, contact, skill, PS);
        //写入输出流
        PrintWriter pw = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("return", status);
        pw.write(jsonObject.toString());
        pw.close();
        //信息显示2
        System.out.println("modifyPeople");
        MS.end(jsonObject.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
