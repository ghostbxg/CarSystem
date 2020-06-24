package service;

import daoimpl.PayDaoImpl;
import javabean.Pay;

import java.util.List;

public class PayService {
    PayDaoImpl pd = new PayDaoImpl();

    public List<Pay> selectBy(String name){
        List<Pay> list= pd.selectBy(name, null);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public List<Pay> select(){
        List<Pay> list = pd.selectAll();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public boolean insert(Pay c){
        if(pd.insert(c)!=0){
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        if(pd.delete(id)!=0){
            return true;
        }
        return false;
    }

    public boolean update(Pay c){
        if(pd.update(c)!=0){
            return true;
        }
        return false;
    }
}
