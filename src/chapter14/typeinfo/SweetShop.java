/**   
 * @{#} SweetShop.java Create on 2018年2月1日 上午10:49:56 
 * @Title:  SweetShop.java   
 * @Package chapter14.typeinfo   
 * @Description:   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter14.typeinfo;
  
class Candy{
    static {
        System.out.println("Loading Candy");
    }
}

class Gum{
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie{
    static {
        System.out.println("Loading Cookie");
    }
}

public class SweetShop {
    public static void main(String[] args) {
        System.out.println("inside main");
        new Candy();
        System.out.println("after create candy");
        try {
            Class.forName("chapter14.typeinfo.Gum");
        }catch (ClassNotFoundException e) {
            System.out.println("could not find Gum");
        }
        System.out.println("after Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("after create cookie");
    }
}
