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
import com.ats.hrmgt.model.AssetTrans;
import com.ats.hrmgt.model.AssetVendor;
import com.ats.hrmgt.model.Assets;
import com.ats.hrmgt.model.AssetsDetailsList;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.AssetAmcRepo;
import com.ats.hrmgt.repository.AssetCategoryRepo;
import com.ats.hrmgt.repository.AssetTransRepo;
import com.ats.hrmgt.repository.AssetVendorRepo;
import com.ats.hrmgt.repository.AssetsDetailsListRepo;
import com.ats.hrmgt.repository.AssetsRepo;

@RestController
public class AssetMgmtApiController {

	@Autowired AssetCategoryRepo assetCatRepo;
	
	@Autowired AssetVendorRepo assetVendorRepo;
	
	@Autowired AssetAmcRepo assetAmcRepo;
	
	@Autowired AssetsRepo assetsRepo;
	
	@Autowired AssetsDetailsListRepo assetsListRepo; 
	
	@Autowired AssetTransRepo assetTransRepo;
	
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
}
