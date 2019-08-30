package com.java8.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateChaining {
	private static List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");

	public static void main(String[] args) {
		//simplePredicate();
		//multipleFiltersAndPredicate();
		//complexPredicate();
		//predicateAnd();
		//predicateOr();
		//predicateNegate();
		//inlinePredicate();
		collectionOfPredicate();
	}

	public static void simplePredicate() {
		List<String> result = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());
		System.out.println("result = " + result);
	}

	public static void multipleFiltersAndPredicate() {
		List<String> result = names.stream()
				.filter(name -> name.startsWith("A"))
				.filter(name -> name.length() < 5)
				.collect(Collectors.toList());
		System.out.println("result = " + result);
	}
	
	//use bitwise operations to build the Predicate
	public static void complexPredicate() {
		List<String> result = names.stream()
			      .filter(name -> name.startsWith("A") && name.length() < 5)
			      .collect(Collectors.toList());
		System.out.println("result = " + result);
	}
	
	//predicate and() method example
	public static void predicateAnd() {
		Predicate<String> predicate1 =  str -> str.startsWith("A");
	    Predicate<String> predicate2 =  str -> str.length() < 5;
	   
	    List<String> result = names.stream()
	      .filter(predicate1.and(predicate2))
	      .collect(Collectors.toList());
		System.out.println("result = " + result);
	}
	
	//predicate or() method example
	public static void predicateOr() {
		Predicate<String> predicate1 =  str -> str.startsWith("J");
	    Predicate<String> predicate2 =  str -> str.length() < 4;
	     
	    List<String> result = names.stream()
	      .filter(predicate1.or(predicate2))
	      .collect(Collectors.toList());
		System.out.println("result = " + result);
	}
	
	//predicate negate() method example
	public static void predicateNegate() {
		Predicate<String> predicate1 =  str -> str.startsWith("J");
	    Predicate<String> predicate2 =  str -> str.length() < 4;
	     
	    List<String> result = names.stream()
	      .filter(predicate1.or(predicate2.negate()))
	      .collect(Collectors.toList());
		System.out.println("result = " + result);
	}
	
	//inline predicate example
	public static void inlinePredicate() {
		List<String> result = names.stream()
			      .filter(((Predicate<String>)name -> name.startsWith("A"))
			      .and(name -> name.length()<5))
			      .collect(Collectors.toList());
		System.out.println("result = " + result);
	}
	
	//predicate with collections
	public static void collectionOfPredicate() {
		List<Predicate<String>> allPredicates = new ArrayList<Predicate<String>>();
	    allPredicates.add(str -> str.startsWith("A"));
	    allPredicates.add(str -> str.contains("d"));        
	    allPredicates.add(str -> str.length() > 4);
	     
	    List<String> result = names.stream()
	      .filter(allPredicates.stream().reduce(x->true, Predicate::and))
	      .collect(Collectors.toList());
		System.out.println("result = " + result);
	}
}
