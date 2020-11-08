package model;

import annotation.Id;

import java.io.Serializable;

public class User implements Serializable {
    @Id
    private int id;
    private String name;  //真名
    private String loginName;  //登录用用户名
    private String loginPwd;   //密码
    private String email;
    private String phone;
    private String address;
    private String lastVideoUrl;  //最后看的一个视频url
    private int identify;  //身份，0为用户，1为讲师
    private String school;  //学校
    private String introduce;  //简介
    private String headUrl; //头像路径

    public User() {
    }

    public User(int id, String name, String loginName, String loginPwd, String email, String phone, String address, String lastVideoUrl, int identify, String school, String introduce, String headUrl) {
        this.id = id;
        this.name = name;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.lastVideoUrl = lastVideoUrl;
        this.identify = identify;
        this.school = school;
        this.introduce = introduce;
    }

    public User(int id, String name, String loginName, String loginPwd) {
        this.id = id;
        this.name = name;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastVideoUrl() {
        return lastVideoUrl;
    }

    public void setLastVideoUrl(String lastVideoUrl) {
        this.lastVideoUrl = lastVideoUrl;
    }

    public int getIdentify() {
        return identify;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
