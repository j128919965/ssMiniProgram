// pages/mine/info/info.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    uData:null,
    picker: ['SunShine','总裁部', '创作部', '歌队','舞队','cos部','网宣部'],
  },
  onLoad:function(){
    let uData = app.globalData.uData;
    if(!uData){
      app.loginCallback = (data)=>{
        this.setData({uData:data})
      }
    }else{
      this.setData({uData:uData})
    }
  }
})