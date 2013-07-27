package com.fishjord.irwidget.ir.codes;

public class IRButton {
	private final String name;
	private final String display;
	private final String group;
	private final IRCommand command;

	public IRButton(String name, String display, String group, IRCommand command) {
		this.name = name;
		this.display = display;
		this.command = command;
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public String getDisplay() {
		return display;
	}
	
	public String getGroup() {
		return group;
	}
	public IRCommand getCommand() {
		return command;
	}
	
	
}
