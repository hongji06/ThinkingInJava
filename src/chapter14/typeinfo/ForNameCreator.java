/**   
 * @{#} ForNameCreator.java Create on 2018年2月1日 上午11:54:43 
 * @Title:  ForNameCreator.java   
 * @Package chapter14.typeinfo   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter14.typeinfo;

import java.util.ArrayList;
import java.util.List;


public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
    
    private static String[] typeNames= {
      "chapter14.typeinfo.Mutt",   
      "chapter14.typeinfo.Pug",
      "chapter14.typeinfo.EgyptianMau",
      "chapter14.typeinfo.Manx",
      "chapter14.typeinfo.Cymric",
      "chapter14.typeinfo.Rat",
      "chapter14.typeinfo.Mouse",
      "chapter14.typeinfo.Hamster"
    };
    
    @SuppressWarnings("unchecked")
    private static void loader() {
        try {
            for(String name : typeNames) {
                types.add((Class<? extends Pet>)Class.forName(name));
            }
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    static {
        loader();
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
    

}
