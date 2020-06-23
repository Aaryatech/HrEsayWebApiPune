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

	@Transactional
	@Modifying
	@Query(value="UPDATE\n" + 
			"    m_assets\n" + 
			"SET\n" + 
			"    asset_status = :status,\n" + 
			"    scrap_date =:scrapDate,\n" + 
			"    scrap_remark =:remark,\n" + 
			"    scrap_authoriy_details =:scrapAuthority,\n" + 
			"    scrap_login_userid =:scrapUserLogInId,\n" + 
			"    scrap_datetime =:scrapDateTime\n" + 
			"WHERE\n" + 
			"    asset_id=:assetId",nativeQuery=true)
	int makeAssetScrap(@Param("assetId") int assetId, @Param("status") int status, @Param("scrapDate") String scrapDate, 
			@Param("remark") String remark, @Param("scrapAuthority") String scrapAuthority,
			@Param("scrapUserLogInId") int scrapUserLogInId, @Param("scrapDateTime") String scrapDateTime);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_assets SET asset_status=:assetStatus, maker_user_id=:userUpdateId, update_datetime=:updateTime WHERE asset_id=:assetId",nativeQuery=true)
	int changeAssetStatusToLost(@Param("assetId") int assetId, @Param("assetStatus") int assetStatus, 
			@Param("userUpdateId") int userUpdateId, @Param("updateTime") String updateTime);
	
	List<Assets> findByLocIdAndDelStatus(int locId, int del);
}
