package in.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import in.admin.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {
	@Modifying
	@Transactional
    @Query("update Employee e set e.action=:action where e.id=:id")
	void updateActionById(String action,Integer id);

}
