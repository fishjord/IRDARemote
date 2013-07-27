package com.fishjord.irwidget.ir.codes;

public class IRCommand {
	private final int frequency;
	private final int[] onOffs;
	
	public IRCommand(int frequency, int[] onOffs) {
		this.frequency = frequency;
		this.onOffs = onOffs;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public int[] getOnOffs() {
		return onOffs;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append(frequency).append(",");
	
		for(int index = 0;index < onOffs.length;index++) {
			ret.append(onOffs[index]);
			if(index + 1 != onOffs.length) {
				ret.append(",");
			}
		}
		return ret.toString();
	}
}
