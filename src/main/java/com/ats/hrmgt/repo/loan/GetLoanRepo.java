package com.ats.hrmgt.repo.loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.loan.GetLoan;

public interface GetLoanRepo extends JpaRepository<GetLoan, Integer> {
	
	@Query(value=" SELECT\n" + 
			"         m_employees.emp_id,\n" + 
			"        SUM(tbl_loan_main.loan_amt) AS loan_amt,\n" + 
			"        SUM(tbl_loan_main.loan_emi) AS loan_emi, SUM(tbl_loan_main.loan_emi_intrest) AS  loan_emi_intrest,\n" + 
			"        SUM(tbl_loan_main.loan_repay_amt) AS loan_repay_amt,\n" + 
			"        SUM(         tbl_loan_main.current_outstanding     ) AS current_outstanding,\n" + 
			"        m_employees.emp_code,\n" + 
			"        m_employees.first_name,\n" + 
			"        m_employees.middle_name,\n" + 
			"        m_employees.surname,\n" + 
			"        m_designation.name AS designation,'NA' as ex_var1 ,'NA' as ex_var2,'NA' as ex_var3\n" + 
			"     FROM\n" + 
			"        m_employees,\n" + 
			"        tbl_loan_main,\n" + 
			"        m_designation \n" + 
			"    WHERE\n" + 
			"        tbl_loan_main.del_status = 1 \n" + 
			"        AND m_designation.desig_id = m_employees.designation_id \n" + 
			"        AND YEAR(tbl_loan_main.loan_add_date) =:calYrId \n" + 
			"        AND tbl_loan_main.loan_status =:status \n" + 
			"        AND tbl_loan_main.emp_id = m_employees.emp_id \n" + 
			"        AND tbl_loan_main.cmp_id =:companyId \n" + 
			"    GROUP BY\n" + 
			"        tbl_loan_main.emp_id",nativeQuery=true)
	List<GetLoan> getLoanHistoryEmpwise(@Param("companyId") int companyId,@Param("status") String status,@Param("calYrId") String calYrId);
	

	
	@Query(value=" SELECT\n" + 
			"         m_employees.emp_id,\n" + 
			"        SUM(tbl_loan_main.loan_amt) AS loan_amt,\n" + 
			"        SUM(tbl_loan_main.loan_emi) AS loan_emi,     SUM(tbl_loan_main.loan_emi_intrest) AS  loan_emi_intrest,\n" + 
			"        SUM(tbl_loan_main.loan_repay_amt) AS loan_repay_amt,\n" + 
			"        SUM(         tbl_loan_main.current_outstanding     ) AS current_outstanding,\n" + 
			"        m_employees.emp_code,\n" + 
			"        m_employees.first_name,\n" + 
			"        m_employees.middle_name,\n" + 
			"        m_employees.surname,\n" + 
			"        m_designation.name AS designation,'NA' as ex_var1 ,'NA' as ex_var2,'NA' as ex_var3\n" + 
			"     FROM\n" + 
			"        m_employees,\n" + 
			"        tbl_loan_main,\n" + 
			"        m_designation \n" + 
			"    WHERE\n" + 
			"        tbl_loan_main.del_status = 1 \n" + 
			"        AND m_designation.desig_id = m_employees.designation_id \n" + 
			"          \n" + 
			"        \n" + 
			"        AND tbl_loan_main.emp_id = m_employees.emp_id \n" + 
			"        AND tbl_loan_main.cmp_id =:companyId AND  tbl_loan_main.loan_status='Active' \n" + 
			"    GROUP BY\n" + 
			"        tbl_loan_main.emp_id",nativeQuery=true)
	List<GetLoan> getLoanHistoryEmpwiseComp(@Param("companyId") int companyId);
	@Query(value=" SELECT\n" + 
			"         m_employees.emp_id,\n" + 
			"        SUM(tbl_loan_main.loan_amt) AS loan_amt,\n" + 
			"        SUM(tbl_loan_main.loan_emi) AS loan_emi,SUM(tbl_loan_main.loan_emi_intrest) AS  loan_emi_intrest,\n" + 
			"        SUM(tbl_loan_main.loan_repay_amt) AS loan_repay_amt,\n" + 
			"        SUM(         tbl_loan_main.current_outstanding     ) AS current_outstanding,\n" + 
			"        m_employees.emp_code,\n" + 
			"        m_employees.first_name,\n" + 
			"        m_employees.middle_name,\n" + 
			"        m_employees.surname,\n" + 
			"        m_designation.name AS designation,'NA' as ex_var1 ,'NA' as ex_var2,'NA' as ex_var3\n" + 
			"     FROM\n" + 
			"        m_employees,\n" + 
			"        tbl_loan_main,\n" + 
			"        m_designation \n" + 
			"    WHERE\n" + 
			"        tbl_loan_main.del_status = 1 \n" + 
			"        AND m_designation.desig_id = m_employees.designation_id \n" + 
			"        AND YEAR(tbl_loan_main.loan_add_date) =:calYrId \n" + 
			"        AND tbl_loan_main.loan_status =:status \n" + 
			"        AND tbl_loan_main.emp_id = m_employees.emp_id \n" + 
			"        AND tbl_loan_main.cmp_id =:companyId  AND tbl_loan_main.emp_id=:empId \n" + 
			"    GROUP BY\n" + 
			"        tbl_loan_main.emp_id",nativeQuery=true)
	GetLoan getLoanHistoryEmpwiseSpec(@Param("companyId") int companyId,@Param("status") String status,@Param("calYrId") String calYrId,@Param("empId") int empId);
	
