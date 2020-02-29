//dependencies:
const restful = require('node-restful')
const mongoose = restful.mongoose

//Schema
let statusSchema = new mongoose.Schema({
    content: String,
    date: Date
})

//return models:
module.exports = restful.model('tblstatus', statusSchema)