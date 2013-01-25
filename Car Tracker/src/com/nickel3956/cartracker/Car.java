package com.nickel3956.cartracker;

public class Car {
	private static String year;
	private static String make;
	private static String model;
	private static String odometer;
	private static String mileage;
	public static boolean image;
	
	public Car(String y, String ma, String mo, String odo, String mpg)
	{
		year=y;
		make=ma;
		model=mo;
		odometer = odo;
		mileage = mpg;
		image = false;
	}
	
	public Car(String y, String ma, String mo, String odo)
	{
		this(y,ma,mo,odo,"0.0");
	}
	
	public static String returnCar()
	{
		String car = year + " " + make + " " + model;
		
		return car;
	}
	
	public String returnYear()
	{
		return year;
	}
	
	public String returnMake()
	{
		return make;
	}
	
	public String returnModel()
	{
		return model;
	}
	
	public String returnOdo()
	{
		return odometer;
	}
	
	public String returnMpg()
	{
		return mileage;
	}
	
	public static void changeYear(String newYear)
	{
		year = newYear;
	}
	
	public static void changeMake(String newMake)
	{
		make = newMake;
	}
	
	public static void changeModel(String newModel)
	{
		model = newModel;
	}
	
	public static void changeOdo(String newOdo)
	{
		odometer = newOdo;
	}
	public boolean hasImage() {
		return image;
	}
	public void setImage(boolean b) {
		this.image = b;
	}
}
