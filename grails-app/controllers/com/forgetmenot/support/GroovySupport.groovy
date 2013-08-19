package com.forgetmenot.support

import grails.plugins.springsecurity.SpringSecurityService;

class GroovySupport {
	
	
	def currentUser() {
		
		def SpringSecurityService springSecurityService
		
		//get the current user based on the id
		def userLoggedIn = springSecurityService.currentUser
		return userLoggedIn
	}
}
