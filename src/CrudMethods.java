import java.util.List;
public interface CrudMethods <T>{

    List<T>findAll();

    void save(T t);

    T findById(int id);

    void delete(int id);

    void update(T t);


}
