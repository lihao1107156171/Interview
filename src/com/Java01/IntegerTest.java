package com.Java01;

public class IntegerTest {

	public static void main(String[] args) {
		// -128 ~ 127 之间使用常量池中对象，超出后创建新对象
		Integer i1=100,i2=100,i3=120,i4=120;
		
		System.out.println(i1 == i2);
		System.out.println(i3 == i4);
	}

}
