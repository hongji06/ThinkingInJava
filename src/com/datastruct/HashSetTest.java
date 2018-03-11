package com.datastruct;

import java.util.HashSet;

public class HashSetTest {

	public static void main(String[] args) {
		HashSet<Object> hashSet = new HashSet<>();
		Object object = new Object();
		System.out.println(hashSet.hashCode()+"====="+object.hashCode());
		hashSet.hashCode();
		object.hashCode();
	}

}
