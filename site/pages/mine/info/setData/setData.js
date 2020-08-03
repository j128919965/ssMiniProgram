// pages/mine/info/setData/setData.js
const app = getApp();
const post = require('../../../../utils/util').post;
const {API_POST_UDATA} = require('../../../../utils/urls')
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
    let flag = true;
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
      case '7' :{
        let regNum=new RegExp('[0-9]','g');
        if(!regNum.exec(this.data.phone)||this.data.phone.length!=11){
          flag = false;
          wx.showToast({
            title: '请输入正确的手机号！',
            icon:"none",
            duration:1500
          })
        }else{
          app.globalData.uData.phone = this.data.phone;
          cs = 'phone'
        }
        break;
      }
    }

    if(!flag)return;
    //此处需要post新的数据
    post(API_POST_UDATA,{opt:cs,data:this.data[cs],uid:this.data.uid});
    

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