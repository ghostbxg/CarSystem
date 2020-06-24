package service;

import daoimpl.CarDaoImpl;
import javabean.Car;

import java.util.List;

public class CarService {
    CarDaoImpl cd = new CarDaoImpl();

    public List<Car>  selectBy(String lpnumber, String password){
        List<Car> list = cd.selectBy(lpnumber, "0");
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public List<Car> select(){
        List<Car> list = cd.selectAll();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public boolean insert(Car c){
        if(cd.insert(c)!=0){
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        if(cd.delete(id)!=0){
            return true;
        }
        return false;
    }

    public boolean update(Car c){
        if(cd.update(c)!=0){
            return true;
        }
        return false;
    }
}
