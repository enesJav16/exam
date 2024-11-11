package service;

import models.House;
import models.HouseType;

public interface Crud {
    public String createHouse(House house);
    public House[] getHouseByHouseType(HouseType houseType);
    public House getHouseById(long id);
    public String updateHouse(long id, House house);
    public String deleteHouse(long id);
}
