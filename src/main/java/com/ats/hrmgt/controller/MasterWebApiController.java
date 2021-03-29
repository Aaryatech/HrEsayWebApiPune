package com.ats.hrmgt.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.hrmgt.claim.repository.GetClaimApplyAuthwiseRepo;
import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.common.RandomString;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.DashboardCount;
import com.ats.hrmgt.model.EmpType;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.GetLeaveStatus;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveDetail;
import com.ats.hrmgt.model.LeaveSummary;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.model.LoginResponse;
import com.ats.hrmgt.model.MailByUsername;
import com.ats.hrmgt.model.User;
import com.ats.hrmgt.model.claim.GetClaimApplyAuthwise;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.DashboardRepo;
import com.ats.hrmgt.repository.EmpTypeRepository;
import com.ats.hrmgt.repository.EmployeeInfoRepository;
import com.ats.hrmgt.repository.GetLeaveStatusRepo;
import com.ats.hrmgt.repository.LeaveDetailRepo;
import com.ats.hrmgt.repository.LeaveSummaryRepository;
import com.ats.hrmgt.repository.LocationRepository;
import com.ats.hrmgt.repository.LoginResponseRepo;
import com.ats.hrmgt.repository.LoginResponseRepository;
import com.ats.hrmgt.repository.MailByUsernameRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.UserRepo;

@RestController
public class MasterWebApiController {
	@Autowired
	UserRepo userRepo;

	@Autowired
	SettingRepo settingRepo;

	/*
	 * @Autowired GetEmpInfoRepo getEmpInfo;
	 * 
	 * @Autowired GetUserDataRepo getUserDataRepo;
	 */

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	EmpTypeRepository empTypeRepository;

	@Autowired
	LeaveSummaryRepository leaveSummaryRepository;

	@Autowired
	LoginResponseRepo loginResponseRepo;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	DashboardRepo dashboardRepo;

	@Autowired
	LeaveDetailRepo leaveDetailRepo;

	@Autowired
	GetLeaveStatusRepo getLeaveStatusRepo;

	@Autowired
	EmployeeInfoRepository employeeInfoRepository;

	@Autowired
	LoginResponseRepository loginResponseRepository;

	@Autowired
	MailByUsernameRepo mailByUsernameRepo;

	@Autowired
	GetClaimApplyAuthwiseRepo getClaimApplyAuthwiseRepo;
	/*
	 * <dependency> <groupId>javax.mail</groupId> <artifactId>mail</artifactId>
	 * <version>1.4</version> </dependency>
	 */

	static String senderEmail = "atsinfosoft@gmail.com";
	static String senderPassword = "atsinfosoft@123";
	static String mailsubject = " HRMS Password Recovery";

