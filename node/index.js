const http = require('http')
const express = require('express')
const app = express()
const mysql = require('mysql')
const mysqlConnection = ("./dbconnect.js")

const hostname = '127.0.0.1'
const PORT = 3001
let sendValue

function getTemperature(callback) {
    connection.query("SELECT temperature FROM weather ORDER BY id DESC limit 1", function (error, result, fields) {
        if (error) {
            throw error    
        } else {
            returnValue = JSON.stringify(result[0].temperature)
            return callback(returnValue)
        }
    })
}



app.get('/', (request, response) => {
    getTemperature(function(res){
        sendValue = res
        response.send(sendValue)  
    })
})

app.listen(PORT, hostname, () => {
    console.log(`Server running at http://${hostname}:${PORT}/`)
})

//const mysql = require('mysql')

const connection = mysql.createConnection({
    host: "iotkurssi3.c01qhrlhpdby.us-east-1.rds.amazonaws.com",
    user: "admin",
    password: "iotkurssi",
    database: "iotkurssi"
})

connection.connect(function (error) {
    if (error) throw error
    console.log("Connected!")
})



//connection.end()