package in.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import in.login.entity.LogAccount;

public interface LogAccountRepository extends JpaRepository<LogAccount, Integer> {
	
	@Modifying
	@Transactional
	@Query("UPDATE LogAccount l  SET l.password=:cPass WHERE  l.password=:nPass AND l.email=:mail")
	void updateCpasswordByEmailAndNpassword(@Param("cPass") String cPass,@Param("mail") String mail,@Param("nPass") String nPass);
	
	@Query("From LogAccount l where l.password=:nPass AND l.email=:mail")
	LogAccount checkByEmailAndPassword(String nPass,String mail);
	
	@Query("SELECT l.password From LogAccount l where l.email=:mail")
	String findPasswordByEmail(String mail);
	
}
