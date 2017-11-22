package dao;

import dao.entity.USEREntity;
import org.springframework.data.repository.CrudRepository;

public interface USERRepository extends CrudRepository<USEREntity,Integer> {
}
