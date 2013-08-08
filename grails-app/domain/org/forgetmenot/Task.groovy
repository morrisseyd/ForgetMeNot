package org.forgetmenot

class Task {
	String description
	Date due
		
    static constraints = {
		//Cannot enter a blank description
		description blank: false
    }
}
