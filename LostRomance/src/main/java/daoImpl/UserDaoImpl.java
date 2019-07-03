package daoImpl;

import util.DBconn;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements dao.UserDao {
    @Override
    public String Register(String username, String password) {
        DBconn.init();
        String sql = "select * from user where username='" + username + "'";
        ResultSet rs = DBconn.selectSql(sql);
        try {
            if (rs.next()) {
                return "Already exist!";
            } else {
                String registerSql = "insert into user (username,password)"
                        + " values ('" + username + "','"
                        + password + "');";
                int flag = DBconn.addUpdDel(registerSql);
                if (flag == 1) {
                    return "Success";
                } else {
                    return "Unknown Error!";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String RegisterByPhoto(String username,String P1,String P2,String P3,String P4,String P5,String P6,String P7,String P8,String P9){
        DBconn.init();
        String sql = "select * from user where username='" + username + "'";
        ResultSet rs = DBconn.selectSql(sql);
        try {
            if (rs.next()) {
                return "Already exist!";
            } else {
                String registerSql = "insert into user (username,P1,P2,P3,P4,P5,P6,P7,P8,P9)"
                        + " values ('" + username + "','"
                        + P1 + "','"+P2+"','"+P3+"','"+P4+"','"+P5+"','"+P6+"','"+P7+"','"+P8+"','"+P9+"');";
                int flag = DBconn.addUpdDel(registerSql);
                if (flag == 1) {
                    return "Success";
                } else {
                    return "Unknown Error!";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String login(String username, String password) {
        DBconn.init();
        String sql = "select * from user where username='" + username + "'";
        ResultSet rs = DBconn.selectSql(sql);
        try{
           if(rs.next()){
                if(rs.getString("password").equals(password)){
                    return "Success";
                }else {
                    return "Password wrong!";
                }
           }else{
               return "User doesn't exist!";
           }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String loginByPhoto(String username,String Q1,String Q2,String Q3,String P1,String P2,String P3) {
        DBconn.init();
        String sql = "select * from user where username='" + username + "'";
        ResultSet rs = DBconn.selectSql(sql);
        try{
            if(rs.next()){
                if(rs.getString("P"+Q1).equals(P1)){
                    if(rs.getString("P"+Q2).equals(P2)){
                        if(rs.getString("P"+Q3).equals(P3)){
                            return "Success";
                        }else {
                            return "Password wrong!";}
                    }else {
                        return "Password wrong!";}
                }else {
                    return "Password wrong!";
                }
            }else{
                return "User doesn't exist!";
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
