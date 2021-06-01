import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Manage manage = new Manage();
        addData(manage);
        int choice;
        while (true) {
            displayMenu();
            System.out.println("Nhap lua chon");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    manage.add("test1.dat", inputStudent());
                    break;
                case 2:
                    manage.display("test1.dat");
                    break;
                case 3:
                    System.out.println("Nhap ten muon sua");
                    String name = sc.nextLine();
                    if (manage.check(name)) {
                        manage.edit("test1.dat", name, inputStudent());
                    } else {
                        System.out.println("invalid");
                    }
                    break;
                case 4:
                    System.out.println("Nhap ten muon tim");
                    name = sc.nextLine();
                    if (manage.check(name)) {
                        manage.search("test2.dat", name);
                    } else {
                        System.out.println("invalid");
                    }
                    break;
                case 5:
                    System.out.println("Nhap ten sinh vien muon xoa");
                    name = sc.nextLine();
                    if (manage.check(name)) {
                        manage.delete("test1.dat", name);
                        System.out.println("Success!");
                    } else {
                        System.out.println("invalid");
                    }
                    break;
                case 6:
                    manage.sort("test1.dat");
                    break;
                case 7:
                    manage.writeToCSV("test1.csv",new Student("Thanh",20,"HN"));
                    System.out.println(manage.readFileCSV("test1.csv"));


                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Nhap lai");
            }

        }

    }

    public static void displayMenu() {
        System.out.println("Menu");
        System.out.println("1.Add");
        System.out.println("2.Display");
        System.out.println("3.Edit");
        System.out.println("4.Search");
        System.out.println("5.Delete");
        System.out.println("6.Sort");
        System.out.println("0.Exit");
    }

    public static void addData(Manage manage) throws IOException {
        manage.add("test1.dat", new Student("name1", 20, "HN"));
        manage.add("test1.dat", new Student("name2", 25, "HP"));
        manage.add("test1.dat", new Student("name3", 22, "TH"));
        manage.add("test1.dat", new Student("name4", 27, "HD"));
        manage.add("test1.dat", new Student("name5", 24, "TB"));
    }

    public static Student inputStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten :");
        String name = sc.nextLine();
        System.out.println("Nhap tuoi");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap dia chi");
        String address = sc.nextLine();
        return new Student(name, age, address);
    }


}
