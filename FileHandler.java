package budget;

import java.io.*;


public class FileHandler<T> {
   private T object;

    FileHandler(T object) {
        this.object = object;
   }

   public void output() throws IOException {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(".\\purchases.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IOException();
        }
    }


    public void input() throws IOException {

        try {


            FileInputStream fileInputStream = new FileInputStream(".\\purchases.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = (T) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public T getObject() {
        return object;
    }
}
