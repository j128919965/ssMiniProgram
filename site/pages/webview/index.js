
Page({

  
  data: {
    url:''
  },

  
  onLoad: function (options) {
    console.log('options',options.url)
    this.setData({
      url:options.url
    })
  },

})