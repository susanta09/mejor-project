//package in.correspondence.s3BaketUnit;
//
//import java.io.File;
//import java.net.URL;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.amazonaws.services.s3.AmazonS3;
//@Component
//public class S3Utils {
//	@Value("${bucketName}")
//	private String bucketName;
//
//	private final AmazonS3 s3;
//
//	public S3Utils(AmazonS3 s3) {
//		this.s3 = s3;
//	}
//	public String uploadObjrct(File f)
//	{
//		s3.putObject(bucketName, f.getName(), f);
//		URL url=s3.getUrl(bucketName, f.getName());
//		return url.toExternalForm();
//	}
//
//}
