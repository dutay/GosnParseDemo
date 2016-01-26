package com.kale.gsontest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author:Jack Tony
 * @description :
 * @Gson download: http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22gson%22
 * @date :2015年1月24日
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//GsonTest01();
		//GsonTest02();
		//GsonTest03();
		//GsonTest04();
		GsonTest05();
	}

	/**
	 * @description 通过assets文件获取json数据，这里写的十分简单，没做循环判断。
	 *
	 * @return Json数据（String）
	 */
	private String getStrFromAssets(String name) {
		String strData = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			InputStream inputStream = getAssets().open(name);
			byte buf[] = new byte[1024];
			int len = 0;
			while((len = inputStream.read(buf))!=-1){
				baos.write(buf,0,len);
				baos.flush();
			}
			strData = baos.toString();
			strData = strData.trim();

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Log.d("Json data", strData);
		return strData;
	}

	
	/**
	 * @description 将json序列变为list对象
	 *
	 */
	private void GsonTest05() {
		Type listType = new TypeToken<ArrayList<Foo01>>(){}.getType();
		ArrayList<Foo01> foos = new Gson().fromJson(getStrFromAssets("Json03"), listType);
		for (int i = 0; i < foos.size(); i++) {
			System.out.println("name ["+ i +"] = " + foos.get(i).id);
		}
	}

	
	/**
	 * @description 解析为数组
	 *
	 */
	private void GsonTest04() {
		Foo01[] foos = new Gson().fromJson(getStrFromAssets("Json03"), Foo01[].class);
		System.out.println("name01 = " + foos[0].id);
		System.out.println("name02 = " + foos[1].id);
		// 这时候想转成List的话调用如下方法
		// List<Foo> foosList = Arrays.asList(foos);
	}
	
	/**
	 * @description 解析嵌套是Json数据
	 *
	 */
	private void GsonTest03() {
		Foo03 foo = new Gson().fromJson(getStrFromAssets("Json02"), Foo03.class);
		System.out.println("name = " + foo.childFoo.name);
	}
	
	/**
	 * @description 当json中有日期对象时，通过定义构建格式生成需要的date对象
	 * 当json数据命名和java命名产生不一致时，可以通过注释的方式实现更换名字，更方便进行代码处理
	 */
	private void GsonTest02() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置日期的格式，遇到这个格式的数据转为Date对象
		Gson gson = gsonBuilder.create();
		Foo02 foo = gson.fromJson(getStrFromAssets("Json01"), Foo02.class);
		System.out.println("date = " + foo.get_my_date());
	}

	/**
	 * @description 将json数据解析为类对象
	 */
	private void GsonTest01() {
		Foo01 foo = new Gson().fromJson(getStrFromAssets("Json01"), Foo01.class);
		System.out.println("id = " + foo.id);
	}

	/**
	 * @description 通过JsonReader解析Json对象
	 * 
	 * @web http://codego.net/480737/
	 */

}
