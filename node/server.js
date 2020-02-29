const express = require('express')
const mongoose = require('mongoose')
const bodyParser = require('body-parser')
const app = express()
const Weather = require('./api/status')

//connect to mongoDB
const password = "iotkurssi"
const url = `mongodb+srv://weather:${password}@hometemp-p72ww.mongodb.net/test?retryWrites=true`
mongoose.connect(url, { useNewUrlParser: true})


/*
const test = new Test({
    content: "Testing",
    date: new Date()
})

test.save().then(response => {
    console.log('note saved!');
    mongoose.connection.close();
  })
*/
//express

app.use(bodyParser.urlencoded({extended: true}))
app.use(bodyParser.json())

//routes
app.use('/api', require('./routes/api'))

//start server
app.listen(3001)
console.log('Server is running on port 3001')


const statusSchema = new mongoose.Schema({
    content: String,
    date: Date
})

const Weather = mongoose.model('tblstatuses', statusSchema)

app.post('/api/status', (request, response) => {
    const body = request.body

    if (body.content == undefined) {
        return response.status(400).json({ error: 'content missing'})
    }

    const weather = new Weather({
        content: body.content,
        date: new Date()
    })

    weather.save().then(savedWeather => {
        response.json(savedWeather.toJSON())
    })
})