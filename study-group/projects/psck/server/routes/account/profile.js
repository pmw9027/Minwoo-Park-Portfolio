/**
 * Created by Minwoo on 2017. 2. 24..
 */

var multer = require('multer')
var storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'uploads/') // cb 콜백함수를 통해 전송된 파일 저장 디렉토리 설정
    },
    filename: function (req, file, cb) {
        cb(null, file.originalname) // cb 콜백함수를 통해 전송된 파일 이름 설정
    }
})
exports.multer = multer({
    dest: './uploads/',
    storage: storage,
    rename :
    function (fieldname, filename) {
        return filename.replace(/\W+/g, '-').toLowerCase() + Date.now()
    },
    onFileUploadStart: function (file) {
        console.log(file.fieldname + ' is starting ...')
    },
    onFileUploadData: function (file, data) {
        console.log(data.length + ' of ' + file.fieldname + ' arrived')
    },
    onFileUploadComplete: function (file) {
        console.log(file.fieldname + ' uploaded to  ' + file.path)
    }
})


exports.post = function (req, res) {

    console.log(req.body) // form fields
    console.log(req.file) // form files
    res.status(204).end()

}

