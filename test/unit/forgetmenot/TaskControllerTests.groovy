package forgetmenot



import grails.test.mixin.*

import com.forgetmenot.Task
import org.junit.*

import com.forgetmenot.TaskController;

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
