package com.towels.graphofcontent.util;

public class Rights {

	//Singleton start
	private Rights(){}
	
	public static synchronized Rights getInstance () {
	    if (Rights.instance == null) {
	      Rights.instance = new Rights ();
	    }
	    return Rights.instance;
	  }
	
	private static Rights instance;
	//Singleton end
	//TODO add function to decode bitmap into boolean array and to encode
	
	@Deprecated
	public boolean[] decode(int a){
		return null;
	}
	@Deprecated
	public int encode(boolean[] a){
		return 0;
	}
	//TODO how many rights?
	@Deprecated
	public int encode(boolean a, boolean b, boolean c, boolean... d){
		return 0;
	}
}
