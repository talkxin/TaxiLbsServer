package com.taxi.admin.system;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class xx {
	private Long id;
	private String userName;
	private Long passWord;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getPassWord() {
		return passWord;
	}

	public void setPassWord(Long passWord) {
		this.passWord = passWord;
	}

	public static void main(String[] args) {
		UploadExcelToInsert<xx> l = new UploadExcelToInsert<xx>();
		HashMap<String, String> key = new HashMap<String, String>();
		key.put("userName", "userName");
		key.put("passWord", "passWord");
		List<xx> il = l.inserts(new File("d:/Book1.xls"), xx.class, key);
		System.out.println(il.get(0).getUserName());
	}
}
