package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.LeaveApply;
 


public interface LeaveApplyRepository extends JpaRepository<LeaveApply, Integer> {

	List<LeaveApply> findByDelStatus(int i);
	
	
	List<LeaveApply> findByCalYrIdAndDelStatusAndEmpId(int calYr,int i,int empId);

	@Transactional
	@Modifying
	@Query("update LeaveApply set del_status=0  WHERE leave_id=:leaveId")
	int deleteLeaveApply(int leaveId);

	LeaveApply findByLeaveIdAndDelStatus(int leaveId, int i);
	
	
	@Transactional
	@Modifying
	@Query("update LeaveApply set ex_int2=:trailId  WHERE leave_id=:leaveId")
	int updateLeaveApply(int leaveId,int trailId);

	
	
	@Transactional
	@Modifying
	@Query("update LeaveApply set ex_int1=:status  WHERE leave_id=:leaveId")
	int updateLeaveStatus(int leaveId,int status);

	@Query(value="SELECT * FROM leave_apply WHERE ((:fromDate between leave_fromdt and leave_todt) or (:toDate between leave_fromdt and leave_todt) or "
			+ "(leave_fromdt between :fromDate and :toDate)or (leave_todt between :fromDate and :toDate)) and ex_int1 in (1,2,3) and emp_id=:empId",nativeQuery=true)
	List<LeaveApply> checkDateForRepetedLeaveValidation(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("empId") int empId);

	@Query(value="SELECT * FROM leave_apply WHERE ((DATE(:fromDate + INTERVAL 1 DAY) between leave_fromdt and leave_todt) or (DATE(:toDate- INTERVAL 1 DAY) between "
			+ "leave_fromdt and leave_todt))  and ex_int1 in (1,2,3) and emp_id=:empId and lv_type_id=:leaveTypeId and leave_num_days!=0",nativeQuery=true)
	List<LeaveApply> checkContinueDateLeave(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("empId") int empId,@Param("leaveTypeId") int leaveTypeId);

	@Query(value="select * from leave_apply where ((leave_fromdt between :fromDate and :toDate) or "
			+ "(leave_todt between :fromDate and :toDate) ) and ex_int1=3 ",nativeQuery=true)
	List<LeaveApply> getleavetList(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	@Transactional
	@Modifying
	@Query("update LeaveApply set leave_num_days=leave_num_days-:updateNoOfDays,leave_cancle_remark=:reason  WHERE leave_id=:leaveId")
	int updateNoOfDaysInLeave(@Param("leaveId")int leaveId,@Param("updateNoOfDays") float updateNoOfDays,@Param("reason") String reason);

	@Query(value="select * from leave_apply where leave_fromdt between :fromDate and :toDate and ex_int1=7 and lvt_application_id_parent!=0",nativeQuery=true)
	List<LeaveApply> leaveListAddeBySystem(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	@Transactional
	@Modifying
	@Query("update LeaveApply set leave_num_days=leave_num_days+:updateNoOfDays,leave_cancle_remark=:reason  WHERE leave_id=:leaveId")
	int reverseupdateNoOfDaysInLeave(@Param("leaveId")int leaveId,@Param("updateNoOfDays") float updateNoOfDays,@Param("reason") String reason);
	
	@Transactional
	@Modifying
	@Query("delete from LeaveApply where leave_id=:leaveId")
	int deleteByLeaveId(@Param("leaveId")int leaveId);

	@Query(value="select * from leave_apply where leave_fromdt between :fromDate and :toDate and ex_int1=7 and lvt_application_id_parent!=0 and emp_id=:empId",nativeQuery=true)
	List<LeaveApply> leaveListAddeBySystem(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("empId") int empId);

	@Query(value="select * from leave_apply where ((leave_fromdt between :fromDate and :toDate) or "
			+ " (leave_todt between :fromDate and :toDate) ) and ex_int1=3 and emp_id=:empId",nativeQuery=true)
	List<LeaveApply> getleavetList(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("empId") int empId);
	
	
			
	@Query(value="select l.leave_id,l.cal_yr_id,l.emp_id,l.lv_type_id,l.leave_duration,l.leave_fromdt,l.leave_todt,l.leave_num_days,l.leave_emp_reason,l.final_status,l.del_status,"
			+ "l.circulated_to,l.is_active,l.maker_user_id,l.maker_enter_datetime,l.ex_int1,l.ex_int2,l.ex_int3,lt.lv_title_short as ex_var1,lt.lv_sumup_id as ex_var2,"
			+ "l.ex_var3,l.status,l.leave_cancle_remark,l.lvt_application_id_parent,l.rec_status from leave_apply l,leave_type lt where "
			+ "((:fromDate between  l.leave_fromdt  and  l.leave_todt) or ( :toDate between  l.leave_fromdt  and  l.leave_todt ) or ( l.leave_fromdt between :fromDate and :toDate ) or ( l.leave_todt between :fromDate and :toDate )) and l.ex_int1=3 and lt.lv_type_id=l.lv_type_id",nativeQuery=true)
	List<LeaveApply> getleavetListForAttndace(@Param("fromDate") String fromDate,@Param("toDate") String toDate);


	@Query(value="select l.leave_id,l.cal_yr_id,l.emp_id,l.lv_type_id,l.leave_duration,l.leave_fromdt,l.leave_todt,l.leave_num_days,l.leave_emp_reason,l.final_status,l.del_status,"
			+ "l.circulated_to,l.is_active,l.maker_user_id,l.maker_enter_datetime,l.ex_int1,l.ex_int2,l.ex_int3,lt.lv_title_short as ex_var1,lt.lv_sumup_id as ex_var2,"
			+ "l.ex_var3,l.status,l.leave_cancle_remark,l.lvt_application_id_parent,l.rec_status from leave_apply l,leave_type lt where "
			+ "((:fromDate between  l.leave_fromdt  and  l.leave_todt) or ( :toDate between  l.leave_fromdt  and  l.leave_todt ) or ( l.leave_fromdt between :fromDate and :toDate ) or ( l.leave_todt between :fromDate and :toDate )) and l.ex_int1=3 and lt.lv_type_id=l.lv_type_id and emp_id=:empId",nativeQuery=true)
	List<LeaveApply> getleavetListForAttndace(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("empId") int empId);

	


}
