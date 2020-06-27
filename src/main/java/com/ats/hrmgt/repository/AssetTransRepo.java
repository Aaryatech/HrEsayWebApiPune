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
	@Query(value="UPDATE t_asset_trans SET return_date=:returnDate, asset_trans_status=:assetTransStatus, return_remark=:returnRemark,"
			+ " return_img_file=:assetReturnImg, update_datetime=:updateDateTime, maker_user_id=:updateUserId  WHERE asset_trans_id=:assetTransId",nativeQuery=true)
	int updateAssetsStatus(@Param("assetTransId") int assetTransId, @Param("assetTransStatus") int assetTransStatus,
			@Param("returnDate") String returnDate, @Param("returnRemark") String returnRemark, 
			@Param("assetReturnImg") String assetReturnImg, @Param("updateDateTime") String updateDateTime, @Param("updateUserId") int updateUserId);

	@Query(value="SELECT * FROM t_asset_trans WHERE t_asset_trans.asset_id=:assetId and t_asset_trans.asset_trans_status=1 and t_asset_trans.del_status=1 AND is_lost=0",nativeQuery=true)
	AssetTrans findByAssetTransId(@Param("assetId") int assetId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE\n" + 
			"    t_asset_trans\n" + 
			"SET\n" + 
			"    is_lost = :status,\n" + 
			"    asset_trans_status = :assetStatus,\n" + 
			"    lost_remark =:lostAssetRemark,\n" + 
			"    maker_user_id =:userUpdateId,\n" + 
			"    update_datetime =:updateTime\n" + 
			"WHERE\n" + 
			"    asset_trans_id =:transactnId",nativeQuery=true)
	int updateStatusAssetsLost(@Param("transactnId") int transactnId, @Param("status")  int status, @Param("userUpdateId") int userUpdateId, 
			@Param("updateTime") String updateTime, @Param("assetStatus") int assetStatus, @Param("lostAssetRemark") String lostAssetRemark);

	@Query(value="SELECT assign_img_file FROM t_asset_trans WHERE emp_id=:empId AND asset_id=:assetId AND del_status=1 AND asset_trans_status=1",nativeQuery=true)
	String getImgByEmpId(@Param("assetId")int assetId, @Param("empId")int empId);

}
