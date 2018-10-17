package com.zwx.transmanage.domain;

/**
 * Created by zhaowenx on 2018/9/5.
 */
public class AddressBook {
    /**
     * ADDRESSBOOK
     * 通讯录表
     */
    private Integer id;//ID
    private Integer userId;//用户ID
    private String chineseName;//中文名
    private String englishName;//英文名
    private String anotherName;//别称
    private String qqNumber;//QQ号码
    private String weixin;//微信
    private String domicile;//户籍所在地
    private String address;//现住址
    private String email;//邮箱
    private String phone;//手机
    private String weibo;//微博
    private String profession;//职业
    private String type;//分类
    private String createTime;//创建时间
    private String updateTime;//更新时间
    private String birthday;//生日

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "AddressBook{" +
                "id=" + id +
                ", userId=" + userId +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", anotherName='" + anotherName + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", weixin='" + weixin + '\'' +
                ", domicile='" + domicile + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", weibo='" + weibo + '\'' +
                ", profession='" + profession + '\'' +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
