/*
 * @Author: your name
 * @Date: 2020-07-30 01:50:58
 * @LastEditTime: 2020-08-04 01:14:56
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \server\app.js
 */
const express = require('express');
const app = express();
const http = require("http");
const https = require('https')
const request = require('request');
const bodyParser = require('body-parser');
const compression = require('compression')
const cors = require('cors');
const fs = require("fs");

const message = require('./route/message');
const honor = require('./route/honor');
const notice = require('./route/notice');
const activity = require('./route/activity');


const myCall = require('./util').myCall;
const query = require('./util/db').Query;

const httpsOption = {
    key: fs.readFileSync("./https/4110087_lizhaorong.xyz.key"),
    cert: fs.readFileSync("./https/4110087_lizhaorong.xyz.pem")
}

app.use(express.static(__dirname + '/'))
app.use(compression())
app.use(cors())
app.use(bodyParser.json({ limit: '10mb', extended: true }))
app.use(bodyParser.urlencoded({ limit: '10mb', extended: true }))

app.use('/message',message);
app.use('/honor',honor);
app.use('/notice',notice);
app.use('/activity',activity);

app.get('/login', async (req, res) =>{
    const APPID = 'wx22285fd44506b373';
    const SECRET = 'f9e8167aec0927246682f992e9731fc2';
    console.log(req.query);
    let code = req.query.code;
    let address = `https://api.weixin.qq.com/sns/jscode2session?appid=${APPID}&secret=${SECRET}&js_code=${code}&grant_type=authorization_code`;
    request(address, async (error, response, body) => {
        if (!error && response.statusCode == 200) {
            let param = {wxid:JSON.parse(body).openid};
            let sql = 'call proc_login(?)';
            let data = await myCall(sql,param);
            res.status(200).json({ message: '成功登陆！' ,data})
        }else{  
            res.status(502).json({ message: '服务器出现错误！' })
        }
    })  
})

app.get('/logins', async (req, res) =>{
    let param = {wxid:req.query.code};
    let sql = 'call proc_login(?)';
    let data = await myCall(sql,param);
    res.status(200).json({ message: '成功登陆！' ,data})
})

app.post('/updateUser',async (req,res) =>{  
    let data = req.body;
    if(data.opt=="gender"){
        data.data = data.data?1:0;
    }
    let sql = `update user set ${data.opt} = ? where uid = ?`;
    await query(sql,[data.data,data.uid]);
    res.status(200).json({ message: '修改成功'})
})

http.createServer(app).listen(80);
//https.createServer(httpsOption, app).listen(443);

module.exports = app;


