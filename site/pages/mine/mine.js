// pages/mine/mine.js
const app = getApp();
const util = require('../../utils/util')
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
          this.setData({uData:data})
        }
      }
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    userInfo:app.globalData.userInfo,
    uData:{cn:'旅行者'}
  },

  /**
   * 组件的方法列表
   */
  methods: {
    getUserInfo(e){
      this.setData({userInfo:e.detail.userInfo});
      wx.login({
        success: async res => {
          let data = await util.get('http://ss.lizhaorong.xyz/login', {
            code: res.code
          });
          data = data.data[0]
          data.gender = data.gender == 1;
          this.setData({uData:data})
          app.setUData(data)
        }
      })
    }
  }
})
