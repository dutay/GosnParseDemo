package com.kale.gsontest;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Foo02 {
    private int id;
    private String body;
    private float number;
    
    @SerializedName("created_at")
    private Date createdAt; // 通过注释的方式更换名字，同时保证使用方式不变。
    
	public String get_my_date() {
		// 可以通过simpleDateFormat指定data对象的输出格式，注意：如果要添加自定义字符串，比如下面的custom，必须在字符串两边加单引号。
		SimpleDateFormat dateFormat = new SimpleDateFormat("'custom'_yyyyMMdd_HHmmss");
		//return dateFormat.format(created_at);
		
		return createdAt.toString();
	}

}