//dependecies
const express = require('express')
const router = express.Router()

//get models
const Status = require('../models/status')

//router:
Status.methods(['get', 'post', 'put', 'delete'])
Status.register(router, '/status')

//return router:
module.exports = router