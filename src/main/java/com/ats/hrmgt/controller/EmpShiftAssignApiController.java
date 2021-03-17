package com.ats.hrmgt.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.CountOfAssignPending;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.GetEmployeeDetailsForCarryFrwdLeave;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.SalaryTypesMaster;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.repo.CountOfAssignPendingRepository;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetEmployeeDetailsForCarryFrwdLeaveRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.GradeRepo;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.SalaryTypesMasterRepo;
import com.ats.hrmgt.repository.ShiftMasterRepository;
import com.ats.hrmgt.repository.TblEmpInfoRepo;
import com.ats.hrmgt.repository.UserRepo;

@RestController
public class EmpShiftAssignApiController {

	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	@Autowired
	CountOfAssignPendingRepository countOfAssignPendingRepository;

	@Autowired
	GetEmployeeDetailsForCarryFrwdLeaveRepo getEmployeeDetailsForCarryFrwdLeaveRepo;

	@RequestMapping(value = { "/getAllEmployeeDetail" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetail() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailList();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailBylocationId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailBylocationId(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailBylocationId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getLeftEmployeeDetailBylocationId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getLeftEmployeeDetailBylocationId(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getLeftEmployeeDetailBylocationId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailAccesibleLoc" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailAccesibleLoc() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailListForAccessibleLoc();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailAccesibleLocBylocationId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailAccesibleLocBylocationId(
			@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailAccesibleLocBylocationId(locId);
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailSkillRate" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailSkillRate() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			System.err.println("skill");
			list = getEmployeeDetailsRepo.getEmpDetailListForSkillRate();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailSkillRateLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailSkillRateLocId(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			System.err.println("skill");
			list = getEmployeeDetailsRepo.getAllEmployeeDetailSkillRateLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailShiftGroup" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailShiftGroup() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			System.err.println("skill");
			list = getEmployeeDetailsRepo.getAllEmployeeDetailShiftGroup();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailAccesssRole" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailAccesssRole() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			list = getEmployeeDetailsRepo.getAllEmployeeDetailAccesssRole();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailAccesssRoleLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailAccesssRole(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			list = getEmployeeDetailsRepo.getAllEmployeeDetailAccesssRoleLocId(locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailGradeLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailGradeLocId(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			list = getEmployeeDetailsRepo.getAllEmployeeDetailGradeLocId(locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getEmpDetailListByLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getEmpDetailListByLocId(@RequestParam("locationIds") List<Integer> locationIds) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailListByLocId(locationIds);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getEmpDetailListforassignshiftbulk" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getEmpDetailListforassignshiftbulk(@RequestParam("date") String date) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailListforassignshiftbulk(date);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailByEmpId" }, method = RequestMethod.POST)
	public GetEmployeeDetails getAllEmployeeDetailByEmpId(@RequestParam("empId") int empId) {
		GetEmployeeDetails list = new GetEmployeeDetails();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailList(empId);
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailByEmpId : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	// Sachin 7-06-2020 get multiple emp detail by comma sepe empIds
	@RequestMapping(value = { "/getEmpDetailByEmpIds" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getEmpDetailByEmpIds(@RequestParam("empIds") List<String> empIds) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailListByCsEmpIds(empIds);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@Autowired
	ShiftMasterRepository shiftMasterRepository;

	@RequestMapping(value = { "/getAllShiftList" }, method = RequestMethod.GET)
	public List<ShiftMaster> getAllShiftList() {
		List<ShiftMaster> list = new ArrayList<ShiftMaster>();
		try {
			list = shiftMasterRepository.findAll();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	SalaryTypesMasterRepo salaryTypesMasterRepo;
	
	@Autowired
	TblEmpInfoRepo empInfoRepo;

	@RequestMapping(value = { "/getSalryTypesMst" }, method = RequestMethod.GET)
	public @ResponseBody List<SalaryTypesMaster> getSalryTypesMst() {

		List<SalaryTypesMaster> list = new ArrayList<SalaryTypesMaster>();
		try {

			list = salaryTypesMasterRepo.findAllByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	EmpSalaryInfoRepo empSalaryInfoRepo;

	@RequestMapping(value = { "/salStructAssignmentUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info salStructAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("structId") String structId) {

		Info info = new Info();
		try {

			int res = 0;
			res = empSalaryInfoRepo.assignsalStruct(empIdList, structId);

			if (res > 0) {
				info.setError(false);
				info.setMsg("success");

			} else {
				info.setError(true);
				info.setMsg("failed");

			}
		} catch (Exception e) {

			System.err.println("Exce in deleteService  " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMsg("excep");
		}

		return info;

	}

	@Autowired
	UserRepo userRepo;

	@RequestMapping(value = { "/empParamAssignmentUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info empParamAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("upDateId") String upDateId, @RequestParam("flag") int flag) {

		Info info = new Info();
		try {

			int res = 0;

			if (flag == 1) {
				res = employeeMasterRepository.assignHoliCat(empIdList, upDateId);
			} else if (flag == 2) {
				res = employeeMasterRepository.assignComapny(empIdList, upDateId);
			} else if (flag == 3) {
				res = employeeMasterRepository.assignDept(empIdList, upDateId);
			} else if (flag == 4) {
				res = employeeMasterRepository.assignDesignation(empIdList, upDateId);
			} else if (flag == 5) {
				res = employeeMasterRepository.assignEmpType(empIdList, upDateId);
			} else if (flag == 6) {
				res = employeeMasterRepository.assignLocation(empIdList, upDateId);
			} else if (flag == 7) {
				res = employeeMasterRepository.assignShift(empIdList, upDateId);
			} else if (flag == 8) {
				res = employeeMasterRepository.weekHoliCat(empIdList, upDateId);
			} else if (flag == 9) {
				res = userRepo.updateAccLoc(empIdList, upDateId);
			} else if (flag == 10) {
				res = employeeMasterRepository.empSkillUpdate(empIdList, upDateId);
			} else if (flag == 11) {
				res = employeeMasterRepository.empEmpCategoryUpdate(empIdList, upDateId);
			} else if (flag == 12) {
				res = employeeMasterRepository.empEmpShiftGroupUpdate(empIdList, upDateId);
			} else if (flag == 13) {
				res = empInfoRepo.empEmpGradeUpdate(empIdList, upDateId);
			} else {
				res = 0;
			}

			if (res > 0) {
				info.setError(false);
				info.setMsg("success");

			} else {
				info.setError(true);
				info.setMsg("failed");

			}
		} catch (Exception e) {

			System.err.println("Exce in deleteService  " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMsg("excep");
		}

		return info;

	}

	@RequestMapping(value = { "/weekoffCatAssignmentUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info weekoffCatAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("holiCatId") String holiCatId) {

		Info info = new Info();
		try {

			System.err.println("hii");

			int res = 0;
			res = employeeMasterRepository.weekHoliCat(empIdList, holiCatId);

			if (res > 0) {
				info.setError(false);
				info.setMsg("success");

			} else {
				info.setError(true);
				info.setMsg("failed");

			}
		} catch (Exception e) {

			System.err.println("Exce in deleteService  " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMsg("excep");
		}

		return info;

	}

	// **************************MstEmpType*******************************************
	@Autowired
	MstEmpTypeRepository mstEmpTypeRepository;

	@RequestMapping(value = { "/saveMstEmpType" }, method = RequestMethod.POST)
	public @ResponseBody MstEmpType saveLeaveType(@RequestBody MstEmpType leaveType) {

		MstEmpType save = new MstEmpType();
		try {

			save = mstEmpTypeRepository.saveAndFlush(leaveType);
			if (save == null) {

				save = new MstEmpType();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getMstEmpTypeList" }, method = RequestMethod.POST)
	public @ResponseBody List<MstEmpType> getLeaveTypeListIsStructure(@RequestParam("companyId") int companyId) {

		List<MstEmpType> list = new ArrayList<MstEmpType>();
		try {

			list = mstEmpTypeRepository.findByDelStatusAndCompanyIdOrderByEmpTypeIdDesc(1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getMstEmpTypeById" }, method = RequestMethod.POST)
	public @ResponseBody MstEmpType getLeaveTypeList(@RequestParam("empTypeId") int empTypeId) {

		MstEmpType list = new MstEmpType();
		try {

			list = mstEmpTypeRepository.findByDelStatusAndEmpTypeId(1, empTypeId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getSalStructCountEmp" }, method = RequestMethod.POST)
	public @ResponseBody Integer getSalStructCountEmp() {

		List<EmployeeMaster> list = new ArrayList<>();
		int a = 0;
		try {

			list = employeeMasterRepository.getEmpSalAssign();

			a = list.size();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return a;

	}

	@RequestMapping(value = { "/getCountOfAssignForAttendance" }, method = RequestMethod.POST)
	public @ResponseBody CountOfAssignPending getCountOfAssignForAttendance(@RequestParam("locId") int locId) {

		CountOfAssignPending count = new CountOfAssignPending();

		try {

			count = countOfAssignPendingRepository.getCountOfAssignForAttendance(locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;

	}

	@Autowired
	EmployeeMasterRepository empRepo;

	@RequestMapping(value = { "/deleteLMstEmpType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLMstEmpType(@RequestParam("empTypeId") int empTypeId) {

		Info info = new Info();
		try {

			List<EmployeeMaster> empList = empRepo.findByEmpTypeAndDelStatus(empTypeId, 1);

			if (empList.size() <= 0) {
				int delete = mstEmpTypeRepository.deleteMstType(empTypeId);
				if (delete > 0) {
					info.setError(false);
					info.setMsg("Employee Type Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed to Delete Employee Type");
				}
			} else {
				info.setError(true);
				info.setMsg("Employee Type Can't Be Deleted as it is Assigned to Employee");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("Failed to Delete Employee Type");
		}

		return info;

	}

	@RequestMapping(value = { "/getAllEmployeeDetailForFullnFinal" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailForFullnFinal() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailForFullnFinal();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailForFullnFinalLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailForFullnFinalLocId(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailForFullnFinalLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	/// ******************************Asiignment of emp Related
	/// Master*****************

	// New to Solve conflict with Ak on 5-5-2020 5:03 pm
	// Sachin 04-05-2020
	@RequestMapping(value = { "/assignEmpLoginType" }, method = RequestMethod.POST)
	public @ResponseBody Info assignEmpLoginType(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("loginType") String loginType) {

		Info info = new Info();
		try {

			int res = 0;
			res = userRepo.updateUserLoginType(empIdList, loginType);
			if (res > 0) {
				info.setError(false);
				info.setMsg("success");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}
		} catch (Exception e) {
			info = new Info();
			info.setError(true);
			info.setMsg("exception");
		}
		return info;
	}

//Sachin 04-05-2020
	@RequestMapping(value = { "/getAllEmployeeDetailForLoginType" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailForLoginType() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailForLoginType();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailForLoginTypeLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailForLoginTypeLocId(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailForLoginTypeLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	// location accessible code 03-09-2020
	// Sachin 05-05-2020

	// Employee Company Assignment
	@RequestMapping(value = { "/getAllEmployeeDetailassignSubCompany" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignSubCompany() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignSubCompany();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailassignSubCompanyLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignSubCompanyLocId(
			@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignSubCompanyLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailshowAssignEmpType" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignEmpType() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignEmpType();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailshowAssignEmpTypeLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignEmpTypeLocId(
			@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignEmpTypeLocId(locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	// Employee Designation Assignment

	@RequestMapping(value = { "/getAllEmployeeDetailshowAssignDesignation" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignDesignation() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignDesignation();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailshowAssignDesignationLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignDesignationLocId(
			@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignDesignationLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// Employee Department Assignment
	@RequestMapping(value = { "/getAllEmployeeDetailassignDept" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignDept() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignDept();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailassignDept : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailassignDeptlocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignDeptlocId(@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignDeptlocId(locId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// Employee Location Assignment
	@RequestMapping(value = { "/getAllEmployeeDetailshowAssignLocation" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowAssignLocation() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowAssignLocation();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailshowAssignLocation : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	// Employee Holiday Category Assignment
	@RequestMapping(value = { "/getAllEmployeeDetailassignHolidayCategory" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignHolidayCategory() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignHolidayCategory();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailassignHolidayCategory : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailassignHolidayCategoryLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignHolidayCategoryLocId(
			@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignHolidayCategoryLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	// Employee Weekly Off Category Assignment

	@RequestMapping(value = { "/getAllEmployeeDetailassignWeekoffCategory" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignWeekoffCategory() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignWeekoffCategory();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailassignWeekoffCategory : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailassignWeekoffCategoryLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailassignWeekoffCategoryLocId(
			@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailassignWeekoffCategoryLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// Employee Salary Structure Assignment
	@RequestMapping(value = { "/getAllEmployeeDetailshowEmpListToAssignSalStruct" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignSalStruct() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowEmpListToAssignSalStruct();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailshowEmpListToAssignSalStruct : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailshowEmpListToAssignSalStructLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignSalStructLocId(
			@RequestParam("locId") List<Integer> locId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowEmpListToAssignSalStructLocId(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	// Employee Shift Assignment

	@RequestMapping(value = { "/getAllEmployeeDetailshowEmpListToAssignShift" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailshowEmpListToAssignShift() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmployeeDetailshowEmpListToAssignShift();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailassignSubCompany : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailForCarryForwordLeave" }, method = RequestMethod.POST)
	public List<GetEmployeeDetailsForCarryFrwdLeave> getAllEmployeeDetailForCarryForwordLeave(
			@RequestParam("locId") int locId) {

		List<GetEmployeeDetailsForCarryFrwdLeave> list = new ArrayList<>();
		try {
			list = getEmployeeDetailsForCarryFrwdLeaveRepo.getEmpDetailListForCarryForwardLeave(locId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getDetailForCarryForwordLeaveByEmpId" }, method = RequestMethod.POST)
	public GetEmployeeDetailsForCarryFrwdLeave getDetailForCarryForwordLeaveByEmpId(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {

		GetEmployeeDetailsForCarryFrwdLeave res = new GetEmployeeDetailsForCarryFrwdLeave();
		try {
			res = getEmployeeDetailsForCarryFrwdLeaveRepo.getDetailForCarryForwordLeaveByEmpId(empId, currYrId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	// Sachin 05-06-2020
	// Get Employees Eligible for Loan/Guarantor
	@RequestMapping(value = { "/getEmpsForLoanOrGuarantor" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getEmpsForLoanOrGuarantor() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpsForLoanOrGuarantor();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
}
