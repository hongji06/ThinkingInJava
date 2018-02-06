/**   
 * @{#} ForNameCreator.java Create on 2018年2月1日 上午11:54:43 
 * @Title:  ForNameCreator.java   
 * @Package chapter14.typeinfo   
 * @Description:   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter14.typeinfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteralPetCreator extends PetCreator {
    private static final List<Class<? extends Pet>> allTypes = Collections
            .unmodifiableList(Arrays.asList(Pet.class, Dog.class, Cat.class, Rodent.class, Mutt.class, Pug.class,
                    EgyptianMau.class, Manx.class, Cymric.class, Rat.class, Mouse.class, Hamster.class));

    private static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Mutt.class),
            allTypes.size());
    
    public List<Class<? extends Pet>> types() {
        return types;
    }
    
    public static void main(String[] args) {
        System.out.println(types);
    }

}
