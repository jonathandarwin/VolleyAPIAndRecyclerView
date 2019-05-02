# Bootcamp Android Day 2 - IT Division
In this Bootcamp, we learn how to get data from Web Service using Volley and display the list of data using Recycler View

## Volley
Volley is used when we want to get the data from Web Service.
The basic is when we request data from Web Service, we don't know when the data returned. To handle this unpredictable event, we used background thread that run together with the main thread, so it won't block the main thread.
There are several method to do dan handle request in android development : Volley, Async Task, and Retrovit. But in this bootcamp, we use volley :)

Let's see example below :

    JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "https://www.test-web-service.com/api/user",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do something when the data is arrived
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // do something when error occur
                    }
                }
        );
        
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
        
Before we send a request, we should prepare the request.
The request specification will be written in "request" variable that has "JsonObjectRequest" type.
the parameter of JsonObjectRequest : 
(method, route/endpoint, parameter that will be pass to web service, listener if success, listener if failed)

        Method           = POST / GET / PUT / DELETE / ... according to endpoint
        Endpoint         = The data source (where we can obtain the data)
        Listener Success = The Listener that will be triggered when we obtain the data from endpoint
        Listener Error   = The Listener that will be triggered if any error occur during the request

When the data is arrived without error, it will trigger the listener on the fourth parameter (onResponse).
If the request catch some error, it will trigger the listener on the fifth parameter (OnErrorResponse).

After preparing the specificatoin, we need to send the request. To send the request, we need this 2 line of code :

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

Response has JSON type. JSON (JavaScript Object Notation) is used to represent data in many programming language.
We know that we cannot passing data from one programming language to other programming language by using primitive data type (original data type from programming language). To make exchange data in other programming language become possible, we use the JSON data type because simply we can say that JSON is the "global" data type (data type that is known by all the programming language). JSON is basically a set of string.
