package database;

import java.util.ArrayList;

//import MyFramework.*;

public class Test {
	public static void main(String[] args){
		MyFramework.MyMap<String,String> map=new MyFramework.MyMap<String,String>();
		
		System.out.println(map.isEmpty());
		map.show();
		map.put("a", "b");
	
		//map.show();
		map.put("b", "c");
		map.put(null, "nullable");
		map.show();
		

		
	}

}
