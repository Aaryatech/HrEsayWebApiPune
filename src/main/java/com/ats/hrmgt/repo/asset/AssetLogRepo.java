package com.ats.hrmgt.repo.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.assets.AssetLog;

public interface AssetLogRepo extends JpaRepository<AssetLog, Integer>{
	
	AssetLog save(AssetLog log);
	
	@Query(value=" SELECT COUNT(*) FROM t_asset_amc WHERE t_asset_amc.del_status=1 and t_asset_amc.asset_id=:assetId ",nativeQuery=true)
	Integer getAMCRecordCount(@Param("assetId") int assetId);
	
	@Query(value=" SELECT COUNT(*) FROM t_asset_amc WHERE t_asset_amc.del_status=1 and t_asset_amc.amc_status=11 and amc_to_date>CURRENT_DATE and  t_asset_amc.asset_id=:assetId ",nativeQuery=true)
	Integer getAMCRecordCountForLive(@Param("assetId") int assetId);
	
	@Query(value=" SELECT COUNT(*) FROM t_asset_amc WHERE t_asset_amc.del_status=1 and t_asset_amc.amc_status=11 and amc_to_date<CURRENT_DATE and  t_asset_amc.asset_id=:assetId ",nativeQuery=true)
	Integer getAMCRecordCountForLivePending(@Param("assetId") int assetId);
	
}
