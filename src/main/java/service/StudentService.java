package service;

import daoimpl.StudentDaoImpl;
import javabean.Students;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class StudentService {

    StudentDaoImpl sd = new StudentDaoImpl();
    public Students selectBy(String name, String number){
        List<Students> list = sd.selectBy(name, number);
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;
    }

    public List<Students> select(){
        List<Students> list = sd.selectAll();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public boolean insert(Students s){
        if(sd.insert(s)!=0){
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        if(sd.delete(id)!=0){
            return true;
        }
        return false;
    }

    public boolean update(Students c){
        if(sd.update(c)!=0){
            return true;
        }
        return false;
    }


}
