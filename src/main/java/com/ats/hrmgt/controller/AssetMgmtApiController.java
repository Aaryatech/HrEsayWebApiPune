package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.AssetCategory;
import com.ats.hrmgt.model.AssetVendor;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.AssetCategoryRepo;
import com.ats.hrmgt.repository.AssetVendorRepo;

@RestController
public class AssetMgmtApiController {

	@Autowired AssetCategoryRepo assetCatRepo;
	
	@Autowired AssetVendorRepo assetVendorRepo;
	
	@RequestMapping(value = { "/getAllAssetCategory" }, method = RequestMethod.GET)
	public List<AssetCategory> getAllEmployee() {
		List<AssetCategory> list = new ArrayList<AssetCategory>();
		try {
			list = assetCatRepo.findByDelStatusOrderByAssetCatIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllAssetCategory : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/saveAssetCat" }, method = RequestMethod.POST)
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
}
