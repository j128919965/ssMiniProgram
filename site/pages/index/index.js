const get = require('../../utils/util').get;
const {API_GET_MESSAGE_COUNT} = require('../../utils/urls')
const app = getApp();
Page({
  data: {
    PageCur: 'main',
    messages:0,
    showMessage:false,
    vis:true
  },
  NavChange(e) {
    this.setData({
      PageCur: e.currentTarget.dataset.cur
    })
  },
  onLoad:function(){
    this.onShow();
  },
  onInnerFresh:function(e){
    this.freshMessage(e.detail.uid);
  },
  freshMessage:async function(uid){
    if(!this.data.showMessage){
      this.setData({showMessage:true})
    }
    let mSize = app.globalData.mSize;
    if(mSize==-1){
      mSize = await app.getMessageSize(uid);
    }
    this.setData({messages:mSize})
  },
  onShow:function(){
    if(app.globalData.uData){
      this.freshMessage(app.globalData.uData.uid);
      const mine = this.selectComponent(".mine");
      if(mine){
        mine.setData({uData:app.globalData.uData})
      }
    }else{
      app.freshMessageCallback = this.onShow;
    }
  },
  onShareAppMessage() {
    return {
      title: '杭师大sunshine动漫社',
      imageUrl: 'https://sunshine-anime.oss-cn-beijing.aliyuncs.com/%E9%A1%B5%E7%9C%89-%E6%A8%A1%E7%B3%8A%403x.png',
      path: '/pages/index/index'
    }
  },
  onPullDownRefresh(){
    this.onShow();
    this.setData({vis:false},()=>{
      this.setData({vis:true})
    })
    wx.stopPullDownRefresh();
  }
})