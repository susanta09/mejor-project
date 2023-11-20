package in.correspondence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.correspondence.entity.Citizen;


public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
//	@Modifying
//	@Transactional
//	@Query("UPDATE Citizen SET planId=:id WHERE caseNum=:caseNum")
//	void updateCaseNum(Integer id ,Integer caseNum);
//	Citizen findByCaseNum(Integer caseNum);

}
