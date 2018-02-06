/**   
 * @{#} Factory.java Create on 2018年2月1日 下午4:06:20 
 * @Title:  Factory.java   
 * @Package chapter14.typeinfo   
 * @Description:factory method   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter14.typeinfo;
  
public interface Factory<T> {
    T create();
}
