// pages/main/main.js
const {get} = require('../../utils/util');
const { API_GET_NOTICE } = require('../../utils/urls');
const notice_sort = (a,b)=>{
  return b.level - a.level;
}
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    urls:['/images/BasicsBg.png','/images/bak.jpg'],
    cards:[
    ]
  },
  lifetimes:{
    attached:async function(){
      let data = await get(API_GET_NOTICE);
      data = data.data.sort(notice_sort);
      this.setData({cards:data})
    }
  },

  /**
   * 组件的方法列表
   */
  methods: {
    nav:function(e){
      let item = e.currentTarget.dataset.i;
      //console.log(item);
      wx.showToast({
        title: '功能未开放',
        icon:"none"
      })
      
      // wx.navigateTo({
      //   url: '/pages/webview/index?url='
      // })
    }
  }
})
