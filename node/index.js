const express = require('request')

const key = "8827c126650cab89acad3db4b1e8917c";
const latitude = 65; 
const longitude = 25.5; 
const url = `http://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${key}`;
const KELVIN = 273;

request(url, function(err, response, body) {
    if(err) {
        console.log('error', error)
    } else {
        let weather = JSON.parse(body)
        let celcius = weather.main.temp - KELVIN
        let message = `It's ${celcius} degrees in ${weather.name}!`
        console.log(message)
    }
});