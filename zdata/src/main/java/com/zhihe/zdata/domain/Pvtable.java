package com.zhihe.zdata.domain;

import java.util.Date;

public class Pvtable {
    private Integer id;
    private String webid;
    private String cookieid;
    private String ip;
    private String phone;
    private String devicetype;
    private String url;
    private String title;
    private String referrer;
    private String res;
    private String colordepth;
    private String lang;
    private String brower;
    private String agent;
    private Date date;
    private String domain;
    private String uri;
    private String country;
    private String province;
    private String city;
    private String isp;

    public Pvtable(String webid, String cookieid, String ip, String phone, String devicetype, String url,
                   String title, String referrer, String res, String colordepth, String lang, String brower,
                   String agent, Date date, String domain, String uri){
        this.webid = webid;
        this.cookieid = cookieid;
        this.ip = ip;
        this.phone = phone;
        this.devicetype = devicetype;
        this.url = url;
        this.title = title;
        this.referrer = referrer;
        this.res = res;
        this.colordepth = colordepth;
        this.lang = lang;
        this.brower = brower;
        this.agent = agent;
        this.date = date;
        this.domain = domain;
        this.uri = uri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebid() {
        return webid;
    }

    public void setWebid(String webid) {
        this.webid = webid == null ? null : webid.trim();
    }

    public String getCookieid() {
        return cookieid;
    }

    public void setCookieid(String cookieid) {
        this.cookieid = cookieid == null ? null : cookieid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer == null ? null : referrer.trim();
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res == null ? null : res.trim();
    }

    public String getColordepth() {
        return colordepth;
    }

    public void setColordepth(String colordepth) {
        this.colordepth = colordepth == null ? null : colordepth.trim();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    public String getBrower() {
        return brower;
    }

    public void setBrower(String brower) {
        this.brower = brower == null ? null : brower.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}