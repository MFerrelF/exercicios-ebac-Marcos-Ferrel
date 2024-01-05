package marcos.ferrel.dao;

/**
 * @author marcos.ferrel
 */
public class ContractDao implements IContractDao{
    @Override
    public void save() {
        throw new UnsupportedOperationException("Does not work with database");
    }

    @Override
    public void find() {
        throw new UnsupportedOperationException("Contract not found in our database");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Contract not found in our database");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Contract not found in our database");
    }
}
