package models;

import com.sun.security.jgss.GSSUtil;
import com.sun.tools.javac.Main;
import database.Database;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class House  {
    private long id;
    private String address;
    private int room;
    private double price;
    private HouseType houseType;
    private LocalDate builtDate;

    public House(){}
    public House(long id, String address, int room, double price, HouseType houseType, LocalDate builtDate) {
        this.id = id;
        this.address = address;
        this.room = room;
        this.price = price;
        this.houseType = houseType;
        this.builtDate = builtDate;
    }
    public House(long id,String address){
        this.id = id;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public LocalDate getBuiltDate() {
        return builtDate;
    }

    public void setBuiltDate(LocalDate builtDate) {
        this.builtDate = builtDate;
    }


    public static House addHouse() throws InputMismatchException{
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        House house=new House();
        while(true) {
            try {
                System.out.println("Give id:");
                long inputID=scanner1.nextLong();

                 house.setId(inputID);
                break;
            }catch (InputMismatchException e){
                System.out.println("Give number");
                scanner1.nextLine();
            }

        }
        System.out.println("Give address:");
        house.setAddress(scanner.nextLine());
        while(true) {
            try {
                System.out.println("Give rooms:");
                house.setRoom(scanner1.nextInt());
                break;
            }catch (InputMismatchException r){
                System.out.println("Give number.");
                scanner1.nextLine();
            }

        }
        while(true) {
            try {
                System.out.println("Give price:");
                double priceInout=scanner1.nextDouble();
                house.setPrice(priceInout);
                break;
            }catch (InputMismatchException e){
                System.out.println("Give price in double or int");
                scanner1.nextLine();
            }
        }
        while(true) {
            try {
                System.out.println("Give house type.");
                String houseTypeInput = scanner.nextLine();
                boolean check = false;
                for (HouseType type : HouseType.values()) {
                    if (houseTypeInput.equalsIgnoreCase(type.name())) {
                        house.setHouseType(type);
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    throw new Exception("There is no such type.");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        LocalDate date = null;
        boolean valid = false;

        while (!valid) {
            System.out.print("Give built date ( YYYY-MM-DD): ");
            String input = scanner.nextLine();

            try {
                date = LocalDate.parse(input);
                valid = true;
                house.setBuiltDate(date);
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect format.");
            }
        }



        return house;
    }
    public static HouseType houseTypeInput(){
        Scanner scanner=new Scanner(System.in);
        HouseType ret=null;
        String typeInput= scanner.nextLine();
        while(true) {
            try {
                boolean check = false;
                for (HouseType type : HouseType.values()) {
                    if (typeInput.equalsIgnoreCase(type.name())) {
                        ret= type;
                        check = true;
                    }
                }
                if (!check) {
                    throw new Exception("There is no such type.");
                }

            } catch (Exception e) {
                e.getMessage();

            }
            break;
        }
        return ret;
    }

    public static boolean checkId(long id) {
        boolean ret = false;
        for (House house : Database.houses) {
            if (house.getId() == id) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return
                "\nid: " + id +
                "\naddress: " + address +
                "\nroom: " + room +
                "\nprice: " + price +
                "\nhouseType: " + houseType +
                "\nbuiltDate: " + builtDate+
                "\n==========================";
    }
}
