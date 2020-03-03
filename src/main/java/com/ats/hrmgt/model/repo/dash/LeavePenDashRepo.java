package com.ats.hrmgt.model.repo.dash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.dashboard.GetNewHiresDash;
import com.ats.hrmgt.model.dashboard.LeavePenDash;

public interface LeavePenDashRepo extends JpaRepository<LeavePenDash,String>{
	
	

	@Query(value = "SELECT UUID() as uni_key,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(lv.leave_id)\n" + 
			"    FROM\n" + 
			"        leave_apply lv\n" + 
			"    WHERE\n" + 
			"        lv.ex_int1 = 1 AND lv.del_status = 1\n" + 
			") AS new_app,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(lv1.leave_id)\n" + 
			"    FROM\n" + 
			"        leave_apply lv1\n" + 
			"    WHERE\n" + 
			"        lv1.ex_int1 = 2 AND lv1.del_status = 1\n" + 
			") AS final_pending", nativeQuery = true)
	LeavePenDash getLeaveCnt();

}
