package com.kale.gsontest;

/**
 * @author:Jack Tony
 * @description  : 针对有嵌套的json数据
 * @date  :2015年1月24日
 */
public class Foo03 {
	public int id;
	public String body;
	public float number;
	public String created_at;
	public ChildFoo childFoo;

	public class ChildFoo {
		public int id;
		public String name;
	}
}
