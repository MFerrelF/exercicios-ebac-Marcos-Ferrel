package marcos.ferrel;

import marcos.ferrel.dao.ContractDao;
import marcos.ferrel.dao.ContractDaoMock;
import marcos.ferrel.dao.IContractDao;
import marcos.ferrel.service.ContractService;
import marcos.ferrel.service.IContractService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author marcos.ferrel
 */

public class ContractServiceTest {

    @Test
    public void saveContractTest() {
        IContractDao mock = new ContractDaoMock();
        IContractService service = new ContractService(mock);
        String ret = service.save();
        Assert.assertEquals("Success", ret);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveContractTestError() {
        IContractDao dao = new ContractDao();
        IContractService service = new ContractService(dao);
        String ret = service.save();
        Assert.assertEquals("Success", ret);
    }

    @Test
    public void findContractTest() {
        IContractDao mock = new ContractDaoMock();
        IContractService service = new ContractService(mock);
        String ret = service.find();
        Assert.assertEquals("Success", ret);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findContractTestError() {
        IContractDao dao = new ContractDao();
        IContractService service = new ContractService(dao);
        String ret = service.find();
        Assert.assertEquals("Success", ret);
    }

    @Test
    public void removeContractTest() {
        IContractDao mock = new ContractDaoMock();
        IContractService service = new ContractService(mock);
        String ret = service.remove();
        Assert.assertEquals("Success", ret);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeContractTestError() {
        IContractDao dao = new ContractDao();
        IContractService service = new ContractService(dao);
        String ret = service.remove();
        Assert.assertEquals("Success", ret);
    }

    @Test
    public void updateContractTest() {
        IContractDao mock = new ContractDaoMock();
        IContractService service = new ContractService(mock);
        String ret = service.update();
        Assert.assertEquals("Success", ret);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateContractTestError() {
        IContractDao dao = new ContractDao();
        IContractService service = new ContractService(dao);
        String ret = service.update();
        Assert.assertEquals("Success", ret);
    }


}
