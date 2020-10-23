// pages/mine/mine.js
const app = getApp();
const util = require('../../utils/util');
const { API_LOGIN } = require('../../utils/urls');
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },
  lifetimes:{
    attached:function(){
      if(!!app.globalData.userInfo && app.globalData.userInfo!={}){
        this.setData({userInfo:app.globalData.userInfo})
      }else{
        app.getUserInfoCallback = data=>{
          this.setData({userInfo:data.userInfo})
        }
      }
      if(!!app.globalData.uData && app.globalData.uData ){
        this.setData({uData:app.globalData.uData})
      }else{
        app.loginCallback = (data)=>{
          console.log(data)
          this.setData({uData:data});
          this.triggerEvent('freshMessage',{uid:data.uid},{})
        }
      }
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    userInfo:app.globalData.userInfo,
    uData:{}
  },

  /**
   * 组件的方法列表
   */
  methods: {
    getUserInfo(e){
      if(!e.detail.userInfo)return;
      this.setData({userInfo:e.detail.userInfo});
      app.globalData.userInfo = e.detail.userInfo;
      wx.login({
        success: async res => {
          let data = await util.get(API_LOGIN, {
            code: res.code
          });
          data = data.data[0]
          data.gender = data.gender == 1;
          this.setData({uData:data})
          app.setUData(data)
          
          this.triggerEvent('freshMessage',{uid:data.uid},{})
        }
      })
    }
  }
})
