package com.forgetmenot


import grails.plugins.springsecurity.Secured;
import com.forgetmenot.Task;
import com.forgetmenot.security.User;
import grails.plugins.springsecurity.SpringSecurityService;
import com.forgetmenot.support.GroovySupport


class TaskController {
	
	def SpringSecurityService springSecurityService
	def taskStatusValues
	
	@Secured(['ROLE_USER','IS_AUTHENTICATED_FULLY'])
	def scaffold = Task
	
	//Used for the create View
	def create = {
		
		//The vlaues of the dropdown list are located within the resources.groovy
		//This central location allows us to update the values so they can be
		//used throughout the application.
		[taskStatusValues: taskStatusValues]
		
	}
	
	
	def list() {
		
		//Get the currently logged in user and only return the list of tasks
		//for this user.
		def userLoggedIn = springSecurityService.currentUser		
		[taskInstanceList: Task.getTaskListForCurrentUser(userLoggedIn),
		taskInstanceTotal: Task.count(),
		userInstanceList: User.list()	]	
	}
	
	//Override Delete Scaffold
	def delete = {
		def taskInstance = Task.findById(params.taskid.toLong())
		if (taskInstance) {
			try {
				taskInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'Task.label', default: 'Task'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'Task.label', default: 'Task'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'Task.label', default: 'Task'), params.id])}"
			redirect(action: "list")
		}
	}
	
	//Override the scaffolded save action 
	def save() {
		
		def now = new Date()
		def task = new Task(params)
		User loggedInUser
						
		log.info "***getting the current user***"
		log.info "***Principal is: ${springSecurityService.principal}"
			
		loggedInUser = User.findByUsername(springSecurityService.principal.username)
					
		log.info "***loggedInUser is: ${loggedInUser}***"
		
		if(loggedInUser){
			log.info "***Setting current user and saving task***"
			task.user = loggedInUser
			task.creationDate = now
			
			try {
				task.save()
			} catch (Exception e) {
				e.printStackTrace()
			}
		}
		
		//Redirect to the listview
		redirect(action:list)
		
	}
	
}
