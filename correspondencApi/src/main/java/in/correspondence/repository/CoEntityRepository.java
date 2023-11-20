package in.correspondence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.correspondence.entity.CoEntity;



public interface CoEntityRepository extends JpaRepository<CoEntity, Integer>{
	@Query(value = "SELECT * FROM co_entity  WHERE case_num=?1",nativeQuery = true )
	CoEntity getCoEntityByFK(Integer caseNum);
	
}
