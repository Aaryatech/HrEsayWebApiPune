package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ShiftMaster;

public interface ShiftMasterRepository extends JpaRepository<ShiftMaster, Integer> {

	@Query(value = "select * from tbl_shift_timming where location_id in (:locationIds) and status=1", nativeQuery = true)
	List<ShiftMaster> showShiftListByLocationIds(@Param("locationIds") List<Integer> locationIds);

	@Transactional
	@Modifying
	@Query("update ShiftMaster set status=0  WHERE id=:shiftId")
	int deleteShiftTime(@Param("shiftId") int shiftId);

	@Query(value = "select * from tbl_shift_timming where location_id =:locationId and status=1 and self_group_id=:groupId", nativeQuery = true)
	List<ShiftMaster> getShiftListByGroupIdandlocId(@Param("locationId") int locationId, @Param("groupId") int groupId);

	List<ShiftMaster> findByStatus( int i);

	List<ShiftMaster> findBySelfGroupIdAndStatus(int bonusId, int i);

	@Query(value = "select\n" + 
			"        id,\n" + 
			"        shiftname,\n" + 
			"        fromtime,\n" + 
			"        totime,\n" + 
			"        changeable,\n" + 
			"        changewith,\n" + 
			"        company_id,\n" + 
			"        max_late_time_allowed,\n" + 
			"        CONCAT(FLOOR(shift_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(shift_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as shift_hr,\n" + 
			"        CONCAT(FLOOR(shift_halfday_hr/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(shift_halfday_hr,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as shift_halfday_hr,\n" + 
			"        early_going_min,\n" + 
			"        ot_calculated_time,\n" + 
			"        ot_calculated_after_hr,\n" + 
			"        CONCAT(FLOOR(shift_ot_hour/60),\n" + 
			"        '.',\n" + 
			"        LPAD(MOD(shift_ot_hour,\n" + 
			"        60),\n" + 
			"        2,\n" + 
			"        '0')) as shift_ot_hour,\n" + 
			"        self_group_id,\n" + 
			"        status,\n" + 
			"        department_id,\n" + 
			"        location_id\n" + 
			"    from\n" + 
			"        tbl_shift_timming \n" + 
			"    where\n" + 
			"          status=1", nativeQuery = true)
	List<ShiftMaster> getShiftListByLpad();

}
