package com.forgetmenot

import grails.plugins.springsecurity.SpringSecurityService;

import com.forgetmenot.security.User;

class Task {
	String description
	Date due
	String status
	Date creationDate
	
	
	
	//Setup relationship with User
	User user
	static belongsTo = [user :User]
		
    static constraints = {
		//Cannot enter a blank description
		description blank: false, unique: true
		//Needs a due date
		due blank:false, deafult:new Date()
		//Status - started, not started, on hold
		status(inList: ["Started", "Not Started", "On Hold"])
		
    }
	
	public static List getTaskListForCurrentUser(User uName) {
		
		def List tasks
				
		//System.out.println("Current User is ${uName}.....")
		
		tasks = Task.findAllByUser(uName)
		
		//System.out.println("Result of getTaskListForCurrentUser User is ${tasks}.....")
				
		return tasks
	}
	

}
