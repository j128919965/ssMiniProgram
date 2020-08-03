const express = require('express');
const app = require('../app');
const router = express.Router();

const myCall = require('../util').myCall;
const query = require('../util/db').Query;

/**
 * @name: 获取站内信列表
 * @method: get
 * @param {uid:int} 
 * @return: [message]
 */
router.get('/getMessages',async(req,res)=>{
  let sql = 'call proc_get_messages(?)';
  let data = await myCall(sql,req.query);
  res.status(200).json({message:"已成功获取站内信列表",data})
})

router.get('/getNoreadCount',async(req,res)=>{
  let sql = 'call proc_get_msg_count(?)';
  let data = await myCall(sql,req.query);
  res.status(200).json({message:"已获取数量",data})
})

router.post('/postReadMsg',async(req,res)=>{
  let sql = 'call proc_read_msg(?)';
  myCall(sql,req.body);
  res.status(200).json({message:"已成功确认已读！"});
})

module.exports = router;