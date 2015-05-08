package com.wzg.bean;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = -7118865379694041314L;

	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
