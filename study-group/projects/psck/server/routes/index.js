module.exports = function(app)
{

    var users = require('./users');
    var client = require('./client');
    var chat = require('./chat');
    var kakao = require('./account/kakao');


    app.use('/account/login/kakao', kakao)

    app.post('/account', require('./account/join').post);
    app.get('/account', require('./account/login').get);

    app.use('/users', users);
    app.use('/client', client);
    app.use('/chat', chat);

    app.post('/test', require('./test').post);


    app.get('/friend', require('./friend/list').get);

    app.post('/friend', require('./friend/add').post);
    app.get('/friend/info', require('./friend/info').get);

    app.post('/device/info', require('./device/info').post);
    app.post('/status/login', require('./status/login').post);


    app.post('/account/profile', require('./account/profile').multer.single('file'), require('./account/profile').post);


}

