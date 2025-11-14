package DAO;

import java.util.List;
import model.Service;

public interface ServiceDAO {
    void save(Service s);
    List<Service> show();
    void delete(String id);
    void update(Service s);
}
