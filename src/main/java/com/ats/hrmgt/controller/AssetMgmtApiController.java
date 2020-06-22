package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.AssetAmc;
import com.ats.hrmgt.model.AssetCategory;
import com.ats.hrmgt.model.AssetEmployee;
import com.ats.hrmgt.model.AssetServiceDetails;
import com.ats.hrmgt.model.AssetServicing;
import com.ats.hrmgt.model.AssetTrans;
import com.ats.hrmgt.model.AssetVendor;
import com.ats.hrmgt.model.Assets;
import com.ats.hrmgt.model.AssetsDetailsList;
import com.ats.hrmgt.model.AssignedAssetsList;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.assets.AMCExpirationDetail;
import com.ats.hrmgt.model.assets.AssetEmpHistoryInfo;
import com.ats.hrmgt.model.assets.AssetNotificatn;
import com.ats.hrmgt.model.assets.CatWiseAssetCount;
import com.ats.hrmgt.model.assets.CatWiseAssetDistributn;
import com.ats.hrmgt.repo.asset.AMCExpirationDetailRepo;
import com.ats.hrmgt.repo.asset.AssetEmpHistoryInfoRepo;
import com.ats.hrmgt.repo.asset.AssetNotificatnRepo;
import com.ats.hrmgt.repo.asset.AssetServiceDetailsRepo;
import com.ats.hrmgt.repo.asset.AssetServicingRepo;
import com.ats.hrmgt.repo.asset.CatWiseAssetCountRepo;
import com.ats.hrmgt.repo.asset.CatWiseAssetDistributnRepo;
import com.ats.hrmgt.repository.AssetAmcRepo;
import com.ats.hrmgt.repository.AssetCategoryRepo;
import com.ats.hrmgt.repository.AssetEmployeeRepo;
import com.ats.hrmgt.repository.AssetTransRepo;
import com.ats.hrmgt.repository.AssetVendorRepo;
import com.ats.hrmgt.repository.AssetsDetailsListRepo;
import com.ats.hrmgt.repository.AssetsRepo;
import com.ats.hrmgt.repository.AssignedAssetsListRepo;

@RestController
public class AssetMgmtApiController {

	@Autowired AssetCategoryRepo assetCatRepo;
	
	@Autowired AssetVendorRepo assetVendorRepo;
	
	@Autowired AssetAmcRepo assetAmcRepo;
	
	@Autowired AssetsRepo assetsRepo;
	
	@Autowired AssetsDetailsListRepo assetsListRepo; 
	
	@Autowired AssetTransRepo assetTransRepo;
	
	@Autowired AssetEmployeeRepo assetEmpRepo;
	
	@Autowired AssignedAssetsListRepo assignedAssetListRepo;
	
	@Autowired AssetServicingRepo assetServiceRepo;
	
	@Autowired AssetServiceDetailsRepo assetServiceListRepo;
	
	@Autowired AssetEmpHistoryInfoRepo assetEmpHistoryRepo;
	
	@Autowired AssetNotificatnRepo assetNotityRepo;
	
	@Autowired AMCExpirationDetailRepo amcExpireNotifyRepo;
	
	@Autowired CatWiseAssetDistributnRepo catWiseAssetDistributnRepo;
	
	@Autowired CatWiseAssetCountRepo cateWiseAssetCntRepo;
	
	/*****************************************************************************/
	
