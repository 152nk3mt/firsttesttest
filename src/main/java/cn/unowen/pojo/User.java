package cn.unowen.pojo;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class User implements Serializable {
	private Integer id;

	private String name;

	private String password;

	private Integer times;

	private Integer lockflag;

	private Date lockTime;

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getLockflag() {
		return lockflag;
	}

	public void setLockflag(Integer lockflag) {
		this.lockflag = lockflag;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", times=" + times + ", lockflag="
				+ lockflag + ", lockTime=" + lockTime + "]";
	}
	
}