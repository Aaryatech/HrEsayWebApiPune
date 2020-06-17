package com.ats.hrmgt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.assets.AMCInfo;
import com.ats.hrmgt.model.assets.AssetEmpInfo;
import com.ats.hrmgt.model.assets.AssetInfo;
import com.ats.hrmgt.model.assets.AssetLog;
import com.ats.hrmgt.repo.asset.AMCInfoRepo;
import com.ats.hrmgt.repo.asset.AssetEmpInfoRepo;
import com.ats.hrmgt.repo.asset.AssetInfoRepo;
import com.ats.hrmgt.repo.asset.AssetLogRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;

//Sachin 13-06-2020 10:35 

@RestController
public class AssetTxApiController {

	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	
	@Autowired AssetEmpInfoRepo assetEmpInfoRepo; //15-06-2020
	@Autowired AssetInfoRepo assetInfoRepo;
	@Autowired AMCInfoRepo aMCInfoRepo;
	
	@Autowired AssetLogRepo aLogRepo;
	
	//Sachin 13-06-2020 10:38
		//To get Emps for Asset Mngt For Specific Location
	@RequestMapping(value = { "/getAllEmpByLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmpByLocId(@RequestParam int locId) {
		List<GetEmployeeDetails> empList = new ArrayList<GetEmployeeDetails>();
		
		try {
			empList = getEmployeeDetailsRepo.getEmpListByLocId(locId);
		} catch (Exception e) {
			empList=new ArrayList<GetEmployeeDetails>();
			System.err.println("Excep in getAllEmpByLocId : AssetTxApiController " + e.getMessage());
			e.printStackTrace();
		}

		return empList;
	}
	// To show Assigned Assets For Selected EMP ID
	@RequestMapping(value = { "/getAssignedAssetByEmpId" }, method = RequestMethod.POST)
	public List<AssetEmpInfo> getAssignedAssetByEmpId(@RequestParam int empId) {
		List<AssetEmpInfo> assignedAssetList = new ArrayList<AssetEmpInfo>();
		
		try {
			assignedAssetList = assetEmpInfoRepo.getAssignedAssetByEmpId(empId);
		} catch (Exception e) {
			assignedAssetList=new ArrayList<AssetEmpInfo>();
			System.err.println("Excep in getAssignedAssetByEmpId : AssetTxApiController " + e.getMessage());
			e.printStackTrace();
		}

		return assignedAssetList;
	}
	
	// To show UnAssigned/Free  Assets For Selected Location Id, for allowing to assign
	@RequestMapping(value = { "/getUnAssignedAssetByLocIdAndStatus" }, method = RequestMethod.POST)
	public List<AssetInfo> getUnAssignedAssetByLocIdAndStatus(@RequestParam int locId) {
		List<AssetInfo> assetInfoList = new ArrayList<AssetInfo>();
		
		try {
			List<String> statusList=new ArrayList<>();
			statusList.add("0");
			assetInfoList = assetInfoRepo.getUnAssignedAssetByLocIdAndStatus(locId, statusList);
		} catch (Exception e) {
			assetInfoList=new ArrayList<AssetInfo>();
			System.err.println("Excep in getUnAssignedAssetByLocIdAndStatus : AssetTxApiController " + e.getMessage());
			e.printStackTrace();
		}

		return assetInfoList;
	}
	
	//To show Asset AMC Info/History By asset ID.
	@RequestMapping(value = { "/getAssetAMCInfoByAssetId" }, method = RequestMethod.POST)
	public List<AMCInfo> getAssetAMCInfoByAssetId(@RequestParam int assetId) {
		List<AMCInfo> amcInfoList = new ArrayList<AMCInfo>();
		
		try {
			Date curDate=new Date();
			
			amcInfoList = aMCInfoRepo.getAssetAMCInfoByAssetId(assetId);
			
			System.err.println("amcInfoList "+amcInfoList.toString());
			
			for(int i=0;i<amcInfoList.size();i++) {
				if(amcInfoList.get(i).getAmcStatus()==11) {
						if(curDate.after(amcInfoList.get(i).getAmcToDate())) {
						amcInfoList.get(i).setStatusText("Pending");
						amcInfoList.get(i).setAmcStatus(10);
						break;
					}
					
				}
			}
			System.err.println("amcInfoList "+amcInfoList.toString());
			
		} catch (Exception e) {
			amcInfoList=new ArrayList<AMCInfo>();
			System.err.println("Excep in getAssetAMCInfoByAssetId : AssetTxApiController " + e.getMessage());
			e.printStackTrace();
		}

		return amcInfoList;
	}
	
	@RequestMapping(value = { "/saveAssetLog" }, method = RequestMethod.POST)
	public Info  saveAssetLog(@RequestParam int assetId,@RequestParam String assetLogDesc, @RequestParam int assetTransId,
			@RequestParam int loginUserId) {
		Info info = new Info();
		
		try {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Calendar curCal = Calendar.getInstance();
		    String curDtTime=String.valueOf(df.format(curCal.getTime()));
			
			Date curDate=new Date();
			
			AssetLog log=new AssetLog();
			log.setAssetId(assetId);
			log.setAssetLogDate(curDate);
			log.setAssetLogDesc(assetLogDesc);
			log.setAssetTransId(assetTransId);
			log.setDelStatus(1);
			log.setMakerUserId(loginUserId);
			log.setUpdateDateTime(curDtTime);
			aLogRepo.save(log);
			
		} catch (Exception e) {
			info = new Info();
			System.err.println("Excep in getAssetAMCInfoByAssetId : AssetTxApiController " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	@RequestMapping(value = { "/validateNewAMCAdd" }, method = RequestMethod.POST)
	public Info  validateNewAMCAdd(@RequestParam int assetId) {
		Info info = new Info();
		Integer result=0;
		try {
			result=aLogRepo.getAMCRecordCount(assetId);
			if(result.equals(0)) {
				info.setError(false);
				info.setMsg("Allow to add");
				result=0;
			}else {
				
				result=aLogRepo.getAMCRecordCountForLive(assetId);
				
				if(result>0) {
					info.setError(true);
					info.setMsg("A Live AMC record for this asset already exist");
					result=0;
				}else {
					result=aLogRepo.getAMCRecordCountForLivePending(assetId);
					
					if(result.equals(0)) {
						info.setError(false);
						info.setMsg("Allow to add");
					}else {
						info.setError(true);
						info.setMsg("Renew or Terminate the Pending AMC record.");
					}
				}
			}
		}catch (Exception e) {
			info = new Info();
		}
		
		return info;
}
}
