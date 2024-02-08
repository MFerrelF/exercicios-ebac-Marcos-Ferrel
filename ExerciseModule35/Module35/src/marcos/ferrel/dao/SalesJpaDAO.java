package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDAO;
import marcos.ferrel.domain.SalesJPA;
import marcos.ferrel.domain.exceptions.DAOException;
import marcos.ferrel.domain.exceptions.KeyTypeNotFoundException;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author marcos.ferrel
 */
public class SalesJpaDAO extends GenericJpaDAO<SalesJPA, Long> implements ISalesJpaDAO {

    public SalesJpaDAO() {
        super(SalesJPA.class);
    }

    @Override
    public void completeSales(SalesJPA sales) throws KeyTypeNotFoundException, DAOException {
        super.update(sales);
    }

    @Override
    public void cancelSales(SalesJPA sales) throws KeyTypeNotFoundException, DAOException {
        super.update(sales);
    }

    @Override
    public SalesJPA consultWithCollection(Long id) {
        openConnection();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SalesJPA> query = builder.createQuery(SalesJPA.class);
        Root<SalesJPA> root = query.from(SalesJPA.class);
        root.fetch("customer");
        root.fetch("products");
        query.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<SalesJPA> tpQuery = entityManager.createQuery(query);
        SalesJPA sales = tpQuery.getSingleResult();
        closeConnection();
        return sales;
    }
}
