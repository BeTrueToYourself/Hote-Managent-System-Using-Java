/*    
      Name :-  Ashwani Kumar
      Roll No. :-  B-38
      Registration No. :- 11806645
      Section :- K18PG
      Subject :- CSE310 (Introduction To Java) 
      Faculty Name :- Roshan Srivastav
      College Name :- Lovely Professional University, Jalandhar   
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Room 
{
	// Each time a new Room is created by Hotel, Room No is 100, but a roomCounter in the Hotel class increments it each time
	protected int roomNo = 100; 					// This is only set for a Room object when the Room object is first is created
	protected String type;
	protected double dailyRent;
	protected boolean hasSeaView;
	protected boolean occupancyStatus;
	
	
	public int setRoom(String type, double dailyRent, boolean hasSeaView) 
              {
		this.type = type;
		this.dailyRent = dailyRent;
		this.hasSeaView = hasSeaView;
		this.occupancyStatus = false;				// By default, whenever a Room is added it is empty
		
		return this.roomNo;
	}
	
	
}

class Booking extends Room
 {
	protected int roomNo;
	protected String guestName;
	protected String guestEmail;
	protected int noOfDays;
	protected double guestBill;	
					
	public double bookRoom(int roomNo, String guestName, String guestEmail, int noOfDays)
             {
		this.roomNo = roomNo;
		this.guestName = guestName;
		this.guestEmail = guestEmail;
		this.noOfDays = noOfDays;
		return this.guestBill;
	}
}

public class Hotel extends Booking
{

	protected String name;
	protected String city;
	protected int maxRooms;				// To hold max possible rooms in a hotel
	protected ArrayList<Room> rooms;
	public static int roomCounter = 1;			// To simply increment each time a room is added
	protected ArrayList<Integer> searchedRooms;		// A list to just hold the searched Rooms list. It will be cleared after each search.
	protected ArrayList<Booking> bookings;
	// Setter for Hotel : Method to set up a Hotel
	public void setHotel(String name, String city, int maxRooms)
               {			
		this.name = name;
		this.city = city;
		this.maxRooms = maxRooms;			//Sets maxRooms to inputed value
		this.rooms= new ArrayList<Room>(maxRooms);	
		this.bookings= new ArrayList<Booking>(maxRooms);	// Declares Array List which can maximum number of  rooms
		System.out.println("Congratulations on Setting Up Your New Hotel.....!!!!\n");
	}
	// Getter for Hotel : Method to display Details of Hotel
	public void getHotel()
               {			
		System.out.println("***************************************************************************************************************************************************\n");
		System.out.println("Hotel Name: "+this.name);
		System.out.println("Hotel City: "+this.city);
		System.out.println("Maximum Rooms in The Hotel: "+(this.maxRooms));
		System.out.println("**************************************************************************************************************************************************\n");
		System.out.println("\n");
	}
	// Function to add new Room into Hotel (ArrayList of rooms)
	public void addRoom(Room roomObj)
               {
		this.rooms.add(roomObj);
	}
	// Function to get all details of all rooms in the Hotel
	public void displayAllRooms() 
                            {											
		System.out.println("Maximum Number Of Rooms in The Hotel "+this.name+" are "+rooms.size()+"\n");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		System.out.println("Room Number        Type              Daily Rent (INR)      Sea View         Occupancy Status "+"\n");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		// Iterates for max rooms number of times, and displays needed details of all rooms line by line
		for(int i=0; i<rooms.size(); i++) {
			System.out.print("   "+rooms.get(i).roomNo+"              ");
			System.out.print(rooms.get(i).type+"               ");
			System.out.print("\t"+rooms.get(i).dailyRent+"               ");
			System.out.print(rooms.get(i).hasSeaView+"\t\t  ");
			System.out.println(rooms.get(i).occupancyStatus);
			System.out.println("===================================================================================================\n");
		}
		System.out.println("***************************************************************************************************************************************************\n");
		System.out.println("               All Room Details of The Registered Hotel are Shown Above.  " +"\n"  );
		System.out.println("***************************************************************************************************************************************************\n");
	}
	// Function to add a new Booking with the Booking Object
	public void addBooking(Booking bookingObj) 
                {
		this.bookings.add(bookingObj);
	}
	// Function to display the room numbers of all the booked rooms
	public void displayAllBookedRooms()
                              {
		System.out.println("The Rooms(Room Numbers) that have been booked are :- "+"\n");
		System.out.println("==========================================================================================================\n");
		for(int i=0; i<bookings.size(); i++)
                          {					// Iterates through the Array List: bookings
			System.out.print(bookings.get(i).roomNo+"  ");
		}
		System.out.println("\n=========================================================================================================\n");
		System.out.println();
	}
	// Function to search for a room based on Case Study Criteria of asking for Room type and whether it has a sea View or not
	public int searchRoom(String roomType,boolean hasSeaView)                  
                            {
		int c=0;						 // Counter for rooms that are matching the criteria
		this.searchedRooms = new ArrayList<Integer>(this.maxRooms);				
		for(int i=0; i<rooms.size(); i++) 
                              {							// Iterating through the ArrayList: rooms
			Room roomObj = rooms.get(i);			// Retrieving Room Object from the ArrayList: rooms, one at a time
                                                   // If criteria matches, increment Counter
			if((roomObj.type.equals(roomType)) && (roomObj.hasSeaView==hasSeaView) && (roomObj.occupancyStatus==false))
                                               {
				searchedRooms.add(roomObj.roomNo);    // The room number gets appended onto the List
				c++;															
			}
		}
		return c;				// Returns the number of rooms that matched the criteria
	              }
	// Function to search for room based on a maxBudget per night constraint as specified by the user
	public int  searchRoom(double maxBudget)
                 {
		int c=0;						//counter for rooms matching criteria
		this.searchedRooms = new ArrayList<Integer>(this.maxRooms);
		for(int i=0; i<rooms.size(); i++) 
                               {					// Iterating through the ArrayList: rooms
			Room roomObj = rooms.get(i);			// Retrieving Room Object from the ArrayList: rooms, one at a time

			// If the daily rent of a room is less than maxBudget, and room is not occupied
			if((roomObj.dailyRent <= maxBudget) && (roomObj.occupancyStatus==false)) 
                                                 {
				searchedRooms.add(roomObj.roomNo); // The room number gets appended onto the List
				c++;
			}
		}
		return c;				// Returns the number of rooms that matched the criteria
	}
	// Function to show all the Guests who have already Booked a room
	public void displayGuestDetails() 
                  {
		System.out.println("************************************************************************************************************************************\n");
		System.out.println("    Room Number        Guest Name        Email ID       Number of Days         Bill (INR)"+"\n");
		System.out.println("===================================================================================================================\n");
		System.out.println();

		for(int i=0; i<bookings.size(); i++)
                                 {									// Iterates through the ArrayList: bookings
			System.out.print("\t"+bookings.get(i).roomNo+"\t\t");
			System.out.print(bookings.get(i).guestName+"\t\t");
			System.out.print(bookings.get(i).guestEmail+"\t\t");
			System.out.print(bookings.get(i).noOfDays+"\t\t ");
			System.out.println(bookings.get(i).guestBill+"\t\t");
		                System.out.println("=============================================================================================================\n");  

		}
	}


	// Main Method
	public static void main(String[] args) throws IOException
                              {
		Scanner sc = new Scanner(System.in);
		// I've used a simple Exception Handling mechanism where the Error message will be apt. Errors during runtime usually might be due to "bad inputs". Always follow the instructions provided
		try 
                                   {
System.out.println("          ******************** SUNSHINE HOTEL MANAGEMENT SYSTEM ********************");
System.out.println("                          ********************** Better Living ********************");
System.out.println("     *                                                                  *");
System.out.println("    ***           ******************************************           ***");
System.out.println("   *****          *   ****    *   *   *       *   ****     *          *****");
System.out.println("  ********        *   *       *   *   * *   * *   *        *         ********");
System.out.println(" **********       *   *       *   *   *   *   *   *        *        **********");
System.out.println(" ****  ****       *   ****    *****   *       *   ****     *        ****  ****");
System.out.println(" ****  ****       *      *    *   *   *       *      *     *        ****  ****");
System.out.println(" ****  ****       *      *    *   *   *       *      *     *        ****  ****");
System.out.println(" ****  ****       *   ****    *   *   *       *   ****     *        ****  ****");
System.out.println(" **********       ******************************************        **********");
			System.out.println("**************************************************************************************************************************************************************\n");
			System.out.println("Welcome to Our Hotel Management System. Before We begin with Our Functionalities, Let Us Set Up Your Hotel.\n");
			System.out.println("========================================================================================================================\n");
			System.out.println("Note:- Please Follow Each instruction Carefully, And You won't have Any Kind of issues." +"\n"+"Thank you....!!!!\n");
			System.out.println("========================================================================================================================\n");
			System.out.println("Enter Hotel Name, City, Total Number of Rooms ( Maximum Number of Rooms that Your Hotel Can Hold. ) :-\n");	// Reading Hotel attributes from user
			System.out.println("========================================================================================================================\n");
			System.out.println("Please Note :- Input should be Like\n Hyatt\n Kolkata\n 2\n");
			System.out.println("========================================================================================================================\n");
			System.out.println("Enter  The Hotel Name :-\n");
			String name = sc.next();
			System.out.println("\nEnter The City Name :-\n");
			String city = sc.next();
			System.out.println("\nEnter The Total Number of Rooms ( Maximum Number of Rooms that Your Hotel Can Hold. ) :-\n");
			int maxRooms = sc.nextInt();
			Hotel hotelObj = new Hotel();			// Hotel Object is created here
			hotelObj.setHotel(name, city, maxRooms); 		// Hotel is SET here
			hotelObj.getHotel();					// To check if all details have been set correctly 
			System.out.println("===================================================================================================\n");
			System.out.println("Now As An Administrator,  You will have to Enter Each Room Details. "
					+ "(Yes, For All The "+hotelObj.maxRooms+" Rooms that You Have Mentioned Which Your Hotel Can Accomodate. "
					+ "\nYour Room Numbers Will Be Auto Generated As per The Computed Logic\n");

			System.out.println("Types of Room Are To Be Entered in A Certain Fashion For Better Readability. "
					+ "\nS: Standard \nD: Deluxe \nL: Luxury \n");
			System.out.println("Please Note :- Input should be Like\n"+ "S/D/L\n"+"5000\n"+"true/false\n");

			System.out.println("====================================================================================================\n");

			for(int i=1; i<=hotelObj.maxRooms; i++)
                                           {			// Iterating up to the Max rooms

				System.out.println("Room "+i);
				System.out.println("Enter The Type of Room, Daily Rent, and Mention if It Has A Sea View or Not [ boolean(  true/false ) ]. "+"\n");
				System.out.println("Enter The Type of Room :- "+"\n");
				String type = sc.next();
				System.out.println("\nEnter The Daily Rent :-"+"\n");
				double dailyRent = sc.nextDouble();
				System.out.println("\nEnter  if It Has A Sea View or Not [ boolean(  true/false ) ] :- "+"\n");
				boolean hasSeaView = sc.nextBoolean();
				Room roomObj = new Room();					// New Room Object is created here
				int roomNo = roomObj.setRoom(type, dailyRent, hasSeaView);	// Room Properties are set here and 100 is always returned 
				roomObj.roomNo = roomNo+roomCounter;			// Custom roomNo property is set here
				roomCounter++;						// Increments whenever a new Room is created
				hotelObj.addRoom(roomObj);			//Adds the Room into the ArrayList: rooms in Hotel
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("Congratulations, All Your Rooms Have Been Set. "+"\n");
			System.out.println("All Your Room Details  for The Registered Hotel Are Given Below :- "+"\n");
			hotelObj.displayAllRooms();								//Displays all rooms
						System.out.println("========================================================================================================\n");
                                              System.out.println("Your Hotel is Set, And All Your Hotel Rooms are Defined.\n"+"\nThank You for Joining Us. "
					+ "\nPlease Note :- From Here Onwards Comes The Functionalities of What Can Be Done.. "
					+ "\nPlease Note :- Neither Your Hotel Details Nor Your Room Details Can Be Redefined. "
					+ "\nPlease Note :- However, If You Really  Want To Redefine it, Run This Program Again. "+"\n");
			System.out.println("\nPlease Note :- Functionalities Provided (or Coded For Are Limited .)"
					+ " \nYou Can Make Bookings, View Total Bookings, or Search for Rooms Based on Criteria [ Type of Room/Cost ].\n");
			int c=-1;		
			System.out.println("========================================================================================================\n");						
			do {
				System.out.println("\nWhat Are You Looikng For? \n"
						+ "Press 1 : Make A Booking \nPress 2 : View All Bookings \nPress 3 : Search The Hotel Rooms \nPress 4 : Display Guest Details\n"
						+ "Press  Any Other Number : Exit  [Positive Numeric Input Only] "+"\n");
			System.out.println("========================================================================================================\n");
				c = sc.nextInt();
				switch(c) 
                                                            {
				// Case: Make a new Booking
				case 1:
			System.out.println("========================================================================================================\n");
					System.out.println("Enter The Room Number, Guest Name, Guest Email ID and Number of Days, They Are Going To Book For :- " +"\n");
			System.out.println("Please Note :- Input should be Like \n"+"101\n"+"Rohan Sharma\n"+"rohansharma@gmail.com\n"+"5\n");
			System.out.println("========================================================================================================\n");
					System.out.println("Enter The Room Number ( See  Above Hotel Details ) :- " +"\n");
					int roomNo = sc.nextInt();
					System.out.println("\nEnter The Guest Name :- " +"\n");
					String guestName = sc.next();	 // This accepts only one  name for reading the name. if we want, we can use sc.nextLine();
					System.out.println("\nEnter The Guest Email ID :- " +"\n");
					String guestEmail = sc.next();
					System.out.println("\nEnter The  Number of Days, They Are Going To Book For :- " +"\n");
					int noOfDays = sc.nextInt();

					Booking bookingObj = new Booking();		// New Booking Object is created

					int desiredRoomLocInList = -1;	// To hold the position of Object: Room in ArrayList: rooms, if found

					for(int i=0; i<hotelObj.rooms.size(); i++)
                                                                      {					// Iterates through the ArrayList rooms till desired Room object is found
						if(roomNo == hotelObj.rooms.get(i).roomNo) 
                                                                                     {
							desiredRoomLocInList = i;			// To find position of desired room in ArrayList: rooms
							break;
						}
					}
			System.out.println("===========================================================================================================\n");
					if(desiredRoomLocInList == -1)
                                                                         {			
						System.out.println("No Such Room Number Exists. Please Read The Above Instructions Carefully. \n");   
			System.out.println("========================================================================================================\n");		// Means user has input a wrong room Number
					}
					// When the Room exists
					else
                                                                         {
						if(noOfDays<=0) 
                                                                                         {
							System.out.println("Number Of Days of Stay Can't Be Zero or Less Than Zero. Please Provide Proper Inputs for Booking The Hotel Room. \n");
			System.out.println("============================================================================================================\n");
						}
						else if(hotelObj.rooms.get(desiredRoomLocInList).occupancyStatus) 
                                                                                         {					// If Room is occupied
							System.out.println("Sorry! That Room has Already Been Booked. "+"\n");
			System.out.println("============================================================================================================\n");
						}
						else {														// When Room is NOT occupied
							double guestBill = bookingObj.bookRoom(roomNo, guestName, guestEmail, noOfDays);			// Always returns a 0 as guestBill is calculated here, not in the Booking class
							bookingObj.guestBill = hotelObj.rooms.get(desiredRoomLocInList).dailyRent * bookingObj.noOfDays;		// Bill is calculated HERE and set 
                                                                                                       hotelObj.rooms.get(desiredRoomLocInList).occupancyStatus = true;							// Set Occupancy status to true
							System.out.println("Room Number "+bookingObj.roomNo+" Has Been Booked By "+bookingObj.guestName+".\n");
							System.out.println("Your Stay for "+bookingObj.noOfDays+" Days With Total Cost  is Rs "+bookingObj.guestBill+".\n");
                                                                                                       hotelObj.addBooking(bookingObj);					// Since the new booking is successful, add it to the list of bookings
			System.out.println("========================================================================================================\n");
						       }
					}
			System.out.println("=============================================================================================================\n");
					break;			// Break for case 1
				case 2:
					if(hotelObj.bookings.size()==0)
						System.out.println("Till Now, No Bookings Done.\n");
					else 
						hotelObj.displayAllBookedRooms();	// Adding a booking
					break;
				case 3:
					double maxBudget = -1;
					int numOfRooms=0;
					System.out.println("Search Based on Criteria[ Press 1] or MaxBudget[ Press 2]\n ");
					System.out.println(" Please Note :- Don't Enter Any Other Choice.\n"+"Press 1 for Search Based on Room Criteria.\n"+"Press 2 for Search Based on Budget. \n ");
					int ch1 = sc.nextInt();
					if(ch1==1)
                                                                      {
			System.out.println("========================================================================================================\n");
						System.out.println("\nEnter The Room Type[S/D/L], Want  Sea View(true/false)\n"+"Please Note :- Yes for Sea View Type 'true' or No  for Sea View Type 'false' .\n");
			System.out.println("\nInput should be Like \n"+"S/D/L\n"+"true/false\n");
			System.out.println("========================================================================================================\n");
						System.out.println("\nEnter The Room Type [S/D/L] :-\n");
						String roomType = sc.next();
						System.out.println("\nPlease Note :- Yes for Sea View Type 'true' or No  for Sea View Type 'false' .\n"+"\nEnter The Boolean Value (true/false) for  Sea View\n");
						boolean hasSeaView = sc.nextBoolean();
						numOfRooms = hotelObj.searchRoom(roomType,hasSeaView);
			System.out.println("========================================================================================================\n");
					}
                                                                       else if(ch1==2)
                                                                     {
			System.out.println("========================================================================================================\n");
						System.out.println("Enter Maximum Budget You Can  Pay For Daily Rent :- ");
						maxBudget = sc.nextDouble();
						numOfRooms = hotelObj.searchRoom(maxBudget);
			System.out.println("========================================================================================================\n");
					}
					if(ch1==1 || ch1==2) 
                                                                                        {
			System.out.println("========================================================================================================\n");
						System.out.println("Number of Rooms That Meet Your Needs Are "+numOfRooms+"\n");
						System.out.println("Rooms Are : ");
						// Gets all Room Numbers available
						for(int i=0; i<hotelObj.searchedRooms.size(); i++)
                                                                                   {		// Iterating through ArrayList: searchedRooms
							System.out.println(hotelObj.searchedRooms.get(i)+" "+"\n");
			System.out.println("========================================================================================================\n");
						}
						
						hotelObj.searchedRooms.clear(); 		// We need to empty the ArrayList for the next Search 
					}
					else
						System.out.println(" Please Note :- No Such Option Exists....!! Please Read The Following Instructions Carefully....!!\n");
					break;
				case 4:
					if(hotelObj.bookings.size()==0)
				              System.out.println("Till Now , There is No Guest in The Hotel Room.\n");
					else 
						hotelObj.displayGuestDetails();
					break;
				}
			}while(c>0 && c<=4);
		}
		catch(Exception e) 
                              {
			System.out.println("**********************************************************************************************************************************************************\n");
			System.out.println("Please Note :- The Program Has Been Terminated As You Probably  Didn't Stick To The Basic Instructions Provided At Each Line."+"\n");
			System.out.println("You Probably Didn't Provide The Correct Datatype Inputs in The correct Order."+"\n");
			System.out.println("Error: "+e+"\n");
			System.out.println("============================================================================================================\n");
		 }
		System.out.println("Thank You, Come Again !!"+"\n");
			System.out.println("============================================================================================================\n");
	}
}
