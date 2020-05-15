package com.ats.hrmgt.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.claim.repository.EmployeeRelatedTblsRepo;
import com.ats.hrmgt.claim.repository.ViewEmployeeRepo;
import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpDoctype;
import com.ats.hrmgt.model.EmpDriver;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.EmployeDoc;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.EmployeeRelatedTbls;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.TblEmpBankInfo;
import com.ats.hrmgt.model.TblEmpInfo;
import com.ats.hrmgt.model.TblEmpNominees;
import com.ats.hrmgt.model.User;
import com.ats.hrmgt.model.ViewEmployee;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpDoctypeRepo;
import com.ats.hrmgt.repository.EmpDriverRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.EmployeeDocsRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.TblEmpBankInfoRepo;
import com.ats.hrmgt.repository.TblEmpInfoRepo;
import com.ats.hrmgt.repository.TblEmpNomineesRepo;
import com.ats.hrmgt.repository.UserRepo;

@RestController
public class EmployeeApiController {
	@Autowired
	EmployeeMasterRepository empRepo;

	// EmpSupportRepository
	@Autowired
	TblEmpInfoRepo empInfoRepo;

	@Autowired
	TblEmpBankInfoRepo bankInfoRepo;

	@Autowired
	TblEmpNomineesRepo nomineeRepo;

	@Autowired
	EmpSalaryInfoRepo empSalRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	AllowancesRepo allowanceRepo;

	@Autowired
	EmpSalAllowanceRepo empSalAllowanceRepo;

	@Autowired
	EmpDoctypeRepo empDocRepo;

	@Autowired
	EmployeeDocsRepository employeeDocsRepository;

	@Autowired
	EmpDriverRepo empDriverRepo;

	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	/********************************** Employee *********************************/

	@RequestMapping(value = { "/getEmpInfoByEmpCode" }, method = RequestMethod.POST)
	public EmployeeMaster getEmpInfoByEmpCode(@RequestParam String empCode) {
		EmployeeMaster emp = new EmployeeMaster();
		try {
			emp = empRepo.findByEmpCodeAndDelStatus(empCode.trim(), 1);
			System.err.println("---" + emp.toString());
		} catch (Exception e) {
			System.err.println("Excep in getEmpInfoByEmpCode : " + e.getMessage());
			e.printStackTrace();
		}

		return emp;

	}

	@RequestMapping(value = { "/getMaxEmp" }, method = RequestMethod.GET)
	public int getMaxEmp() {
		int n = 0;
		EmployeeMaster emp = new EmployeeMaster();

		try {
			emp = empRepo.getEmpMax();
			n = emp.getEmpId();
		} catch (Exception e) {
			System.err.println("Excep in getEmpInfoByEmpCode : " + e.getMessage());
			e.printStackTrace();
		}

		return n;

	}

	@Autowired
	SettingRepo settingRepo;

