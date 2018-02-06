/**   
 * @{#} DirList.java Create on 2018年2月2日 上午11:55:04 
 * @Title:  DirList.java   
 * @Package chapter18.io   
 * @Description:
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by Hongji.Xu   
 */   
package chapter18.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;


class DirFilter implements FilenameFilter{
    private  Pattern pattern;
    
    public DirFilter(String regex) {
        pattern=Pattern.compile(regex);
    }
    
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
    
}
public class DirList {
    public static void main(String[] args) {
        File path = new File("E:\\copyright");
        
        String[] list;
        if(args.length==0) {
            list = path.list();
        }else {
            list=path.list(new DirFilter(args[0]));
        }
        
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
