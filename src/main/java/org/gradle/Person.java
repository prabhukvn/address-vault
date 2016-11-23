package org.gradle;

import org.apache.commons.collections.list.GrowthList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {
	public static final Logger logger = LogManager.getLogger(Person.class);
    private final String name;

    public Person(String name) {
        this.name = name;
        new GrowthList();
    }

    public String getName() {
        return name;
    }
    
    public static void main(String[] args) {
		
		logger.error("this is an error");
	}
}
