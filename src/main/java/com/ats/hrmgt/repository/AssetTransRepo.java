package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AssetTrans;

public interface AssetTransRepo extends JpaRepository<AssetTrans, Integer> {
	
	List<AssetTrans> findByDelStatusOrderByAssetTransIdDesc(int del);
	
	AssetTrans findByAssetTransIdAndDelStatus(int assetTransId, int del);

	@Transactional
	@Modifying
	@Query(value="UPDATE t_asset_trans SET return_date=:returnDate, asset_trans_status=:assetTransStatus, return_remark=:returnRemark  WHERE asset_trans_id=:assetTransId",nativeQuery=true)
	int updateAssetsStatus(@Param("assetTransId") int assetTransId, @Param("assetTransStatus") int assetTransStatus,
			@Param("returnDate") String returnDate, @Param("returnRemark") String returnRemark);

}
