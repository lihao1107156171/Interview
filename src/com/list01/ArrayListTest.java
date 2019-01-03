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
		 * 	动态代理类 （以下简称为代理类 ）是一个实现在类创建时在运行时指定的接口列表的类，具有如下所述的行为。
		 *  	代理接口是由代理类实现的接口。 代理实例是代理类的一个实例。 每个代理实例都有一个关联的调用处理程序对象，
		 *  	它实现了接口InvocationHandler 。 通过其代理接口之一的代理实例上的方法调用将被分派到实例调用处理程序的
		 *  	invoke方法，传递代理实例， java.lang.reflect.Method被调用方法的java.lang.reflect.Method对象以及包
		 *  	含参数的类型Object Object的数组。 调用处理程序适当地处理编码方法调用，并且返回的结果将作为方法在代理
		 *  	实例上调用的结果返回。 
		*/
		
		/*
		 * 	newProxyInstance(ClassLoader loader,
                                      类<?>[] interfaces,
                                      InvocationHandler h)
		 * 	返回指定接口的代理类的实例，该接口将方法调用分派给指定的调用处理程序。
		 * 	loader - 类加载器来定义代理类
		 * 	interfaces - 代理类实现的接口列表
		 * 	h - 调度方法调用的调用处理函数	
		 * 	具有由指定的类加载器定义并实现指定接口的代理类的指定调用处理程序的代理实例 
		*/
		@SuppressWarnings("unchecked")
		List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), 
				list.getClass().getInterfaces(), 
				new InvocationHandler() {//由代理实例的调用处理程序实现的接口
					
					// 处理代理实例上的方法调用并返回结果。 当在与之关联的代理实例上调用方法时，将在调用处理程序中调用此方法。
					// proxy - 调用该方法的代理实例
					// method -所述方法对应于调用代理实例上的接口方法的实例。 方法对象的声明类将是该方法声明的接口，它可以是代理类继承该方法的代理接口的超级接口。
					// args -包含的方法调用传递代理实例的参数值的对象的阵列，或null如果接口方法没有参数。 原始类型的参数包含在适当的原始包装器类的实例中，
					//例如java.lang.Integer或java.lang.Boolean 。
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return method.invoke(list, args);
				}}
		);
		
		proxyInstance.add("你好");
		System.out.println(list);
	}
	
}