	@RequestMapping(value = { "/getAllAssetCategory" }, method = RequestMethod.GET)
	public List<AssetCategory> getAllAssetCategory() {
		List<AssetCategory> list = new ArrayList<AssetCategory>();
		try {
			list = assetCatRepo.findByDelStatusOrderByAssetCatIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetCategory : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/saveAssetCat"}, method = RequestMethod.POST)
	public AssetCategory saveAssetCat(@RequestBody AssetCategory asset) {
		AssetCategory assetCat = new AssetCategory();
		try {
			if(asset.getAssetCatId()==0) {
				AssetCategory res = assetCatRepo.findByCatNameAndDelStatus(asset.getCatName(), 1);
				
				 if(res == null) {			 
				 assetCat = assetCatRepo.save(asset);
				 }else { 
					 assetCat.setAssetCatId(-1);				 
				 }
			}else{
				//System.err.println("In Else");
				int res = assetCatRepo.findAssetCat(asset.getAssetCatId(), asset.getCatName());
				 if(res == 0) {			 
					 assetCat = assetCatRepo.save(asset);
					 }else { 
						 assetCat.setAssetCatId(-1);				 
					 }
			}
			
		} catch (Exception e) {
			System.err.println("Excep in saveAssetCat : " + e.getMessage());
			e.printStackTrace();
		}

		return assetCat;
	}
	
	
	@RequestMapping(value = { "/getAssetCatById" }, method = RequestMethod.POST)
	public AssetCategory getAssetCatById(@RequestParam int assetCatId) {
		AssetCategory assetCat = new AssetCategory();
		
		try {
			assetCat = assetCatRepo.findByAssetCatId(assetCatId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetCatById : " + e.getMessage());
			e.printStackTrace();
		}

		return assetCat;
	}
	
	
	@RequestMapping(value = { "/deleteAssetCat" }, method = RequestMethod.POST)
	public Info deleteAssetCat(@RequestParam int assetCatId) {
		Info info = new Info();
		
		try {
			int i = assetCatRepo.deleteAssetCatId(assetCatId);
			
			if(i>0) {
				info.setError(false);
				info.setMsg("Asset Category Deleted Successfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Delete Asset Category");
			}
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetCat : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	/****************************************************************************/
	@RequestMapping(value = { "/getAllAssetVendor" }, method = RequestMethod.GET)
	public List<AssetVendor> getAllAssetVendor() {
		List<AssetVendor> list = new ArrayList<AssetVendor>();
		try {
			list = assetVendorRepo.findByDelStatusOrderByVendorIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetVendor : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	
	@RequestMapping(value = { "/saveAssetVendor" }, method = RequestMethod.POST)
	public AssetVendor saveAssetVendor(@RequestBody AssetVendor assetVendor) {
		AssetVendor assetCat = new AssetVendor();
		try {
			if(assetVendor.getVendorId()==0) {
				AssetVendor resComp = assetVendorRepo.findByCompNameAndDelStatus(assetVendor.getCompName(), 1);
				AssetVendor resEmail = assetVendorRepo.findByVendorEmailAndDelStatus(assetVendor.getVendorEmail(),1);
				 if(resComp == null && resEmail==null) {			 
				 assetCat = assetVendorRepo.save(assetVendor);
				 }else { 
					 assetCat.setVendorId(-1);		 
				 }
			}else{
				System.err.println("In Else");
				int resCompName = assetVendorRepo.getVendorDataByCompName(assetVendor.getVendorId(), assetVendor.getCompName());
				int resEmail = assetVendorRepo.getVendorDataByEmail(assetVendor.getVendorId(), assetVendor.getVendorEmail());
				 if(resCompName == 0 && resEmail == 0) {			 
					 assetCat = assetVendorRepo.save(assetVendor);
					 }else { 
						 assetCat.setVendorId(-1);		 			 
					 }
			}
			
		} catch (Exception e) {
			System.err.println("Excep in saveAssetVendor : " + e.getMessage());
			e.printStackTrace();
		}

		return assetCat;
	}
	
	
	
	@RequestMapping(value = { "/getAssetVendorById" }, method = RequestMethod.POST)
	public AssetVendor getAssetVendorById(@RequestParam int assetVendorId) {
		AssetVendor assetVendor = new AssetVendor();
		
		try {
			assetVendor = assetVendorRepo.findByVendorId(assetVendorId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetVendorById : " + e.getMessage());
			e.printStackTrace();
		}

		return assetVendor;
	}
	
	@RequestMapping(value = { "/deleteAssetVendorById" }, method = RequestMethod.POST)
	public Info deleteAssetVendorById(@RequestParam int assetVendorId) {
		Info info = new Info();
		
		try {
			int i = assetVendorRepo.deleteAssetVendor(assetVendorId);
			
			if(i>0) {
				info.setError(false);
				info.setMsg("Asset Vendor Deleted Successfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Delete Asset Vendor");
			}
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetVendorById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	/***********************************************************************************/
	
	
	@RequestMapping(value = { "/getAllAssetAmc" }, method = RequestMethod.GET)
	public List<AssetAmc> getAllAssetAmc() {
		List<AssetAmc> list = new ArrayList<AssetAmc>();
		try {
			list = assetAmcRepo.findByDelStatusOrderByAmcIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetAmc : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAllAssetAMCDetails" }, method = RequestMethod.GET)
	public List<AssetAmc> getAllAssetAMCDetails() {
		List<AssetAmc> list = new ArrayList<AssetAmc>();
		try {
			list = assetAmcRepo.getAllAssetAMC();
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetAMCDetails : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAssetAmcById" }, method = RequestMethod.POST)
	public AssetAmc getAssetAmcById(@RequestParam int assetAmcId) {
		AssetAmc assetAmc = new AssetAmc();
		
		try {
			assetAmc = assetAmcRepo.findByAmcId(assetAmcId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetAmcById : " + e.getMessage());
			e.printStackTrace();
		}

		return assetAmc;
	}
	
	@RequestMapping(value = { "/saveAssetAmc" }, method = RequestMethod.POST)
	public AssetAmc getAssetAmcById(@RequestBody  AssetAmc assetAmc) {
		AssetAmc amc = new AssetAmc();
		
		try {
			amc = assetAmcRepo.save(assetAmc);
		} catch (Exception e) {
			System.err.println("Excep in saveAssetAmc : " + e.getMessage());
			e.printStackTrace();
		}

		return amc;
	}
	
	@RequestMapping(value = { "/deleteAssetAmcById" }, method = RequestMethod.POST)
	public Info deleteAssetAmcById(@RequestParam int assetAmcId) {
		Info info = new Info();
		
		try {
			int i = assetAmcRepo.deleteAssetAmc(assetAmcId);
			
			if(i>0) {
				info.setError(false);
				info.setMsg("Asset Amc Deleted Successfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Delete Asset Amc");
			}
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetAmcById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	@RequestMapping(value = { "/saveRenewAssetAmc" }, method = RequestMethod.POST)
	public AssetAmc renewAssetAmc(@RequestBody  AssetAmc assetAmc) {
		AssetAmc amc = new AssetAmc();
		Info info = new Info();
		try {			
			amc = assetAmcRepo.save(assetAmc);
			
			
		} catch (Exception e) {
			System.err.println("Excep in saveAssetAmc : " + e.getMessage());
			e.printStackTrace();
		}

		return amc;
	}
	
	@RequestMapping(value = { "/updtAssetAMCStatus" }, method = RequestMethod.POST)
	public Info updtAssetAMCStatus(@RequestParam int assetAmcId, @RequestParam int status) {
		Info info = new Info();
		
		try {
			
				int i = assetAmcRepo.updtAmcStatus(assetAmcId, status);
				if(i>0) {
					info.setError(false);
					info.setMsg("Asset AMC Renewed Successfully");
				}else {
					info.setError(true);
					info.setMsg("Failed to Renewed Asset AMC");
				}
			
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetAmcById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	@RequestMapping(value = { "/terminateAssetAMC" }, method = RequestMethod.POST)
	public Info terminateAssetAMC(@RequestParam int assetAMCId, @RequestParam int status, 
			@RequestParam int userUpdateId,@RequestParam String updateTime) {
		
		Info info = new Info();
		
		try {
			
				int i = assetAmcRepo.terminateAMC(assetAMCId, status, userUpdateId, updateTime);
				if(i>0) {
					info.setError(false);
					info.setMsg("Asset AMC Terminated Successfully");
				}else {
					info.setError(true);
					info.setMsg("Failed to Terminated Asset AMC");
				}
			
		} catch (Exception e) {
			System.err.println("Excep in terminateAssetAMC : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	
	
	@RequestMapping(value = { "/updtAssetToScrap" }, method = RequestMethod.POST)
	public Info updtAssetToScrap(@RequestParam int assetId, @RequestParam int status, @RequestParam String scrapDate,
			@RequestParam String remark, @RequestParam String scrapAuthority, @RequestParam int scrapUserLogInId, @RequestParam String scrapDateTime) {
		
		Info info = new Info();
		
		try {
			
				int i = assetsRepo.makeAssetScrap(assetId, status, scrapDate, remark, scrapAuthority, scrapUserLogInId, scrapDateTime);
				if(i>0) {
					info.setError(false);
					info.setMsg("Asset Status Updated Successfully");
				}else {
					info.setError(true);
					info.setMsg("Failed to Updated Asset Status");
				}
			
		} catch (Exception e) {
			System.err.println("Excep in updtAssetToScrap : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	
	/********************************************************************/
	@RequestMapping(value = { "/getAssetsList" }, method = RequestMethod.GET)
	public List<Assets> getAssetsList() {
		List<Assets> list = new ArrayList<Assets>();
		try {
			list = assetsRepo.findByDelStatusOrderByAssetIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssets : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAllAssets" }, method = RequestMethod.GET)
	public List<AssetsDetailsList> getAllAssets() {
		List<AssetsDetailsList> list = new ArrayList<AssetsDetailsList>();
		try {
			list = assetsListRepo.getAllAssetList();
		} catch (Exception e) {
			System.err.println("Excep in getAllAssets : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAllUnassignAssets" }, method = RequestMethod.GET)
	public List<AssetsDetailsList> getAllUnassignAssets() {
		List<AssetsDetailsList> list = new ArrayList<AssetsDetailsList>();
		try {
			list = assetsListRepo.getAllUnassignAssetList();
		} catch (Exception e) {
			System.err.println("Excep in getAllUnassignAssets : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/saveAssets" }, method = RequestMethod.POST)
	public Assets saveAssets(@RequestBody Assets assets) {
		Assets aset = new Assets();
		try {
			aset = assetsRepo.save(assets);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssets : " + e.getMessage());
			e.printStackTrace();
		}

		return aset;
	}
	
	@RequestMapping(value = { "/getAssetById" }, method = RequestMethod.POST)
	public Assets getAssetById(@RequestParam int assetId) {
		Assets aset = new Assets();
		try {
			aset = assetsRepo.findByAssetId(assetId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetById : " + e.getMessage());
			e.printStackTrace();
		}

		return aset;
	}
	
	
	@RequestMapping(value = { "/deleteAssetById" }, method = RequestMethod.POST)
	public Info deleteAssetById(@RequestParam int assetId) {
		Info info = new Info();
		
		try {
			int i = assetsRepo.deleteAsset(assetId);
			
			if(i>0) {
				info.setError(false);
				info.setMsg("Asset Deleted Successfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Delete Asset");
			}
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	
	@RequestMapping(value = { "/getAllAssetsByLocation" }, method = RequestMethod.POST)
	public List<AssetsDetailsList> getAllAssetsByLocation(@RequestParam int locId) {
		List<AssetsDetailsList> list = new ArrayList<AssetsDetailsList>();
		try {
			if(locId>0) {
				list = assetsListRepo.getAllAssetByLoc(locId);
			}else {
				list = assetsListRepo.getAllAssetList();
			}
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetsByLocation : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAssetInfoById" }, method = RequestMethod.POST)
	public AssetsDetailsList getAssetInfoById(@RequestParam int assetId) {
		AssetsDetailsList assetCat = new AssetsDetailsList();
		
		try {
			assetCat = assetsListRepo.getAssetDetailById(assetId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetInfoById : " + e.getMessage());
			e.printStackTrace();
		}

		return assetCat;
	}
	/*********************************************************************************/
	
	@RequestMapping(value = { "/getAllAssetsTransactions" }, method = RequestMethod.GET)
	public List<AssetTrans> getAllAssetsTransactions() {
		List<AssetTrans> list = new ArrayList<AssetTrans>();
		try {
			list = assetTransRepo.findByDelStatusOrderByAssetTransIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetsTransactions : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAssetTransactionById" }, method = RequestMethod.POST)
	public AssetTrans getAssetTransactionById(@RequestParam int assetTransId) {
		AssetTrans aset = new AssetTrans();
		try {
			aset = assetTransRepo.findByAssetTransIdAndDelStatus(assetTransId, 1);
		} catch (Exception e) {
			System.err.println("Excep in getAssetTransactionById : " + e.getMessage());
			e.printStackTrace();
		}

		return aset;
	}
	
	@RequestMapping(value = { "/saveAssetsTrans" }, method = RequestMethod.POST)
	public List<AssetTrans> saveAssetsTrans(@RequestBody List<AssetTrans> assetTransList) {
		List<AssetTrans> assetTrans = new ArrayList<AssetTrans>();
		System.out.println("Save AssetTransList--------" + assetTransList);
		try {
			assetTrans = assetTransRepo.saveAll(assetTransList);
			System.err.println(assetTransList);
			
			for (int i = 0; i < assetTrans.size(); i++) {
				System.err.println(assetTrans.get(i).getAssetTransId());
				int assetId = assetTrans.get(i).getAssetId();
				int updtAsset = assetsRepo.changeAssetStatus(assetId, 1);
			}
		} catch (Exception e) {
			System.err.println("Excep in saveAssetsTrans : " + e.getMessage());
			e.printStackTrace();
		}

		return assetTrans;
	}
	
	@RequestMapping(value = { "/returnAssetByIds" }, method = RequestMethod.POST)
	public Info returnAssetByIds(@RequestParam int assetTransId, @RequestParam int assetTransStatus, 
			@RequestParam String returnDate,@RequestParam int assetId, @RequestParam String returnRemark, 
			@RequestParam String assetReturnImg, @RequestParam String updateDateTime, @RequestParam int updateUserId) {
		Info info = new Info();
		
		try {
			int i = assetTransRepo.updateAssetsStatus(assetTransId, assetTransStatus, returnDate, returnRemark, assetReturnImg, updateDateTime, updateUserId);
			
			if(i>0) {
				int updtAsset = assetsRepo.changeAssetStatus(assetId, 0);
				info.setError(false);
				info.setMsg("Asset Deleted Successfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Delete Asset");
			}
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
		
	@RequestMapping(value = { "/getAssetTransactnById" }, method = RequestMethod.POST)
	public AssetTrans getAssetTransactnById(@RequestParam int assetId) {
		AssetTrans aset = new AssetTrans();
		try {
			aset = assetTransRepo.findByAssetTransId(assetId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetTransactionById : " + e.getMessage());
			e.printStackTrace();
		}

		return aset;
	}
	
	
	
	@RequestMapping(value = { "/updateAssetStatusLost" }, method = RequestMethod.POST)
	public Info updateAssetStatusLost(@RequestParam int transactnId, @RequestParam int assetId, 
			@RequestParam String lostAssetRemark, @RequestParam int userUpdateId, @RequestParam String updateTime, @RequestParam int status,
			@RequestParam int assetStatus) {
		Info info = new Info();
		
		try {
			int i = assetTransRepo.updateStatusAssetsLost(transactnId, status, userUpdateId, updateTime, assetStatus, lostAssetRemark);
			
			if(i>0) {
				int updtAsset = assetsRepo.changeAssetStatusToLost(assetId, assetStatus, userUpdateId, updateTime);
				info.setError(false);
				info.setMsg("Asset Lost Updated Succesfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Updated Asset Lost");
			}
		} catch (Exception e) {
			System.err.println("Excep in updateAssetStatusLost : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	/**************************************************************************************/

	@RequestMapping(value = { "/getAllAssetEmployees" }, method = RequestMethod.POST)
	public List<AssetEmployee> getAllAssetEmployees(@RequestParam int locId) {
		List<AssetEmployee> list = new ArrayList<AssetEmployee>();
		try {
			list = assetEmpRepo.getAllAssetsEmpByLocation(locId);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetEmployees : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAssetEmployee" }, method = RequestMethod.POST)
	public AssetEmployee getAssetEmployee(@RequestParam int locId, @RequestParam int empId) {
		AssetEmployee emp = new AssetEmployee();
		try {
			emp = assetEmpRepo.getAssetsEmpByLocationAndEmpId(locId, empId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetEmployee : " + e.getMessage());
			e.printStackTrace();
		}

		return emp;
	}
	
	/**********************************************************************************/
	@RequestMapping(value = { "/getAllAssignedAssets" }, method = RequestMethod.POST)
	public List<AssignedAssetsList> getAllAssignedAssets(@RequestParam int empId) {
		List<AssignedAssetsList> list = new ArrayList<AssignedAssetsList>();
		try {
			list = assignedAssetListRepo.getAllAssignedAssetsToEmp(empId);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetCategory : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	/********************************************************************************/
	
	@RequestMapping(value = { "/saveAssetsService" }, method = RequestMethod.POST)
	public AssetServicing saveAssetsService(@RequestBody AssetServicing assetService) {
		AssetServicing service = new AssetServicing();
		try {
			service = assetServiceRepo.save(assetService);
		} catch (Exception e) {
			System.err.println("Excep in saveAssetsService : " + e.getMessage());
			e.printStackTrace();
		}

		return service;
	}
	
	
	@RequestMapping(value = { "/getAssetServicingById" }, method = RequestMethod.POST)
	public AssetServicing getAssetServicingById(@RequestParam int serviceId) {
		AssetServicing service = new AssetServicing();
		try {
			service = assetServiceRepo.findByTServicingId(serviceId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetTransactionById : " + e.getMessage());
			e.printStackTrace();
		}

		return service;
	}
	

	@RequestMapping(value = { "/deleteAssetServiceById" }, method = RequestMethod.POST)
	public Info deleteAssetServiceById(@RequestParam int serviceId) {
		Info info = new Info();
		
		try {
			int i = assetServiceRepo.deleteAssetServiceInfo(serviceId);
			
			if(i>0) {
				info.setError(false);
				info.setMsg("Asset Service Deleted Successfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Delete Asset Service");
			}
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
	
	@RequestMapping(value = { "/updtRegService" }, method = RequestMethod.POST)
	public Info updtRegService(@RequestParam int serviceId) {
		Info info = new Info();
		
		try {
			int i = assetServiceRepo.chngRegAssetService(serviceId);
			
			if(i>0) {
				info.setError(false);
				info.setMsg("Asset Service Deleted Successfully");
			}else {
				info.setError(true);
				info.setMsg("Failed to Delete Asset Service");
			}
		} catch (Exception e) {
			System.err.println("Excep in deleteAssetById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}
		
	@RequestMapping(value = { "/getAssetServiceList" }, method = RequestMethod.POST)
	public List<AssetServiceDetails> getAssetServiceList(@RequestParam int assetId) {
		List<AssetServiceDetails> list = new ArrayList<AssetServiceDetails>();
		try {
			list = assetServiceListRepo.getAssetServiceList(assetId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetServiceList : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAssetEmpHistoryList" }, method = RequestMethod.POST)
	public List<AssetEmpHistoryInfo> getAssetEmpHistoryList(@RequestParam int assetId) {
		List<AssetEmpHistoryInfo> list = new ArrayList<AssetEmpHistoryInfo>();
		try {
			list = assetEmpHistoryRepo.getAssetAssignedToEmp(assetId);
		} catch (Exception e) {
			System.err.println("Excep in getAssetEmpHistoryList : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
/*****************************************************************************/
	//Dashboard Query
	@RequestMapping(value = { "/getAllAssetsForNotifiction" }, method = RequestMethod.GET)
	public List<AssetNotificatn> getAllAssetsForNotifiction() {
		List<AssetNotificatn> list = new ArrayList<AssetNotificatn>();
		try {
			list = assetNotityRepo.getAssetDetailForNotification();
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetsForNotifiction : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getAllAssetsAMCForNotifiction" }, method = RequestMethod.GET)
	public List<AMCExpirationDetail> getAllAssetsAMCForNotifiction() {
		List<AMCExpirationDetail> list = new ArrayList<AMCExpirationDetail>();
		try {
			list = amcExpireNotifyRepo.getAMCExpirationDetailForNotificatn();
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetsAMCForNotifiction : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
		
	@RequestMapping(value = { "/getAllCateWiseAssetDistributn" }, method = RequestMethod.GET)
	public List<CatWiseAssetDistributn> getAllCateWiseAssetDistributn() {
		List<CatWiseAssetDistributn> list = new ArrayList<CatWiseAssetDistributn>();
		try {
			list = catWiseAssetDistributnRepo.getCatWiseAssetDistributn();
		} catch (Exception e) {
			System.err.println("Excep in getAllCateWiseAssetDistributn : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
		
	@RequestMapping(value = { "/getCateWiseAssetCnt" }, method = RequestMethod.GET)
	public List<CatWiseAssetCount> getCateWiseAssetCnt() {
		List<CatWiseAssetCount> list = new ArrayList<CatWiseAssetCount>();
		try {
			list = cateWiseAssetCntRepo.getCatWiseAssetCount();
		} catch (Exception e) {
			System.err.println("Excep in getCateWiseAssetCnt : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
}