	@RequestMapping(value = { "/getAllDriverEmployee" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllDriverEmployee() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		Setting setting = settingRepo.findByKey("designation_id");

		try {
			list = getEmployeeDetailsRepo.getDriverEmpDetailList(Integer.parseInt(setting.getValue()));
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployee : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployee" }, method = RequestMethod.POST)
	public List<EmployeeMaster> getAllEmployee(@RequestParam int companyId) {
		List<EmployeeMaster> list = new ArrayList<EmployeeMaster>();
		try {
			list = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployee : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getEmployeeById" }, method = RequestMethod.POST)
	public EmployeeMaster getEmployeeById(@RequestParam int empId) {
		EmployeeMaster emp = new EmployeeMaster();
		try {
			emp = empRepo.findByEmpIdAndDelStatus(empId, 1);
		} catch (Exception e) {
			System.err.println("Excep in getEmployeeById : " + e.getMessage());
			e.printStackTrace();
		}

		return emp;

	}

	@RequestMapping(value = { "/getDriverByEmpId" }, method = RequestMethod.POST)
	public EmpDriver getDriverByEmpId(@RequestParam int empId) {
		EmpDriver emp = new EmpDriver();
		try {
			emp = empDriverRepo.findByEmpIdAndDelStatus(empId, 1);
		} catch (Exception e) {
			System.err.println("Excep in getEmployeeById : " + e.getMessage());
			e.printStackTrace();
		}

		return emp;

	}

	@RequestMapping(value = { "/deleteEmployee" }, method = RequestMethod.POST)
	public Info deleteEmployee(@RequestParam int empId) {

		Info info = new Info();
		try {

			int res = empRepo.deleteEmployee(empId);

			if (res > 0) {
				int a = empInfoRepo.deleteEmployeeInfo(empId);
				int b = bankInfoRepo.deleteEmpBankInfo(empId);
				int c = nomineeRepo.deleteEmpNominee(empId);
				int d = empSalRepo.deleteEmpSalInfo(empId);
				int e = empSalAllowanceRepo.deleteEmpAllowances(empId);
				int f = employeeDocsRepository.deleteEmpDoc(empId);

				info.setError(false);
				info.setMsg("Sucess");
			} else {
				info.setError(true);
				info.setMsg("Fail");
			}

		} catch (Exception e) {
			System.err.println("Excep in deleteEmployee : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/saveEmployee" }, method = RequestMethod.POST)
	public EmployeeMaster saveEmployee(@RequestBody EmployeeMaster emp) {
		EmployeeMaster empSave = new EmployeeMaster();

		try {
			empSave = empRepo.save(emp);
		} catch (Exception e) {
			System.err.println("Excep in saveBank : " + e.getMessage());
			e.printStackTrace();
		}

		return empSave;

	}

	@RequestMapping(value = { "/saveDriver" }, method = RequestMethod.POST)
	public EmpDriver saveDriver(@RequestBody EmpDriver emp) {
		EmpDriver empSave = new EmpDriver();

		try {
			empSave = empDriverRepo.save(emp);
		} catch (Exception e) {
			System.err.println("Excep in saveDriver : " + e.getMessage());
			e.printStackTrace();
		}

		return empSave;

	}

	/*
	 * @RequestMapping(value = { "/updateDriverDetails" }, method =
	 * RequestMethod.POST) public Info updateDriverDetails(@RequestParam EmpDriver
	 * emp) { Info inf = new Info();
	 * 
	 * try { int i = empDriverRepo.updateEmpDriverDetails(emp); } catch (Exception
	 * e) { System.err.println("Excep in saveDriver : " + e.getMessage());
	 * e.printStackTrace(); }
	 * 
	 * return empSave;
	 * 
	 * }
	 */
	@RequestMapping(value = { "/saveEmployeeList" }, method = RequestMethod.POST)
	public List<EmployeeMaster> saveEmployeeList(@RequestBody List<EmployeeMaster> empList) {
		List<EmployeeMaster> empSaveResp = new ArrayList<EmployeeMaster>();

		try {
			empSaveResp = empRepo.saveAll(empList);
		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeList : " + e.getMessage());
			e.printStackTrace();
		}

		return empSaveResp;

	}

	@Autowired
	EmployeeRelatedTblsRepo empRelatedRepo;

	@RequestMapping(value = { "/getEmpRelatedInfo" }, method = RequestMethod.POST)
	public EmployeeRelatedTbls getEmpRelatedInfo(@RequestParam String empCode) {

		EmployeeRelatedTbls resp1 = new EmployeeRelatedTbls();

		try {

			resp1 = empRelatedRepo.getAllEmpRelatedInfo(empCode);

			if (resp1.getEmpId() != 0) {
				try {
					if (resp1.getAllowanceId() == null) {

						resp1.setAllowanceId("0");

					}

				} catch (Exception e) {
					resp1.setAllowanceId("0");
				}
				try {
					if (resp1.getEmpSalAllowanceId() == null) {

						resp1.setEmpSalAllowanceId("0");

					}
				} catch (Exception e) {
					resp1.setEmpSalAllowanceId("0");
				}

				try {
					if (resp1.getDocId() == null) {

						resp1.setDocId("0");

					}
				} catch (Exception e) {
					resp1.setDocId("0");
				}
			}
			

		} catch (Exception e) {
			resp1 = null;
			e.printStackTrace();
		}

		return resp1;

	}

	/***********************************************************************/
	@RequestMapping(value = { "/saveEmployeeIdInfo" }, method = RequestMethod.POST)
	public TblEmpInfo saveEmployee(@RequestBody TblEmpInfo empInfo) {
		TblEmpInfo empSave = new TblEmpInfo();
		System.out.println("empInfo--------" + empInfo);
		try {
			empSave = empInfoRepo.save(empInfo);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeInfo : " + e.getMessage());
			e.printStackTrace();
		}

		return empSave;

	}

	@RequestMapping(value = { "/getEmployeePersonalInfo" }, method = RequestMethod.POST)
	public TblEmpInfo getEmployeePersonalInfo(@RequestParam int empId) {
		TblEmpInfo emp = new TblEmpInfo();
		try {
			emp = empInfoRepo.findByEmpIdAndDelStatus(empId, 1);
		} catch (Exception e) {
			System.err.println("Excep in getEmployeePersonalInfo : " + e.getMessage());
			e.printStackTrace();
		}

		return emp;

	}

	/******************************************************************************/

	@RequestMapping(value = { "/saveEmployeeIdBank" }, method = RequestMethod.POST)
	public TblEmpBankInfo saveEmployee(@RequestBody TblEmpBankInfo empBank) {
		TblEmpBankInfo empSave = new TblEmpBankInfo();
		System.out.println("empBank--------" + empBank);
		try {
			empSave = bankInfoRepo.save(empBank);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeIdBank : " + e.getMessage());
			e.printStackTrace();
		}

		return empSave;

	}

	@RequestMapping(value = { "/getEmployeeBankInfo" }, method = RequestMethod.POST)
	public TblEmpBankInfo getEmployeeBankInfo(@RequestParam int empId) {

		TblEmpBankInfo empBank = new TblEmpBankInfo();
		try {
			empBank = bankInfoRepo.findByEmpIdAndDelStatus(empId, 1);

		} catch (Exception e) {
			System.err.println("Excep in getEmployeeBankInfo : " + e.getMessage());
			e.printStackTrace();
		}

		return empBank;

	}

	/***********************************************************************************/
	@RequestMapping(value = { "/saveEmployeeIdNominee" }, method = RequestMethod.POST)
	public TblEmpNominees saveEmployeeIdNominee(@RequestBody TblEmpNominees empNominee) {
		TblEmpNominees empSave = new TblEmpNominees();
		System.out.println("empNominee--------" + empNominee);
		try {
			empSave = nomineeRepo.save(empNominee);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeIdNominee : " + e.getMessage());
			e.printStackTrace();
		}

		return empSave;

	}

	@RequestMapping(value = { "/getEmployeeNominee" }, method = RequestMethod.POST)
	public TblEmpNominees getEmployeeNominee(@RequestParam int empId) {
		TblEmpNominees nominee = new TblEmpNominees();
		System.out.println("empNominee--------" + nominee);
		try {
			nominee = nomineeRepo.findByEmpIdAndDelStatus(empId, 1);

		} catch (Exception e) {
			System.err.println("Excep in getEmployeeNominee : " + e.getMessage());
			e.printStackTrace();
		}

		return nominee;

	}

	/***************************************************************************/

	@RequestMapping(value = { "/saveEmployeeIdSalary" }, method = RequestMethod.POST)
	public EmpSalaryInfo saveEmployeeIdNominee(@RequestBody EmpSalaryInfo empSal) {
		EmpSalaryInfo empSave = new EmpSalaryInfo();
		System.out.println("empSal--------" + empSal);
		try {
			empSave = empSalRepo.save(empSal);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeIdSalary : " + e.getMessage());
			e.printStackTrace();
		}

		return empSave;

	}

	@RequestMapping(value = { "/getEmployeeSalInfo" }, method = RequestMethod.POST)
	public EmpSalaryInfo getEmployeeSalInfo(@RequestParam int empId) {
		EmpSalaryInfo empSal = new EmpSalaryInfo();

		try {
			empSal = empSalRepo.findByEmpIdAndDelStatus(empId, 1);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeIdSalary : " + e.getMessage());
			e.printStackTrace();
		}

		return empSal;

	}

	/***************************************************************************/
	@RequestMapping(value = { "/saveEmployeeIdUser" }, method = RequestMethod.POST)
	public User saveEmployeeIdUser(@RequestBody User user) {
		User empSave = new User();
		System.out.println("user--------" + user);
		try {
			empSave = userRepo.save(user);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeIdUser : " + e.getMessage());
			e.printStackTrace();
		}

		return empSave;

	}

	/***************************************************************************/
	@RequestMapping(value = { "/getAllAllowances" }, method = RequestMethod.GET)
	public List<Allowances> getAllAllowances() {
		List<Allowances> list = new ArrayList<Allowances>();

		try {
			list = allowanceRepo.findBydelStatusAndIsActive(0, 1);

		} catch (Exception e) {
			System.err.println("Excep in getAllAllowances : " + e.getMessage());
			e.printStackTrace();
		}

		return list;

	}

	/***************************************************************************/

	@RequestMapping(value = { "/saveEmpSalAllowanceInfo" }, method = RequestMethod.POST)
	public List<EmpSalAllowance> saveEmpSalAllowanceInfo(@RequestBody List<EmpSalAllowance> allowncList) {
		List<EmpSalAllowance> empAllowance = new ArrayList<EmpSalAllowance>();
		System.out.println("allowance--------" + allowncList);
		try {
			empAllowance = empSalAllowanceRepo.saveAll(allowncList);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeIdUser : " + e.getMessage());
			e.printStackTrace();
		}

		return empAllowance;

	}

	@RequestMapping(value = { "/deleteEmpSalAllowanceInfo" }, method = RequestMethod.POST)
	public Info deleteEmpSalAllowanceInfo(@RequestParam int empId) {

		Info info = new Info();
		try {

			int res = empSalAllowanceRepo.deleteEmpAllowances(empId);

			if (res > 0) {
				info.setError(false);
				info.setMsg("Sucess");
			} else {
				info.setError(true);
				info.setMsg("Fail");
			}

		} catch (Exception e) {
			System.err.println("Excep in deleteEmpSalAllowanceInfo : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/saveEmpSalAllowanceIds" }, method = RequestMethod.POST)
	public EmpSalAllowance saveEmpSalAllowanceIds(@RequestBody EmpSalAllowance allowance) {
		EmpSalAllowance empAllowanceIds = new EmpSalAllowance();
		System.out.println("allowance--------" + allowance);
		try {
			empAllowanceIds = empSalAllowanceRepo.save(allowance);

		} catch (Exception e) {
			System.err.println("Excep in saveEmployeeIdUser : " + e.getMessage());
			e.printStackTrace();
		}

		return empAllowanceIds;

	}

	@RequestMapping(value = { "/getEmployeeSalAllowances" }, method = RequestMethod.POST)
	public List<EmpSalAllowance> getEmployeeSalAllowances(@RequestParam int empId) {
		List<EmpSalAllowance> empAllowance = new ArrayList<EmpSalAllowance>();

		try {
			empAllowance = empSalAllowanceRepo.findByEmpIdAndDelStatus(empId, 1);

		} catch (Exception e) {
			System.err.println("Excep in getEmployeeSalAllowances : " + e.getMessage());
			e.printStackTrace();
		}

		return empAllowance;

	}

	/**************************************************************************/
	@RequestMapping(value = { "/getAllEmpDocTypes" }, method = RequestMethod.POST)
	public List<EmpDoctype> getAllEmpDocTypes(@RequestParam int companyId) {
		List<EmpDoctype> empDocTypes = new ArrayList<EmpDoctype>();

		try {
			empDocTypes = empDocRepo.findByDelStatusAndCompanyId(0, companyId);

		} catch (Exception e) {
			System.err.println("Excep in getAllEmpDocTypes : " + e.getMessage());
			e.printStackTrace();
		}
		return empDocTypes;

	}

	@RequestMapping(value = { "/saveEmpDocList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeDoc> saveEmpDocList(@RequestBody List<EmployeDoc> employeeDepartment) {

		List<EmployeDoc> save = new ArrayList<>();
		try {

			save = employeeDocsRepository.saveAll(employeeDepartment);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	/****************************************************************************/
	@RequestMapping(value = { "/getEmployeeDocs" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeDoc> getEmployeeDocs(@RequestParam int empId) {

		List<EmployeDoc> docs = new ArrayList<>();
		try {

			docs = employeeDocsRepository.findByEmpIdAndDelStatus(empId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return docs;
	}

	@Autowired
	ViewEmployeeRepo empProfileRepo;

	@RequestMapping(value = { "/getEmployeeAllInfo" }, method = RequestMethod.POST)
	public @ResponseBody ViewEmployee getEmployeeAllInfo(@RequestParam int empId) {

		ViewEmployee emp = new ViewEmployee();
		try {

			emp = empProfileRepo.getEmployeeDetails(empId);
			System.out.println(emp);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return emp;
	}

}
