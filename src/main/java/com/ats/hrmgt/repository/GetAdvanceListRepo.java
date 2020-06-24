package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetAdvanceList;

public interface GetAdvanceListRepo extends JpaRepository<GetAdvanceList, Integer> {

	@Query(value = "select id,emp_id, sum(adv_amount) as adv_amount from tbl_advance where ded_month=:month and ded_year=:year "
			+ "and del_status=1 and is_ded=0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
	List<GetAdvanceList> getAdvanceList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	
    @Query(value = "select id, emp_id, sum(adv_amount) as adv_amount from tbl_advance where del_status=1 and is_ded=0  and emp_id in (:empIds) group by emp_id", nativeQuery = true)
	GetAdvanceList getAdvanceListForFullFinal(List<Integer> empIds);

    @Query(value = "select\n" + 
    		"        id,\n" + 
    		"        emp_id,\n" + 
    		"        round(adv_amount) as adv_amount \n" + 
    		"    from\n" + 
    		"        tbl_advance \n" + 
    		"    where\n" + 
    		"        ded_month=:month\n" + 
    		"        and ded_year=:year\n" + 
    		"        and del_status=1 \n" + 
    		"        and is_ded=1 \n" + 
    		"        and emp_id in (:empIds) and ex_int1=0", nativeQuery = true)
	List<GetAdvanceList> getAdvanceListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);


    @Query(value = "select\n" + 
    		"        id,\n" + 
    		"        emp_id,\n" + 
    		"        round(adv_amount) as adv_amount \n" + 
    		"    from\n" + 
    		"        tbl_advance \n" + 
    		"    where\n" + 
    		"        ded_month=:month\n" + 
    		"        and ded_year=:year\n" + 
    		"        and del_status=1 \n" + 
    		"        and is_ded=1 \n" + 
    		"        and emp_id in (:empIds) and ex_int1=1", nativeQuery = true)
	List<GetAdvanceList> getAbsentDedListSaparate(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

}
