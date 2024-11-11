import com.sun.security.jgss.GSSUtil;
import database.Database;
import models.House;
import models.HouseType;
import service.serviceImpl.CrudImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        House house1 = new House(1L, "House1 address", 1, 100, HouseType.VILLA, LocalDate.of(2020, 1, 1));
        House house2 = new House(2L, "House2 address", 2, 200, HouseType.APARTMENT, LocalDate.of(2020, 2, 2));
        House house3 = new House(3L, "House3 address", 3, 300, HouseType.COTTAGE, LocalDate.of(2020, 3, 3));
        House house4 = new House(4L, "House4 address", 4, 400, HouseType.COTTAGE, LocalDate.of(2020, 4, 4));
        House house5 = new House(5L, "House5 address", 5, 500, HouseType.APARTMENT, LocalDate.of(2020, 5, 5));
        CrudImpl impl = new CrudImpl();
        impl.createHouse(house1);
        impl.createHouse(house2);
        impl.createHouse(house3);
        impl.createHouse(house4);
        impl.createHouse(house5);

        System.out.println(Arrays.toString(impl.getHouseByHouseType(HouseType.COTTAGE)));

//        impl.updateHouse(3,house5);
        System.out.println(impl.deleteHouse(2));



        while (true) {
            System.out.println("""
                    1.Add house.
                    2.Get houses by type.
                    3.Get house by id.
                    4.Update house.
                    5.Delete house.
                    
                    """);
            int input = scanner1.nextInt();

            switch (input) {
                case 1 ->
                    impl.createHouse(House.addHouse());


                case 2 -> System.out.println(Arrays.toString(impl.getHouseByHouseType(House.houseTypeInput())));

                case 3 -> {

                    System.out.println("Type id:");

                    while (true) {
                        try {
                            long idInput = scanner.nextLong();
                            System.out.println(impl.getHouseById(idInput));
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Try again.");
                            scanner.nextLine();
                        }
                    }
                }

                case 4 -> {
                    System.out.println("Type id of house you want to update:");
                    while (true) {
                        try {
                            long idInput = scanner.nextLong();
                            if (House.checkId(idInput)) {
                                System.out.println("Changing house with id " + idInput);
                                System.out.println(impl.updateHouse(idInput, House.addHouse()));
                            } else {
                                throw new Exception("There is no home with such id.\nType id:");
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Try again.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            scanner.nextLine();
                        }
                    }
                }
                case 5->
                    impl.deleteHouse(scanner.nextLong());
                case 6-> System.out.println(Arrays.toString(Database.houses));
                default->
                    System.out.println("Give another command:");

            }

        }
    }



}