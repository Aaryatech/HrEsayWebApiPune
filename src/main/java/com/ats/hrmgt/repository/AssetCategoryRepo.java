package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.model.AssetCategory;
@Repository
public interface AssetCategoryRepo extends JpaRepository<AssetCategory, Integer> {

	List<AssetCategory> findByDelStatusOrderByAssetCatIdDesc(int del);

	AssetCategory findByAssetCatId(int assetCatId);

	@Transactional
	@Modifying
	@Query(value="UPDATE m_asset_category SET del_status=0 WHERE asset_cat_id=:assetCatId", nativeQuery=true)
	int deleteAssetCatId(@Param("assetCatId") int assetCatId);
}
