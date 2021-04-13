package javaCode.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2021-03-03-14:23
 */
public class CreateTxt {
    public static void main(String[] args) {
        int begin = 1000000;
        String prefix = "1380";
        String linekey = System.getProperty ("line.separator");
        StringBuilder sb = new StringBuilder ();
        sb.append (prefix);
        File file = new File ("E:/number.txt");
        try {
            RandomAccessFile randomFile = new RandomAccessFile ("E:/number.txt", "rw");
//            if (!file.exists ()) {
//                boolean newFile = file.createNewFile ();
//            }
            FileWriter fileWritter = new FileWriter (file.getName (), true);
            for (int i = begin; i < begin + 1000000; i++) {
                sb.append (i);
                randomFile.writeChars (sb.toString ());
                randomFile.writeChars (linekey);
//                fileWritter.write (sb.toString ());
//                fileWritter.write (linekey);
                sb.delete (4, 11);
            }
            randomFile.close ();
//            fileWritter.close ();
            System.out.println ("Done");

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

}
