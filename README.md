# README

**Problem Description:**
>The problem for the Android coding challenge was to create an Android app that would request JSON-formatted data over the network from a given URL, and display the data in a coherent and smooth way (in a Recycler View).

**Solution:**
>I utilized the Volley and Picasso APIs to solve this problem. I used Volley to request the data from the URL as a string, and I then converted that string to a JSON object. This root JSON object consisted of an array of JSON objects, which made up the relevant data for each individual guide. I iterated through the array and parsed each JSON object.
The relevant data was the Guide name, city, state, end date, and icon. I created a Guide object that had these as parameters, and passed the data through as I was parsing. Then, I displayed each Guide on a Card View, and used an Adapter class to display the relevant data on a Recycler View.

**Technical Choices:**
>I used both Volley and Picasso because I have dealt with these APIs before, and to me they were the most appropriate for the task at hand. I understand how to Volley to make JSON requests, and parsing the data was easier because of that. 

>If I were to spend more time on the project, I would add more error-checking. Due to the time constraints, I built the app to fit the guidelines given, and not much more. Because of this, the app is not very flexible in terms of handling different URLs and JSON data. Also, it is possible that the app will not be able to handle all potential missing data.

>I would also focus more on the look and feel of the app. I used a lot of the default design choices, and as a result, the app doesn’t look very sleek. The icons may be a bit small, the text may not be formatted perfectly, and the colors may not be very appealing to the eye.
