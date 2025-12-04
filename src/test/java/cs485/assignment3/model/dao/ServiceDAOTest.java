package cs485.assignment3.model.dao;
import cs485.assignment3.model.entity.Service;
import cs485.assignment3.model.dao.ServiceDAO;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ServiceDAOTest {
    @Test
    public void testFake(){
        assertAll(
                () -> assertEquals(1,1)
        );
    }

    @Test
    public void testCreateService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        dao.setTestDatabase();

        Service service = new Service();
        service.setName("Test Service");
        service.setType("Routine");
        service.setDescription("Test Description");
        service.setBasePrice(50.0);
        service.setEstimatedDuration(30);

        dao.create(service); // Method Under Test

        //clean up
        dao.delete(service.getID());

        assertAll(
                () -> assertNotNull(service.getID())
        );

    }

    @Test
    public void testReadService() throws Exception{
        ServiceDAO dao = new ServiceDAO();
        dao.setTestDatabase();

        Service service = new Service();
        service.setName("Test Service");
        service.setType("Routine");
        service.setDescription("Test Description");
        service.setBasePrice(50.0);
        service.setEstimatedDuration(30);

        dao.create(service);
        Service found = dao.read(service.getID()); //Function under test

        //clean up
        dao.delete( service.getID() );

        assertAll(
                () -> assertEquals(found.getID(), service.getID()),
                () -> assertEquals(found.getName(), service.getName()),
                () -> assertEquals(found.getType(), service.getType()),
                () -> assertEquals(found.getDescription(), service.getDescription()),
                () -> assertEquals(found.getBasePrice(), service.getBasePrice()),
                () -> assertEquals(found.getEstimatedDuration(), service.getEstimatedDuration())
        );

    }
    @Test
    public void testReadServiceDoesNotExist() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        dao.setTestDatabase();

        // Using an ID that should not exist
        Service found = dao.read(999999);

        assertAll(
                () -> assertNull(found.getID()),
                () -> assertNull(found.getName()),
                () -> assertNull(found.getType())
        );
    }

    @Test
    public void testListService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        dao.setTestDatabase();

        List<Service> before = dao.list();
        int beforeSize = before.size();

        // test service
        Service service = new Service();
        service.setName("List Service");
        service.setType("Grooming");
        service.setDescription("Service created in testListService");
        service.setBasePrice(75.0);
        service.setEstimatedDuration(45);

        dao.create(service);


        List<Service> after = dao.list();
        int afterSize = after.size();

        // Clean up
        dao.delete(service.getID());

        assertAll(
                () -> assertEquals(beforeSize + 1, afterSize)
        );

    }

    @Test
    public void testUpdateService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        dao.setTestDatabase();

        //create a service
        Service service = new Service();
        service.setName("Original Name");
        service.setType("Routine");
        service.setDescription("Before update");
        service.setBasePrice(60.0);
        service.setEstimatedDuration(30);

        dao.create(service); // Method Under Test

        //update values
        service.setName("Updated Name");
        service.setType("Emergency");
        service.setDescription("After update");
        service.setBasePrice(150.0);
        service.setEstimatedDuration(120);

        //update...
        dao.update(service); // Method under test

        //see what that is
        Service updated = dao.read(service.getID());

        // Clean up
        dao.delete(service.getID());

        assertAll(
                () -> assertEquals("Updated Name", updated.getName()),
                () -> assertEquals("Emergency", updated.getType()),
                () -> assertEquals("After update", updated.getDescription()),
                () -> assertEquals(150.0, updated.getBasePrice()),
                () -> assertEquals(120, updated.getEstimatedDuration())
        );
    }

    @Test
    public void testDeleteService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        dao.setTestDatabase();

        //create a service
        Service service = new Service();
        service.setName("Test Name");
        service.setType("Test Type");
        service.setDescription("Test Delete Description");
        service.setBasePrice(60.0);
        service.setEstimatedDuration(30);

        dao.create(service);

        Integer createdID = service.getID();

        dao.delete(createdID);

        Service found = dao.read(createdID);

        assertAll(
                () -> assertNull(found.getID()),
                () -> assertNull(found.getName()),
                () -> assertNull(found.getType()),
                () -> assertNull(found.getDescription())

        );


    }




}
