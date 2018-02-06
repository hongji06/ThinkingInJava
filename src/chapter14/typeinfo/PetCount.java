/**   
 * @{#} PetCount.java Create on 2018年2月1日 下午1:47:40 
 * @Title:  PetCount.java   
 * @Package chapter14.typeinfo   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter14.typeinfo;

import java.util.HashMap;


public class PetCount {
    
    static class PetCounter extends HashMap<String, Integer> {
        private static final long serialVersionUID = 1L;

        public void count(String type) {
            Integer quantity = get(type);
            if(quantity==null) {
                put(type, 1);
            }else {
                put(type, quantity+1);
            }
            
        }
    }

    public static void countPet(PetCreator creator) {
        PetCounter counter = new PetCounter();
        for(Pet pet : creator.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName()+" ");
            if(pet instanceof Pet) {
                counter.count("Pet");
            }
            if(pet instanceof Dog) {
                counter.count("Dog");
            }
            if(pet instanceof Mutt) {
                counter.count("Mutt");
            }
            if(pet instanceof Pug) {
                counter.count("Pug");
            }
            if(pet instanceof Cat) {
                counter.count("Cat");
            }
            if(pet instanceof Manx) {
                counter.count("Manx");
            }
            if(pet instanceof Cymric) {
                counter.count("Cymric");
            }
            if(pet instanceof Rodent) {
                counter.count("Rodent");
            }
            if(pet instanceof Rat) {
                counter.count("Rat");
            }
            if(pet instanceof Mouse) {
                counter.count("Mouse");
            }
            if(pet instanceof Hamster) {
                counter.count("Hamster");
            }          
            
        }
        System.out.println("\n"+counter);
    }

    public static void main(String[] args) {
        countPet(new ForNameCreator());
    }
}
