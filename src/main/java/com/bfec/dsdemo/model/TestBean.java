package com.bfec.dsdemo.model;

public class TestBean {
    private Integer id;

    private String name;

    public TestBean() {}

	public TestBean(String name) {
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}