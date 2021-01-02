package com.stdplatform.gui_selector.util;

public class pair<N,M> {

	private N first ;
	private M second ;

	pair(N first , M second){
		this.first = first ;
		this.second = second ;
	}

	public N getFirst() {
		return first;
	}

	public void setFirst(N first) {
		this.first = first;
	}

	public M getSecond() {
		return second;
	}

	public void setSecond(M second) {
		this.second = second;
	}


}
