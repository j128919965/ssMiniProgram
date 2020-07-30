//app.js
const util = require('./utils/util')
App({

  onLaunch: function () {

    wx.getSystemInfo({
      success: e => {
        this.globalData.StatusBar = e.statusBarHeight;
        let custom = wx.getMenuButtonBoundingClientRect();
        this.globalData.Custom = custom;
        this.globalData.CustomBar = custom.bottom + custom.top - e.statusBarHeight;
      }
    })


    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: res => {
              this.globalData.userInfo = res.userInfo
              if (this.getUserInfoCallback) {
                this.getUserInfoCallback(res)
              }
              // 登录
              wx.login({
                success: async res => {
                  let data = await util.get('http://ss.lizhaorong.xyz/login', {
                    code: res.code
                  });
                  data = data.data[0]
                  data.gender = data.gender == 1;
                  this.globalData.uData = data;
                  if (this.loginCallback) {
                    this.loginCallback(data);
                  }
                }
              })
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    uData: null,
  }
})