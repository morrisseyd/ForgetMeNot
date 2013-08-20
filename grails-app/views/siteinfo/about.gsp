<html>

<head>
	<title><g:message code="default.about.title" args="[meta(name:'app.name')]"/></title>
	<meta name="layout" content="kickstart" />
</head>

<body>

	<section id="intro">
		<h1><g:message code="default.welcome.title" args="[meta(name:'app.name')]"/> <g:img dir="images" file="BlueLogoKnot.png" width="40" height="40"/></h1>
		<p class="lead">
			${meta(name:'app.name')} is in Beta Release!!! 
		<p>ForgetMeNot is a small application for managing tasks. It allows the user to add, edit,
			and delete tasks basically covering all CRUD operations. ForgetMeNot is built using Grails v. 2.2.4 
			and uses Spring Security for RBAC functionality. It allows uses the Bootstrap, from Twitter which is implemented
			via the kickstart plugin. Bootstrap uses responsive CSS to alter the look and feel based on the access device.</p>
	</section>

</body>

</html>
