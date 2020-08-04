// pages/activity/activity.js
const {get,post} = require('../../utils/util');
const { API_GET_ACT_BY_ID ,API_POST_SIGNUP,API_GET_HAS_SIGNED} = require('../../utils/urls');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    act:null,
    status:1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: async function (options) {
    let data;
    if(!!options.id){
      data = await get(API_GET_ACT_BY_ID,{id:options.id})
      if(data.data.length==0)return;
      data = data.data[0];
      //console.log(data);
      this.setData({act:data})
    }
    //没有登录，不能报名
    if(!app.globalData.uData){
      this.setData({status:2})
      return;
    }
    //身份不符合
    let levels = data.level.split(",");
    if(levels.indexOf(app.globalData.uData.level)<0){
      this.setData({status:3})
      return;
    }
    let signed = await get(API_GET_HAS_SIGNED,{uid:app.globalData.uData.uid,aid:data.id});
    if(signed.data){
      this.setData({status:4})
    }
  },
  signup:async function(){
    let data = {uid:app.globalData.uData.uid,aid:this.data.act.id}
    let x = await post(API_POST_SIGNUP,data);
    console.log(x);
    if(x.message=="成功报名活动"){
      wx.showToast({
        title: x.message,
        icon:"success"
      })
      this.setData({status:4})
      app.globalData.mSize++;
    }

  }
})