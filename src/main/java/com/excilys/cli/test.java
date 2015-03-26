package com.excilys.cli;

enum Const {

	 A(1),B(2),C(3);

	 int value;

	 private Const(int value){

	 this.value = value;

	} }

	public class test {

	 public static void main(String... args){

	 for(Const c : Const.values()){

	 switch(c){

	 case A:System.out.print(c.value + " ");

	 default:System.out.print(c.value + " ");

	 case B:System.out.print(c.value + " ");

	} } } }
