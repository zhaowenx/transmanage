package com.zwx.transmanage.domain;

import java.util.Date;

/**
 * Created by zhaowenx on 2018/8/23.
 */
public class User{

    /**
     * USER
     * 用户表
     */
    private Integer id;//用户ID
    private String userName;//用户名
    private String passWord;//登录密码(经过3Des加密)
    private String realName;//真实姓名
    private String phone;//电话
    private String mobile;//手机
    private String email;//邮箱
    private String weixin;//微信
    private String qqNumber;//qq
    private String staffNo;//工号
    private Integer isSuperUser;//是否超级用户，0：非超级用户，1：超级用户
    private Integer loginTimes;//登录次数
    private String lastLoginDate;//最后一次登录时间
    private String lastIp;//最后登录的IP
    private Integer sex;//性别 1:男，0:女
    private String createTime;//创建时间
    private String updateTime;//更新时间
    private String  isDelete;//是否删除，Y：是，N：否
    private String provinceName;//归属省份名称
    private String cityName;//归属城市名称
    private String countyName;//归属地区名称
    private String department;//所属部门
    private String company;//所属公司
    private String hireDate;//入职时间
    private String validateCode;//验证码
    private String idCard;//身份证号码
    private String profession;//职业
    private String hobby;//兴趣爱好
    private String wisdom;//至理名言

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public Integer getIsSuperUser() {
        return isSuperUser;
    }

    public void setIsSuperUser(Integer isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getWisdom() {
        return wisdom;
    }

    public void setWisdom(String wisdom) {
        this.wisdom = wisdom;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", weixin='" + weixin + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", staffNo='" + staffNo + '\'' +
                ", isSuperUser=" + isSuperUser +
                ", loginTimes=" + loginTimes +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", lastIp='" + lastIp + '\'' +
                ", sex=" + sex +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countyName='" + countyName + '\'' +
                ", department='" + department + '\'' +
                ", company='" + company + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", validateCode='" + validateCode + '\'' +
                ", idCard='" + idCard + '\'' +
                ", profession='" + profession + '\'' +
                ", hobby='" + hobby + '\'' +
                ", wisdom='" + wisdom + '\'' +
                '}';
    }
}
