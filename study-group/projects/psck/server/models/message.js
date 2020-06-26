/**
 * Created by Minwoo on 2017. 2. 24..
 */

var mongoose = require('mongoose');
mongoose.Promise = global.Promise;

var Schema = mongoose.Schema;

var ThingSchema = new Schema({
    'm_id':String,
    'm_content':String
});

module.exports = mongoose.model('message', ThingSchema, 'message');


