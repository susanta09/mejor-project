package in.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import in.admin.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
	@Modifying
	@Transactional
    @Query("update Plan p set p.action=:action where p.id=:id")
	void updateActionById(String action,Integer id);

}
