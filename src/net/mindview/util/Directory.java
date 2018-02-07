/**   
 * @{#} Directory.java Create on 2018年2月2日 下午3:13:31 
 * @Title:  Directory.java   
 * @Package com.eternal.util   
 * @Description:
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by Hongji.Xu   
 */
package net.mindview.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        // the default iterable element is the file list
        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }
        
        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "TreeInfo [\n  files=" + files + "\n  dirs=" + dirs + "\n]";
        }

    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if(item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            }else {
                if(item.getName().matches(regex)) {
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if(args.length==0) {
            System.out.println(walk("."));
        }else {
            for (String arg : args) {
                System.out.println(walk(arg).files);
            }
        }
    }
}
