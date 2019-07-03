package dao;

public interface UserDao {
    String Register(String username,String password);

    String login(String username,String password);

    String RegisterByPhoto(String username,String P1,String P2,String P3,String P4,String P5,String P6,String P7,String P8,String P9);

    String loginByPhoto(String username,String Q1,String Q2,String Q3,String P1,String P2,String P3);
}
