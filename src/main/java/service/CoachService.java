package service;


import daoimpl.CoachDaoimpl;
import javabean.Coach;

import java.util.List;

public class CoachService {
    CoachDaoimpl cd = new CoachDaoimpl();

    public Coach selectBy(String name,String password){
        List<Coach> list = cd.selectBy(name, password);
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }

    public List<Coach> select(){
        List<Coach> list = cd.selectAll();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public boolean insert(Coach c){
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

    public boolean update(Coach c){
        if(cd.update(c)!=0){
            return true;
        }
        return false;
    }
}
