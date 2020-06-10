package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.AssetTrans;

public interface AssetTransRepo extends JpaRepository<AssetTrans, Integer> {
	
	List<AssetTrans> findByDelStatusOrderByAssetTransIdDesc(int del);
	
	AssetTrans findByAssetTransIdAndDelStatus(int assetTransId, int del);

}
