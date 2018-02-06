package chapter12.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TurnOffChecking {
    public static void main(String[] args) {
        WrapCheckException wce = new WrapCheckException();
        wce.throwRuntimeException(3);

        for(int i = 0 ;i<4;i++) {
            try {
                if(i<3) {
                    wce.throwRuntimeException(i);
                }else {
                    throw new SomeOtherException();
                }
            }catch (SomeOtherException e) {
                System.out.println("Some other Exception:"+e);
            }catch (RuntimeException re) {
                try {
                    throw re.getCause();
                }catch (FileNotFoundException e) {
                    System.out.println("File not found:"+e);
                }catch (IOException e) {
                    System.out.println("IO exception"+e);
                }catch (Throwable e) {
                    System.out.println("Throwable:"+e);
                }
                
            }
        }
    }
}
