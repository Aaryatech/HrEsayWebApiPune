package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Assets;

public interface AssetsRepo extends JpaRepository<Assets, Integer> {

	List<Assets> findByDelStatusOrderByAssetIdDesc(int del);
	
	Assets findByAssetId(int assetId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_assets SET del_status=0 WHERE asset_id=:assetId",nativeQuery=true)
	int deleteAsset(@Param("assetId") int assetId);

	@Transactional
	@Modifying
	@Query(value="UPDATE m_assets SET asset_status=:stat WHERE asset_id=:assetId",nativeQuery=true)
	int changeAssetStatus(@Param("assetId") int assetId, @Param("stat") int stat);
}
