const mysql = require('mysql')

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

connection.end()