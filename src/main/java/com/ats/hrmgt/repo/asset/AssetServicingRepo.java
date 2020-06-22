package com.ats.hrmgt.repo.asset;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AssetServicing;

public interface AssetServicingRepo extends JpaRepository<AssetServicing, Integer> {

	AssetServicing findByTServicingId(int serviceId);

	@Modifying
	@Transactional
	@Query(value="UPDATE t_asset_servicing SET del_status=0 WHERE t_servicing_id=:serviceId",nativeQuery=true)
	int deleteAssetServiceInfo(@Param("serviceId") int serviceId);

	@Modifying
	@Transactional
	@Query(value="UPDATE t_asset_servicing SET service_type = 1 WHERE t_servicing_id !=:serviceId",nativeQuery=true)
	int chngRegAssetService(@Param("serviceId")  int serviceId);
	 

}