	/*
	 * @RequestMapping(value = { "/GetEmployeeInfo" }, method = RequestMethod.POST)
	 * public @ResponseBody GetEmployeeInfo getEmployeeInfo(@RequestParam("empId")
	 * int empId) {
	 * 
	 * GetEmployeeInfo company = new GetEmployeeInfo(); try {
	 * 
	 * company = getEmpInfo.getEmpByEmpId(empId);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return company;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/checkUserName" }, method = RequestMethod.POST)
	 * public @ResponseBody GetUserData checkUserName( @RequestParam("inputValue")
	 * String inputValue) {
	 * 
	 * GetUserData user = new GetUserData(); try {
	 * 
	 * user = getUserDataRepo.getUserByEmailId(inputValue);
	 * 
	 * if(user==null) { user = new GetUserData(); user.setError(true); }else {
	 * user.setError(false); String finalmsg="Your Password is:"+user.getUserPwd()+
	 * "\n DO NOT REPLY to this EMAIL-ID - contact HR.PUNE@infrabeat.com.";
	 * 
	 * Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com",
	 * "atsinfosoft@123", user.getEmpEmail(), " HRMS Password Recovery",
	 * user.getEmpEmail(),finalmsg ); } } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return user;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @RequestMapping(value = { "/getUserInfoByEmpId" }, method =
	 * RequestMethod.POST) public @ResponseBody User
	 * getUserInfoByEmpId(@RequestParam("empId") int empId) {
	 * 
	 * User user = new User(); try {
	 * 
	 * user = userRepo.findByEmpIdAndDelStatus(empId,1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return user;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/getUserInfoByEmpIdPass" }, method =
	 * RequestMethod.POST) public @ResponseBody User
	 * getUserInfoByEmpIdPass(@RequestParam("empId") int
	 * empId,@RequestParam("password") String password) {
	 * 
	 * User user = new User(); try {
	 * 
	 * user = userRepo.getSpecificUserRecord(empId,password);
	 * 
	 * if(user==null) { user = new User(); user.setError(true); }else {
	 * user.setError(false);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * System.out.println(user);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return user;
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = { "/updateUserPass" }, method = RequestMethod.POST)
	 * public @ResponseBody Info updateUserPass(@RequestParam("empId") int
	 * empId,@RequestParam("password") String password) {
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * int delete = userRepo.updateUserPassword(empId,password);
	 * 
	 * if (delete > 0) { info.setError(false); info.setMsg("deleted"); } else {
	 * info.setError(true); info.setMsg("failed"); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	 * 
	 * return info;
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = { "/GetCurrCalYear" }, method = RequestMethod.POST)
	 * public @ResponseBody GetEmployeeInfo GetCurrCalYear() {
	 * 
	 * GetEmployeeInfo company = new GetEmployeeInfo(); try {
	 * 
	 * company = getEmpInfo.getEmpByEmpId(1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return company;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public @ResponseBody LoginResponse loginUser(@RequestParam("username") String userName,
			@RequestParam("userPass") String pass) {

		LoginResponse loginResponse = new LoginResponse();
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(pass.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			loginResponse = loginResponseRepository.loginProcess(userName, hashtext);

			if (loginResponse == null) {
				loginResponse = new LoginResponse();
				loginResponse.setError(true);
				// loginResponse.setMsg("record Not found");
			} else {
				loginResponse.setError(false);
				// loginResponse.setMsg("Record Found");
			}

		} catch (Exception e) {
			e.printStackTrace();

			loginResponse = new LoginResponse();
			loginResponse.setError(true);
			// loginResponse.setMsg("record Not found");
		}

		return loginResponse;

	}

	@RequestMapping(value = { "/getDashboardCount" }, method = RequestMethod.POST)
	public @ResponseBody DashboardCount getDashboardCount(@RequestParam("empId") int empId) {

		DashboardCount dashboardCount = new DashboardCount();
		try {

			CalenderYear calendearYear = new CalenderYear();
			calendearYear = calculateYearRepository.findByIsCurrent(1);
			int curYrId = 0;
			if (calendearYear != null) {
				curYrId = calendearYear.getCalYrId();
			}

			dashboardCount = dashboardRepo.getDashboardCount(empId, curYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return dashboardCount;

	}

	@RequestMapping(value = { "/getLeaveStatusList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveDetail> getLeaveStatuslist(@RequestParam("empId") int empId,
			@RequestParam("status") List<Integer> status) {

		List<LeaveDetail> resList = new ArrayList<LeaveDetail>();

		try {

			CalenderYear calendearYear = new CalenderYear();
			calendearYear = calculateYearRepository.findByIsCurrent(1);

			int curYrId = 0;
			if (calendearYear != null) {
				curYrId = calendearYear.getCalYrId();
			}
			// System.err.println("list "+leaveDetailRepo.getLeaveStatus1(empId, status,
			// curYrId));

			resList = leaveDetailRepo.getLeaveStatus1(empId, status, curYrId);
			if (resList != null) {
				for (int i = 0; i < resList.size(); i++) {
					List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
					leaveStatus = getLeaveStatusRepo.getLeaveTrailByLeaveId(resList.get(i).getLeaveId());
					resList.get(i).setGetLeaveStatusList(leaveStatus);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resList;

	}

	/*
	 * @RequestMapping(value = { "/getClaimStatusList" }, method =
	 * RequestMethod.POST) public @ResponseBody List<ClaimDetail>
	 * getClaimStatusList(@RequestParam("empId") int empId,
	 * 
	 * @RequestParam("status") List<Integer> status) {
	 * 
	 * List<ClaimDetail> list = new ArrayList<ClaimDetail>();
	 * 
	 * try {
	 * 
	 * list = claimDetailRepo.getClaimStatus(empId, status); if (list != null) { for
	 * (int i = 0; i < list.size(); i++) { List<GetClaimTrailStatus> leaveStatus =
	 * new ArrayList<GetClaimTrailStatus>(); leaveStatus =
	 * getClaimTrailStatusRepo.getClaimTrailByClaimId(list.get(i).getClaimId());
	 * list.get(i).setGetClaimTrailStatus(leaveStatus); } }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/getClaimStatusList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimApplyAuthwise> getClaimStatusList(@RequestParam("empId") int empId) {
		List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();

		try {

			list = getClaimApplyAuthwiseRepo.getClaimStatusList(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	/*
	 * @RequestMapping(value = { "/getEmpListForClaimAuthByEmpId" }, method =
	 * RequestMethod.POST) public @ResponseBody List<EmployeeInfo>
	 * getEmpListForClaimAuthByEmpId(@RequestParam("empId") int empId) {
	 * 
	 * List<EmployeeInfo> list = new ArrayList<EmployeeInfo>(); try {
	 * 
	 * list = employeeInfoRepository.getEmpListForClaimByEmpId(empId);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */
	/*
	 * @RequestMapping(value = { "/checkUniqueEmail" }, method = RequestMethod.POST)
	 * public @ResponseBody Info checkUniqueEmail(@RequestParam("email") String
	 * email) {
	 * 
	 * Info info=new Info(); Location location = new Location(); try {
	 * 
	 * location = locationRepository.findByLocHrContactEmailAndDelStatus(email,1);
	 * System.out.print("Loction :"+location); if(location!=null) {
	 * info.setError(false); info.setMsg("Record Found"); } else {
	 * info.setError(true); info.setMsg("Record Not Found"); }
	 * 
	 * 
	 * } catch (Exception e) { info.setError(true); info.setMsg("Record Not Found");
	 * e.printStackTrace(); }
	 * 
	 * return info;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/checkUniqueMobileNumber" }, method =
	 * RequestMethod.POST) public @ResponseBody Info
	 * checkUniqueMobileNumber(@RequestParam("mobileNo") String mobileNo) {
	 * 
	 * Info info=new Info(); Location location = new Location(); try {
	 * 
	 * location =
	 * locationRepository.findByLocHrContactNumberAndDelStatus(mobileNo,1);
	 * System.out.print("Loction :"+location); if(location!=null) {
	 * info.setError(false); info.setMsg("Record Found"); } else {
	 * info.setError(true); info.setMsg("Record Not Found"); }
	 * 
	 * 
	 * } catch (Exception e) { info.setError(true); info.setMsg("Record Not Found");
	 * e.printStackTrace(); }
	 * 
	 * return info;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/saveLeaveSummary" }, method = RequestMethod.POST)
	 * public @ResponseBody LeaveSummary saveLeaveSummary(@RequestBody LeaveSummary
	 * employeeCategory) {
	 * 
	 * 
	 * LeaveSummary save = new LeaveSummary(); try {
	 * 
	 * save = leaveSummaryRepository.saveAndFlush(employeeCategory);
	 * 
	 * if(save!=null) { save.setError(false); }else {
	 * 
	 * save = new LeaveSummary(); save.setError(true); }
	 * 
	 * 
	 * } catch (Exception e) { save = new LeaveSummary(); save.setError(true);
	 * e.printStackTrace(); }
	 * 
	 * return save;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/getLeaveSummaryList" }, method =
	 * RequestMethod.POST) public @ResponseBody List<LeaveSummary>
	 * getLeaveSummaryList(@RequestParam("compId") int compId) {
	 * 
	 * 
	 * List<LeaveSummary> list = new ArrayList<LeaveSummary>(); try {
	 * 
	 * if(compId!=0) {
	 * 
	 * list = leaveSummaryRepository.findByDelStatusAndCompanyId(1,compId);
	 * 
	 * }else {
	 * 
	 * list = leaveSummaryRepository.findByDelStatus(1);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/deleteLeaveSummary" }, method =
	 * RequestMethod.POST) public @ResponseBody Info
	 * deleteLeaveSummary(@RequestParam("lvSumupId") int lvSumupId) {
	 * 
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * int delete = leaveSummaryRepository.deleteLeaveSummary(lvSumupId);
	 * 
	 * if(delete>0) { info.setError(false); info.setMsg("deleted"); }else {
	 * info.setError(true); info.setMsg("failed"); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	 * 
	 * return info;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/getLeaveSummaryById" }, method =
	 * RequestMethod.POST) public @ResponseBody LeaveSummary
	 * getLeaveSummaryById(@RequestParam("lvSumupId") int lvSumupId) {
	 * 
	 * 
	 * LeaveSummary employeeCategory = new LeaveSummary(); try {
	 * 
	 * employeeCategory =
	 * leaveSummaryRepository.findByLvSumupIdAndDelStatus(lvSumupId,1);
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return employeeCategory;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/getEmployeeListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeInfo> getEmployeeListByEmpId(@RequestParam("empId") int empId) {

		List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
		try {

			list = employeeInfoRepository.getEmployeeListByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveTrailList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveStatus> getLeaveTrailList(@RequestParam("leaveId") int leaveId) {

		List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
		try {
			leaveStatus = getLeaveStatusRepo.getLeaveTrailByLeaveId(leaveId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveStatus;

	}

	@RequestMapping(value = { "/updateIsVistStatus" }, method = RequestMethod.POST)
	public @ResponseBody Info updateIsVistStatus(@RequestParam("empId") int empId,
			@RequestParam("password") String password) {

		Info info = new Info();

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			int update = userRepo.updateIsVistStatus(empId, hashtext);

			if (update > 0) {

				info.setError(false);
				info.setMsg("successfully password changed");

			} else {
				info.setError(true);
				info.setMsg("failed password changed");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/checkUserName" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUserName(@RequestParam("inputValue") String inputValue) {

		Info emailRes = new Info();
		try {

			// MailByUsername mailByUsername =
			// mailByUsernameRepo.getUserByEmailId(inputValue);

			LoginResponse loginResponse = new LoginResponse();

			/*if (inputValue.contains("@")) {
				System.err.println("Its Email");
				loginResponse = loginResponseRepository.CheckUserForPasswordByEmail(inputValue);

			} else {*/
				System.err.println("Its Code ");
				loginResponse = loginResponseRepository.CheckUserForPasswordByUsername(inputValue);
			//}
			if (loginResponse != null) {
				RandomString randomString = new RandomString();
				String password = randomString.nextString();
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(password.getBytes());
				BigInteger number = new BigInteger(1, messageDigest);
				String hashtext = number.toString(16);

				String finalmsg = "Your Password is:" + password + "\n DO NOT REPLY to this EMAIL-ID";

				emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123",
						loginResponse.getEmailId(), " Password Recovery", loginResponse.getEmailId(), finalmsg);
				int update = userRepo.updateIsVistStatus(loginResponse.getEmpId(), hashtext);
			} else {
				emailRes.setError(true);
			}

			/*
			 * RandomString randomString = new RandomString(); String password =
			 * randomString.nextString(); MessageDigest md =
			 * MessageDigest.getInstance("MD5"); byte[] messageDigest =
			 * md.digest(password.getBytes()); BigInteger number = new BigInteger(1,
			 * messageDigest); String hashtext = number.toString(16);
			 * 
			 * String finalmsg = "Your Password is:" + password +
			 * "\n DO NOT REPLY to this EMAIL-ID";
			 * 
			 * emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123",
			 * mailByUsername.getEmail(), " HRMS Password Recovery",
			 * mailByUsername.getEmail(), finalmsg); int update =
			 * userRepo.updateIsVistStatus(mailByUsername.getEmpId(), hashtext);
			 */
		} catch (Exception e) {

			e.printStackTrace();
		}

		return emailRes;

	}

	@RequestMapping(value = { "/updateEmpProfPicForApp" }, method = RequestMethod.POST)
	public @ResponseBody Info updateEmpProfPicForApp(@RequestParam("empId") int empId,
			@RequestParam("profilePic") MultipartFile profilePic) {

		Info info = new Info();

		try {

			// String imageSaveUrl = "/home/lenovo/Downloads/myUploads/";
			String imageSaveUrl = "/opt/tomcat/webapps/hr/";
			String getImageSaveUrl = "http://ifbthrms.infrabeat.com:8181/hr/";
			String[] allowExt = { "jpg", "jpeg", "gif", "png" };
			int isResize = 0;
			int width = 0;
			int hieght = 0;
			int isCheckSize = 0;
			int imageSizeMax = 0;

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			Boolean ret = false;
			if (ret == false) {

				if (profilePic.getOriginalFilename() != "") {
					System.out.println("before split " + profilePic.getOriginalFilename());
					String tempImg = profilePic.getOriginalFilename().split("\\.")[0];
					System.out.println("After split " + tempImg);
					tempImg = tempImg.concat(".").concat("png");
					System.out.println("final split " + tempImg);

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + tempImg;

					try {

						// start

						String extension = FilenameUtils.getExtension(tempImg);

						if (ArrayUtils.contains(allowExt, extension.toLowerCase())) {

							Path path = Paths.get(imageSaveUrl + imageName);

							byte[] bytes = profilePic.getBytes();

							Files.write(path, bytes);

							if (isResize == 1) {

								Image img = null;
								BufferedImage tempPNG = null;

								File newFilePNG = null;

								// System.out.println("File " + imageName);
								img = ImageIO.read(new File(imageSaveUrl + imageName));
								tempPNG = EmailUtility.resizeImage(img, width, hieght);

								newFilePNG = new File(imageSaveUrl + "thumbnail" + imageName);

								ImageIO.write(tempPNG, extension, newFilePNG);

								// System.out.println("DONE");
							}

							info.setError(false);
							info.setMsg("Upload Successfully ");
							// System.err.println("imageName " + imageName);
							// int up = employeeInfoRepository.updateEmpProfPic(empId, imageName);
							int up = 0;
							if (up > 0) {
								info.setError(false);
								info.setMsg(imageName);
							} else {
								info.setError(true);
								info.setMsg("failed");
							}
						} else {
							info.setError(true);
							info.setMsg("Error While Uploading Image");
						}

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return info;

	}

	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody Info photoUpload(@RequestParam("file") MultipartFile[] uploadfile,
			@RequestParam("imageName") List<String> imageName, @RequestParam("type") String type) {

		System.out.println("HELLO------------------------");

		System.err.println(" no  of files to push " + uploadfile.length);
		Info info = new Info();

		// System.out.println("File Name " + imageName.toString());

		try {

			saveUploadedFiles(uploadfile, imageName, type);

			info.setError(false);
			info.setMsg("File uploaded successfully");

		} catch (IOException e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("File upload failed");
		}

		return info;
	}

	/*
	 * private static String DOC_URL= "/opt/apache-tomcat-8.5.6/webapps/media/hr/";
	 * public static final String leaveDocSaveUrl =
	 * "/home/lenovo/Downloads/old/apache-tomcat-8.5.37/webapps/media/";
	 */

	private static String DOC_URL = "/opt/apache-tomcat-8.5.47/webapps/hrdocument/mixDoc/";
	public static final String leaveDocSaveUrl = "/home/lenovo/Downloads/old/apache-tomcat-8.5.37/webapps/media/";

	private void saveUploadedFiles(MultipartFile[] files, List<String> imageName, String type) throws IOException {

		try {
			for (int i = 0; i < files.length; i++) {
				Path path = null;

				if (type.equalsIgnoreCase("1")) {

					String name = imageName.get(i).substring(1, imageName.get(i).length() - 1);

					path = Paths.get(DOC_URL + name);
				} else if (type.equalsIgnoreCase("3")) {

					String name = imageName.get(i).substring(1, imageName.get(i).length() - 1);

					path = Paths.get(leaveDocSaveUrl + name);
				}

				byte[] bytes = files[i].getBytes();

				Files.write(path, bytes);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
