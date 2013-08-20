// Place your Spring DSL code here
beans = {
	customPropertyEditorRegistrar(CustomDateEditorRegistrar)
	
	//Custom bean for task status
	taskStatusValues(java.util.HashMap, [Started: 'Started',
		  NotStarted: 'Not Started', OnHold: 'On Hold'])
	}
