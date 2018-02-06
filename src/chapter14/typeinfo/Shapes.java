/**   
 * @{#} Shape.java Create on 2018年2月1日 上午10:39:25 
 * @Title:  Shape.java   
 * @Package chapter14.typeinfo   
 * @Description:TODO(用一句话描述该文件做什么)   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter14.typeinfo;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
    void draw() {
        System.out.println(this + ".draw()");
    }

    abstract public String toString();
}

class Circle extends Shape {

    @Override
    public String toString() {
        return "Circle";
    }

}

class Square extends Shape {

    @Override
    public String toString() {
        return "Square";
    }

}

class Triangle extends Shape {

    @Override
    public String toString() {
        return "Triangle";
    }

}

public class Shapes {
    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }
}
