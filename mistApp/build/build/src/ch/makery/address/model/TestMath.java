/* Class that holds all the values for the test screen


 * i.e., condition type, how many repetitions, answers
 * wrong and right, etc.*/

// SHOULD I CODE THE CONDITION TYPE IN INTEGERS???? (EASIER, MAYBE)

package ch.makery.address.model;

import java.util.ArrayList;
import java.util.Collections;



public class TestMath {

	// all the Object properties for the MIST test

	public enum condition{
		REST,CONTROL,EXPERIMENTAL

	}

	private ArrayList<condition> order;

	private boolean hasOrderBeenSet;
	private TestSettings testSettings;

	//int containing the total number of trials to be done
	private int numberOfTrials;
	private int difficulty;
	private int uLimit;
	private double expSpeed;

	// constructor method
	// takes the number of repetitions and the condition order (strict v. random)
	// all inputs from the settings screen
 	public TestMath(TestSettings testSettings) {

		this.hasOrderBeenSet = false;
		this.testSettings = testSettings;
		order = new ArrayList<condition>();
		this.difficulty = this.testSettings.getDiff();
		this.uLimit = 30;
		this.expSpeed = 6000;



	}

	// function that sets the order array for the entirety of the MIST
	private void setOrder(){

		if(!this.hasOrderBeenSet){

		    //index for the condition array order
		    //this block runs if order is strict

		    //do things that make the order Strict

		    if(this.testSettings.getIsRest()){
		        this.order.add(condition.REST);
		    }

		    if(this.testSettings.getIsCon()){
		        this.order.add(condition.CONTROL);
		    }

		    if(this.testSettings.getIsExp()){
		        this.order.add(condition.EXPERIMENTAL);
		    }

		    if(!this.testSettings.getIsStrict()){

		       Collections.shuffle(order);


		    }

		    this.hasOrderBeenSet = true;
		    this.numberOfTrials = this.testSettings.getReps()*this.order.size();
		    System.out.println(this.numberOfTrials);
		    System.out.println("Order size: " + this.order.size());
		    System.out.println("Repetitions: " + this.testSettings.getReps());



		}
		//if the order has already been decided.
		//prevents reordering once set
		else{
			return;
		}
	}


	//calls the private function setOrder()
	public void callSetOrder(){
		setOrder();
	}

	//returns the condition corresponding the to given index
	public condition returnOrder(int index){
		return this.order.get(index);
	}

	public int returnOrderSize(){
	    return this.order.size();
	}

	public int getNumberOfTrials(){
	    return this.numberOfTrials;
	}

	public int getDiff(){
	    return this.difficulty;
	}

	public void increaseULimit(){
	    if(this.uLimit < 180){
	        this.uLimit+=15;
	    }
	    else{
	         return;
	    }

	}
	public void decreaseULimit(){
	    if(this.uLimit > 15){
	        this.uLimit-=15;
	    }
	    else{
	        return;
	    }
	}

	public int getULimit(){
	    return this.uLimit;
	}

	public double getExpSpeed(){
	    return this.expSpeed;
	}

	public void setExpSpeed(double speed){
	    this.expSpeed = speed;
	}
}

