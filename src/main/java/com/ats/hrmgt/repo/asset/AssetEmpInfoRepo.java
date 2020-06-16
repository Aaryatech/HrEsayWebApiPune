package com.ats.hrmgt.repo.asset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.assets.AssetEmpInfo;

public interface AssetEmpInfoRepo extends JpaRepository<AssetEmpInfo, Integer> {

	// Query For 
	//On Click of Emp Name Show All Currently Assigned Assets For Selected EMP ID.
	
	@Query(value = " SELECT t_asset_trans.asset_trans_id,t_asset_trans.emp_id,t_asset_trans.asset_id,t_asset_trans.use_from_date,t_asset_trans.use_to_date,t_asset_trans.assign_remark, " + 
			" t_asset_trans.return_remark,t_asset_trans.assign_img_file,t_asset_trans.return_img_file," + 
			" m_employees.emp_code,m_employees.first_name,m_employees.surname, " + 
			" dep.name_sd AS dept_name, dg.name_sd AS emp_desgn, m_assets.asset_code,m_assets.asset_name " + 
			" FROM t_asset_trans,m_assets,m_employees " + 
			" LEFT JOIN m_department dep ON  " + 
			" m_employees.depart_id = dep.depart_id " + 
			" LEFT JOIN m_designation dg ON " + 
			" m_employees.designation_id = dg.desig_id " + 
			" WHERE  m_employees.del_status = 1 and t_asset_trans.emp_id=m_employees.emp_id "
			+ "AND t_asset_trans.del_status=1 AND t_asset_trans.asset_id=m_assets.asset_id AND "
			+ "m_assets.asset_status=1 "
			+ "and t_asset_trans.is_lost=0 and m_employees.emp_id=:empId", nativeQuery = true)
	List<AssetEmpInfo> getAssignedAssetByEmpId(@Param("empId") int empId);
	
	/*
	 * @Query(value =
	 * "SELECT t_asset_trans.asset_trans_id,t_asset_trans.emp_id,t_asset_trans.asset_id,t_asset_trans.use_from_date,t_asset_trans.use_to_date,t_asset_trans.assign_remark,\n"
	 * +
	 * "t_asset_trans.return_remark,t_asset_trans.assign_img_file,t_asset_trans.return_img_file,\n"
	 * + "m_employees.emp_code,m_employees.first_name,m_employees.surname,\n" +
	 * "dep.name_sd AS dept_name, dg.name_sd AS emp_desgn\n" +
	 * "FROM t_asset_trans,m_assets,m_employees\n" +
	 * "LEFT JOIN m_department dep ON \n" +
	 * "m_employees.depart_id = dep.depart_id\n" + "LEFT JOIN m_designation dg ON\n"
	 * + "m_employees.designation_id = dg.desig_id\n" +
	 * "WHERE  m_employees.del_status = 1 and t_asset_trans.emp_id=m_employees.emp_id "
	 * +
	 * "AND t_asset_trans.del_status=1 AND t_asset_trans.asset_id=m_assets.asset_id AND "
	 * + "m_assets.asset_status=0 " +
	 * "and t_asset_trans.is_lost=0 and m_employees.emp_id=:empId", nativeQuery =
	 * true) List<AssetEmpInfo> getUnAssignedAssetByEmpId(@Param("empId") int
	 * empId);
	 */
	@Query(value = " SELECT t_asset_trans.asset_trans_id,t_asset_trans.emp_id,t_asset_trans.asset_id,"
			+ "t_asset_trans.use_from_date,"
			+ "t_asset_trans.use_to_date,t_asset_trans.assign_remark, " + 
			" t_asset_trans.return_remark,t_asset_trans.assign_img_file,t_asset_trans.return_img_file, " + 
			" m_employees.emp_code,m_employees.first_name,m_employees.surname, " + 
			" dep.name_sd AS dept_name, dg.name_sd AS emp_desgn " + 
			" FROM t_asset_trans,m_assets,m_employees " + 
			" LEFT JOIN m_department dep ON  " + 
			" m_employees.depart_id = dep.depart_id " + 
			" LEFT JOIN m_designation dg ON " + 
			" m_employees.designation_id = dg.desig_id " + 
			" WHERE  m_employees.del_status = 1 and t_asset_trans.emp_id=m_employees.emp_id "
			+ " AND t_asset_trans.del_status=1 AND t_asset_trans.asset_id=m_assets.asset_id AND "
			+ " m_assets.asset_status IN (0,1) "
			+ " and t_asset_trans.is_lost=0 ", nativeQuery = true)
	List<AssetEmpInfo> getAssetTransHistoryByAssetId(@Param("assetId") int assetId);

}
