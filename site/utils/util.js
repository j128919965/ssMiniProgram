const urls = require('./urls')

const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

const sleep = async (time) => {
  return new Promise((resolve) => setTimeout(resolve, time));
}

const get = async (url, data) => {
  return new Promise((resolve) => {
    wx.request({
      url: url,
      method:"GET",
      data: data,
      success: (x) => {
        resolve(x.data)
      },
      fail: (x) => {
        wx.showToast({
          title: '网络错误',
          icon:"none"
        })
        resolve(x.data)
      }
    })
  })
}

const post = async (url, data) => {
  return new Promise((resolve) => {
    wx.request({
      url: url,
      method:"POST",
      header: {
        'Content-Type': "application/x-www-form-urlencoded",
      },
      data: data,
      success: (x) => {
        resolve(x.data)
      },
      fail: (x) => {
        wx.showToast({
          title: '网络错误',
          icon:"none"
        })
        reject(x.data)
      }
    })
  })
}

module.exports = {
  formatTime: formatTime,
  sleep: sleep,
  get: get,
  post:post
}