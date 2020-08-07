/*
 * @Author: your name
 * @Date: 2020-08-03 21:09:28
 * @LastEditTime: 2020-08-07 22:34:45
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \server\route\notice.js
 */
const express = require('express');
const app = require('../app');
const router = express.Router();

const myCall = require('../util').myCall;
const query = require('../util/db').Query;

router.get('/getSwiper',async (req,res)=>{
  let sql = 'select * from swiper';
  let data = await query(sql);
  data = data.map((x)=>x.url)
  console.log(data);
  res.status(200).json({ message: '已获取轮播条',data});
})

router.get('/getNotice',async(req,res)=>{
  let sql = 'select * from notice where visiable=1';
  let data = await query(sql);
  res.status(200).json({ message: '已获取公告列表',data})
})


module.exports = router;