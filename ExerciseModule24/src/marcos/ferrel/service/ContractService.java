package marcos.ferrel.service;

import marcos.ferrel.dao.ContractDao;
import marcos.ferrel.dao.IContractDao;

/**
 * @author marcos.ferrel
 */
public class ContractService implements IContractService{

    private IContractDao contractDao;

    public ContractService(IContractDao dao) {
        this.contractDao = dao;
    }

    @Override
    public String save() {
        contractDao.save();
        return "Success";
    }

    @Override
    public String find() {
        contractDao.find();
        return "Success";
    }

    @Override
    public String remove() {
        contractDao.remove();
        return "Success";
    }

    @Override
    public String update() {
        contractDao.update();
        return "Success";
    }
}
