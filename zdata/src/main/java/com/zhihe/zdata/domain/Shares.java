package com.zhihe.zdata.domain;

import java.util.Date;

public class Shares {
	private Integer id;

	private String webid;

	private String cookieid;

	private String channel;

	private String phone;

	private String ip;

	private String devicetype;

	private String res;

	private String colordepth;

	private String lang;

	private String brower;

	private String country;

	private String province;

	private String city;

	private String isp;

	private String url;

	private String domain;

	private String referrer;

	private String title;

	private String uri;

	private String agent;

	private Date date;

	public Shares(String webid, String cookieid, String channel, String phone, String ip, String devicetype, String res,
			String colordepth, String lang, String brower, String url, String domain, String referrer, String title,
			String uri, String agent, Date date, String country, String province, String city, String isp) {
		this.webid = webid;
		this.cookieid = cookieid;
		this.channel = channel;
		this.phone = phone;
		this.ip = ip;
		this.devicetype = devicetype;
		this.res = res;
		this.colordepth = colordepth;
		this.lang = lang;
		this.brower = brower;
		this.url = url;
		this.domain = domain;
		this.referrer = referrer;
		this.title = title;
		this.uri = uri;
		this.agent = agent;
		this.date = date;
        this.country = country;
        this.province = province;
        this.city = city;
        this.isp = isp;
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
        this.webid = webid;
    }

    public String getCookieid() {
        return cookieid;
    }

    public void setCookieid(String cookieid) {
        this.cookieid = cookieid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getColordepth() {
        return colordepth;
    }

    public void setColordepth(String colordepth) {
        this.colordepth = colordepth;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getBrower() {
        return brower;
    }

    public void setBrower(String brower) {
        this.brower = brower;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}