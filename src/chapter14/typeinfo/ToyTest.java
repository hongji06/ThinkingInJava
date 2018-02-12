package chapter14.typeinfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
	public Toy() {
	}

	public Toy(int i) {
	}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
	private int id;
	private String name;
	
	public FancyToy() {
		super(1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

public class ToyTest {
	static void printInfo(Class<?> clz) {
		System.out.println("Class name: " + clz.getName() + " is interface? [" + clz.isInterface() + "]");
		System.out.println("Simple name: " + clz.getSimpleName());
		System.out.println("Canonical name:" + clz.getCanonicalName());
	}
	
	public static void main(String[] args) {
		Class<?> clz = null;
		try {
			clz=Class.forName("chapter14.typeinfo.FancyToy");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		printInfo(clz);
		System.out.println("==================================");
		Method[] methods = clz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}		
		System.out.println("==================================");
		for(Class<?> face :clz.getInterfaces()) {
			printInfo(face);
		}
		
		Class<?> up = clz.getSuperclass();
		Object obj=null;
		try {
			obj = up.newInstance();
		}catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		printInfo(obj.getClass());
	}
}
