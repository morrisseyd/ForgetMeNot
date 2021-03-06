
<%@ page import="com.forgetmenot.Task" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
	<g:javascript> 
     function success(){ 
 		document.location.href='${createLink(controller:'task', 
 		action:'list')}'; 
    } 
    
	</g:javascript>
	<script type="text/javascript">
    function changeId(id){ 
    	var theId = id;
 		var theElement = document.getElementById("taskid");
 		theElement.value = id;
 		var modalElement = document.getElementById("DeleteModal")
 		$(modalElement).modal()
 		
 	} 
</script>
</head>


<body>
	
<section id="list-task" class="first">

	<table class="table table-bordered">
		<thead>
			<tr>
			
				<g:sortableColumn property="description" title="${message(code: 'task.description.label', default: 'Description')}" />
				
				<g:sortableColumn property="creationDate" title="${message(code: 'task.creationDate.label', default: 'Created')}" />
			
				<g:sortableColumn property="due" title="${message(code: 'task.due.label', default: 'Due')}" />
			
				<g:sortableColumn property="status" title="${message(code: 'task.status.label', default: 'Status')}" />
				
				<g:sortableColumn property="completed" title="${message(code: 'task.done.label', default: 'Completed')}" />
				
				<g:sortableColumn property="Delete" title="Delete" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${taskInstanceList}" status="i" var="taskInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "description")}</g:link></td>
				
				<td><g:formatDate date="${taskInstance.creationDate}"/></td>
			
				<td><g:formatDate date="${taskInstance.due}"/></td>
			
				<td>${fieldValue(bean: taskInstance, field: "status")}</td>
				
				<td>
					<g:if test="${taskInstance.status != "Done"}">
						<g:set var="isChecked" value="${false}"/>
					</g:if>
					<g:else>
						<g:set var="isChecked" value="${true}"/>
					</g:else>
						<g:checkBox name='done' value="${isChecked}" onclick="${remoteFunction(action:'toggleDone', id:taskInstance.id, params:'\'done=\' + this.checked',onSuccess:'success();')}" />
				</td>
												
				<td>
				<g:form>
					<g:hiddenField name="deletetaskid" value="${taskInstance.id}" />
					<a href="#" onclick="changeId(${taskInstance.id})" role="button" class="" data-toggle="modal" title="${message(code: 'default.button.delete.label', default: 'Delete')}">
						<i class="icon-trash icon-large"></i> Delete
					</a>
						
				</g:form>					
				</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<g:render template="/_common/modals/deleteDialog" model="[taskInstance: taskInstance]"/>
	<div class="pagination">
		<bs:paginate total="${taskInstanceTotal}" />
	</div>
</section>

</body>

</html>
