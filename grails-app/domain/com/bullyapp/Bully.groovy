package com.bullyapp

class Bully {

    String name
    byte[] driversLicenseFile
    String driversLicenseImageLink
    
    static constraints = {
	name blank:false
	driversLicenseFile blank:false, nullable:true, size:0..1000000
	driversLicenseImageLink blank:true, nullable:true
    }
}
