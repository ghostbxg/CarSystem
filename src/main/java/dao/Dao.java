package dao;


import java.util.List;

public interface Dao<T> {
    int insert(T t);
    int delete(int id);
    int update(T t);
    List<T> selectAll();
    List<T>  selectBy(String name,String number);
}
