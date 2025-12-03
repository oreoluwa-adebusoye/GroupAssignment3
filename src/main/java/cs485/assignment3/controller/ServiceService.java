package cs485.assignment3.controller;
import cs485.assignment3.model.dao.ServiceDAO;
import cs485.assignment3.model.entity.Service;
import java.util.List;
import java.text.SimpleDateFormat;

public class ServiceService {

    public Service createService(String name, String type, String description,
                                 double basePrice, int estimatedDuration) throws Exception {
        Service s = new Service();
        s.setName(name);
        s.setType(type);
        s.setDescription(description);
        s.setBasePrice(basePrice);
        s.setEstimatedDuration(estimatedDuration);

        ServiceDAO dao = new ServiceDAO();
        dao.create(s);

        return s;
    }

    public List<Service> getAllServices() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.list();
    }

    public void deleteService(int id) throws Exception {
        ServiceDAO dao = new ServiceDAO();
        dao.delete(id);
    }

    public void updateService(Service s) throws Exception {
        ServiceDAO dao = new ServiceDAO();
        dao.update(s);
    }

    public Service getServiceById(int id) throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.read(id);
    }
}
