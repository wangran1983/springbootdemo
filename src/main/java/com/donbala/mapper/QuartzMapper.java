package com.donbala.mapper;

import com.donbala.model.Quartz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuartzMapper {

	 List<Quartz> selectJobPlan(Quartz qz);

	 int selectJobPlanCount(Quartz qz);

	 List<Quartz> getAllJobPlan();

	 List<Quartz> getAvailableJobPlan();

	 List<Quartz> selectJobName();

	 List<Quartz> selectRepeatUnit();

	 List<Quartz> selectJobParam(Quartz qz);

	 int insertJobPlanDef(Quartz qz);

	 int insertJobPlanParam(Quartz qz);

	 int deleteJobPlanDef(Map<String, String> map);

	 int deleteJobPlanParam(Map<String, String> map);

	 int stopJobPlanDef(Map<String, String> map);

	 int startJobPlanDef(Map<String, String> map);

	 Quartz selectStopJobPlan(Map<String, String> map);

	 List<Quartz> selectReturnView(Quartz qz);

	 List<Quartz> selectParamByCode(Quartz qz);

	 int deletePlanParam(Quartz qz);

	 int updateJobPlan(Quartz qz);

	 Quartz selectJobPlanState(String jobPlanCode);

	 void updateJobRunLog(Map<String, String> map);

	 String selectJobRunLogSerialNO(Map<String, String> map);

	 void updateJobPlanStateStart(@Param("jobPlanCode") String jobPlanCode);

	 void updateJobPlanStateTerminate(@Param("jobPlanCode") String jobPlanCode);
}
