/**   
 * @{#} Recept.java Create on 2018年1月24日 下午4:13:26 
 * @Title:  Recept.java   
 * @Package java.io   
 * @Description:   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */   
package chapter13.strings;

import java.util.Formatter;

public class Recept {
    private double total=0;
    private Formatter f = new Formatter(System.out);
    public void printTitle() {
        f.format("%-15s %5s %10s\n", "Item","Qty","Price");
        f.format("%-15s %5s %10s\n", "----","---","-----");
    }
    
    public void print(String name,int qty,double price) {
        f.format("%-15.15s %5s %10.2f\n", name,qty,price);
        total+=price;
    }
    
    public void printTotal() {
        f.format("%-15.15s %5s %10.2f\n", "Tax","",total*0.06);
        f.format("%-15.15s %5s %10s\n", "","","-----");
        f.format("%-15.15s %5s %10.2f\n", "Total","",total*1.06);
    }
    
    public static void main(String[] args) {
        Recept recept = new Recept();
        recept.printTitle();
        recept.print("Jack", 4, 4.25);
        recept.print("Princess Peas", 3, 5.1);
        recept.print("Three Porridge", 1, 14.29);
        recept.printTotal();
    }
}
