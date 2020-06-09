package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AssetVendor;

public interface AssetVendorRepo extends JpaRepository<AssetVendor, Integer> {

	List<AssetVendor> findByDelStatusOrderByVendorIdDesc(int del);
	
	AssetVendor findByVendorId(int assetVendorId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_asset_vendor SET del_status=0 WHERE vendor_id=:assetVendorId", nativeQuery=true)
	int deleteAssetVendor(@Param("assetVendorId") int assetVendorId);
	
	AssetVendor findByCompNameAndVendorEmail(String compName, String vendorEmail);
	
	@Query(value="SELECT\n" + 
			"    COUNT(vendor_id)\n" + 
			"FROM\n" + 
			"    `m_asset_vendor`\n" + 
			"WHERE\n" + 
			"    del_status = 1 AND vendor_id !=:vendorId AND comp_name =:compName", nativeQuery=true)
	int getVendorDataByCompName(@Param("vendorId") int vendorId, @Param("compName") String compName);
	
	@Query(value="SELECT\n" + 
			"    COUNT(vendor_id)\n" + 
			"FROM\n" + 
			"    `m_asset_vendor`\n" + 
			"WHERE\n" + 
			"    del_status = 1 AND vendor_id !=:vendorId AND vendor_email =:vendorEmail", nativeQuery=true)
	int getVendorDataByEmail(@Param("vendorId") int vendorId, @Param("vendorEmail") String vendorEmail);

	AssetVendor findByCompNameAndDelStatus(String compName, int i);

	AssetVendor findByVendorEmailAndDelStatus(String vendorEmail, int i);
}
