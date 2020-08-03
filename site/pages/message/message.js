// pages/message/message.js
const {API_GET_MESSAGE_LIST,API_POST_READ_MSG} = require('../../utils/urls');
const { post,get } = require('../../utils/util');
const app = getApp();

const mySort = (x,y)=>{
  if(y.read!=x.read){
    return x.read-y.read;
  }else{
    let y1 = parseInt( y.time.split(/-/g).join(""));
    let x1 = parseInt( x.time.split(/-/g).join(""));
    return y1-x1;
  }
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    message:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: async function (options) {
    let data = await get(API_GET_MESSAGE_LIST,{uid:app.globalData.uData.uid});
    data = data.data;
    data.sort(mySort);
    this.setData({message:data})
  },
  onWatchMSG:function(index){
    index = index.currentTarget.dataset.index;
    let msg = this.data.message;
    if(msg[index].read==0){
      msg[index].read = 1;
      this.setData({message:msg})
      app.globalData.mSize = app.globalData.mSize-1;
      post(API_POST_READ_MSG,{id:msg[index].id});
    }
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
})