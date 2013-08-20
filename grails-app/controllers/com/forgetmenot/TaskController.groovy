package com.forgetmenot


import grails.plugins.springsecurity.Secured;
import com.forgetmenot.Task;
import com.forgetmenot.security.User;
import grails.plugins.springsecurity.SpringSecurityService;
import com.forgetmenot.support.GroovySupport

/**
 * Controller for the Task views.
 * @author David.Morrissey
 *
 */
class TaskController {
	
	def SpringSecurityService springSecurityService
	def taskStatusValues
	
	@Secured(['ROLE_USER','IS_AUTHENTICATED_FULLY'])
	def scaffold = Task
	
	/**
	 * Creates the view.
	 * The values of the dropdown list are located within resources.groovy
	 * This central location allows us to update the values so they can be
	 * used throughout the application. 
	 */
	def create = {		
		[taskStatusValues: taskStatusValues]
	}
	
	/**
	 * Gets the currently loggedin user and passes to a method within 
	 * the domain class to return a list of task for only that user
	 */
	def list() {
		//Get the currently logged in user and only return the list of tasks
		//for this user.
		def userLoggedIn = springSecurityService.currentUser		
		[taskInstanceList: Task.getTaskListForCurrentUser(userLoggedIn),
		taskInstanceTotal: Task.count(),
		userInstanceList: User.list()	]	
	}
	
	/**
	 * Override Delete Action and apply code to delete 
	 * specific tasks.
	 */
	def delete = {
		def taskInstance = Task.findById(params.taskid.toLong())
		if (taskInstance) {
			try {
				taskInstance.delete(flush: true)
				flash.message = "${message(code: 'default.task.deleted.message', args: [message(code: 'Task.label', default: 'Task'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.task.not.deleted.message', args: [message(code: 'Task.label', default: 'Task'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'Task.label', default: 'Task'), params.id])}"
			redirect(action: "list")
		}
	}
	
	/**
	 * Override save Action and apply code to delete 
	 * specific tasks.
	 */ 
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
			
			task.save()
		}
		
		//Redirect to the listview
		redirect(action:list)
		
	}
	
}
