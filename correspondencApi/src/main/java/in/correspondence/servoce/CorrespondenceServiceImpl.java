package in.correspondence.servoce;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import in.correspondence.entity.CoEntity;
import in.correspondence.pdfGenUnit.PdfGenaretionUnit;
import in.correspondence.repository.CitizenRepository;
import in.correspondence.repository.CoEntityRepository;
import in.correspondence.repository.EligibDetectionRepository;
import in.correspondence.repository.PlanRepository;
import in.correspondence.request.ReAction;
import in.correspondence.responce.CoEntityR;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class CorrespondenceServiceImpl implements CorrespondenceService {
	@Autowired
	private CoEntityRepository coEntityRepository;
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private EligibDetectionRepository detectionRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private PdfGenaretionUnit pdfGenaretionUnit;
	
	private List<CoEntityR> getCoERL(List<CoEntity> col )
	{
		List<CoEntityR>corl=new ArrayList<>();
		for (CoEntity coEntity : col) {
			Integer caseNum=coEntity.getCitizen().getCaseNum();
			CoEntityR cor=new CoEntityR();
			cor.setCaseNum(caseNum);
			cor.setPlanName(detectionRepository.getEligibDetectionByFK(caseNum).getPlanName());
			cor.setPlanStatus(detectionRepository.getEligibDetectionByFK(caseNum).getPlanStatus());
			cor.setStartDate(detectionRepository.getEligibDetectionByFK(caseNum).getStartDate());
			cor.setEndDate(detectionRepository.getEligibDetectionByFK(caseNum).getEndDate());
			cor.setBenefitAmount(detectionRepository.getEligibDetectionByFK(caseNum).getBenefitAmount());
			cor.setNoticeStatus(coEntityRepository.getCoEntityByFK(caseNum).getNoticeStatus());
			corl.add(cor);
		}
		return corl;
	}
	
	@Override
	public List<CoEntityR> getAllCorresposneData()
	{
		List<CoEntity>col=coEntityRepository.findAll();
		return getCoERL(col);
	}
	@Override
	public List<CoEntityR> getCorresposneData(Integer caseNum)
	{
		System.out.println(caseNum);
		CoEntity co=coEntityRepository.getCoEntityByFK(caseNum);
		List<CoEntity> col=new ArrayList<>();
		if (co != null) {
			col.add(co);
			CoEntityR c=getCoERL(col).get(0);
			if(c.getNoticeStatus().equals("progress"))
			{
				c.setNoticePrintDate(co.getNoticePrintDate());
				c.setTraceId(co.getCoId());
				c.setPrint(co.getPrint());
				c.setNoticeS3Url(co.getNoticeS3Url());
			}
			List<CoEntityR> l=new ArrayList<>();
			l.add(c);
			return l;
		} else {
			return new ArrayList<>();
		}
		
	}
	
	public Integer performAction(ReAction action,HttpServletResponse response)
	{
		CoEntity co=coEntityRepository.getCoEntityByFK(action.getCaseNum());
		if(co!=null)
		{
			Integer id=null;
			if(co.getNoticeStatus().equals("pending"))
			{
				co.setNoticeStatus("progress");
				co.setNoticePrintDate(LocalDate.now());
				try {
					pdfGenaretionUnit.exportToPDF(response,getCorresposneData(action.getCaseNum()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				co.setPrint("print");
				co.setNoticeS3Url("Added url");
				id=coEntityRepository.save(co).getCoId();
			}
			
			return id;
		}else {
			return null;
		}
		
	}


	

}
