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
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.AssetCategoryRepo;

@RestController
public class AssetMgmtApiController {

	@Autowired AssetCategoryRepo assetCatRepo;
	
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
			assetCat = assetCatRepo.save(asset);
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
}
