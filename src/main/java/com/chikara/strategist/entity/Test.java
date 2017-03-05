package com.chikara.strategist.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



public class Test {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("Hellooo");
		list.add("Byeeeee");
		list.add("Ankush sood");
		m2(list);
		Set<Integer> list2 = new TreeSet<Integer>();
		list2.add(1);
		list2.add(2);
		list2.add(5);
		list2.add(4);
		list2.add(6);
		for (Integer integer : list2) {
			System.out.println(integer);
		}
		//m2(list2);
		
		/*Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i <= 20;i++){
			map.put("String" + i, null);
		}
		System.out.println(map.size());*/
	}
	
	private static void m2(List<? extends Object> list) {
		for (Object object : list) {
			System.out.println(object);
		}
	}
}