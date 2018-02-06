/**   
 * @{#} Person.java Create on 2018年2月1日 上午11:12:07 
 * @Title:  Person.java   
 * @Package chapter14.typeinfo   
 * @Description:   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter14.typeinfo;

import java.util.ArrayList;

public class Pet extends Individual {
    public static final PetCreator creater = new LiteralPetCreator();
    
    public static Pet randomPet() {
        return creater.randomPet();
    }
    
    public static Pet[] createArray(int size) {
        return creater.createArray(size);
    }
    
    public static ArrayList<Pet> arrayList(int size){
        return creater.arrayList(size);
    }
    
    public Pet(String name) {
        super(name);
    }
    
    public Pet() {
        super();
    }
}
