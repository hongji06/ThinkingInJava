/**   
 * @{#} WhitherStringbuilder.java Create on 2018年1月24日 下午3:18:09 
 * @Title:  WhitherStringbuilder.java   
 * @Package chapter13.strings   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter13.strings;
  
public class WhitherStringbuilder {
    
    public String implicit(String[] fields) {
        String result="";
        for (int i = 0; i < fields.length; i++) {
            result+=fields[i];
        }
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            result.append(fields[i]);
        }
        return result.toString();

    }

}
