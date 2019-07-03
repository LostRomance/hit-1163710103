package entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class People {
    private String PeopleId;
    private String Username;
    private String Name;
    private String Relationship;
    private String Birthday;
    private String Address;
    private String Career;
    private Map<String,String> contact = new LinkedHashMap<>();
    private Map<String,String> skill = new LinkedHashMap<>();
    private Map<String,String> PS = new LinkedHashMap<>();


    public String getPeopleId() {
        return PeopleId;
    }

    public void setPeopleId(String peopleId) {
        PeopleId = peopleId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRelationship() {
        return Relationship;
    }

    public void setRelationship(String relationship) {
        Relationship = relationship;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCareer() {
        return Career;
    }

    public void setCareer(String career) {
        Career = career;
    }

    public Map<String, String> getContact() {
        return contact;
    }

    public void setContact(Map<String, String> contact) {
        this.contact = contact;
    }

    public Map<String, String> getSkill() {
        return skill;
    }

    public void setSkill(Map<String, String> skill) {
        this.skill = skill;
    }

    public Map<String, String> getPS() {
        return PS;
    }

    public void setPS(Map<String, String> PS) {
        this.PS = PS;
    }
}
