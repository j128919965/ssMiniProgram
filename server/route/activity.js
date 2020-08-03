/*
 * @Author: your name
 * @Date: 2020-08-04 00:55:14
 * @LastEditTime: 2020-08-04 01:08:14
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
  let data = myCall(sql,req.body);
  res.status(200).json({message:'成功报名活动'})
})
/**
 * query:{id}
 */
router.get('/getActivityByID' ,async(req,res)=>{
  let sql = 'call proc_get_activity_by_id(?)';
  let data = await myCall(sql,req.query);
  res.status(200).json({message:'获取活动内容成功',data})
})


module.exports = router;