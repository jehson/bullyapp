package com.bullyapp

import groovy.util.logging.Log;

import java.util.logging.Logger;
import java.util.prefs.Base64;

import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils;
import org.bouncycastle.math.ec.WTauNafMultiplier;
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

class BullyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def getFileDetails = { bully ->
	if(request instanceof MultipartHttpServletRequest) {
	    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	    CommonsMultipartFile file = (CommonsMultipartFile)multiRequest.getFile("driversLicenseFile");
	    bully.driversLicenseFileName = file.originalFilename
	    bully.driversLicenseContentType = file.contentType
	    bully.driversLicenseFile = file.bytes
	}
    }

    def getDriversLicenseImage = {
	def bullyInstance = Bully.get(params.id);
	response.setContentType(bullyInstance.driversLicenseContentType);
	response.setContentLength(bullyInstance.driversLicenseFile.size());
	OutputStream out = response.getOutputStream();
	out.write(bullyInstance.driversLicenseFile);
	out.close();
    }

    def index() {
	redirect(action: "list", params: params)
    }

    def list(Integer max) {
	params.max = Math.min(max ?: 10, 100)
	[bullyInstanceList: Bully.list(params), bullyInstanceTotal: Bully.count()]
    }

    def create() {
	[bullyInstance: new Bully(params)]
    }

    def save() {
	def bullyInstance = new Bully(params)
	getFileDetails bullyInstance

	def url = 'https://api.imgur.com/3/upload.json'
	def clientId = '99c5fed06cad39e'
	def clientSecret = '6744141b06ab5a310850f04bfb36ac956d09ae6f'
	def b64 = Base64.byteArrayToBase64(bullyInstance.driversLicenseFile)
	def reqString = 'image=${b64}&key=${clientId}&type=base64'

	HttpClient httpClient = new DefaultHttpClient();
	try {
	    HttpPost httpPost = new HttpPost(url);
	    StringEntity body = new StringEntity(reqString);
	    httpPost.addHeader("Authorization", "Client-ID " + clientId);
	    httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
	    httpPost.setEntity(body);
	    HttpResponse httpResponse = httpClient.execute(httpPost);
	    String responseString = EntityUtils.toString(httpResponse.getEntity());

	    // handle response here...
	    log.debug 'RESPONSE IS: ${responseString}'

	}catch (Exception ex) {
	    // handle exception here
	    log.error 'NO RESPONSE'
	    log.error ex.printStackTrace()
	} finally {
	    httpClient.getConnectionManager().shutdown();
	}

	if (!bullyInstance.save(flush: true)) {
	    render(view: "create", model: [bullyInstance: bullyInstance])
	    return
	}

	flash.message = message(code: 'default.created.message', args: [
	    message(code: 'bully.label', default: 'Bully'),
	    bullyInstance.id
	])
	redirect(action: "show", id: bullyInstance.id)
    }

    def show(Long id) {
	def bullyInstance = Bully.get(id)

	if (!bullyInstance) {
	    flash.message = message(code: 'default.not.found.message', args: [
		message(code: 'bully.label', default: 'Bully'),
		id
	    ])
	    redirect(action: "list")
	    return
	}

	[bullyInstance: bullyInstance]
    }

    def edit(Long id) {
	def bullyInstance = Bully.get(id)
	if (!bullyInstance) {
	    flash.message = message(code: 'default.not.found.message', args: [
		message(code: 'bully.label', default: 'Bully'),
		id
	    ])
	    redirect(action: "list")
	    return
	}

	[bullyInstance: bullyInstance]
    }

    def update(Long id, Long version) {
	def bullyInstance = Bully.get(id)
	if (!bullyInstance) {
	    flash.message = message(code: 'default.not.found.message', args: [
		message(code: 'bully.label', default: 'Bully'),
		id
	    ])
	    redirect(action: "list")
	    return
	}

	if (version != null) {
	    if (bullyInstance.version > version) {
		bullyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
			[
			    message(code: 'bully.label', default: 'Bully')] as Object[],
			"Another user has updated this Bully while you were editing")
		render(view: "edit", model: [bullyInstance: bullyInstance])
		return
	    }
	}

	bullyInstance.properties = params

	if (!bullyInstance.save(flush: true)) {
	    render(view: "edit", model: [bullyInstance: bullyInstance])
	    return
	}

	flash.message = message(code: 'default.updated.message', args: [
	    message(code: 'bully.label', default: 'Bully'),
	    bullyInstance.id
	])
	redirect(action: "show", id: bullyInstance.id)
    }

    def delete(Long id) {
	def bullyInstance = Bully.get(id)
	if (!bullyInstance) {
	    flash.message = message(code: 'default.not.found.message', args: [
		message(code: 'bully.label', default: 'Bully'),
		id
	    ])
	    redirect(action: "list")
	    return
	}

	try {
	    bullyInstance.delete(flush: true)
	    flash.message = message(code: 'default.deleted.message', args: [
		message(code: 'bully.label', default: 'Bully'),
		id
	    ])
	    redirect(action: "list")
	}
	catch (DataIntegrityViolationException e) {
	    flash.message = message(code: 'default.not.deleted.message', args: [
		message(code: 'bully.label', default: 'Bully'),
		id
	    ])
	    redirect(action: "show", id: id)
	}
    }
}
