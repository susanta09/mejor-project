package in.correspondence.servoce;

import java.util.List;

import in.correspondence.request.ReAction;
import in.correspondence.responce.CoEntityR;
import jakarta.servlet.http.HttpServletResponse;

public interface CorrespondenceService {
	public List<CoEntityR> getCorresposneData(Integer caseNum);
	public List<CoEntityR> getAllCorresposneData();
	public Integer performAction(ReAction action,HttpServletResponse response);

}
