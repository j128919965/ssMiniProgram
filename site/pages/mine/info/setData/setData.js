// pages/mine/info/setData/setData.js
const app = getApp();
const post = require('../../../../utils/util').post;
const urls = require('../../../../utils/urls')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    opt:null,
    picker: ['总裁部', '创作部', '歌队','舞队','cos部','网宣部'],
    opts:['昵称','部门','姓名','性别','生日','班级','学号'],
    changed:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({opt:options.opt})
    const uData = app.globalData.uData;
    this.setData({...uData})
  },

  onChange:function(){
    this.setData({changed:true})
  },

  save:function(){
    let cs = '';
    switch(this.data.opt)
    {
      case '0' :
        app.globalData.uData.cn = this.data.cn;
        cs = 'cn';
        break;
      case '1' :
        app.globalData.uData.department = this.data.department;
        cs = 'department'
        break;
      case '2' :
        app.globalData.uData.name = this.data.name;
        cs = 'name';
        break;
      case '3' :
        app.globalData.uData.gender = this.data.gender;
        cs = 'gender';
        break;
      case '4' :
        app.globalData.uData.birthday = this.data.birthday;
        cs = 'birthday';
        break;
      case '5' :
        app.globalData.uData.className = this.data.className;
        cs = 'className';
        break;
      case '6' :
        app.globalData.uData.stuId = this.data.stuId;
        cs = 'stuId';
        break;
      default:
        console.log("sb");
        return;
    }
    //此处需要post新的数据
    post('http://localhost/updateUser',{opt:cs,data:this.data[cs],uid:this.data.uid});
    

    let pages = getCurrentPages(); //当前页面
    let beforePage = pages[pages.length - 2]; //前一页
    wx.navigateBack({
      success: function () {
        beforePage.onLoad(); // 执行前一个页面的onLoad方法
        wx.showToast({
          title: '修改成功',
          icon: "success",
          duration:1500
        })
      }
    });
  }
})