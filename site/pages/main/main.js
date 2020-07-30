// pages/main/main.js
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
    url:'http://lizhaorong.xyz'
  },

  /**
   * 组件的方法列表
   */
  methods: {
    nav:function(){
      wx.navigateTo({
        url: '/pages/webview/index',
      })
    }
  }
})
