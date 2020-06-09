package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AssetAmc;

public interface AssetAmcRepo extends JpaRepository<AssetAmc, Integer> {

	List<AssetAmc> findByDelStatusOrderByAmcIdDesc(int del);
	
	AssetAmc findByAmcId(int assetAmcId);

	@Transactional
	@Modifying
	@Query(value="UPDATE t_asset_amc SET del_status=0 WHERE amc_id=:assetAmcId",nativeQuery=true)
	int deleteAssetAmc(@Param("assetAmcId") int assetAmcId);
}
