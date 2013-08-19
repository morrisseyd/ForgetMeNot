package com.forgetmenot

import java.util.Map;

import javax.servlet.FilterChain;

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.expression.Expression;

/**
 * ForgetMeNotTagLib
 * A taglib library provides a set of reusable tags to help rendering the views.
 */
class ForgetMeNotTagLib {

	//static namespace = 'fmn'
	
	/** Dependency injection for springSecurityService. */
	def springSecurityService
	
	/**
	 * Renders the user if logged in.
	 */
	def loggedinuser = {attrs, body ->
		if (springSecurityService.isLoggedIn()) {
			out << springSecurityService.currentUser
		}
	
	}
	
}
