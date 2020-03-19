package proxy.protect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args) {
		PersonBean personBean = getInitializedPersonBean();
		
		//personBean 소유권자의 접근제어 프록시
		PersonBean personBeanOwnerProxy = getProxy(personBean, new OwnerInvocationHandler(personBean));
		
		System.out.println("Name is " + personBeanOwnerProxy.getName());
		
		personBeanOwnerProxy.setInterests("bowling, Go");
		System.out.println("Interests set from owner proxy");
		
		try {
			personBeanOwnerProxy.setHotOrNotRating(10);
		}catch(Exception e) {
			System.out.println("Can't set rating from owner proxy");
		}
		System.out.println("Rating is " + personBeanOwnerProxy.getHotOrNotRating());
		
		System.out.println("--------------------------");
		
		//personBean 소유권자가 아닌 자의 접근제어 프록시
		PersonBean personBeanNonOwnerProxy = getProxy(personBean, new NonOwnerInvocationHandler(personBean));
		
		System.out.println("Name is " + personBeanNonOwnerProxy.getName());
		
		try {
			personBeanNonOwnerProxy.setInterests("bowling, Go");
		}catch(Exception e) {
			System.out.println("Can't set interests from non owner proxy");
		}
		
		personBeanNonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Rating is " + personBeanNonOwnerProxy.getHotOrNotRating());

	}

	static PersonBean getInitializedPersonBean() {
		PersonBean personBean = new PersonBeanImpl();
		personBean.setName("john");
		personBean.setGender("male");
		personBean.setInterests("programming");
		return personBean;
	}

	//다이나믹 프록시.
	@SuppressWarnings("unchecked")
	static <T> T getProxy(T target, InvocationHandler invocationHandler) {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), invocationHandler);
	}

}
