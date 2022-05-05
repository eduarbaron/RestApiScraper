# RestApiScraper

This project is to learn how to do web scraping with spring framework and using jsoup.

The project only works for a MercadoLibre url on the best PlayStations 5 deals on this platform.

By business logic, only items with the "OFF" tag are filtered, which refers to the % discount it has. 
The rest of the filters are made from the url itself defined in the properties file.
For example: The best sellers filter.

Note: Each website has its own html and a separate method is required in the services classes to scrape each of them.
If you liked the project, you can add another url of a different e-commerce to the properties file and implement your own method.

You can also apply filters from the API and pass them to the urls of the e-commerces.

I hope it will be very helpful to you.