import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Manage {
    private List<Student> students;

    public Manage() {
        students = new ArrayList<>();
    }

    public void add(String path, Student student, String path2) throws IOException {
        students.add(student);
        write(path, students );
        writeToCSV(path2, students);
    }

    public void display(String path, String path2) throws IOException, ClassNotFoundException {
        System.out.println("File dat:");
        for (Student student : read(path)) {
            System.out.println(student);
        }
        System.out.println("File csv:");
        for (Student student : readFileCSV(path2)) {
            System.out.println(student);
        }
    }

    public void edit(String path, String name, Student student1,String path2) throws IOException {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                student.setName(student1.getName());
                student.setAddress(student1.getAddress());
                student.setAge(student1.getAge());
            }
        }
        write(path, students);
        writeToCSV(path2, students);
    }

    public void delete(String path, String name,String path2) throws IOException {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                students.remove(i);
            }
        }

        write(path, students);
        writeToCSV(path2, students);
    }

    public void search(String path, String name) throws IOException, ClassNotFoundException {
        List<Student> list = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                list.add(student);
            }
        }
        write(path, list);
        for (Student student : read(path)) {
            System.out.println(student);
        }
    }

    public void sort(String path,String path2) throws IOException {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        write(path, students);
        writeToCSV(path2, students);
    }

    public boolean check(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void write(String path, List<Student> list ) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void writeToCSV(String path, List<Student> list) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Student student : list) {
            bufferedWriter.write(student.getName() + "," + student.getAge() + "," + student.getAddress()+"\n");
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    public List<Student> readFileCSV(String path) throws IOException {
        List<Student> list = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(",");
            list.add(new Student(arr[0], Integer.parseInt(arr[1]), arr[2]));
        }
        return list;
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
