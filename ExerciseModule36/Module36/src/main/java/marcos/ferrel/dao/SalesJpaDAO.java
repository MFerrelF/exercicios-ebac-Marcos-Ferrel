package marcos.ferrel.dao;

import marcos.ferrel.dao.generics.GenericJpaDB1DAO;
import marcos.ferrel.domain.CustomerJpa;
import marcos.ferrel.domain.ProductJpa;
import marcos.ferrel.domain.SalesJpa;
import marcos.ferrel.exceptions.DAOException;
import marcos.ferrel.exceptions.KeyTypeNotFound;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author marcos.ferrel
 *
 */
public class SalesJpaDAO extends GenericJpaDB1DAO<SalesJpa, Long> implements ISalesJpaDAO {

    public SalesJpaDAO() {
        super(SalesJpa.class);
    }

    @Override
    public void finishSales(SalesJpa salesJpa) throws KeyTypeNotFound, DAOException {
        super.update(salesJpa);
    }

    @Override
    public void cancelSales(SalesJpa salesJpa) throws KeyTypeNotFound, DAOException {
        super.update(salesJpa);
    }

    @Override
    public SalesJpa consultWithCollection(Long id) {
        openConnection();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SalesJpa> query = builder.createQuery((SalesJpa.class));
        Root<SalesJpa> root = query.from(SalesJpa.class);
        root.fetch("customer");
        root.fetch("products");
        query.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<SalesJpa> typedQuery = entityManager.createQuery(query);
        SalesJpa salesJpa = typedQuery.getSingleResult();
        closeConnection();
        return salesJpa;
    }

    public SalesJpa register(SalesJpa entity) throws KeyTypeNotFound, DAOException {
        try {
            openConnection();
            entity.getProducts().forEach(product -> {
                    ProductJpa prodJpa = entityManager.merge(product.getProductJpa());
                    product.setProductJpa(prodJpa);
            } );
            CustomerJpa customerJpa = entityManager.merge(entity.getCustomerJpa());
            entity.setCustomerJpa(customerJpa);
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            closeConnection();
            return entity;
        } catch (Exception e) {
            throw new DAOException("ERROR WHILE SAVING SALES!!!", e);
        }
    }

    public void remove(SalesJpa entity) throws DAOException {
        throw new UnsupportedOperationException("OPERATION NOT ALLOWED!!!");
    }

}
