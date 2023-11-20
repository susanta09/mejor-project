package in.correspondence.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.correspondence.entity.Plan;



public interface PlanRepository extends JpaRepository<Plan, Integer> {
	@Query("SELECT pId,planName From Plan")
	List<Object> getPlan();
	@Query(value = "SELECT * FROM plan Where case_num=?1",nativeQuery = true )
	Plan getPlanByFK(Integer caseNum);

}
