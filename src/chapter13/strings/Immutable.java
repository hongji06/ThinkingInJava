package chapter13.strings;

public class Immutable {
    public static String upcase(String s) {
        return s.toUpperCase();
    }
    
    public static void main(String[] args) {
        String q = "howday";
        System.out.println(q);
        String qq=upcase(q);
        System.out.println(qq);
    }
}
