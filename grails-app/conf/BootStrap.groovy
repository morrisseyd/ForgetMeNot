import java.awt.color.ProfileDataException;

import com.forgetmenot.security.Role;
import com.forgetmenot.security.UserRole;
import com.forgetmenot.security.User
import com.forgetmenot.Task

/**
 * Sets up our temp objects within the in-memory DB. The objects are Task, Role, and User 
 * objects.
 * @author David.Morrissey
 */

class BootStrap {
	
	def springSecurityService
	
	/**
	 * Initializes Task, UserRole, and User objects. 
	 */
	
	def init =  { servletContext ->
	
		//KISS - no loops or itterators
		//Randoms For Task creation
		def now = new Date()
		def random = new Random()
		
		//Check to see if they exist first if not create
		def userRole = Role.findByAuthority("ROLE_USER")?: new Role(authority: "ROLE_USER").save()
		def adminRole = Role.findByAuthority("ROLE_ADMIN")?: new Role(authority: "ROLE_ADMIN").save()
		
		def userChuck = new User(username: "chuck_norris", password: "password", enabled: true).save(failOnError:true)
		def userOpera = new User(username: "opera", password: "password", enabled: true).save(failOnError:true)
		def userTest = new User(username: "test", password: "abc123", enabled: true).save(failOnError:true)
		
		UserRole.create userChuck, userRole
		UserRole.create userOpera, userRole
		UserRole.create userTest, userRole
		
		//Setup some Tasks for chuck
		def task1Chuck = new Task(description: "Showdown with Charles Bronsan", creationDate: now, due:now+10, status:"Done", done:true, user: userChuck).save(failOnError:true)
		def task2Chuck = new Task(description: "Showdown with Arnie", creationDate: now, due:now+10, status:"Not Started", done:false, user: userChuck).save(failOnError:true)
		def task3Chuck = new Task(description: "Showdown with Sly", creationDate: now, due:now+10, status:"Done", done:true, user: userChuck).save(failOnError:true)
		def task4Chuck = new Task(description: "Showdown with Vin", creationDate: now, due:now+10, status:"Not Started", done:false, user: userChuck).save(failOnError:true)
		//Setup some tasks for Opera
		def task1Opera = new Task(description: "Showdown with Judge Judy", creationDate: now, due:now+10, status:"Not Started", done:false, user: userOpera).save(failOnError:true)
		def task2Opera = new Task(description: "Showdown with Ellen", creationDate: now, due:now+10, status:"Not Started", done:false, user: userOpera).save(failOnError:true)
		//Setup some tasks for Test
		def task1Test = new Task(description: "Buy some slinkys", creationDate: now, due:now+10, status:"Done", done:true, user: userTest).save(failOnError:true)
		def task2Test = new Task(description: "Put the set list together", creationDate: now+10, due:now, status:"Not Started", done:false, user: userTest).save(failOnError:true)
		def task3Test = new Task(description: "New machinehead", creationDate: now, due:now+10, status:"Not Started", done:false, user: userTest).save(failOnError:true)
		def task4Test = new Task(description: "Buy a new stand", creationDate: now, due:now+10, status:"Done", done:true, user: userTest).save(failOnError:true)
		
		
		//Create the relationships
		userChuck.addToTasks(task1Chuck)
		userChuck.addToTasks(task2Chuck)
		userChuck.addToTasks(task3Chuck)
		userChuck.addToTasks(task4Chuck)
		userOpera.addToTasks(task1Opera)
		userOpera.addToTasks(task2Opera)
		userTest.addToTasks(task1Test)
		userTest.addToTasks(task2Test)
		userTest.addToTasks(task3Test)
		userTest.addToTasks(task4Test)
		
		//Save the data
		userChuck.save(failOnError:true)
		userOpera.save(failOnError:true)
		userTest.save(failOnError:true)
			
	}
		def destroy = {
    	}
	}
