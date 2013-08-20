package forgetmenot

import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import com.forgetmenot.security.User
import com.forgetmenot.security.Role;
import com.forgetmenot.security.UserRole;

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 * Unit tests for testing the User domain
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(User)
class UserTests {

	def mockUser
	def now = new Date()
    
	@Before
	void setUp() {
		mockForConstraintsTests(User)
		mockUser = initMockUser()
	}
	
	/**
	* Create our Mock User
	*/
	
	def initMockUser = {
		mockUser = new User(
			username: "chuck_norris", 
			password: "password", 
			enabled: true
		)
		mockUser
	}
	
	/**
	* Test user to test uniqueness of username property
	*/
	
	void testUserNameUnique() {
		def test = new User(username: 'chuck_norris', password: 'password', enabled:true)
		mockForConstraintsTests(User, [test])
		assertFalse (mockUser.validate())
		assertEquals("unique",mockUser.errors.username)
		assertEquals(1,mockUser.errors.getErrorCount())		
	}
	
	/**
	* Test user to test username is not blank
	*/
	
	void testUserNameBlank(){
		mockUser.setUsername("")
		assertFalse (mockUser.validate())
		assertEquals("blank",mockUser.errors.username)
		assertEquals(1,mockUser.errors.getErrorCount())
	}

	/**
	* Test user to test username is greater than 4 chars
	*/
	
	void testUserNameUnderMinSize(){
		mockUser.setUsername("wow")
		assertFalse (mockUser.validate())
		assertEquals("minSize",mockUser.errors.username)
		assertEquals(1,mockUser.errors.getErrorCount())
	}
	
	/**
	* Test user to test password is not blank
	*/
	
	void testPasswordBlank(){
		mockUser.setPassword("")
		assertFalse (mockUser.validate())
		assertEquals("blank",mockUser.errors.password)
		assertEquals(1,mockUser.errors.getErrorCount())
	}
	
	/**
	 * Test the overall success
	 */
	void testSuccess() {
		assert (mockUser.validate())
		assertEquals (0,mockUser.errors.getErrorCount())
	}

}
