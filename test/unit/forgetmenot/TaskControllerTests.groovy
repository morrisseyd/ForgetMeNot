package forgetmenot



import grails.test.mixin.*

import org.forgetmenot.Task
import org.forgetmenot.TaskController;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TaskController)
class TaskControllerTests {

    void testSomething() {
       Task testTask = new Task(description: "this is as test description")
	   assertEquals "this is as test description",testTask.description
    }
}
