<%@ page import="com.forgetmenot.Task" %>

			<div class="control-group fieldcontain ${hasErrors(bean: taskInstance, field: 'description', 'error')} required">
				<label for="description" class="control-label"><g:message code="task.description.label" default="Description" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:textField name="description" required="" value="${taskInstance?.description}"/>
					<span class="help-inline">${hasErrors(bean: taskInstance, field: 'description', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: taskInstance, field: 'due', 'error')} required">
				<label for="due" class="control-label"><g:message code="task.due.label" default="Due" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<bs:datePicker name="due" precision="day"  value="${taskInstance?.due}"  />
					<span class="help-inline">${hasErrors(bean: taskInstance, field: 'due', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: taskInstance, field: 'status', 'error')} required">
				<label for="status" class="control-label"><g:message code="task.status.label" default="Status" /><span class="required-indicator">*</span></label>
				<div class="controls">
					
					<g:if test="${taskInstance == null}">
						<g:select name="status" optionKey="key" optionValue="value" from="${taskStatusValues.entrySet()}" noSelection="['':'-Choose the task status-']" />
					</g:if>
					<g:else>
						<g:select name="status" optionKey="key" optionValue="value" from="${taskStatusValues.entrySet()}" value="${taskInstance?.status}" noSelection="['':'-Choose the task status-']"/>
					</g:else>		
										
					<span class="help-inline">${hasErrors(bean: taskInstance, field: 'status', 'error')}</span>
				</div>
			</div>
			

			
			

			

		

