// pages/main/main.js
const {get} = require('../../utils/util');
const { API_NOTICE,API_SWIPER } = require('../../utils/urls');
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
    urls:[],
    cards:[
    ]
  },
  lifetimes:{
    attached:async function(){

      let swp = await get(API_SWIPER);
      this.setData({urls:swp})
      let data = await get(API_NOTICE);
      data = data.sort(notice_sort);
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
      if(item.type=='活动报名'){
        wx.navigateTo({
          url: '/pages/activity/activity?id='+item.url,
        })
      }else if(item.type=='素质拓展'){
        wx.navigateTo({
          url: '/pages/do/st/st',
        })
      }else{
        wx.navigateTo({
          url: '/pages/showPic/showPic?url='+item.url+'&title='+item.title,
        })
      }
    }
  }
})