	@Query(value=" SELECT\n" + 
			"         m_employees.emp_id,\n" + 
			"        SUM(tbl_loan_main.loan_amt) AS loan_amt,SUM(tbl_loan_main.loan_emi_intrest) AS  loan_emi_intrest,\n" + 
			"        SUM(tbl_loan_main.loan_emi) AS loan_emi,\n" + 
			"        SUM(tbl_loan_main.loan_repay_amt) AS loan_repay_amt,\n" + 
			"        SUM(         tbl_loan_main.current_outstanding     ) AS current_outstanding,\n" + 
			"        m_employees.emp_code,\n" + 
			"        m_employees.first_name,\n" + 
			"        m_employees.middle_name,\n" + 
			"        m_employees.surname,\n" + 
			"        m_designation.name AS designation,'NA' as ex_var1 ,'NA' as ex_var2,'NA' as ex_var3\n" + 
			"     FROM\n" + 
			"        m_employees,\n" + 
			"        tbl_loan_main,\n" + 
			"        m_designation \n" + 
			"    WHERE\n" + 
			"        tbl_loan_main.del_status = 1 \n" + 
			"        AND m_designation.desig_id = m_employees.designation_id \n" + 
			"        \n" + 
			"         \n" + 
			"        AND tbl_loan_main.emp_id = m_employees.emp_id \n" + 
			"        AND tbl_loan_main.cmp_id =:companyId  AND tbl_loan_main.emp_id=:empId AND tbl_loan_main.loan_status='Active' \n" + 
			"    GROUP BY\n" + 
			"        tbl_loan_main.emp_id",nativeQuery=true)
	GetLoan getLoanHistoryEmpwiseSpec(@Param("companyId") int companyId,@Param("empId") int empId);


	@Query(value=" SELECT\n" + 
			"         m_employees.emp_id,\n" + 
			"        SUM(tbl_loan_main.loan_amt) AS loan_amt,\n" + 
			"        SUM(tbl_loan_main.loan_emi) AS loan_emi,     SUM(tbl_loan_main.loan_emi_intrest) AS  loan_emi_intrest,\n" + 
			"        SUM(tbl_loan_main.loan_repay_amt) AS loan_repay_amt,\n" + 
			"        SUM(         tbl_loan_main.current_outstanding     ) AS current_outstanding,\n" + 
			"        m_employees.emp_code,\n" + 
			"        m_employees.first_name,\n" + 
			"        m_employees.middle_name,\n" + 
			"        m_employees.surname,\n" + 
			"        m_designation.name AS designation,'NA' as ex_var1 ,'NA' as ex_var2,'NA' as ex_var3\n" + 
			"     FROM\n" + 
			"        m_employees,\n" + 
			"        tbl_loan_main,\n" + 
			"        m_designation \n" + 
			"    WHERE\n" + 
			"        tbl_loan_main.del_status = 1 \n" + 
			"        AND m_designation.desig_id = m_employees.designation_id \n" + 
			"          \n" + 
			"        \n" + 
			"        AND tbl_loan_main.emp_id = m_employees.emp_id \n" + 
			"        AND tbl_loan_main.cmp_id =1 AND  tbl_loan_main.loan_status='Active' and m_employees.location_id in (:locId)\n" + 
			"    GROUP BY\n" + 
			"        tbl_loan_main.emp_id",nativeQuery=true)
	List<GetLoan> getLoanHistoryEmpWiseForCompanyLocId(@Param("locId") List<Integer> locId);


	@Query(value=" SELECT\n" + 
			"         m_employees.emp_id,\n" + 
			"        SUM(tbl_loan_main.loan_amt) AS loan_amt,\n" + 
			"        SUM(tbl_loan_main.loan_emi) AS loan_emi, SUM(tbl_loan_main.loan_emi_intrest) AS  loan_emi_intrest,\n" + 
			"        SUM(tbl_loan_main.loan_repay_amt) AS loan_repay_amt,\n" + 
			"        SUM(         tbl_loan_main.current_outstanding     ) AS current_outstanding,\n" + 
			"        m_employees.emp_code,\n" + 
			"        m_employees.first_name,\n" + 
			"        m_employees.middle_name,\n" + 
			"        m_employees.surname,\n" + 
			"        m_designation.name AS designation,'NA' as ex_var1 ,'NA' as ex_var2,'NA' as ex_var3\n" + 
			"     FROM\n" + 
			"        m_employees,\n" + 
			"        tbl_loan_main,\n" + 
			"        m_designation \n" + 
			"    WHERE\n" + 
			"        tbl_loan_main.del_status = 1 \n" + 
			"        AND m_designation.desig_id = m_employees.designation_id \n" + 
			"        AND YEAR(tbl_loan_main.loan_add_date) =:calYrId \n" + 
			"        AND tbl_loan_main.loan_status =:status \n" + 
			"        AND tbl_loan_main.emp_id = m_employees.emp_id \n" + 
			"        AND tbl_loan_main.cmp_id =:companyId and m_employees.location_id in(:locId)\n" + 
			"    GROUP BY\n" + 
			"        tbl_loan_main.emp_id",nativeQuery=true)
	List<GetLoan> getLoanHistoryEmpWiseLocId( @Param("companyId")int companyId, @Param("status") String status,  @Param("calYrId")String calYrId, @Param("locId") List<Integer> locId);

}
