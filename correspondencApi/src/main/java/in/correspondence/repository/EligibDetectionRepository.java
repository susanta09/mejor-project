package in.correspondence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.correspondence.entity.EligibDetection;


public interface EligibDetectionRepository extends JpaRepository<EligibDetection, Integer> {
	@Query(value = "SELECT * FROM eligib_detection WHERE case_num=?1",nativeQuery = true )
	EligibDetection getEligibDetectionByFK(Integer caseNum);
}
