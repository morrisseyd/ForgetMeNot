package forgetmenot

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import com.forgetmenot.*
import com.forgetmenot.security.User
import com.forgetmenot.security.Role;
import com.forgetmenot.security.UserRole;


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 * Unit tests for testing the Task domain
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(Task)
class TaskTests {

	def mockTask
	def mockTask2
	def now = new Date()
	def userRole = new Role(authority: "ROLE_USER")
	def userChuck = new User(username: "chuck_norris", password: "password", enabled: true)
	
	/**
	 * Creates a String for use in tests
	 */
	String veryLongText(){
		StringBuilder sb = new StringBuilder()
		while(sb.toString().length() < 500){
		sb << "very long long text."
	}
		sb.toString()
	}
	
	/**
	 * Setup test
	 */
	@Before
	void setUp() {
		mockForConstraintsTests(Task)
		mockTask = initMockTask()
	}
	
	/**
	 * Create Mock task object
	 */
	def initMockTask = {
		mockTask = new Task(
		description : "This is a test task...........",
		creationDate : now,
		due: now+10,
		status:"Not Started", 
		done:false,
		user: userChuck
		)
		mockTask
	}
	
	/**
	* Test the task description isn't blank
	*/
	void testDescriptionBlank(){
		mockTask.setDescription("")
		assertFalse (mockTask.validate())
		assertEquals("blank",mockTask.errors.description)
		assertEquals(1,mockTask.errors.getErrorCount())
	}
    
	/**
	 * Test the task description doesn't exceed
	 * the max size constraint
	 */
	void testDescriptionExceedMaxSize(){
		mockTask.setDescription(veryLongText())
		assertFalse (mockTask.validate())
		assertEquals("maxSize",mockTask.errors.description)
		assertEquals(1,mockTask.errors.getErrorCount())
	}

	/**
	 * Test the task description is unique
	 */
	
	void testDescriptionUnique(){
		
		// Test task to test uniqueness of description property	
		def mockTest2 = new Task(
			description : "This is a test task...........",
			creationDate : now,
			due: now+10,
			status:"Not Started", 
			user: userChuck
		)
		mockForConstraintsTests(Task, [mockTest2])
		assertFalse (mockTask.validate())
		assertEquals("unique",mockTask.errors.description)
		assertEquals(1,mockTask.errors.getErrorCount())
	}
	
	/**
	 * Test overall success
	 */
	void testSuccess() {
		assert (mockTask.validate())
		assertEquals (0,mockTask.errors.getErrorCount())
	}
}

