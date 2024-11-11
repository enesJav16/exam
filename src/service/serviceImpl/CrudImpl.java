package service.serviceImpl;

import database.Database;
import models.House;
import models.HouseType;
import service.Crud;

import java.util.InputMismatchException;

public class CrudImpl implements Crud {
    @Override
    public String createHouse(House house) {
        House[] newHouses = new House[Database.houses.length + 1];
        for (int i = 0; i < Database.houses.length; i++) {
            newHouses[i] = Database.houses[i];
        }
        newHouses[Database.houses.length] = house;
        Database.houses = newHouses;
        return "Success!";
    }

    @Override
    public House[] getHouseByHouseType(HouseType houseType) {
        int typeCount = 0;
        for (int i = 0; i < Database.houses.length; i++) {
            if (Database.houses[i].getHouseType() == houseType) {
                typeCount++;
            }
        }
        House[] housesByType = new House[typeCount];

        int index = 0;
        for (int i = 0; i < Database.houses.length; i++) {
            if (Database.houses[i].getHouseType() == houseType) {
                housesByType[index] = Database.houses[i];
                index++;
            }
        }

        return housesByType;
    }

    @Override
    public House getHouseById(long id) throws InputMismatchException {
        House house=null;
        for (int i = 0; i < Database.houses.length; i++) {
            if (Database.houses[i].getId() == id) {
                house= Database.houses[i];
            }
        }
        return house;
    }

    @Override
    public String updateHouse(long id, House house) {
        for (int i = 0; i < Database.houses.length; i++) {
            if (Database.houses[i].getId() == id) {
                Database.houses[i] = house;
            }
        }
        return "Success";
    }

    @Override
    public String deleteHouse(long id) {

        int deleteIndex = 0;

        for (int i = 0; i < Database.houses.length; i++) {
            if (Database.houses[i].getId() == id) {
                deleteIndex = i;
            }
        }
        House[] arr1 = new House[deleteIndex];

        if (arr1.length > 0) {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = Database.houses[i];
            }
        }

        int arr2Length = Database.houses.length - deleteIndex - 1;
        int continueIndex = deleteIndex + 1;

        House[] arr2 = new House[arr2Length];

        if (arr2.length > 0) {
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = Database.houses[continueIndex];
                continueIndex++;
            }
        }

        House[] finalHouses = new House[Database.houses.length - 1];

        int indexContinue = arr1.length;

        for (int i = 0; i < arr1.length; i++) {
            finalHouses[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            finalHouses[indexContinue] = arr2[i];
            indexContinue++;
        }

        Database.houses = finalHouses;

        return "Deleted";
    }


}

