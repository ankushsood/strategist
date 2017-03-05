package com.chikara.strategist.constants;

public enum TestC {
    PENNY(1);
    private int value;

    private TestC(int value) {
    	System.out.println("heerreerere");
            this.value = value;
    }
    
    public int getValue(){
    	return value;
    }
};

