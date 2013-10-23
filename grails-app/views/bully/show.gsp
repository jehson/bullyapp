
<%@ page import="com.bullyapp.Bully" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bully.label', default: 'Bully')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-bully" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bully" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list bully">
			
				<g:if test="${bullyInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="bully.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${bullyInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bullyInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="bully.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${bullyInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bullyInstance?.color}">
				<li class="fieldcontain">
					<span id="color-label" class="property-label"><g:message code="bully.color.label" default="Color" /></span>
					
						<span class="property-value" aria-labelledby="color-label"><g:fieldValue bean="${bullyInstance}" field="color"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bullyInstance?.driversLicenseContentType}">
				<li class="fieldcontain">
					<span id="driversLicenseContentType-label" class="property-label"><g:message code="bully.driversLicenseContentType.label" default="Drivers License Content Type" /></span>
					
						<span class="property-value" aria-labelledby="driversLicenseContentType-label">
						<g:fieldValue bean="${bullyInstance}" field="driversLicenseContentType"/>
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${bullyInstance?.driversLicenseFile}">
				<li class="fieldcontain">
					<span id="driversLicenseFile-label" class="property-label"><g:message code="bully.driversLicenseFile.label" default="Drivers License File" /></span>
					
					<span id="driversLicenseFile-label" class="property-label">
						<image src="${createLink(controller:'bully', action:'getDriversLicenseImage', id:bullyInstance.ident()) }" />
					</span>
					
				</li>
				</g:if>
			
				<g:if test="${bullyInstance?.driversLicenseFileName}">
				<li class="fieldcontain">
					<span id="driversLicenseFileName-label" class="property-label"><g:message code="bully.driversLicenseFileName.label" default="Drivers License File Name" /></span>
					
						<span class="property-value" aria-labelledby="driversLicenseFileName-label"><g:fieldValue bean="${bullyInstance}" field="driversLicenseFileName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${bullyInstance?.id}" />
					<g:link class="edit" action="edit" id="${bullyInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
