package chapter12.exceptions;

@SuppressWarnings("serial")
class FourException extends Exception {
}

public class AlwaysFinally {
    public static void main(String[] args) {
        System.out.println("Entering first block");
        int counter=0;
        
        try {
            System.out.println("Entering second block");
            try {
                defualFlag:
                while(counter++<5) {
                    if(counter==1) continue defualFlag;
                    throw new FourException();
                }
               
            }catch (FourException e) {
                System.out.println("Caught FourExecption in second block");
            }
            finally {
                System.out.println("Finally in second block");
            }
        }catch (Exception e) {
            System.out.println("Caught FourExecption in first block");
        }finally {
            System.out.println("Finally in first block");
        }
    }
}
