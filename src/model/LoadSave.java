package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author ricardo
 */


public class LoadSave{
       
    public static void saveGame(File file, Object obj) throws IOException{
        try(ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file))) {
            oout.writeObject(obj);
        }
    }

    public static Object loadGame(File file) throws IOException, ClassNotFoundException{
        try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file))) {
            return oin.readObject();
        }
    }
    
}
