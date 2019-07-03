package dao;

import entity.People;

import java.util.List;
import java.util.Map;

public interface PeopleDao {
    String addPeople(String username, String name, String relationship, String birthday, String address, String career, Map<String,String> contact,Map<String,String> skill,Map<String,String> PS );
    String modifyPeople(String username, String name, String relationship, String birthday, String address, String career, Map<String,String> contact,Map<String,String> skill,Map<String,String> PS );
    String deletePeople(String username,String name);
    List<People> showPeople(String username);
}
