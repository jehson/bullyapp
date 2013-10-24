<%@ page import="com.bullyapp.Bully" %>



<div class="fieldcontain ${hasErrors(bean: bullyInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="bully.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${bullyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bullyInstance, field: 'driversLicenseFile', 'error')} required">
	<label for="driversLicenseFile">
		<g:message code="bully.driversLicenseFile.label" default="Driver's License File" />
	</label>
	<input type="file" id="driversLicenseFile" name="driversLicenseFile" />
</div>

