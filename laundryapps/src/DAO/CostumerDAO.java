package DAO;

import java.util.List;
import model.Costumer;

public interface CostumerDAO {
    void save(Costumer c);           
    List<Costumer> show();           
    void delete(String id);        
    void update(Costumer c);         
}
