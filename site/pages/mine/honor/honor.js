// pages/mine/honor/honor.js
const app = getApp();
const get = require("../../../utils/util").get;
const { API_HONOR } = require("../../../utils/urls");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    visiable:false,
    honors:[]
  },
  onLoad:async function(){
    let data = await get(API_HONOR,{uid:app.globalData.uData.uid});
    data.forEach((x)=>{
      x.description = x.description.replace(/\\n/g,'\n')
    })
    this.setData({honors:data})
    
  },
  showmodal:function(e){
    this.setData({visiable:true})
    this.setData({modalId:e.currentTarget.dataset.id})
  },
  hideModal:function(){
    this.setData({visiable:false})
  }

})