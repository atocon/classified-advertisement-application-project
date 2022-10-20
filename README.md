# ListIt: Classified Advertisement Application Project

<h2>Overview</h2>
<p>This project concerns ListIt, which is an online marketplace system for buyers and sellers to buy and sell goods and services. ListIt allows sellers to create, edit, and post listings of items for sale to a centralized directory of item listings. Buyers can use ListIt to browse through a directory of items listed for sale and find specific information about items for sale. Sellers are able to browse listings by category to allow them to find listings relevant to the items they are searching for. Sellers and buyers are able to log in and out of the application and interact with ListIt via a robust, intuitive menu system. The ListIt application allows users to create an account which is required to access certain functionality such as creating item listings. ListIt maintains a database of item listings and user account data to support long-term storage and to ensure a robust system design that can successfully recover after an unexpected system failure. User and item listing data is read into the ListIt application in a concurrent manner to ensure that the console menu is rapidly displayed to buyers and sellers. The purpose of the term project is to create a marketplace system, which resembles Facebook Marketplace or Craigslist, to allow for the fair exchange of goods and services between shoppers and sellers.</p>

<h2>Instructions to Run Source Code</h2>
<p>The ListIt System source code should be run from the Main class in its current form. The Main class provides a menu which exercises the functionality of ListIt. The current ListIt system allows buyers and sellers to login, create user accounts, browse item listings, create item listings, and logout. The ListIt application reads in user account and item listing data into the application in a concurrent manner to display a console menu to users. The ListIt System maintains a database of user account and item listing information to allow for long-term, durable storage.</p>

<h2>ListIt Class Diagram</h2>

![Class Diagram](https://github.com/atocon/classified-advertisement-application-project/blob/main/images/list_it_class_diagram.png)

<h2>ListIt Sequence Diagram 1:</h2>
<p>Use case: The ListIt system is initialized for a user by reading in user and item listing data from the ListItDatabase.db file in parallel to create UserAccount and ItemListing objects which are added to the ListItUserDirectory and ListItItemDirectory for manipulation when the application is running.</p>

![Sequence Diagram 1](https://github.com/atocon/classified-advertisement-application-project/blob/main/images/list_it_sequence_diagram1.png)

<h2>ListIt Sequence Diagram 2:</h2>
<p>Use case: The ListIt system is initialized for a user and a user creates an item listing by interacting with the console menu. The created VehicleListing object is inserted into the ListIt Database for long-term storage.</p>

![Sequence Diagram 2](https://github.com/atocon/classified-advertisement-application-project/blob/main/images/list_it_sequence_diagram2.png)
