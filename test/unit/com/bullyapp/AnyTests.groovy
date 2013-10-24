package com.bullyapp

import static org.junit.Assert.*

import java.io.IOException;
import java.util.List;

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.jackson.map.ObjectMapper
import org.junit.*
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate;

import com.bullyapp.imgur.UploadRequest;
import com.bullyapp.imgur.UploadResponse;


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class AnyTests {
    def uploadURL = 'https://api.imgur.com/3/upload.json'
    def clientId = '99c5fed06cad39e'
    void setUp() {
	// Setup logic here
    }

    void tearDown() {
	// Tear down logic here
    }

    void testGettingJSONObjects() {
	def expectedLink = "http://i.imgur.com/Ga4lUog.png"
	def rawResponse= """
		{"data":{
			"id":"Ga4lUog",
			"title":null,
			"description":null,
			"datetime":1382599966,
			"type":"image/png",
			"animated":false,
			"width":52,
			"height":51,
			"size":165,
			"views":0,
			"bandwidth":0,
			"favorite":false,
			"nsfw":null,
			"section":null,
			"deletehash":"1zpNJ7GyUSrbYLu",
			"link":"${expectedLink}"
			},
		"success":true,
		"status":200 }"""

	JSONObject jsonObject = new JSONObject(rawResponse)
	JSONObject jsonData = jsonObject.get("data");
	def link = jsonData.get("link")
	assertEquals(expectedLink, link)
    }

    void testReSTTemplate() {

	RestTemplate template = new RestTemplate();

	UploadRequest request = new UploadRequest();
	request.setImage('iVBORw0KGgoAAAANSUhEUgAAADQAAAAzCAIAAABXH7wEAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAA6SURBVGhD7dAxAQAAAMKg9U/tYwyIQGHAgAEDBgwYMGDAgAEDBgwYMGDAgAEDBgwYMGDAgAEDBgx8YB9HAAFbYcvmAAAAAElFTkSuQmCC');
	request.setType('base64');
	request.setKey(clientId);
	
	String auth = "Client-ID ${clientId}"
	
	
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.put("Authorization", Collection.asList(auth));
	
	HttpEntity<UploadRequest> httpRequest = new HttpEntity<UploadRequest>();
	UploadResponse response = template.postForObject(uploadURL, request, UploadResponse.class);
	
    }

    private class UploadRequestHttpMessageConverter implements HttpMessageConverter<UploadRequest> {

	@Override
	public boolean canRead(Class<?> arg0, MediaType arg1) {
	    // TODO Auto-generated method stub
	    return true;
	}

	@Override
	public boolean canWrite(Class<?> arg0, MediaType arg1) {
	    // TODO Auto-generated method stub
	    return true;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
	    // TODO Auto-generated method stub
	    return Collection.asList({MediaType.APPLICATION_JSON});
	}

	@Override
	public UploadRequest read(Class<? extends UploadRequest> request,
		HttpInputMessage inputMessage) throws IOException,
	HttpMessageNotReadableException {
	    // TODO Auto-generated method stub
	    String inputString = FileCopyUtils.copyToString(inputMessage.getBody());
	    JSONObject jsonObject = new JSONObject(inputString);
	    return null;
	}

	@Override
	public void write(UploadRequest arg0, MediaType arg1,
		HttpOutputMessage arg2) throws IOException, HttpMessageNotWritableException {
	    // TODO Auto-generated method stub
	h

	}

    }

}
