/**   
 * @{#} SimpleProxyDemo.java Create on 2018年2月2日 上午10:21:25 
 * @Title:  SimpleProxyDemo.java   
 * @Package chapter14.typeinfo.proxy   
 * @Description:
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter14.typeinfo.proxy;

interface Interface {
    void doSomething();

    void doSomethingElse(String arg);
}

class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void doSomethingElse(String arg) {
        System.out.println("doSomethingElse " + arg);
    }

}

class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();
    }

    @Override
    public void doSomethingElse(String arg) {
        System.out.println("SimpleProxy doSomethingElse " + arg);
        proxied.doSomethingElse(arg);
    }

}

class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.doSomethingElse("booooon.");

    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
