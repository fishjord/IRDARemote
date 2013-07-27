package com.fishjord.irwidget.ir.codes;

import java.util.List;

public class Manufacturer {
	private final List<IRButton> buttons;
	private final String name;
	
	Manufacturer(String name, List<IRButton> buttons) {
		this.name = name;
		this.buttons = buttons;
	}

	public List<IRButton> getButtons() {
		return buttons;
	}

	public String getName() {
		return name;
	}
}
