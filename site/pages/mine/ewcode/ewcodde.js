// pages/mine/ewcode/ewcodde.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    headURL:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let app = getApp();
    let url = app.globalData.userInfo.avatarUrl;
    this.setData({headURL:url})
  },
})