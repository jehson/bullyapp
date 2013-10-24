package com.bullyapp

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*

import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class JSONTestTests {

    void setUp() {
	// Setup logic here
    }

    void tearDown() {
	// Tear down logic here
    }

    void testSomething() {
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
			"link":"http://i.imgur.com/Ga4lUog.png"
			},
		"success":true,
		"status":200 }"""
	
	JSONObject jsonObject = new JSONObject(rawResponse)
	JSONObject jsonData = jsonObject.get("data");
	def link = jsonData.get("link")
	assertEquals("http://i.imgur.com/Ga4lUog.png", link)
	
    }
}
