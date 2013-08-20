package com.forgetmenot

import grails.plugins.springsecurity.SpringSecurityService;
import com.forgetmenot.security.User;
import com.forgetmenot.support.GroovySupport

/**
 * Task object.
 * @author David.Morrissey
 *
 */

class Task {
	String description
	Date due
	String status
	Date creationDate
	
	
	/**
	 * Setup relationship with User 
	 */
	User user
	static belongsTo = [user :User]
	
	/**
	 * Setup constraints
	 */
    static constraints = {
		//Cannot enter a blank description
		description blank: false, unique: true, maxSize: 300
		//Needs a due date
		due blank:false, deafult:new Date()
		//Status - started, not started, on hold
		status(inList: ["Started", "Not Started", "On Hold"])
		
    }

	/**
	 * Returns a list of tasks for displaying on the task list screen
	 * @param User the currently logged in user
	 * @returns List a list of tasks belonging to the user
	 */
	public static List getTaskListForCurrentUser(User uName) {
		def List tasks
		tasks = Task.findAllByUser(uName)		
		return tasks
	}
	

}
