/*
 * @Author: your name
 * @Date: 2020-08-04 00:55:14
 * @LastEditTime: 2020-08-04 12:12:56
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \server\route\activity.js
 */
const express = require('express');
const app = require('../app');
const router = express.Router();

const myCall = require('../util').myCall;
const query = require('../util/db').Query;

router.get('/getActivities' ,async(req,res)=>{
  let sql = 'call proc_get_activities';
  let data = await myCall(sql,{});
  res.status(200).json({message:'获取活动列表成功',data})
})

/**
 * param:{uid,aid}
 */
router.post('/signup',async(req,res)=>{
  let sql = 'call proc_signup(?)';
  let data = await myCall(sql,req.body);
  res.status(200).json({message:'成功报名活动'});
  let title = '报名成功啦！';
  let context = `恭喜，你已经成功报名我们的面试了哦，请务必尽快在个人信息页面完善您的信息，后续我们将通过短信下达面试的时间的地点哦。`;
  let uid = req.body.uid;
  sql = "call proc_send_message(?)";
  myCall(sql,{title,context,uid});
})
/**
 * query:{id}
 */
router.get('/getActivityByID' ,async(req,res)=>{
  let sql = 'call proc_get_activity_by_id(?)';
  let data = await myCall(sql,req.query);
  res.status(200).json({message:'成功获取活动内容',data})
})

router.get('/getHasSigned',async(req,res)=>{
  let sql = 'call proc_get_has_signed(?)';
  let data = await myCall(sql,req.query);
  res.status(200).json({message:"成功获取是否已报名",data:data[0].num==1})
})

module.exports = router;