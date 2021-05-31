import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manage {
    private List<Student> students;

    public Manage() {
        students = new ArrayList<>();
    }
    public void add(String path, Student student) throws IOException {
        students.add(student);
        write(path,students);
    }
    public void display(String path) throws IOException, ClassNotFoundException {
        for (Student student:read(path)){
            System.out.println(student);
        }
    }
    public void edit(String path,String name,Student student1) throws IOException {
        for (Student student:students){
            if(student.getName().equals(name)){
                student.setName(student1.getName());
                student.setAddress(student1.getAddress());
                student.setAge(student1.getAge());
            }
        }
        write(path,students);
    }

    public void search(String path,String name) throws IOException, ClassNotFoundException {
        List<Student> list = new ArrayList<>();
        for (Student student:students){
            if(student.getName().equals(name)){
                list.add(student);
            }
        }
        write(path,list);
        for (Student student:read(path)){
            System.out.println(student);
        }
    }

    public boolean check(String name){
        for (Student student:students){
            if(student.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public void write(String path,List<Student> list) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public List<Student> read(String path) throws IOException, ClassNotFoundException {
        List<Student> list = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        list = (List<Student>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return list;
    }


}
