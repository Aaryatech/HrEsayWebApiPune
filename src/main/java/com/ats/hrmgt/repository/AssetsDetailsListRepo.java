package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AssetsDetailsList;

public interface AssetsDetailsListRepo extends JpaRepository<AssetsDetailsList, Integer> {

	@Query(value="select\n" + 
			"        assets.asset_id,\n" + 
			"        m_asset_category.cat_name,\n" + 
			"        assets.asset_code,\n" + 
			"        assets.asset_desc,\n" + 
			"        assets.asset_make,\n" + 
			"        assets.asset_model,\n" + 
			"        assets.asset_name,\n" + 
			"        assets.asset_pur_date,\n" + 
			"        assets.asset_remark,\n" +
			"        assets.asset_pur_image,\n" +
			"        assets.asset_status,\n" +
			"        assets.asset_srno,\n" + 
			"        assets.del_status,\n" + 
			"        assets.ex_int1,\n" + 
			"        assets.ex_int2,\n" + 
			"        assets.ex_var1,\n" + 
			"        assets.ex_var2,\n" + 
			"        assets.maker_user_id,\n" + 
			"        assets.update_datetime,\n" + 
			"        m_asset_vendor.comp_name as vendor, m_location.loc_name as location\n" + 
			"    from\n" + 
			"        m_assets assets,\n" + 
			"        m_asset_vendor,\n" + 
			"        m_asset_category, m_location\n" + 
			"        \n" + 
			"    where\n" + 
			"        assets.del_status=1 AND\n" + 
			"        assets.asset_cat_id=m_asset_category.asset_cat_id AND\n" + 
			"        assets.vendor_id=m_asset_vendor.vendor_id AND m_location.loc_id=assets.loc_id\n" + 
			"    order by\n" + 
			"        assets.asset_id desc", nativeQuery=true)
	List<AssetsDetailsList> getAllAssetList();
	
	@Query(value="select\n" + 
			"        assets.asset_id,\n" + 
			"        m_asset_category.cat_name,\n" + 
			"        assets.asset_code,\n" + 
			"        assets.asset_desc,\n" + 
			"        assets.asset_make,\n" + 
			"        assets.asset_model,\n" + 
			"        assets.asset_name,\n" + 
			"        assets.asset_pur_date,\n" + 
			"        assets.asset_remark,\n" +
			"        assets.asset_pur_image,\n" +
			"        assets.asset_status,\n" +
			"        assets.asset_srno,\n" + 
			"        assets.del_status,\n" + 
			"        assets.ex_int1,\n" + 
			"        assets.ex_int2,\n" + 
			"        assets.ex_var1,\n" + 
			"        assets.ex_var2,\n" + 
			"        assets.maker_user_id,\n" + 
			"        assets.update_datetime,\n" + 
			"        m_asset_vendor.comp_name as vendor, m_location.loc_name as location\n" + 
			"    from\n" + 
			"        m_assets assets,\n" + 
			"        m_asset_vendor,\n" + 
			"        m_asset_category, m_location\n" + 
			"        \n" + 
			"    where\n" + 
			"        assets.del_status=1 AND\n" + 
			"        assets.asset_cat_id=m_asset_category.asset_cat_id AND\n" + 
			"        assets.vendor_id=m_asset_vendor.vendor_id  AND m_location.loc_id=:locId AND  m_location.loc_id=assets.loc_id\n" + 
			"    order by\n" + 
			"        assets.asset_id desc", nativeQuery=true)
	List<AssetsDetailsList> getAllAssetByLoc(@Param("locId") int locId);	
	
	@Query(value="select\n" + 
			"        assets.asset_id,\n" + 
			"        m_asset_category.cat_name,\n" + 
			"        assets.asset_code,\n" + 
			"        assets.asset_desc,\n" + 
			"        assets.asset_make,\n" + 
			"        assets.asset_model,\n" + 
			"        assets.asset_name,\n" + 
			"        assets.asset_pur_date ,\n" + 
			"        assets.asset_remark,\n" + 
			"        assets.asset_pur_image,\n" + 
			"        assets.asset_status,\n" +
			"        assets.asset_srno,\n" + 
			"        assets.del_status,\n" + 
			"        assets.ex_int1,\n" + 
			"        assets.ex_int2,\n" + 
			"        assets.ex_var1,\n" + 
			"        assets.ex_var2,\n" + 
			"        assets.maker_user_id,\n" + 
			"        assets.update_datetime,\n" + 
			"        m_asset_vendor.comp_name as vendor, m_location.loc_name as location\n" + 
			"    from\n" + 
			"        m_assets assets,\n" + 
			"        m_asset_vendor,\n" + 
			"        m_asset_category, m_location\n" + 
			"        \n" + 
			"    where\n" + 
			"        assets.del_status=1 AND\n" + 
			"        assets.asset_cat_id=m_asset_category.asset_cat_id AND\n" + 
			"        assets.vendor_id=m_asset_vendor.vendor_id AND m_location.loc_id=assets.loc_id AND assets.asset_id=:assetId\n" + 
			"    order by\n" + 
			"        assets.asset_id desc", nativeQuery=true)
	AssetsDetailsList getAssetDetailById(@Param("assetId") int assetId);

	@Query(value="select\n" + 
			"        assets.asset_id,\n" + 
			"        m_asset_category.cat_name,\n" + 
			"        assets.asset_code,\n" + 
			"        assets.asset_desc,\n" + 
			"        assets.asset_make,\n" + 
			"        assets.asset_model,\n" + 
			"        assets.asset_name,\n" + 
			"        assets.asset_pur_date,\n" + 
			"        assets.asset_remark,\n" + 
			"        assets.asset_pur_image,\n" +
			"        assets.asset_status,\n" +
			"        assets.asset_srno,\n" + 
			"        assets.del_status,\n" + 
			"        assets.ex_int1,\n" + 
			"        assets.ex_int2,\n" + 
			"        assets.ex_var1,\n" + 
			"        assets.ex_var2,\n" + 
			"        assets.maker_user_id,\n" + 
			"        assets.update_datetime,\n" + 
			"        m_asset_vendor.comp_name as vendor,\n" + 
			"        m_location.loc_name as location     \n" + 
			"    from\n" + 
			"        m_assets assets,\n" + 
			"        m_asset_vendor,\n" + 
			"        m_asset_category,\n" + 
			"        m_location              \n" + 
			"    where\n" + 
			"        assets.del_status=1 \n" + 
			"        AND         assets.asset_cat_id=m_asset_category.asset_cat_id \n" + 
			"        AND         assets.vendor_id=m_asset_vendor.vendor_id \n" + 
			"        AND m_location.loc_id=assets.loc_id     \n" + 
			"        AND assets.asset_status=0\n" + 
			"    order by\n" + 
			"        assets.asset_id desc",nativeQuery=true)
	List<AssetsDetailsList> getAllUnassignAssetList();
}
