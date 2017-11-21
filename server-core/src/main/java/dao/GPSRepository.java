package dao;

import dao.entity.GPSEntity;
import org.springframework.data.repository.CrudRepository;

public interface GPSRepository extends CrudRepository<GPSEntity,Integer> {
}
