package org.forgetmenot



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

 void testSomething() {
       Task testTask = new Task(description: "this is as test description")
	   assertEquals "this is as test description",testTask.description
    }
}
