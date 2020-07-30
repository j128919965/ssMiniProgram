Page({
  data: {
    PageCur: 'mine'
  },
  NavChange(e) {
    this.setData({
      PageCur: e.currentTarget.dataset.cur
    })
  },
  onShareAppMessage() {
    return {
      title: '杭师大sunshine动漫社',
      imageUrl: '/images/share.jpg',
      path: '/pages/index/index'
    }
  },
})