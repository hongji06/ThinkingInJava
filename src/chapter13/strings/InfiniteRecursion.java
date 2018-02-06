/**   
 * @{#} InfiniteRecursion.java Create on 2018年1月24日 下午3:44:44 
 * @Title:  InfiniteRecursion.java   
 * @Package chapter13.strings   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter13.strings;

import java.util.ArrayList;
import java.util.List;

public class InfiniteRecursion {
    public String toString() {
        return "InfiniteRecursion address:" + super.toString();
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> arrayList = new ArrayList<InfiniteRecursion>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new InfiniteRecursion());
        }
        System.out.println(arrayList);
    }
}
