package it.polimi.modaclouds.adaptationDesignTime4Cloud;


import it.polimi.modaclouds.qos_models.schema.ApplicationTier;
import it.polimi.modaclouds.qos_models.schema.Container;
import it.polimi.modaclouds.qos_models.schema.Containers;
import it.polimi.tower4clouds.rules.Action;
import it.polimi.tower4clouds.rules.CollectedMetric;
import it.polimi.tower4clouds.rules.Condition;
import it.polimi.tower4clouds.rules.MonitoredTarget;
import it.polimi.tower4clouds.rules.MonitoringMetricAggregation;
import it.polimi.tower4clouds.rules.MonitoringRule;
import it.polimi.tower4clouds.rules.MonitoringRules;
import it.polimi.tower4clouds.rules.ObjectFactory;
import it.polimi.tower4clouds.rules.Parameter;


public class MonitoringRulesHelper {
	
	public MonitoringRulesHelper(){
		
	}
	
	public MonitoringRules createResponseTimeThresholdRules(Containers model, int timestepDuration){
		
		
		ObjectFactory factory= new ObjectFactory();
		MonitoringRules toReturn= factory.createMonitoringRules();
		MonitoringRule rule;
		MonitoredTarget target;
		Action action;
		CollectedMetric collectedMetric;
		Condition condition;
		MonitoringMetricAggregation aggregation;
		Parameter tempParam;
		
			for(Container c: model.getContainer()){
				for(ApplicationTier t: c.getApplicationTier()){
					
					if(t.getFunctionality().size()>0){
					
						double threshold= t.getResponseTimeThreshold().get(0).getValue();
						
						rule= factory.createMonitoringRule();
						rule.setMonitoredTargets(factory.createMonitoredTargets());
						rule.setId("respTimeThreshold_"+t.getId());
						rule.setTimeStep(Integer.toString(timestepDuration*60));
						rule.setTimeWindow(Integer.toString(timestepDuration*60));
						
						target=factory.createMonitoredTarget();
						target.setClazz("VM");
						target.setType(t.getId());
							
						rule.getMonitoredTargets().getMonitoredTargets().add(target);
						
						
						collectedMetric=factory.createCollectedMetric();
						collectedMetric.setMetricName("ResponseTime");
						tempParam=factory.createParameter();
						tempParam.setName("samplingProbability");
						tempParam.setValue("1");
						collectedMetric.getParameters().add(tempParam);
						
						rule.setCollectedMetric(collectedMetric);
						
						aggregation=factory.createMonitoringMetricAggregation();
						aggregation.setAggregateFunction("Average");
						aggregation.setGroupingClass("VM");
						
						rule.setMetricAggregation(aggregation);
						
						
						condition=factory.createCondition();
	
						condition.setValue("METRIC > "+threshold);
						
						rule.setCondition(condition);
						
						action=factory.createAction();
						action.setName("OutputMetric");
						
		
						
						tempParam=factory.createParameter();
						tempParam.setName("metric");
						tempParam.setValue("ResponseTimeOverThreshold");
						action.getParameters().add(tempParam);
						
						tempParam=factory.createParameter();
						tempParam.setName("value");
						tempParam.setValue("METRIC");
						action.getParameters().add(tempParam);
						
						tempParam=factory.createParameter();
						tempParam.setName("resourceId");
						tempParam.setValue("ID");
						action.getParameters().add(tempParam);
						
						rule.setActions(factory.createActions());
						
						rule.getActions().getActions().add(action);
		
						toReturn.getMonitoringRules().add(rule);
					
					}
				}
			}
		
	
		return toReturn;
	}

}


