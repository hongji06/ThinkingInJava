/**   
 * @{#} PetCreator.java Create on 2018年2月1日 上午11:36:22 
 * @Title:  PetCreator.java   
 * @Package chapter14.typeinfo   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter14.typeinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public abstract class PetCreator {
    private Random random = new Random(47);
    //this list of the different types of pet to create
    public abstract List<Class<? extends Pet>> types();
    
    public Pet randomPet() {
        int n = random.nextInt(types().size());
        try {
            return types().get(n).newInstance();
        }catch (InstantiationException e) {
            throw new RuntimeException(e);
        }catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomPet();            
        }
        return result;
    }
    
    public ArrayList<Pet> arrayList(int size){
        ArrayList<Pet> result = new ArrayList<Pet>();
        Collections.addAll(result, createArray(size));
        return result;
    }
}
