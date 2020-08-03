const express = require('express');
const app = require('../app');
const router = express.Router();

const myCall = require('../util').myCall;
const query = require('../util/db').Query;

router.get('/getHonors' ,async(req,res)=>{
  let sql = 'call proc_gethonor(?)';
  let data = await myCall(sql,req.query);
  res.status(200).json({message:'获取荣耀奖章成功',data})
})


module.exports = router;