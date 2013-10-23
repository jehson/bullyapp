package com.bullyapp

class Bully {
	
	String name
	String color
	String type
	byte[] driversLicenseFile
	String driversLicenseFileName
	String driversLicenseContentType

	static constraints = {
		name blank:false
		type blank:false
		color blank:false
		driversLicenseFile size:0..1000000
		driversLicenseFileName nullable:true
		driversLicenseContentType nullable:true
    }
	
}
