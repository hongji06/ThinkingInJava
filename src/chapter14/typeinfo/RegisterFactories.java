/**   
 * @{#} RegisterFactories.java Create on 2018年2月1日 下午4:18:22 
 * @Title:  RegisterFactories.java   
 * @Package chapter14.typeinfo   
 * @Description:   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter14.typeinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Part {
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactories = new ArrayList<>();

    static {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CarbinAirFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
    }
    
    private static Random random = new Random();
    
    public static Part createRandom() {
        int n = random.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
}

class Filter extends Part{}

class FuelFilter extends Filter{
    public static class Factory implements chapter14.typeinfo.Factory<FuelFilter>{

        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
        
    }
}

class AirFilter extends Filter{
    public static class Factory implements chapter14.typeinfo.Factory<AirFilter>{

        @Override
        public AirFilter create() {
            return new AirFilter();
        }
        
    }
}

class CarbinAirFilter extends Filter{
    public static class Factory implements chapter14.typeinfo.Factory<CarbinAirFilter>{

        @Override
        public CarbinAirFilter create() {
            return new CarbinAirFilter();
        }
        
    }
}

class OilFilter extends Filter{
    public static class Factory implements chapter14.typeinfo.Factory<OilFilter>{

        @Override
        public OilFilter create() {
            return new OilFilter();
        }
        
    }
}

class Belt extends Part{}

class FanBelt extends Belt{
    public static class Factory implements chapter14.typeinfo.Factory<FanBelt>{

        @Override
        public FanBelt create() {
            return new FanBelt();
        }
        
    }
}

class GeneratorBelt extends Belt{
    public static class Factory implements chapter14.typeinfo.Factory<GeneratorBelt>{

        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
        
    }
}

class PowerSteeringBelt extends Belt{
    public static class Factory implements chapter14.typeinfo.Factory<PowerSteeringBelt>{

        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
        
    }
}

public class RegisterFactories {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }

}
