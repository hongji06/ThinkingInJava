/**   
 * @{#} UsingStringbuilder.java Create on 2018年1月24日 下午3:28:28 
 * @Title:  UsingStringbuilder.java   
 * @Package chapter13.strings   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter13.strings;

import java.util.Random;

public class UsingStringbuilder {
    public static Random random = new Random(47);
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 25; i++) {
            result.append(random.nextInt(100));
            result.append(", ");
        }
        result.delete(result.length()-2, result.length());
        result.append("]");
        return result.toString();
    }
    
    public static void main(String[] args) {
        UsingStringbuilder usb = new UsingStringbuilder();
        System.out.println(usb);
    }

}
