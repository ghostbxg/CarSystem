package service;

import daoimpl.TakeCarDaoImpl;
import javabean.TakeCar;

import java.util.List;

public class TakeCarService {
    TakeCarDaoImpl td = new TakeCarDaoImpl();
    public List<TakeCar> selectBy(String lpnumber, String name){
        List<TakeCar> list= td.selectBy(lpnumber, name);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public List<TakeCar> select(){
        List<TakeCar> list = td.selectAll();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public boolean insert(TakeCar c){
        if(td.insert(c)!=0){
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        if(td.delete(id)!=0){
            return true;
        }
        return false;
    }

    public boolean update(TakeCar c){
        if(td.update(c)!=0){
            return true;
        }
        return false;
    }
}
