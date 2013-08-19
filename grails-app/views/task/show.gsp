
<%@ page import="com.forgetmenot.Task" %>
<!doctype html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-task" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="task.description.label" default="Description" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: taskInstance, field: "description")}</td>
				
			</tr>
			
			<tr class="prop">
				<td valign="top" class="name"><g:message code="task.creationDate.label" default="Created" /></td>
				
				<td valign="top" class="value"><g:formatDate date="${taskInstance?.creationDate}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="task.due.label" default="Due" /></td>
				
				<td valign="top" class="value"><g:formatDate date="${taskInstance?.due}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="task.status.label" default="Status" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: taskInstance, field: "status")}</td>
				
			</tr>
							
		</tbody>
	</table>
</section>

</body>

</html>
