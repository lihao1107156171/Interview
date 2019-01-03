package com.list01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

	public static void main(String[] args) {
		final List<String> list = new ArrayList<>();
		/*
		 * 	��̬������ �����¼��Ϊ������ ����һ��ʵ�����ഴ��ʱ������ʱָ���Ľӿ��б���࣬����������������Ϊ��
		 *  	����ӿ����ɴ�����ʵ�ֵĽӿڡ� ����ʵ���Ǵ������һ��ʵ���� ÿ������ʵ������һ�������ĵ��ô���������
		 *  	��ʵ���˽ӿ�InvocationHandler �� ͨ�������ӿ�֮һ�Ĵ���ʵ���ϵķ������ý������ɵ�ʵ�����ô�������
		 *  	invoke���������ݴ���ʵ���� java.lang.reflect.Method�����÷�����java.lang.reflect.Method�����Լ���
		 *  	������������Object Object�����顣 ���ô�������ʵ��ش�����뷽�����ã����ҷ��صĽ������Ϊ�����ڴ���
		 *  	ʵ���ϵ��õĽ�����ء� 
		*/
		
		/*
		 * 	newProxyInstance(ClassLoader loader,
                                      ��<?>[] interfaces,
                                      InvocationHandler h)
		 * 	����ָ���ӿڵĴ������ʵ�����ýӿڽ��������÷��ɸ�ָ���ĵ��ô������
		 * 	loader - ������������������
		 * 	interfaces - ������ʵ�ֵĽӿ��б�
		 * 	h - ���ȷ������õĵ��ô�����	
		 * 	������ָ��������������岢ʵ��ָ���ӿڵĴ������ָ�����ô������Ĵ���ʵ�� 
		*/
		@SuppressWarnings("unchecked")
		List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), 
				list.getClass().getInterfaces(), 
				new InvocationHandler() {//�ɴ���ʵ���ĵ��ô������ʵ�ֵĽӿ�
					
					// �������ʵ���ϵķ������ò����ؽ���� ������֮�����Ĵ���ʵ���ϵ��÷���ʱ�����ڵ��ô�������е��ô˷�����
					// proxy - ���ø÷����Ĵ���ʵ��
					// method -����������Ӧ�ڵ��ô���ʵ���ϵĽӿڷ�����ʵ���� ��������������ཫ�Ǹ÷��������Ľӿڣ��������Ǵ�����̳и÷����Ĵ���ӿڵĳ����ӿڡ�
					// args -�����ķ������ô��ݴ���ʵ���Ĳ���ֵ�Ķ�������У���null����ӿڷ���û�в����� ԭʼ���͵Ĳ����������ʵ���ԭʼ��װ�����ʵ���У�
					//����java.lang.Integer��java.lang.Boolean ��
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return method.invoke(list, args);
				}}
		);
		
		proxyInstance.add("���");
		System.out.println(list);
	}
	
}
