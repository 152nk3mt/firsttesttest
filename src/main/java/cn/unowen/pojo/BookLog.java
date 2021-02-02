package cn.unowen.pojo;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.ToString;

@Scope(value = "prototype")
@Component
@ToString
public class BookLog {
	private Integer id;

	private String idcode;

	private String booknumber;

	private Date regtime;

	private Integer regflag;

	private String ip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode == null ? null : idcode.trim();
	}

	public String getBooknumber() {
		return booknumber;
	}

	public void setBooknumber(String booknumber) {
		this.booknumber = booknumber == null ? null : booknumber.trim();
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Integer getRegflag() {
		return regflag;
	}

	public void setRegflag(Integer regflag) {
		this.regflag = regflag;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}
}