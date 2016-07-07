package org.jboss.quickstarts.brms.bpms_notification_project;

import java.util.HashMap;
import java.util.Map;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.jbpm.process.workitem.*;

import org.slf4j.*;

public class DetermineNotificationDetailsHandler extends AbstractLogOrThrowWorkItemHandler {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(DetermineNotificationDetailsHandler.class);
	
	protected RuntimeManager runtimeManager;
	
	public DetermineNotificationDetailsHandler (RuntimeManager runtimeManager) {
		this.runtimeManager = runtimeManager;
	}
	
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		LOG.info("Entering executeWorkItem()");
		String notificationType = (String) workItem.getParameter("in_emailNotificationType");
		LOG.info("notificationType: " + notificationType);
		//Get the KieSession
		KieSession kieSession = getKieSession(workItem.getProcessInstanceId());
		//instantiation of new EmailNotification object and setting of its type
		EmailNotification emailNotification = new EmailNotification();
		emailNotification.setType(EmailNotificationType.valueOf(notificationType));
		//inserting the fact into Working Memory and evaluate based on rules supplied
		kieSession.insert(emailNotification);
		int i = kieSession.fireAllRules();
		LOG.info("emailNotification="+emailNotification);
		//map the now populated emailNotification back to the engine to be mapped to a process variable
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("out_emailNotification", emailNotification);
		manager.completeWorkItem(workItem.getId(), results);
	}
	

	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
	}
	
	private KieSession getKieSession(long processInstanceId) {
		ProcessInstanceIdContext context = new ProcessInstanceIdContext(processInstanceId);
		KieSession kieSession = runtimeManager.getRuntimeEngine(context).getKieSession();
		return kieSession;
	}

}

