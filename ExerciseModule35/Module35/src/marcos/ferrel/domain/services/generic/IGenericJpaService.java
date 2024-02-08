package marcos.ferrel.domain.services.generic;

import marcos.ferrel.dao.IPersistence;

import java.io.Serializable;

public interface IGenericJpaService <T extends IPersistence, E extends Serializable> {
}
