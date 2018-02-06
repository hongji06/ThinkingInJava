/**   
 * @{#} Individual.java Create on 2018年2月1日 上午11:11:49 
 * @Title:  Individual.java   
 * @Package chapter14.typeinfo   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter14.typeinfo;

public class Individual {
    private String name;
    
    public Individual() {

    }

    public Individual(String name) {
        this.name=name;
    }
    
    public String toString() {
        return this.name;
    }
}
