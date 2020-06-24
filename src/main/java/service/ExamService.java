package service;

import daoimpl.ExamDaoImpl;
import javabean.Exam;

import java.util.List;

public class ExamService {
    ExamDaoImpl ed = new ExamDaoImpl();

    public List<Exam> selectBy(String name, String password){
        List<Exam> list= ed.selectBy(name, null);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public List<Exam> select(){
        List<Exam> list = ed.selectAll();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public boolean insert(Exam c){
        if(ed.insert(c)!=0){
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        if(ed.delete(id)!=0){
            return true;
        }
        return false;
    }

    public boolean update(Exam c){
        if(ed.update(c)!=0){
            return true;
        }
        return false;
    }
}
