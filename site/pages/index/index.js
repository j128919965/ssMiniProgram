const get = require('../../utils/util').get;
const {API_GET_MESSAGE_COUNT} = require('../../utils/urls')
const app = getApp();
Page({
  data: {
    PageCur: 'mine',
    messages:0,
    showMessage:false
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
      mine.setData({uData:app.globalData.uData})
    }
    
    
  },
  onShareAppMessage() {
    return {
      title: '杭师大sunshine动漫社',
      imageUrl: '/images/share.jpg',
      path: '/pages/index/index'
    }
  },
  
})