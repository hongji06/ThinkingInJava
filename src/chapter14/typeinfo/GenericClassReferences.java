/**   
 * @{#} GenericClassReferences.java Create on 2018年2月1日 上午10:59:27 
 * @Title:  GenericClassReferences.java   
 * @Package chapter14.typeinfo   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter14.typeinfo;

public class GenericClassReferences {

    public static void main(String[] args) {
        Class<?> intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class;
        intClass = Dog.class;
        System.out.println(intClass.getName());
        System.out.println(genericIntClass.getName());
    }

}
