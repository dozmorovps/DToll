package dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSRepository extends CrudRepository<GPSEntity,Integer> {
}
