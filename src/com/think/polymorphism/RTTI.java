package com.think.polymorphism;

class Useful{
    void f() {}
    void g() {}
}

class MoreUseful extends Useful{
    void f() {}
    void g() {}
    void u() {}
    void v() {}
    void w() {}
}
public class RTTI {
    public static void main(String[] args) {
        Useful[] x = {new Useful(),new MoreUseful()};
        x[0].f();
        x[1].g();
        
        ((MoreUseful)x[1]).w();
    }
}
