const express = require('express')
const app = express()
const mysql = require('mysql')
const fetch = require("node-fetch")


const PORT = 3001

let sendValue
let sendWeather
const KELVIN = 273


function getTemperature(callback) {
    connection.query("SELECT temperature FROM weather ORDER BY id DESC limit 1", function (error, result, fields) {
        if (error) {
            throw error    
        } else {
            returnTemperature = JSON.stringify(result[0].temperature)
            return callback(returnTemperature)
        }
    })
}

function getWeather(callback) {
    let latitude = 65
    let longitude = 25.5
    let returnWeather
    const key = "your own private key here"
    let api = `http://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${key}`
    fetch(api)
        .then(function(response) {
            let data = response.json()
            return data
        })
        .then(function(data) {
            returnWeather = JSON.stringify(Math.floor(data.main.temp - KELVIN))
            return callback(returnWeather)
        })   
}

app.get('/', (request, response) => {
    getTemperature(function(res){
        sendValue = res
        response.send(sendValue)  
    })
})

app.get('/openweather/', (request, response) => {
    getWeather(function(res){
        sendWeather = res
        console.log(sendWeather)
        response.send(sendWeather)  
    })
})

app.listen(PORT, () => {
    console.log(`Server running at port ${PORT}`)
})

//const mysql = require('mysql')

const connection = mysql.createConnection({
    //Removed my private connection values
    host: "host",
    user: "user",
    password: "password",
    database: "database"
})

connection.connect(function (error) {
    if (error) throw error
    console.log("Connected!")
})



//connection.end()