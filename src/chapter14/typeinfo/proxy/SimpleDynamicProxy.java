/**   
 * @{#} SimpleDynamicProxy.java Create on 2018年2月2日 上午10:36:50 
 * @Title:  SimpleDynamicProxy.java   
 * @Package chapter14.typeinfo.proxy   
 * @Description:
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter14.typeinfo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class DynamicProxyHandler implements InvocationHandler{
    private Object proxied;
    
    public DynamicProxyHandler(Object proxied) {
        this.proxied=proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("***proxy:"+proxy.getClass()+", method"+method+",args:"+args);
        if(args!=null) {
            for(Object arg:args) {
                System.out.println(" "+arg);
            }
        }
        return method.invoke(proxied, args);
    }
    
}
  
class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.doSomethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        
        Interface proxy=(Interface)Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[] {Interface.class}, new DynamicProxyHandler(real));
        consumer(proxy);
    }

}
