package com.ats.hrmgt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.assets.AMCInfo;
import com.ats.hrmgt.model.assets.AssetEmpInfo;
import com.ats.hrmgt.model.assets.AssetInfo;
import com.ats.hrmgt.repo.asset.AMCInfoRepo;
import com.ats.hrmgt.repo.asset.AssetEmpInfoRepo;
import com.ats.hrmgt.repo.asset.AssetInfoRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;

//Sachin 13-06-2020 10:35

@RestController
public class AssetTxApiController {

	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	
	@Autowired AssetEmpInfoRepo assetEmpInfoRepo; //15-06-2020
	@Autowired AssetInfoRepo assetInfoRepo;
	@Autowired AMCInfoRepo aMCInfoRepo;
	
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
	
}
