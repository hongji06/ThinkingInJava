/**   
 * @{#} SimpleFormat.java Create on 2018年1月24日 下午3:59:27 
 * @Title:  SimpleFormat.java   
 * @Package chapter13.strings   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter13.strings;

public class SimpleFormat {
    public static void main(String[] args) {
        int x=5;
        double y = 5.332542;
        System.out.println("Row 1:["+x+" "+y+"]");
        System.out.format("Row 1:[%d %f]\n", x,y);
        System.out.printf("Row 1:[%d %f]\n", x,y);
    }
}
