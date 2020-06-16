package com.ats.hrmgt.repo.asset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.assets.AMCInfo;

public interface AMCInfoRepo extends JpaRepository<AMCInfo, Integer>{
	
	//On Click of Assets name we show its AMC Detail ie  Vendor Name AMC Period Status Amt Etc.
	
	@Query(value = "SELECT t_asset_amc.amc_id,t_asset_amc.asset_id,t_asset_amc.amc_fr_date,"
			+ "t_asset_amc.amc_to_date,\n" + 
			"t_asset_amc.amc_amt,t_asset_amc.positive_remark,t_asset_amc.negative_remark,"
			+ "t_asset_amc.vendor_id,\n" + 
			"m_asset_vendor.comp_name, t_asset_amc.amc_status,\n" + 
			"dm_status_mst.status_text,t_asset_amc.ex_var1,t_asset_amc.ex_var2 \n" + 
			"FROM t_asset_amc,m_asset_vendor,dm_status_mst\n" + 
			"WHERE   t_asset_amc.amc_status=dm_status_mst.status_value AND t_asset_amc.del_status=1 "
			+ "and t_asset_amc.vendor_id=m_asset_vendor.vendor_id "
			+ "AND m_asset_vendor.del_status=1 and t_asset_amc.asset_id=:assetId", nativeQuery = true)
	List<AMCInfo> getAssetAMCInfoByAssetId(@Param("assetId") int assetId);

}
