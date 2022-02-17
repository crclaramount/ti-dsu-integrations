# Weather app

## [Client](https://github.com/crclaramount/ti-dsu-integrations/tree/Weather_app/hw2%20Weather%20Client/weather-front) 
## [Server](https://github.com/crclaramount/ti-dsu-integrations/tree/Weather_app/weatherRestServer) 
We use a get method to recieve the data. 

The server is goign to recieve the an array of the following types 
* Dates
* Counties
* Cities

As the folloowing URL 
```
http://localhost:8080/MyFirstProject/Weather?countries=guatemala,guatemala&cities=guatemala,villa%20nueva&dates=12/12/2022,11/11/2022
```

In the server side we recieve the information, and made a split by `,`  then we compare if the three arrays contains the same number of elements 




in the server side we return an json object as the following example
```json 
 [
  {
    "city": "",
    "country": "",
    "weatherType": "",
    "temperature": "",
    "humidity": "",
    "maxTemperature": "",
    "minTemperature": "",
    "daylyTemperature": [],
    "daylyHumidity": []
  }
]
```
