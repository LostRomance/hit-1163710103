package daoImpl;

import dao.PeopleDao;
import entity.People;
import util.DBconn;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PeopleDaoImpl implements PeopleDao {

    /**
     * 添加人际
     * @param username
     * @param name
     * @param relationship
     * @param birthday
     * @param address
     * @param career
     * @param contact
     * @param skill
     * @param PS
     * @return status
     */
    @Override
    public String addPeople(String username, String name, String relationship, String birthday, String address, String career, Map<String, String> contact, Map<String, String> skill, Map<String, String> PS) {
        DBconn.init();
        String sql1 = "select * from user where username='" + username + "'";
        String sql2 = "select * from people where username='" + username + "'and name='" + name + "'";
        ResultSet rs1 = DBconn.selectSql(sql1);
        ResultSet rs2 = DBconn.selectSql(sql2);
        try {
            //用户存在
            if (rs1.next()) {
                //联系人存在
                if (rs2.next()) {
                    modifyPeople(username,name,relationship,birthday,address,career,contact,skill,PS);
                    return "Modify sccess";
                } else {
                    if(birthday.equals("")){
                        birthday = "1900-1-1";
                    }
                    java.sql.Date birthdayData = getDate(birthday);
                    //插入people表中信息
                    String addsql0 = "insert into people (name , username,birthday,relationship,address,career ) " + " values ('" + name + "','" + username +"','" + birthdayData +"','" + relationship  + "','" + address + "','" + career + "');";
                    int count = DBconn.addUpdDel(addsql0);
                    //再找一遍确定peopleid
                    ResultSet rs = DBconn.selectSql(sql2);
                    if (rs.next()) {
                        String peopleId = rs.getString("peopleId");
                        //插入contact表中信息
                        for (String method:contact.keySet()) {
                            String addsql1 = "insert into contact (peopleId , method , value ) " + " values ('" + peopleId + "','" + method + "','" + contact.get(method) + "');";
                            DBconn.addUpdDel(addsql1);
                        }
                        //插入skill表中信息
                        for (String ability:skill.keySet()) {
                            String addsql2 = "insert into skill (peopleId , ability , proficiency ) " + " values ('" + peopleId + "','" + ability + "','" + skill.get(ability) + "');";
                            DBconn.addUpdDel(addsql2);
                        }
                        //插入PS表中信息
                        for (String title:PS.keySet()) {
                            String addsql3 = "insert into PS (peopleId , title , content ) " + " values ('" + peopleId + "','" + title + "','" + PS.get(title) + "');";
                            DBconn.addUpdDel(addsql3);
                        }
                        if (count == 1) {
                            return "Add success";
                        }
                    } else {
                        return "Unknown error!";
                    }
                }
            } else {
                return "No user!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 实现方法：
     * 1.删除原来的联系人和他的所有contact，skill，PS
     * 2.第二步，调用添加函数重新进行添加
     * @param username
     * @param name
     * @param relationship
     * @param birthday
     * @param address
     * @param career
     * @param contact
     * @param skill
     * @param PS
     * @return status
     */
    @Override
    public String modifyPeople(String username, String name, String relationship, String birthday, String address, String career, Map<String, String> contact, Map<String, String> skill, Map<String, String> PS) {

        DBconn.init();
            //确定用户是否存在
        String sql1 = "select * from user where username='" + username + "'";
        ResultSet rs1 = DBconn.selectSql(sql1);
            //判断此人是否存在
        String sql2 = "select * from people where username='" + username + "'and name='" + name + "'";
        ResultSet rs2 = DBconn.selectSql(sql2);
        try {
            //用户存在
            if(rs1.next()){
                //联系人存在
                if(rs2.next()){
                    //找到peopleId
                    String peopleId = rs2.getString("peopleId");
                    //删除contract，skill，PS
                    String delsql1 = "delete from contact where peopleId = '"+peopleId+"'";
                    String delsql2 = "delete from skill where peopleId = '"+peopleId+"'";
                    String delsql3 = "delete from PS where peopleId = '"+peopleId+"'";
                    String delsql0 = "delete from People where username='" + username + "'and name='" + name + "'";
                    int count;
                    DBconn.addUpdDel(delsql1);
                    DBconn.addUpdDel(delsql2);
                    DBconn.addUpdDel(delsql3);
                    count = DBconn.addUpdDel(delsql0);
                    //删除成功则继续
                    if(count==1){
                        return addPeople(username,name,relationship,birthday,address,career, contact, skill,  PS);
                    }else{
                        return "Delete error!";
                    }
                }else{
                    return "No people!";
                }
            }else{
                return "No user!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deletePeople(String username, String name) {

        DBconn.init();
        //确定用户是否存在
        String sql1 = "select * from user where username='" + username + "'";
        ResultSet rs1 = DBconn.selectSql(sql1);
        //判断此人是否存在
        String sql2 = "select * from people where username='" + username + "'and name='" + name + "'";
        ResultSet rs2 = DBconn.selectSql(sql2);
        try {
            //用户存在
            if(rs1.next()){
                //联系人存在
                if(rs2.next()){
                    //找到peopleId
                    String peopleId = rs2.getString("peopleId");
                    //删除contract，skill，PS
                    String delsql1 = "delete from contact where peopleId = '"+peopleId+"'";
                    String delsql2 = "delete from skill where peopleId = '"+peopleId+"'";
                    String delsql3 = "delete from PS where peopleId = '"+peopleId+"'";
                    String delsql0 = "delete from People where username='" + username + "'and name='" + name + "'";
                    int count;
                    DBconn.addUpdDel(delsql1);
                    DBconn.addUpdDel(delsql2);
                    DBconn.addUpdDel(delsql3);
                    count = DBconn.addUpdDel(delsql0);
                    //删除成功则继续
                    if(count==1){
                        return "Delete success";
                    }else{
                        return "Delete error!";
                    }
                }else{
                    return "No people!";
                }
            }else{
                return "No user!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param username
     * @return List<People>
     */
    @Override
    public List<People> showPeople(String username) {
        DBconn.init();
        String selsql = "select * from people where username='"+username+"'";
        ResultSet rs = DBconn.selectSql(selsql);
        List<People> peopleList = new ArrayList<>();
        try {
            while(rs.next()){
                People people = new People();
                people.setPeopleId(rs.getString("PeopleId"));
                people.setUsername(rs.getString("Username"));
                people.setName(rs.getString("Name"));
                people.setRelationship(rs.getString("Relationship"));
                people.setBirthday(rs.getString("Birthday"));
                people.setAddress(rs.getString("Address"));
                people.setCareer(rs.getString("Career"));
                Map<String,String> contact = new HashMap<>();
                Map<String,String> skill = new HashMap<>();
                Map<String,String> PS = new HashMap<>();
                String selsql1 = "select * from contact where PeopleId = '"+rs.getString("PeopleId")+"'";
                String selsql2 = "select * from skill where PeopleId = '"+rs.getString("PeopleId")+"'";
                String selsql3 = "select * from PS where PeopleId = '"+rs.getString("PeopleId")+"'";
                ResultSet rs1 = DBconn.selectSql(selsql1);
                ResultSet rs2 = DBconn.selectSql(selsql2);
                ResultSet rs3 = DBconn.selectSql(selsql3);
                while (rs1.next()){
                    String method = rs1.getString("method");
                    String value = rs1.getString("value");
                    contact.put(method,value);
                }
                while (rs2.next()){
                    String ability = rs2.getString("ability");
                    String proficiency = rs2.getString("proficiency");
                    skill.put(ability,proficiency);
                }
                while(rs3.next()){
                    String title = rs3.getString("title");
                    String content = rs3.getString("content");
                    PS.put(title,content);
                }
                people.setContact(contact);
                people.setSkill(skill);
                people.setPS(PS);
                peopleList.add(people);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(peopleList.size()==0){
            return null;
        }else {
            return peopleList;
        }
    }

    //转化日期类型
    public static java.sql.Date getDate(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = format.parse(dateTime);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return new java.sql.Date(utilDate.getTime());
    }

}
