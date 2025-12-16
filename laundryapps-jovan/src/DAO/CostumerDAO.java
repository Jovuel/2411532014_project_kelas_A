package DAO;

import java.util.List;
import model.Costumer;

public interface CostumerDAO {
    public void save(Costumer cs);                   
    public void delete(String id);        
    public void update(Costumer cs);         
    public List<Costumer> show();   
}
